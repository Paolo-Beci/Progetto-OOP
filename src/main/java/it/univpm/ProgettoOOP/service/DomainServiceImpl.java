package it.univpm.ProgettoOOP.service;

import java.util.Vector;

import it.univpm.ProgettoOOP.exception.NoDataException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.ProgettoOOP.filters.Filter;
import it.univpm.ProgettoOOP.model.Domain;
import it.univpm.ProgettoOOP.stats.*;


/**
 * ...
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 */

@Service
public class DomainServiceImpl implements DomainService {

	private Vector<Domain> domains= new Vector<>();
	private Vector<Domain> filteredDomains= new Vector<>();

	public DomainServiceImpl() { }

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
			if (f0.getFiltriNome().size() == 0 && f0.getFiltriCountry().size() != 0) {
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
			q = new Quantita();
			q.setVector(domains);
			q.calcoloStatistica();
			jo.put("Quantità", q.getInt());


			//Tempo medio di vita
			q = new TempoMedioVita();
			q.setVector(domains);
			q.calcoloStatistica();
			jo.put("Tempo medio di vita(in giorni)", q.getDouble());

			//Tempo medio di update
			q = new TempoMedioUpdate();
			q.setVector(domains);
			q.calcoloStatistica();
			jo.put("Tempo medio di update(in giorni)", q.getDouble());

			//Nazioni di Hosting
			q = new NazioniHost();
			q.setVector(domains);
			q.calcoloStatistica();
			jo.put("Nazioni di Hosting", q.getJSONObject());

			//ParoleChiave
			q = new ParoleChiave();
			q.setVector(domains);
			q.calcoloStatistica();
			jo.put("Parole chiave", q.getJSONObject());
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
