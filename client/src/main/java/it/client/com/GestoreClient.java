package it.client.com;
import java.io.*;
import java.net.*;


public class GestoreClient implements Runnable {
    private Socket socket;
    private Spettacolo spettacolo;

public GestoreClient(Socket socket, Spettacolo spettacolo){
    this.socket = socket;
    this.spettacolo = spettacolo;


}

 @Override
 public void run(){

    try {
        Socket s = socket;
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        {
            String riga;
            while ((riga = in.readLine()) != null){
                String risposta = elabora(riga);
                out.println(risposta);

                if (riga.trim().equals("ESCI")){

                    break;
                }

            }
        }
    } catch (IOException e) {
        System.out.println("Connessione terminata");
        e.getMessage();
    }

         

 }

 private String elabora(String riga){
    String[] parti = riga.trim().split("\\s+");

        if (parti.length == 1 && parti[0].equals("DISPONIBILI")) {
            return "OK;Posti disponibili: " + spettacolo.getDispo();
        }

        if (parti.length == 1 && parti[0].equals("ESCI")) {
            return "OK;Connessione in chiusura";
        }

        if (parti.length == 2 &&
                (parti[0].equals("PRENOTA") || parti[0].equals("ANNULLA"))) {
            int quantita;

            try {
                quantita = Integer.parseInt(parti[1]);
            } catch (NumberFormatException e) {
                return "ERRORE;Quantità non valida";
            }

            if (parti[0].equals("PRENOTA")) {
                return spettacolo.prenota(quantita);
            } else {
                return spettacolo.annulla(quantita);
            }
        }

        return "ERRORE;Comando sconosciuto";


 }

}
