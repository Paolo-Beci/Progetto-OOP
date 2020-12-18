package it.univpm.ProgettoOOP.stats;

import it.univpm.ProgettoOOP.model.Domain;

import org.json.simple.JSONObject;

import java.util.Vector;

/**
 * <b>Superclasse</b> rappresentante la statistica da calcolare
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 * @version 1.0
 */
public abstract class Stats {
	
	/**
	 * <b>Vettore</b> che conterrà i domini sui quali effettuare statistica.
	 */
    Vector<Domain> domains = new Vector<>();
    
    /**
     * <b>Costruttore</b> della classe stessa
     * @param domains Vettore di domini sui quali efetture statistica.
     */
    public Stats(Vector<Domain> domains) {
    	this.domains = domains;
    }
    
    /**
     * <b>Metodo</b> che restituisce il vettore di domini sui quali calcolare statistica.
     * @return vettore di domini
     */
    public Vector<Domain> getDomains() {
        return domains;
    }
    
    /**
     * <b>Metodo</b> che modifica il vettore di domini sui quali verrà cacolata la statistica.
     * @param domains Nuovo vettore di domini sui quali calcolare statistica. 
     */
    public void setDomains(Vector<Domain> domains) {
    	this.domains = domains;
    }
    
    /**
     * <b>Metodo</b> che torna una statistica di tipo <code>int</code>.
     * @return valore di default
     */
    public int getInt() {
    	return 0;
    }
    
    /**
     * <b>Metodo</b> che torna una statistica di tipo <code>double</code>.
     * @return valore di default
     */
    public double getDouble() {
    	return 0;
    }
    
    /**
     * <b>Metodo</b> che torna una statistica di tipo <code>JSONObject</code>.
     * @return valore di default
     */
    public JSONObject getJSONObject() {
    	return null;
    }
    
    /**
     * <b>Metodo</b> astratto per il calcolo della statistica.
     */
    public abstract void calculateStat();
    }
