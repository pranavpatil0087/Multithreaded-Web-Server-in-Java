package MultiThreading_Web_Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public void run() throws IOException {

        // Port number on which server is running
        int port = 8010;
        // Get IP address of localhost (same computer)
        InetAddress address = InetAddress.getByName("localhost");
        // Connect to server using IP address and port number
        Socket socket = new Socket(address, port);
        // Used to send data to the server
        PrintWriter toSocket =
                new PrintWriter(socket.getOutputStream());

        // Used to receive data from the server
        BufferedReader fromSocket =
                new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));
        // Send message to server
        toSocket.println("Hello from the client");
        // Force the message to be sent immediately
        toSocket.flush();
        // Read response sent by server
        String line = fromSocket.readLine();
        // Display server response
        System.out.println("Response from the server is: " + line);
        // Close all resources
        toSocket.close();
        fromSocket.close();
        socket.close();
    }

    public static void main(String[] args) {
        try {
            // Create client object
            Client client = new Client();
            // Start client
            client.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}