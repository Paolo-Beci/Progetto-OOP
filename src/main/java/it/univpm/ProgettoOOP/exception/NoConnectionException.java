package it.univpm.ProgettoOOP.exception;

/**
 * Eccezione che avvisa quando non vi è stata una connessione all'API
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 */
public class NoConnectionException extends Exception{

    // Eccezione
    public NoConnectionException() {
        super("ERRORE: CONNESSIONE ALL'API NON RIUSCITA, IL PROGRAMMA PROCEDE CON IL FILE DI DOMINI SALVATO IN LOCALE...");
    }
}