package it.univpm.ProgettoOOP.stats;

import it.univpm.ProgettoOOP.model.Domain;

import java.util.Vector;

public class Stats {
    private Vector<Domain> domains= new Vector<Domain>();
    public Vector<Domain> getStats(){
        return this.domains;
    }

    /** statistiche da elaborare:
     * - Quantità di domini  (sono sempre 50?)
     * - Tempo medio di vita
     * - Tempo medio di update
     * - Nazioni di hosting
     * - Parole chiave più utilizzate
     *
     * Le statistiche vengono calcolate sul vettore dei domini (eventualmente filtrati)
     *
     * Utilizzare libreria esterna (GregorianCalendar) per prendere la data del giorno
     */
}
