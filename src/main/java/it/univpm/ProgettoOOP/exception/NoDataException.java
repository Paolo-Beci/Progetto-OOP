package it.univpm.ProgettoOOP.exception;

/**
 * Eccezione che avvisa quando non sono presenti dati in memoria
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