package it.univpm.ProgettoOOP.exception;

/**
 * Eccezione che avvisa quando il body della richiesta di post su filtri è vuoto
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 */
public class BodyIsEmptyException extends Exception{

    // Eccezione
    public BodyIsEmptyException() {
        super("Il body del post non contiene nulla");
    }
}
