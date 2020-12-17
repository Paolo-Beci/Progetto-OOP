package it.univpm.ProgettoOOP.stats;

import it.univpm.ProgettoOOP.model.Domain;

import org.json.simple.JSONObject;

import java.util.Vector;

public class Stats {

    Vector<Domain> domainsStats= new Vector<>();

    public void setVector(Vector<Domain> domainsStats)
    {
        this.domainsStats = domainsStats;

    }

    public Vector<Domain> getVector() {
        return domainsStats;
    }
    //metodo che torna una statistica di tipo "intero"
    public int getInt() {
    	return 0;
    }
    
    //metodo che torna una statistica di tipo "double"
    public double getDouble() {
    	return 0;
    }
    
    //metodo che torna una statistica di tipo "struttura dati"
    public JSONObject getJSONObject() {
    	return null;
    }
    
    //metodo che calcola una statistica

    public void calcoloStatistica(){}
    
    
    /** statistiche da elaborare:
     * - Quantità di domini  (sono sempre 50)
     * - Tempo medio di vita
     * - Tempo medio di update
     * - Nazioni di hosting
     * - Parole chiave più utilizzate
     *
     * Le statistiche vengono calcolate sul vettore dei domini (eventualmente filtrati)
     *
     */
    
    
    
    }
