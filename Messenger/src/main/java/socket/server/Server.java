package socket.server;

import socket.controller.Message;
import socket.controller.StringHelper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    private final List<ConnectionHandler> connections;
    private ServerSocket server;
    private boolean done;
    private ExecutorService pool;
    private final int port;

    public Server(int port) {
        this.port = port;
        this.connections = new LinkedList<>();
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(port);
            pool = Executors.newCachedThreadPool();
            while (!done) {
                Socket client = server.accept();
                ConnectionHandler handler = new ConnectionHandler(client);
                connections.add(handler);
                pool.execute(handler);
            }
        } catch (Exception e) {
            shutdown();
        }
    }

    public void broadcast(String message, ConnectionHandler excludedConnection) {
        for (ConnectionHandler connection : connections) {
            if (connection != null && connection != excludedConnection) {
                connection.sendMessage(message);
            }
        }
    }

    public void shutdown() {
        try {
            done = true;
            pool.shutdown();
            if (!server.isClosed()) {
                server.close();
            }
            for (ConnectionHandler connection : connections) {
                connection.shutdown();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    class ConnectionHandler implements Runnable {

        private final Socket client;
        private BufferedReader reader;
        private PrintWriter writer;
        private  String nickName;

        public ConnectionHandler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream(), StandardCharsets.UTF_8), true);
                reader = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));

                sendMessage(Message.REQUEST_NICKNAME.toString());
                while ((nickName = reader.readLine()) != null) {
                    if (nickName.length() > 1) {
                        break;
                    }
                }

                sendMessage(Message.GREETINGS + " " + nickName);
                broadcast(nickName + Message.CONNECTED_TO_CHAT, this);

                String receivedMessage;
                boolean ukrainianVerification = false;
                while ((receivedMessage = reader.readLine()) != null) {
                    if (Message.EXIT.toString().equals(receivedMessage)) {
                        shutdown();
                        return;
                    }

                    System.out.println(receivedMessage);

                    if (!ukrainianVerification && StringHelper.isRussianCharsPresent(receivedMessage)) {
                        sendMessage(Message.UKRAINIAN_VERIFICATION_QUESTION.toString());
                        ukrainianVerification = true;
                        continue;
                    }

                    if (ukrainianVerification) {
                        if (Message.RIGHT_ANSWER.toString().equalsIgnoreCase(receivedMessage)) {
                            ukrainianVerification = false;
                            sendMessage(Message.RESPONSE_RIGHT_ANSWER +
                                    LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
                        } else {
                            sendMessage(Message.RESPONSE_WRONG_ANSWER.toString());
                            shutdown();
                            return;
                        }
                    }

                    broadcast(nickName + Message.WRITE_MESSAGE + receivedMessage, this);
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            } finally {
                shutdown();
            }
        }

        public void sendMessage(String message) {
            writer.println(message);
        }

        public void shutdown() {
            broadcast(nickName + Message.LEFT_CHAT, this);
            try {
                reader.close();
                writer.close();
                if (!client.isClosed()) {
                    client.close();
                }
                connections.remove(this);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
