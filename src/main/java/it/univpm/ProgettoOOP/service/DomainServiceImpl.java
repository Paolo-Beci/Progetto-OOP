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

	public DomainServiceImpl() {
		DownloadDomains d= new DownloadDomains();
		this.domains = d.Download();
	}
	
	public Vector<Domain> getDomains(){
		return this.domains;		
	}

	public Vector<Domain> getFilteredDomains(JSONObject bodyFilter) {

		Vector<Domain> domainsToFilter= new Vector<>();
		DownloadDomains d= new DownloadDomains();
		domainsToFilter= d.Download();
		boolean or= false;
		this.filteredDomains.clear();
		Filter f0= new Filter();
		
		f0.parsingFilters(bodyFilter);

		for(Filter f : f0.getFilters()) {
			System.out.println(f);
			if(f.getOr()) {
				f.filtra(domainsToFilter, filteredDomains);
				or= true;
			}	
			else
				f.filtra(domainsToFilter);
		}
		if(!or) {
			filteredDomains= domainsToFilter;
		}
		
		return filteredDomains;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getStats(){ 
		JSONObject jo = new JSONObject();
		Stats q;
		
		//VERSIONE 1
		//Quantità
	    q = new Quantita();
	    q.calcoloStatistica();
	    jo.put("Quantità", q.getInt());
	    
	    //Tempo medio di vita
	    q = new TempoMedioVita();
	    q.calcoloStatistica();
	    jo.put("Tempo medio di vita(in giorni)", q.getDouble());
	    
	    //Tempo medio di update
	    q = new TempoMedioUpdate();
	    q.calcoloStatistica();
	    jo.put("Tempo medio di update(in giorni)", q.getDouble());
	    
		//Nazioni di Hosting
	    q = new NazioniHost();
	    q.calcoloStatistica();
	    jo.put("Nazioni di Hosting", q.getJSONObject());
	    
	    //ParoleChiave
	    q = new ParoleChiave();
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
