package AdvanceJava.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 7070)) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println("Hello Server");

            String msg = reader.readLine();
            System.out.println("server msg : " + msg);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
