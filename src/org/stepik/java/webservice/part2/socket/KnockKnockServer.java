package org.stepik.java.webservice.part2.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class KnockKnockServer {
    public static void main(String[] args) throws IOException {
        int portNumber = 8092;

        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {

            String inputLine, outputLine;
//            System.out.println(clientSocket.getInetAddress().getHostAddress());
            // Initiate conversation with client
            KnockKnockProtocol kkp = new KnockKnockProtocol();
            outputLine = kkp.processInput(null);
            out.println(outputLine);
            System.out.println(outputLine);
            inputLine = in.readLine();
            while (inputLine != null) {
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
                System.out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
                inputLine = in.readLine();
            }
        } catch (IOException e) {
            System.err.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}