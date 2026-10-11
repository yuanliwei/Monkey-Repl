package com.android.commands.monkey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

import org.json.JSONObject;

import com.android.commands.monkey.Monkey.Argument;


public abstract class IOWrapper {

    private static final String OK_STR = "OK";
    private static final String ERROR_STR = "ERROR";

    long sequence = 0;
    final Argument args;
    LinkedBlockingQueue<Command> queue = new LinkedBlockingQueue<>();

    IOWrapper(Argument args) {
        this.args = args;
    }

    Command readCommand() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            return null;
        }
    }

    abstract IOWrapper init();

    Command parseCommand(String line) {
        Command command = new Command();
        command.id = sequence++;
        command.data = line;
        try {
            if (line.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(line);
                command.id = jsonObject.getLong("id");
                command.data = jsonObject.getString("data");
            }
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }
        return command;
    }

    void sendResult(Command command, boolean isSuccess, String data) {
        if ("json".equals(args.commandType())) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("id", command.id);
                jsonObject.put("isSuccess", isSuccess);
                jsonObject.put("data", data);
            } catch (Exception e) {
            }
            command.send.accept(jsonObject.toString());
        } else {
            StringBuilder sb = new StringBuilder();
            if (isSuccess) {
                sb.append(OK_STR);
            } else {
                sb.append(ERROR_STR);
            }
            if (data != null) {
                sb.append(":");
                sb.append(data);
            }
            command.send.accept(sb.toString());
        }
    }

    public static class Command {
        long id;
        String data;
        Consumer<String> send;
    }

    public static class IOWrapperStdio {

        public static IOWrapper build(Argument args) {

            return new IOWrapper(args) {

                IOWrapper init() {
                    new Thread() {
                        @Override
                        public void run() {
                            PrintWriter output = new PrintWriter(System.out, true);
                            try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
                                while (true) {
                                    String line = input.readLine();
                                    if (line == null) { queue.add(new Command()); break; }
                                    Command command = parseCommand(line);
                                    command.send = new Consumer<String>() {
                                        @Override
                                        public void accept(String message) {
                                            output.println(message);
                                        }
                                    };
                                    queue.add(command);
                                }
                            } catch (IOException e) {
                                Logger.error(e.getMessage(), e);
                            }
                        }
                    }.start();
                    return this;
                }
            }.init();
        }
    }

    public static class IOWrapperTCP {

        public static IOWrapper build(Argument args) {

            return new IOWrapper(args) {
                IOWrapper init() {
                    new Thread() {
                        @Override
                        public void run() {
                            try (ServerSocket serverSocket = new ServerSocket(args.port(), 50, InetAddress.getByName(args.host()))) {
                                while (true) {
                                    Socket socket = serverSocket.accept();
                                    InetAddress address = socket.getInetAddress();
                                    String hostAddress = address.getHostAddress();
                                    Logger.out.println("new client connect : " + hostAddress);
                                    if (hostAddress == null) {
                                        Logger.out.println("reject: hostAddress is null");
                                        socket.close();
                                        continue;
                                    }
                                    if (!hostAddress.matches(args.allowIpAddress())) {
                                        Logger.out.println("reject: " + hostAddress + " not match " + args.allowIpAddress());
                                        socket.close();
                                        continue;
                                    }

                                    new Thread() {
                                        @Override
                                        public void run() {
                                            try (BufferedReader input = new BufferedReader(
                                                    new InputStreamReader(socket.getInputStream()));
                                                    PrintWriter output = new PrintWriter(socket.getOutputStream(),
                                                            true)) {
                                                while (true) {
                                                    String line = input.readLine();
                                                    if (line == null) { queue.add(new Command()); break; }
                                                    Command command = parseCommand(line);
                                                    command.send = new Consumer<String>() {
                                                        @Override
                                                        public void accept(String message) {
                                                            output.println(message);
                                                        }
                                                    };
                                                    queue.add(command);
                                                }
                                            } catch (Exception e) {
                                                Logger.error(e.getMessage(), e);
                                            }
                                        }
                                    }.start();
                                }
                            } catch (IOException e) {
                                Logger.error(e.getMessage(), e);
                            }
                        }
                    }.start();
                    return this;
                }
            }.init();
        }
    }
}
