package it.univpm.ProgettoOOP.service;

import java.util.Vector;

import it.univpm.ProgettoOOP.exception.NoDataException;
import it.univpm.ProgettoOOP.stats.*;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.ProgettoOOP.filters.Filter;
import it.univpm.ProgettoOOP.model.Domain;


/**
 * <b>Classe</b> che implementa l'interfaccia DomainService
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 * @version 1.0
 */

@Service
public class DomainServiceImpl implements DomainService {
    
	/**
	 * <b>Vettore</b> di domini ottenuti dall'API.
	 */
	private Vector<Domain> domains= new Vector<>();
	
	/**
	 * <b>Vettore</b> di domini filtrati.
	 */
	private Vector<Domain> filteredDomains= new Vector<>();
	
	/**
	 * <b>Costruttore</b> senza parametri
	 */
	public DomainServiceImpl() {}
	
	/**
	 * <b>Metodo</b> che restituisce i domini.
	 * @param url Url che consente l'accesso all'API. 
	 * @return vettore di domini
	 * @see DownloadDomains#download(String)
	 * @throws NoDataException se il vettore domains non contiene nessun elemento
	 */
	public Vector<Domain> getDomains(String url) throws NoDataException{
			DownloadDomains d = new DownloadDomains();
			this.domains = d.download(url);
			System.out.println(this.domains);
			if (this.domains == null || this.domains.contains("[]"))
				throw new NoDataException();
		return this.domains;
	}

	/**
	 * <b>Metodo</b> che restituisce i domini filtrati.
	 * @param bodyFilter <code>JSONObject</code> contenente i filtri scelti dall'utente.
	 * @param url Url che consente l'accesso all'API. 
	 * @return vettore di domini filtrati
	 * @see DownloadDomains#download(String)
	 * @see Vector#clear()
	 * @see Vector#size()
	 * @see Filter#parsingFilters(JSONObject)
	 * @see Filter#getFiltersName()
	 * @see Filter#getFiltersCountry()
	 * @see Filter#getFilters()
	 * @see Filter#toFilter(Vector)
	 * @see Filter#toFilter(Vector, Vector)
	 * @throws NoDataException quando il vettore da filtrare non contiene alcun elemento
	 */
	public Vector<Domain> getFilteredDomains(JSONObject bodyFilter, String url) {
		try{
			DownloadDomains d = new DownloadDomains();
			Vector<Domain> domainsToFilter1 = d.download(url);
			Vector<Domain> domainsToFilter2 = new Vector<>();

			if(domainsToFilter1 == null)
					throw new NoDataException();

			this.filteredDomains.clear();

			Filter f0 = new Filter();
			f0.parsingFilters(bodyFilter);

			if (!f0.getFiltersName().isEmpty() && !f0.getFiltersCountry().isEmpty()) {
				for (Filter f : f0.getFiltersName()) {
					f.toFilter(domainsToFilter1, domainsToFilter2);
				}
				for (Filter f : f0.getFiltersCountry()) {
					f.toFilter(domainsToFilter2, filteredDomains);
				}
			}
			if (f0.getFiltersName().isEmpty() && !f0.getFiltersCountry().isEmpty()) {
				for (Filter f : f0.getFiltersCountry()) {
					f.toFilter(domainsToFilter1, filteredDomains);
				}
			}
			if (!f0.getFiltersName().isEmpty() && f0.getFiltersCountry().isEmpty()) {
				for (Filter f : f0.getFiltersName()) {
					f.toFilter(domainsToFilter1, filteredDomains);
				}
			}
			if (f0.getFiltersName().isEmpty() && f0.getFiltersCountry().isEmpty()) {
				filteredDomains = domainsToFilter1;
			}
			for (Filter f : f0.getFilters()) {
				f.toFilter(filteredDomains);
			}
		}
		catch(Exception e){
			System.out.println("ERRORE: GENERICO in getFilteredDomains().");
			System.out.println("MESSAGGI: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
		}
		return filteredDomains;
	}

	/**
	 * <b>Metodo</b> che restituisce le statistiche sui domini.
	 * @param url Url che consente l'accesso all'API. 
	 * @return <code>JSONObject</code> contenente la statistica elaborata
	 * @see DownloadDomains#download(String)
	 * @see Quantity#calculateStat()
	 * @see AverageLifeTime#calculateStat()
	 * @see AverageUpdateTime#calculateStat()
	 * @see HostCountry#calculateStat()
	 * @see KeyWord#calculateStat()
	 * @throws NoDataException se il vettore di domini sui cui calcolare la statistica non contiene alcun elemento
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getStats(String url){

		JSONObject Stat= new JSONObject();
		Stats q;
		try {
			DownloadDomains d = new DownloadDomains();
			domains= d.download(url);
			if(this.domains == null)
				throw new NoDataException();
		}catch(Exception e)
		{
			System.out.println("ERRORE GENERICO in getStats().");
			System.out.println("MESSAGGIO: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
		}
		
		//Quantità
		q = new Quantity(domains);
		q.calculateStat();
		Stat.put("Quantity", q.getInt());


		//Tempo medio di vita
		q = new AverageLifeTime(domains);
		q.calculateStat();
		Stat.put("Average lifetime(days)", q.getDouble());

		//Tempo medio di update
		q = new AverageUpdateTime(domains);
		q.calculateStat();
		Stat.put("Average update time(days)", q.getDouble());

		//Nazioni di Hosting
		q = new HostCountry(domains);
		q.calculateStat();
		Stat.put("Host countries", q.getJSONObject());

		//ParoleChiave
		q = new KeyWord(domains);
		q.calculateStat();
		Stat.put("Keywords", q.getJSONObject());
		
	    return Stat;
	}
}