package it.univpm.ProgettoOOP.filters;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.model.Domain;

public class Filter {
	
	protected Vector<Filter> filters= new Vector<Filter>();
	protected String value;
		
	public Filter() {}
	
	public Filter(String value) {
		this.value= value;
	}
	
	public void filtra(Vector<Domain> domainsToFilter) {}
	
	/**
	 * Non è possibile inserire piu di un filtro dello stesso tipo
	 * @param bodyFilter
	 */
	public void parsingFilters(JSONObject bodyFilter) {
		
		if(bodyFilter.containsKey("name")) { //<-------QUI
			Filter f= new FilterName((String)bodyFilter.get("name"));
			filters.add(f);
		}	
			
		if(bodyFilter.containsKey("country")) {
			String loc = (String)bodyFilter.get("country");
			Filter f= new FilterCountry(loc.toUpperCase());
			filters.add(f);
		}
			
		if(bodyFilter.containsKey("createDate")) {
			Filter f= new FilterCreateDate((String)bodyFilter.get("createDate"));
			filters.add(f);
		}
			
		if(bodyFilter.containsKey("updateDate")) {
			Filter f= new FilterUpdateDate((String)bodyFilter.get("updateDate"));
			filters.add(f);
		}
			
		if(bodyFilter.containsKey("isDead")) {
			String d = (String)bodyFilter.get("isDead");
			// Controllo correttezza inserimento
			if(d.contains("TRUE")||d.contains("true")){
				d = "True";
			}
			if(d.contains("FALSE")||d.contains("false")){
				d = "False";
			}
			Filter f= new FilterIsDead(d);
			filters.add(f);
		}
	}
	
	public Vector<Filter> getFilters() {
		return this.filters;
	}
	
	public String getValue() {
		return this.value;
	}
}
