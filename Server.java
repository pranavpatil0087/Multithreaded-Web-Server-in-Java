package MultiThreading_Web_Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void run() throws  IOException{

        // Port number on which server will listen
        int port = 8010;

        try {

            // Create a server socket and start listening on port 8010
            ServerSocket socket = new ServerSocket(port);

            // Wait for only 10 seconds for a client connection
            socket.setSoTimeout(60000);

            // Keep the server running forever
            while (true) {

                // Display message that server is ready
                System.out.println("Server is listening on port " + port);

                // Wait here until a client connects
                Socket acceptedConnection = socket.accept();

                // Print client's IP address and port number
                System.out.println(
                        "Connection accepted from client "
                                + acceptedConnection.getRemoteSocketAddress());

                // Used to send data from server to client
                PrintWriter toClient =
                        new PrintWriter(
                                acceptedConnection.getOutputStream());

                // Used to receive data from client
                BufferedReader fromClient =
                        new BufferedReader(
                                new InputStreamReader(
                                        acceptedConnection.getInputStream()));

                // Send a message to the connected client
                toClient.println("Hello from the server");

                // Force the message to be sent immediately
                toClient.close();
                fromClient.close();

                // Close the connection after communication is complete
                acceptedConnection.close();
            }

        } catch (IOException ex) {

            // Print error details if something goes wrong
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // Create server object
        Server server = new Server();
try {
    // Start the server
    server.run();
}catch (Exception ex){
    ex.printStackTrace();
}
    }
}