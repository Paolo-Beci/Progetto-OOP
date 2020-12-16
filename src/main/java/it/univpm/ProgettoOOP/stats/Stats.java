package it.univpm.ProgettoOOP.stats;

import it.univpm.ProgettoOOP.service.*;

import org.json.simple.JSONObject;

public abstract class Stats {
	
    protected DomainService dsi = new DomainServiceImpl();
	
    public Stats() {}
    
    //metodo che torna una statistica di tipo "intero"
    public int getInt() {
    	return 0;
    }
    
    //metodo che torna una statistica di tipo "struttura dati"
    public JSONObject getJSONObject() {
    	return null;
    }
    
    //metodo che calcola una statistica
    public abstract void calcoloStatistica();
    
    
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
