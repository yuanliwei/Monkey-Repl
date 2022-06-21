package com.android.commands.monkey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import java.util.regex.Pattern;

import javax.sound.midi.Sequence;

import org.json.JSONObject;

import com.android.commands.monkey.Monkey.Argument;

import android.util.Log;

public abstract class IOWrapper {

    private static final String OK_STR = "OK";
    private static final String ERROR_STR = "ERROR";

    LinkedBlockingQueue<Command> queue = new LinkedBlockingQueue<>();

    Command readCommand() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            return null;
        }
    }

    abstract IOWrapper init();

    abstract void sendResult(Command command, boolean isSuccess, String message);

    public static class Command {
        long id;
        String data;
        Consumer<String> send;
    }

    public static class IOWrapperStdio {

        public static IOWrapper build(Argument args) {

            return new IOWrapper() {

                long sequence = 0;
                PrintWriter output = new PrintWriter(System.out, true);

                IOWrapper init() {
                    new Thread() {
                        @Override
                        public void run() {
                            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                            try {
                                while (true) {
                                    String line = input.readLine();
                                    Command command = new Command();
                                    command.id = sequence++;
                                    command.data = line;
                                    queue.add(command);
                                }
                            } catch (IOException e) {
                                Logger.error(e.getMessage(), e);
                            }
                        }
                    }.start();
                    return this;
                }

                @Override
                void sendResult(Command command, boolean isSuccess, String message) {
                    if ("json".equals(args.commandType())) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("id", command.id);
                            jsonObject.put("isSuccess", isSuccess);
                            jsonObject.put("message", message);
                        } catch (Exception e) {
                        }
                        output.print(jsonObject);
                    } else {
                        if (isSuccess) {
                            output.print(OK_STR);
                        } else {
                            output.print(ERROR_STR);
                        }
                        if (message != null) {
                            output.print(":");
                            output.print(message);
                        }
                        output.println();
                    }
                }

            }.init();
        }
    }

    public static class IOWrapperUDP {

        public static IOWrapper build(Argument args) {

            return new IOWrapper() {
                IOWrapper init() {
                    new Thread() {
                        @Override
                        public void run() {
                            try (DatagramSocket socket = new DatagramSocket(args.port())) {
                                while (true) {
                                    byte[] buffer = new byte[4096];
                                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                                    System.out.println("start receive");
                                    socket.receive(packet);
                                    InetAddress address = packet.getAddress();
                                    String hostAddress = address.getHostAddress();
                                    System.out.println("receive data : " + hostAddress);
                                    if (hostAddress == null) {
                                        continue;
                                    }
                                    if (!hostAddress.matches(args.allowIpAddress())) {
                                        continue;
                                    }
                                    try {
                                        byte[] data = packet.getData();
                                        String line = new String(data).trim();
                                        Logger.out.println("receive packet line : " + line);
                                        JSONObject jsonObject = new JSONObject(line);
                                        Command command = new Command();
                                        command.id = jsonObject.getLong("id");
                                        command.data = jsonObject.getString("data");
                                        command.send = new Consumer<String>() {
                                            @Override
                                            public void accept(String message) {
                                                try {
                                                    packet.setData(message.getBytes(StandardCharsets.UTF_8));
                                                    socket.send(packet);
                                                } catch (IOException e) {
                                                    Logger.error(e.getMessage(), e);
                                                    try {
                                                        packet.setData(
                                                                (e.getMessage() + "\n" + Log.getStackTraceString(e))
                                                                        .getBytes(StandardCharsets.UTF_8));
                                                        socket.send(packet);
                                                    } catch (IOException e1) {
                                                        Logger.error(e1.getMessage(), e1);
                                                    }
                                                }
                                            }
                                        };
                                        queue.add(command);
                                    } catch (Exception e) {
                                        Logger.error(e.getMessage(), e);
                                    }
                                }
                            } catch (IOException e) {
                                Logger.error(e.getMessage(), e);
                            }
                        }
                    }.start();
                    return this;
                }

                @Override
                void sendResult(Command command, boolean isSuccess, String message) {
                    if ("json".equals(args.commandType())) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("id", command.id);
                            jsonObject.put("isSuccess", isSuccess);
                            jsonObject.put("message", message);
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
                        if (message != null) {
                            sb.append(":");
                            sb.append(message);
                        }
                        sb.append("\n");
                        command.send.accept(sb.toString());
                    }
                }

            }.init();
        }
    }

    public static class IOWrapperTCP {

        public static IOWrapper build(Argument args) {

            return new IOWrapper() {
                IOWrapper init() {
                    new Thread() {
                        @Override
                        public void run() {
                            try (ServerSocket serverSocket = new ServerSocket(5324)) {
                                while (true) {
                                    Socket socket = serverSocket.accept();

                                    // DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                                    // packet.setData(new byte[4096]);
                                    // System.out.println("start receive");
                                    // socket.receive(packet);
                                    // InetAddress address = packet.getAddress();
                                    // String hostAddress = address.getHostAddress();
                                    // System.out.println("receive data : " + hostAddress);
                                    // if (hostAddress == null) {
                                    //     continue;
                                    // }
                                    // if (!hostAddress.matches(args.allowIpAddress())) {
                                    //     continue;
                                    // }
                                    // try {
                                    //     byte[] data = packet.getData();
                                    //     String line = new String(data).trim();
                                    //     Logger.out.println("receive packet line : " + line);
                                    //     JSONObject jsonObject = new JSONObject(line);
                                    //     Command command = new Command();
                                    //     command.id = jsonObject.getLong("id");
                                    //     command.data = jsonObject.getString("data");
                                    //     command.send = new Consumer<String>() {
                                    //         @Override
                                    //         public void accept(String message) {
                                    //             try {
                                    //                 packet.setData(message.getBytes(StandardCharsets.UTF_8));
                                    //                 socket.send(packet);
                                    //             } catch (IOException e) {
                                    //                 Logger.error(e.getMessage(), e);
                                    //                 try {
                                    //                     packet.setData(
                                    //                             (e.getMessage() + "\n" + Log.getStackTraceString(e))
                                    //                                     .getBytes(StandardCharsets.UTF_8));
                                    //                     socket.send(packet);
                                    //                 } catch (IOException e1) {
                                    //                     Logger.error(e1.getMessage(), e1);
                                    //                 }
                                    //             }
                                    //         }
                                    //     };
                                    //     queue.add(command);
                                    // } catch (Exception e) {
                                    //     Logger.error(e.getMessage(), e);
                                    // }
                                }
                            } catch (IOException e) {
                                Logger.error(e.getMessage(), e);
                            }
                        }
                    }.start();
                    return this;
                }

                @Override
                void sendResult(Command command, boolean isSuccess, String message) {
                    if ("json".equals(args.commandType())) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("id", command.id);
                            jsonObject.put("isSuccess", isSuccess);
                            jsonObject.put("message", message);
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
                        if (message != null) {
                            sb.append(":");
                            sb.append(message);
                        }
                        sb.append("\n");
                        command.send.accept(sb.toString());
                    }
                }

            }.init();
        }
    }
}
