package it.univpm.ProgettoOOP.service;

import java.util.Vector;

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

	private Vector<Domain> domains= new Vector<Domain>();
	private Vector<Domain> filteredDomains= new Vector<Domain>();

	public DomainServiceImpl() { }

	public Vector<Domain> getDomains(String url){
		DownloadDomains d= new DownloadDomains();
		this.domains = d.Download(url);
		return this.domains;		
	}

	public Vector<Domain> getFilteredDomains(JSONObject bodyFilter, String url) {

		Vector<Domain> domainsToFilter1= new Vector<>();
		Vector<Domain> domainsToFilter2= new Vector<>();
		DownloadDomains d= new DownloadDomains();
		domainsToFilter1= d.Download(url);

		this.filteredDomains.clear();
		Filter f0= new Filter();
		
		f0.parsingFilters(bodyFilter);
		
		//Aggiungo in OR tutti i Domini con quei nomi
		if(f0.getFiltriNome().size()!=0 && f0.getFiltriCountry().size()!=0) {
			
			System.out.println("## CASO 1");
			for(Filter f: f0.getFiltriNome()) {
				System.out.println(f);
				f.filtra(domainsToFilter1, domainsToFilter2);
			}
			//Aggiungo in OR tutti i Domini con quei country
			for(Filter f: f0.getFiltriCountry()) {
				System.out.println(f);
				f.filtra(domainsToFilter2, filteredDomains);
			}
		}
		if(f0.getFiltriNome().size()==0 && f0.getFiltriCountry().size()!=0) {
			System.out.println("## CASO 2");
			for(Filter f: f0.getFiltriCountry()) {
				System.out.println(f);
				f.filtra(domainsToFilter1, filteredDomains);
			}
		}
		if(f0.getFiltriNome().size()!=0 && f0.getFiltriCountry().size()==0) {
			System.out.println("## CASO 3");
			for(Filter f: f0.getFiltriNome()) {
				System.out.println(f);
				f.filtra(domainsToFilter1, filteredDomains);
			}
		}
		if(f0.getFiltriNome().size()==0 && f0.getFiltriCountry().size()!=0) {
			System.out.println("## CASO 4");
			filteredDomains= domainsToFilter1;
		}
		
		for(Filter f : f0.getFilters()) {
			System.out.println(f);
				f.filtra(filteredDomains);
		}

		return filteredDomains;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getStats(String url){
		//Vector<Domain> domainsStats= new Vector<>();
		DownloadDomains d= new DownloadDomains();
		domains= d.Download(url);

		JSONObject jo = new JSONObject();
		Stats q;

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
	    
	    return jo;
	}
}
/*
//parsing da String a JSON
ObjectMapper obj = new ObjectMapper();
String out = null;
try {
	out = obj.writeValueAsString(stat.entrySet());

} catch (JsonProcessingException e) {
	e.printStackTrace();
}
return out;
*/
