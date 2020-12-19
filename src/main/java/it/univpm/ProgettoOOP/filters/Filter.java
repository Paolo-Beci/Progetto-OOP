package it.univpm.ProgettoOOP.filters;

import java.util.Vector;
import java.util.regex.PatternSyntaxException;

import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.model.Domain;

public class Filter {
	
	protected Vector<Filter> filters= new Vector<>();
	protected Vector<Filter> filtersName= new Vector<>();
	protected Vector<Filter> filtersCountry= new Vector<>();
	protected String value;
		
	public Filter() {}
	
	public Filter(String value) {
		this.value= value;
	}
	
	public void toFilter(Vector<Domain> domainsToFilter) {}
	public void toFilter(Vector<Domain> domainsToFilter, Vector<Domain> filteredDomains) {}
	
	/**
	 * Non è possibile inserire piu di un filtro dello stesso tipo
	 * @param bodyFilter description
	 */
	public void parsingFilters(JSONObject bodyFilter) {
		
		if(bodyFilter.containsKey("name")) { //Filtraggio OR
			Filter f= new Filter();
			for(String s: f.parseString((String)bodyFilter.get("name"))){
				Filter f1= new FilterName(s);
				filtersName.add(f1);
			}
		}	
		
		if(bodyFilter.containsKey("country")) { //Filtraggio OR
			Filter f= new Filter();
			
			for(String s: f.parseString((String)bodyFilter.get("country"))){
				Filter f1= new FilterCountry(s);
				filtersCountry.add(f1);
			}
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

	public String[] parseString(String line) {
		String[] splittedLine= null;
		try{
			splittedLine = line.split(";");
		}catch(PatternSyntaxException p){
			System.out.println("ERRORE: GENERICO in parseString()");
			System.out.println("MESSAGGI: " + p.getMessage());
			System.out.println("CAUSA: " + p.getCause());
		}
		return splittedLine;
	}
	
	public Vector<Filter> getFilters() {
		return filters;
	}

	public Vector<Filter> getFiltersName() {
		return filtersName;
	}

	public Vector<Filter> getFiltersCountry() {
		return filtersCountry;
	}

	public String getValue() {
		return this.value;
	}
	
}
