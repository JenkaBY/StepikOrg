package org.stepik.java.webservice.part2.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketDataExchanger implements Runnable {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public SocketDataExchanger(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
//            String outputLine = in.readLine();
            String inputLine = in.readLine();
//            System.out.println("Read line from socket: " + inputLine);
            while (inputLine != null) {
//                System.out.println("Read line from socket: " + inputLine);
                out.println(inputLine);
                if (inputLine.equals("Bye.")) {
                    System.out.println("Bye!");
                    break;
                }
                inputLine = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
