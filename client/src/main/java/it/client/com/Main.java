package it.client.com;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        

 Spettacolo spettacolo = new Spettacolo();

        try (ServerSocket server = new ServerSocket(12345)) {
            System.out.println("Server in ascolto sulla porta 12345");

            while (true) {
                Socket socket = server.accept();
                Thread thread = new Thread(
                        new GestoreClient(socket, spettacolo));
                thread.start();
            }
        } catch (IOException e) {
            System.err.println("Errore del server: " + e.getMessage());

    }
    }
}