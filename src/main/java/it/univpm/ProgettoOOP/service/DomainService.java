package it.univpm.ProgettoOOP.service;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.model.Domain;

/**
 * <b>Interfaccia</b> di servizio per gestire le operazioni sui domini.
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 * @version 1.0
 */
public interface DomainService {
	
	/**
	 * <b>Intestazione</b> del metodo che restituisce i domini.
	 * @param url Url che consente l'accesso all'API. 
	 * @return vettore di domini
	 */
	Vector<Domain> getDomains(String url);
	
	/**
	 * <b>Intestazione</b> del metodo che restituisce i domini filtrati.
	 * @param bodyFilter <code>JSONObject</code> contenente i filtri scelti dall'utente.
	 * @param url Url che consente l'accesso all'API. 
	 * @return vettore di domini filtrati
	 */
	Vector<Domain> getFilteredDomains(JSONObject bodyFilter, String url);
	
	/**
	 * <b>Intestazione</b> del metodo che restituisce le statistiche sui domini.
	 * @param url Url che consente l'accesso all'API. 
	 * @return <code>JSONObject</code> contenente la statistica elaborata.
	 */
	JSONObject getStats(String url);
}
