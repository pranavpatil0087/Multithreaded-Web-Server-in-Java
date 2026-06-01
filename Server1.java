package MultiThreading_Web_Server;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {

    public static void main(String[] args) {

        int port = 8010;

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("Server is listening on port " + port);

            while (true) {

                Socket clientSocket = serverSocket.accept();

                Thread thread = new Thread(() -> {

                    try {

                        PrintWriter toClient =
                                new PrintWriter(
                                        clientSocket.getOutputStream(),
                                        true);

                        toClient.println("Hello from the server");

                        clientSocket.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                thread.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}