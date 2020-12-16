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
		
		this.filteredDomains.clear();
		Filter f0= new Filter();
		
		f0.parsingFilters(bodyFilter);
		
		System.out.println("\n################## FILTRI");
		for(Filter f : f0.getFilters()) {
			System.out.println(f);
		}

		for(Filter f : f0.getFilters()) {
			f.filtra(domainsToFilter);
		}
		
		filteredDomains= domainsToFilter;
		return this.filteredDomains;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getStats(){ 
		JSONObject jo = new JSONObject();
		
		//VERSIONE 1
		//Quantità
	    Stats q = new Quantita();
	    q.calcoloStatistica();
	    jo.put("Quantità", q.getInt());
	    
	    //Tempo medio di vita
	    Stats q1 = new TempoMedioVita();
	    q1.calcoloStatistica();
	    jo.put("Tempo medio di vita", q1.getInt());
	    
	    //Tempo medio di update
	    Stats q2 = new TempoMedioUpdate();
	    q2.calcoloStatistica();
	    jo.put("Tempo medio di update", q2.getInt());
	    
		//Nazioni di Hosting
	    Stats q3 = new NazioniHost();
	    q3.calcoloStatistica();
	    jo.put("Nazioni di Hosting", q3.getJSONObject());
		
	    //ParoleChiave
	    Stats q4 = new ParoleChiave();
	    q4.calcoloStatistica();
	    jo.put("Parole chiave", q4.getJSONObject());
	    
	    return jo;
	    
	    /* VERSIONE 2
		Stats q;
		for(int i = 0; i < 5; i++) {
			
			switch(i) {
			case 0: //Quantità
					q = new Quantita();
					q.calcoloStatistica();
					jo.put("Quantità", q.getInt());
					break;	    
					    
		    case 1: //Tempo medio di vita
					q = new TempoMedioVita();
					q.calcoloStatistica();
					jo.put("Tempo medio di vita", q.getInt());
					break;
					
			case 2: //Tempo medio di update
		            q = new TempoMedioUpdate();
		            q.calcoloStatistica();
		            jo.put("Tempo medio di update", q.getInt());
		            break;
		            
		    case 3: //Nazioni di Hosting
		            q = new NazioniHost();
		            q.calcoloStatistica();
		            jo.put("Nazioni di Hosting", q.getJSONObject());
		            break;
		            
		    case 4: //ParoleChiave
		            q = new ParoleChiave();
		            q.calcoloStatistica();
		            jo.put("Parole chiave", q.getJSONObject());
		            break;
		            
		    default: break;
		    }
	    }
		*/
		
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
