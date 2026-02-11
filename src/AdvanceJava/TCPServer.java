package AdvanceJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(7070)) {
            System.out.println("Server started... waiting for client");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            String msg = bufferedReader.readLine();
            System.out.println("Client msg: " + msg);

            writer.println("Hello from Server");
            socket.close();
        }
    }
}
