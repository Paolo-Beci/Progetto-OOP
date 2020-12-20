package it.univpm.ProgettoOOP.exception;

/**
 * <b>Eccezione</b> che avvisa quando non sono presenti dati in memoria
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 */
public class NoDataException extends Exception{

    /**
     * <b>Costruttore</b>
     */
    public NoDataException() {
        super("ERRORE: IL PROGRAMMA NON HA ALCUN DATO DA ELABORARE");
    }
}