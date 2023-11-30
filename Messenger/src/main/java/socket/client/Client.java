package socket.client;

import socket.controller.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {

    private Socket client;
    private PrintWriter writer;
    private BufferedReader reader;
    private boolean done;
    private final String host;
    private final int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            client = new Socket(host, port);
            writer = new PrintWriter(client.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

            InputHandler inputHandler = new InputHandler();
            Thread t = new Thread(inputHandler);
            t.start();

            String inputMessage;
            while (!done && (inputMessage = reader.readLine()) != null) {
                System.out.println(Message.SERVER_RESPONSE + inputMessage);
            }
        } catch (IOException e) {
            shutdown();
        }
    }

    private void shutdown() {
        done = true;
        try {
            reader.close();
            writer.close();
            if (!client.isClosed()) {
                client.close();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    class InputHandler implements Runnable {
        @Override
        public void run() {
            try (Scanner scanner = new Scanner(System.in)) {
                while (!done) {
                    String message = scanner.nextLine();
                    if (Message.EXIT.toString().equals(message)) {
                        writer.println(message);
                        shutdown();
                    } else {
                        writer.println(message);
                    }
                }
            }
        }
    }
}
