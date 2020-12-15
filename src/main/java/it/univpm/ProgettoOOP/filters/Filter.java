package it.univpm.ProgettoOOP.filters;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.model.Domain;

public class Filter {
	
	protected Vector<Filter> Filters= new Vector<Filter>();
	protected String value;
		
	public Filter() {}
	
	public Filter(String value) {
		this.value= value;
	}
	
	public void filtra(Vector<Domain> dominiDaFiltrare) {}
	
	/**
	 * Non è possibile inserire piu di un filtro dello stesso tipo
	 * @param bodyFilter
	 */
	public void parsingFilters(JSONObject bodyFilter) {
		
		if(bodyFilter.containsKey("name")) {
			Filter f= new FilterName((String)bodyFilter.get("name"));
			Filters.add(f);
		}	
			
		if(bodyFilter.containsKey("country")) {
			Filter f= new FilterCountry((String)bodyFilter.get("country"));
			Filters.add(f);
		}
			
		if(bodyFilter.containsKey("createDate")) {
			Filter f= new FilterCreateDate((String)bodyFilter.get("createDate"));
			Filters.add(f);
		}
			
		if(bodyFilter.containsKey("updateDate")) {
			Filter f= new FilterUpdateDate((String)bodyFilter.get("updateDate"));
			Filters.add(f);
		}
			
		if(bodyFilter.containsKey("isDead")) {
			Filter f= new FilterIsDead((String)bodyFilter.get("isDead"));
			Filters.add(f);
		}
	}
	
	public Vector<Filter> getFilters() {
		return this.Filters;
	}
	
	public String getValue() {
		return this.value;
	}
}
