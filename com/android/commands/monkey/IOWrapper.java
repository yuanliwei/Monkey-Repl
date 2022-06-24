package com.android.commands.monkey;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

import org.json.JSONObject;

import com.android.commands.monkey.Monkey.Argument;

import android.util.Log;

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
                            try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                                    PrintWriter output = new PrintWriter(System.out, true)) {
                                while (true) {
                                    String line = input.readLine();
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

    public static class IOWrapperUDP {

        public static IOWrapper build(Argument args) {

            return new IOWrapper(args) {
                IOWrapper init() {
                    new Thread() {
                        @Override
                        public void run() {
                            try (DatagramSocket socket = new DatagramSocket(args.port())) {
                                while (true) {
                                    byte[] buffer = new byte[4096];
                                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                                    Logger.out.println("start receive");
                                    socket.receive(packet);
                                    InetAddress address = packet.getAddress();
                                    String hostAddress = address.getHostAddress();
                                    Logger.out.println("receive data : " + hostAddress);
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
                                        Command command = parseCommand(line);
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
                            try (ServerSocket serverSocket = new ServerSocket(args.port())) {
                                while (true) {
                                    Socket socket = serverSocket.accept();
                                    InetAddress address = socket.getInetAddress();
                                    String hostAddress = address.getHostAddress();
                                    Logger.out.println("new client connect : " + hostAddress);
                                    if (hostAddress == null) {
                                        socket.close();
                                        continue;
                                    }
                                    if (!hostAddress.matches(args.allowIpAddress())) {
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
                                                while (socket.isConnected()) {
                                                    String line = input.readLine();
                                                    Command command = parseCommand(line);
                                                    command.send = new Consumer<String>() {
                                                        @Override
                                                        public void accept(String message) {
                                                            output.print(message);
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

    public static class IOWrapperHTTP {

        public static IOWrapper build(Argument args) {

            return new IOWrapper(args) {
                IOWrapper init() {
                    new Thread() {
                        @Override
                        public void run() {
                            try (ServerSocket serverSocket = new ServerSocket(args.port())) {
                                while (true) {
                                    try {
                                        Socket socket = serverSocket.accept();
                                        InetAddress address = socket.getInetAddress();
                                        String hostAddress = address.getHostAddress();
                                        Logger.out.println("new client connect : " + hostAddress);
                                        if (hostAddress == null) {
                                            socket.close();
                                            continue;
                                        }
                                        if (!hostAddress.matches(args.allowIpAddress())) {
                                            socket.close();
                                            continue;
                                        }
                                        InputStream input = socket.getInputStream();
                                        String line = getInputStreamString(input);
                                        int index = line.indexOf("\r\n\r\n");
                                        line = line.substring(index + 4);
                                        Logger.out.println("receive line : " + line);
                                        Command command = parseCommand(line);
                                        command.send = new Consumer<String>() {
                                            @Override
                                            public void accept(String message) {
                                                Logger.out.println("send message : " + message.length());
                                                if (socket.isClosed()) {
                                                    Logger.out.println("socket is closed! " + hostAddress);
                                                    return;
                                                }

                                                try (OutputStream output = socket.getOutputStream()) {
                                                    DateFormat format = SimpleDateFormat
                                                            .getDateTimeInstance(SimpleDateFormat.DEFAULT,
                                                                    SimpleDateFormat.DEFAULT, Locale.US);
                                                    format.setTimeZone(TimeZone.getTimeZone("GMT"));

                                                    output.write(("HTTP/1.1 200 OK" + "\r\n" +
                                                            "content-length: " + message.getBytes().length
                                                            + "\r\n" +
                                                            "content-type: text/plain; charset=UTF-8"
                                                            + "\r\n" +
                                                            "access-control-allow-origin: *" + "\r\n" +
                                                            "access-control-allow-methods: POST"
                                                            + "\r\n" +
                                                            "Date: " + format.format(new Date()) + "\r\n" +
                                                            "Connection: keep-alive" + "\r\n" +
                                                            "Keep-Alive: timeout=5\r\n\r\n" + message)
                                                            .getBytes());
                                                    output.flush();
                                                } catch (Exception e) {
                                                    Logger.error(e.getMessage(), e);
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
            }.init();
        }
    }

    private static String getInputStreamString(InputStream inputStream_) throws IOException {
        InputStream inputStream = inputStream_;
        byte[] buffer = new byte[4096];
        int len;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) > 0) {
            os.write(buffer, 0, len);
            if (inputStream.available() < 1) {
                break;
            }
        }
        return os.toString();
    }
}
