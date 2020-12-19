package it.univpm.ProgettoOOP.service;

import java.util.Vector;

import it.univpm.ProgettoOOP.exception.NoDataException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.ProgettoOOP.filters.Filter;
import it.univpm.ProgettoOOP.model.Domain;
import it.univpm.ProgettoOOP.stats.*;


/**
 * <b>Implemetazione</b> dell'interfaccia DomainService.
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 * @version 1.0
 */

@Service
public class DomainServiceImpl implements DomainService {
    
	/**
	 * <b>Vettore</b> che conterrà i domini ottenuti dall'API.
	 */
	private Vector<Domain> domains= new Vector<>();
	
	/**
	 * <b>Vettore</b> che conterrà i domini filtrati.
	 */
	private Vector<Domain> filteredDomains= new Vector<>();
	
	/**
	 * <b>Costruttore</b> vuoto della stessa
	 */
	public DomainServiceImpl() {}
	
	/**
	 * <b>Metodo</b> che restituisce i domini.
	 * @param url Url che consente l'accesso all'API. 
	 * @return vettore di domini
	 * @see DownloadDomains#Download(String)
	 * @throws NoDataException
	 */
	public Vector<Domain> getDomains(String url){
		try {
			DownloadDomains d = new DownloadDomains();
			this.domains = d.Download(url);
			if(this.domains == null)
				throw new NoDataException();
		}catch(NoDataException e)
		{
			System.out.println("MESSAGGI: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
		}
		catch (Exception e)
		{
			System.out.println("ERRORE: GENERICO getDomain");
			System.out.println("MESSAGGI: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
		}
		return this.domains;
	}

	/**
	 * <b>Metodo</b> che restituisce i domini filtrati.
	 * @param bodyFilter <code>JSONObject</code> contenente i filtri scelti dall'utente.
	 * @param url Url che consente l'accesso all'API. 
	 * @return vettore di domini filtrati
	 */
	public Vector<Domain> getFilteredDomains(JSONObject bodyFilter, String url) {

		Vector<Domain> domainsToFilter1 = new Vector<>();
		Vector<Domain> domainsToFilter2 = new Vector<>();
		try {
			DownloadDomains d = new DownloadDomains();
			domainsToFilter1 = d.Download(url);
			if(domainsToFilter1 == null)
				throw new NoDataException();
		}catch(NoDataException e)
		{
			System.out.println("MESSAGGI: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
		}
		try {
			this.filteredDomains.clear();
		} catch (NullPointerException e) {
			System.out.println("PROBLEMA CON LA PULIZIA DEL VETTORE FILTEREDDOMAINS");
			System.out.println("MESSAGGIO: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
		}
		try{
			Filter f0 = new Filter();

			f0.parsingFilters(bodyFilter);

			//Aggiungo in OR tutti i Domini con quei nomi
			if (f0.getFiltriNome().size() != 0 && f0.getFiltriCountry().size() != 0) {

				System.out.println("## CASO 1");
				for (Filter f : f0.getFiltriNome()) {
					System.out.println(f);
					f.toFilter(domainsToFilter1, domainsToFilter2);
				}
				//Aggiungo in OR tutti i Domini con quei country
				for (Filter f : f0.getFiltriCountry()) {
					System.out.println(f);
					f.toFilter(domainsToFilter2, filteredDomains);
				}
			}
			if (f0.getFiltriNome().size() == 0 && f0.getFiltriCountry().size() != 0) {
				System.out.println("## CASO 2");
				for (Filter f : f0.getFiltriCountry()) {
					System.out.println(f);
					f.toFilter(domainsToFilter1, filteredDomains);
				}
			}
			if (f0.getFiltriNome().size() != 0 && f0.getFiltriCountry().size() == 0) {
				System.out.println("## CASO 3");
				for (Filter f : f0.getFiltriNome()) {
					System.out.println(f);
					f.toFilter(domainsToFilter1, filteredDomains);
				}
			}
			if (f0.getFiltriNome().size() == 0 && f0.getFiltriCountry().size() == 0) {
				System.out.println("## CASO 4");
				filteredDomains = domainsToFilter1;
			}

			for (Filter f : f0.getFilters()) {
				System.out.println(f);
				f.toFilter(filteredDomains);
			}
		}catch(Exception e){
			System.out.println("ERRORE: GENERICO");
			System.out.println("MESSAGGI: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
		}
		return filteredDomains;
	}

	/**
	 * <b>Metodo</b> che restituisce le statistiche sui domini.
	 * @param url Url che consente l'accesso all'API. 
	 * @return <code>JSONObject</code> contenente la statistica elaborata
	 * @see DownloadDomains#Download(String)
	 * @see Quantity#calculateStat()
	 * @see AverageLifeTime#calculateStat()
	 * @see AverageUpdateTime#calculateStat()
	 * @see HostCountry#calculateStat()
	 * @see KeyWord#calculateStat()
	 * @throws NoDataException
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getStats(String url){

		JSONObject jo = new JSONObject();
		Stats q;
		try {
			DownloadDomains d = new DownloadDomains();
			domains= d.Download(url);
			if(this.domains == null)
				throw new NoDataException();

			//Quantità
			q = new Quantity(domains);
			q.calculateStat();
			jo.put("Quantity", q.getInt());


			//Tempo medio di vita
			q = new AverageLifeTime(domains);
			q.calculateStat();
			jo.put("Average life time(days)", q.getDouble());

			//Tempo medio di update
			q = new AverageUpdateTime(domains);
			q.calculateStat();
			jo.put("Average update time(days)", q.getDouble());

			//Nazioni di Hosting
			q = new HostCountry(domains);
			q.calculateStat();
			jo.put("Host countries", q.getJSONObject());

			//ParoleChiave
			q = new KeyWord(domains);
			q.calculateStat();
			jo.put("Keywords", q.getJSONObject());
		}catch(NoDataException e)
		{
			System.out.println("MESSAGGI: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
		}catch(Exception e)
		{
			System.out.println("ERRORE GENERICO.");
			System.out.println("MESSAGGIO: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
		}
	    return jo;
	}
}
