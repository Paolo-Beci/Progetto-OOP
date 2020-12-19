package it.univpm.ProgettoOOP.exception;

/**
 * <b>Eccezione</b> che avvisa quando una struttura dati che dovrebbe essere vuota non lo è
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 */
public class NotClearException extends Exception{

    // Eccezione
    public NotClearException() {
        super("ERRORE: LA STRUTTURA DATI NON E' STATA CORRETTAMENTE RIPULITA");
    }
}