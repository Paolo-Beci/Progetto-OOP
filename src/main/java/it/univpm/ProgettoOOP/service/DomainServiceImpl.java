package it.univpm.ProgettoOOP.service;

import java.util.Vector;

import it.univpm.ProgettoOOP.exception.NoDataException;
import it.univpm.ProgettoOOP.exception.NotClearException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.ProgettoOOP.filters.Filter;
import it.univpm.ProgettoOOP.model.Domain;
import it.univpm.ProgettoOOP.stats.*;


/**
 * <b>Implemetazione</b> dell'interfaccia DomainService
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
	public Vector<Domain> getDomains(String url) {
		try {
			DownloadDomains d = new DownloadDomains();
			this.domains = d.Download(url);
			System.out.println(this.domains);
			if (this.domains == null || this.domains.contains("[]"))
				throw new NoDataException();
		}
		catch(NoDataException e) {
			System.out.println("ERRORE: NESSUN DOMINIO RICEVUTO.");
			System.out.println("MESSAGGI: " + e.getMessage());
			System.out.println("CAUSA: POTRESTI AVER INSERITO UN CAMPO DOMINIO E ZONA CHE NON SONO PRESENTI NEL DATABASE...\n");
		}
		catch (Exception e)
		{
			System.out.println("ERRORE: GENERICO in getDomains().");
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
	 * @see DownloadDomains#Download(String)
	 * @see Filter#parsingFilters(JSONObject)
	 * @see Filter#getFiltersName()
	 * @see Filter#getFiltersCountry()
	 * @see Filter#getFilters()
	 * @throws NoDataException
	 * @throws NotClearException
	 */
	public Vector<Domain> getFilteredDomains(JSONObject bodyFilter, String url) {
		try{
			Vector<Domain> domainsToFilter1 = new Vector<>();
			Vector<Domain> domainsToFilter2 = new Vector<>();
			DownloadDomains d = new DownloadDomains();
			domainsToFilter1 = d.Download(url);
			if(domainsToFilter1 == null)
					throw new NoDataException();

			this.filteredDomains.clear();
			if(filteredDomains.size()!=0)
				throw new NotClearException();


			Filter f0 = new Filter();

			f0.parsingFilters(bodyFilter);

			//Aggiungo in OR tutti i Domini con quei nomi
			if (f0.getFiltersName().size() != 0 && f0.getFiltersCountry().size() != 0) {

				System.out.println("## CASO 1");
				for (Filter f : f0.getFiltersName()) {
					System.out.println(f);
					f.toFilter(domainsToFilter1, domainsToFilter2);
				}
				//Aggiungo in OR tutti i Domini con quei country
				for (Filter f : f0.getFiltersCountry()) {
					System.out.println(f);
					f.toFilter(domainsToFilter2, filteredDomains);
				}
			}
			if (f0.getFiltersName().size() == 0 && f0.getFiltersCountry().size() != 0) {
				System.out.println("## CASO 2");
				for (Filter f : f0.getFiltersCountry()) {
					System.out.println(f);
					f.toFilter(domainsToFilter1, filteredDomains);
				}
			}
			if (f0.getFiltersName().size() != 0 && f0.getFiltersCountry().size() == 0) {
				System.out.println("## CASO 3");
				for (Filter f : f0.getFiltersName()) {
					System.out.println(f);
					f.toFilter(domainsToFilter1, filteredDomains);
				}
			}
			if (f0.getFiltersName().size() == 0 && f0.getFiltersCountry().size() == 0) {
				System.out.println("## CASO 4");
				filteredDomains = domainsToFilter1;
			}

			for (Filter f : f0.getFilters()) {
				System.out.println(f);
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

		JSONObject Stat= new JSONObject();
		Stats q;
		try {
			DownloadDomains d = new DownloadDomains();
			domains= d.Download(url);
			if(this.domains == null)
				throw new NoDataException();
			
		}catch(NoDataException e)
		{
			System.out.println("MESSAGGI: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
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
		Stat.put("Average life time(days)", q.getDouble());

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
