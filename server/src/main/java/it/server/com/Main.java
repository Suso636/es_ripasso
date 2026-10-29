package it.server.com;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (
            Socket socket = new Socket("localhost", 12345);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner tastiera = new Scanner(System.in)
        ) {
            boolean continua = true;

            while (continua) {
                System.out.println("\n1. Consulta i posti disponibili");
                System.out.println("2. Prenota posti");
                System.out.println("3. Annulla posti");
                System.out.println("0. Esci");
                System.out.print("Scelta: ");

                String scelta = tastiera.nextLine();
                String comando;

                switch (scelta) {
                    case "1":
                        comando = "DISPONIBILI";
                        break;
                    case "2":
                        System.out.print("Quanti posti vuoi prenotare? ");
                        comando = "PRENOTA " + tastiera.nextLine();
                        break;
                    case "3":
                        System.out.print("Quanti posti vuoi annullare? ");
                        comando = "ANNULLA " + tastiera.nextLine();
                        break;
                    case "0":
                        comando = "ESCI";
                        continua = false;
                        break;
                    default:
                        System.out.println("Scelta non valida.");
                        continue;
                }

                out.println(comando);
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            System.err.println("Impossibile collegarsi al server: "
                    + e.getMessage());
        }
    }
}