package it.client.com;

public class Spettacolo {
    
    private final int capienza = 30;
    private int disponibili = 30;

    public synchronized int getDispo(){

        return disponibili;

    }


    public synchronized String prenota(int quantita){

        if (quantita <= 0){
            return "ERRORE; la quantità deve essere positiva";


        }
        if (quantita > disponibili){
            return "ERRORE; posti insufficienti : " + disponibili;

        }

        disponibili = disponibili - quantita;
        return "PRENOTAZIONE EFFETTUATA";
    }

    public synchronized String annulla(int quantita){

        if (quantita > capienza - disponibili){

            return "ERRORE; annullamento non disponibile";

        }

        disponibili += quantita;
        return "ANNULLAMENTO EFFETTUATO CON SUCCESSO";
    }   

}
