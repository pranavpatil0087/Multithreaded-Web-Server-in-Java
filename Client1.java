package MultiThreading_Web_Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class Client1 {

    // Creates the task that each thread will run
    public Runnable getRunnable() {

        return () -> {

            try {

                int port = 8010;

                // Get localhost address
                InetAddress address =
                        InetAddress.getByName("localhost");

                // Connect to server
                Socket socket =
                        new Socket(address, port);

                // Read response from server
                BufferedReader fromServer =
                        new BufferedReader(
                                new InputStreamReader(
                                        socket.getInputStream()));

                // Receive message
                String line = fromServer.readLine();

                System.out.println(
                        Thread.currentThread().getName()
                                + " -> " + line);

                // Close connection
                socket.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {

        Client1 client = new Client1();

        for (int i = 0; i < 100; i++) {

            Thread thread =
                    new Thread(client.getRunnable());

            thread.start();
        }
    }}