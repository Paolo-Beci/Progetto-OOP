package it.univpm.ProgettoOOP.service;

import java.util.Vector; 

import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.model.Domain;

/**
 * Interfaccia implementata da DomainServiceImpl
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 */
public interface DomainService {
	
	public Vector<Domain> getDomains(String url);
	public Vector<Domain> getFilteredDomains(JSONObject bodyFilter, String url);
	public JSONObject getStats();
}
