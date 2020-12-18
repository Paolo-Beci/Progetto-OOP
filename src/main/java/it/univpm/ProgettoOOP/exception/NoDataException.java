package it.univpm.ProgettoOOP.exception;

/**
 * Eccezione che avvisa quando non vi è stata una connessione all'API
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 */
public class NoDataException extends Exception{

    // Eccezione
    public NoDataException() {
        super("ERRORE: CONNESSIONE ALL'API NON RIUSCITA, IL PROGRAMMA NON HA ALCUN DATO DA ELABORARE");
    }
}