package it.univpm.ProgettoOOP.filters;

import java.util.List;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;

import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.model.Domain;

/**
 * <b>Superclasse</b> che modella il filtro
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 * @version 1.0
 */

public class Filter {
	/**
	 * <b>Vettore</b> che contiene filtri del tipo: FilterUpdateDate, FilterCreateDate, FilterIsDead
	 */
	protected List<Filter> filters= new Vector<>();

	/**
	 * <b>Vettore</b> che contiene filtri del tipo FilterName
	 */
	protected List<Filter> filtersName= new Vector<>();

	/**
	 * <b>Vettore</b> che contiene filtri del tipo FilterCountry
	 */
	protected List<Filter> filtersCountry= new Vector<>();

	/**
	 * indica il valore del filtro
	 */
	protected String value;

	/**
	 * <b>Costruttore</b> senza parametri
	 */
	public Filter() {}

	/**
	 * <b>Costruttore</b>
	 * @param value indica il valore del filtro
	 */
	public Filter(String value) {
		this.value= value;
	}

	/**
	 * <b>Metodo</b> che filtra con logica AND
	 * @param domainsToFilter indica il vettore da filtrare
	 */
	public void toFilter(List<Domain> domainsToFilter) {}

	/**
	 * <b>Metodo</b> che filtra con logica OR
	 * @param domainsToFilter indica il vettore da filtrare
	 * @param filteredDomains indica il vettore filtrato
	 */
	public void toFilter(List<Domain> domainsToFilter, List<Domain> filteredDomains) {}
	
	/**
	 * <b>Metodo</b> che analizza i filtri inseriti dall'utente, li riconosce, e li aggiunge al vettore di riferimento
	 * @param bodyFilter contiene i filtri inseriti dall'utente in fase di chiamata
	 * @see Filter#parseString(String)
	 * @see JSONObject#containsKey(Object)
	 * @see JSONObject#get(Object)
	 * @see Vector#add(Object) 
	 */
	public void parsingFilters(JSONObject bodyFilter){
		
		if(bodyFilter.containsKey("name")) {
			Filter f= new Filter();
			String line = (String)bodyFilter.get("name");
			line = line.toLowerCase();
			for(String s: f.parseString(line)){
				Filter f1= new FilterName(s);
				filtersName.add(f1);
			}
		}	
		
		if(bodyFilter.containsKey("country")) {
			Filter f= new Filter();
			String line = (String)bodyFilter.get("country");
			line = line.toUpperCase();
			for(String s: f.parseString(line)){
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
			d = d.toLowerCase();
			if(d.equals("true")){
				d = "True";
			}
			if(d.equals("false")){
				d = "False";
			}
			Filter f= new FilterIsDead(d);
			filters.add(f);
		}
	}

	/**
	 * <b>Metodo</b> che scompone una stringa in sottostringhe separate da ";"
	 * @param line indica la stringa da scomporre
	 * @return array contenente le sottostringhe
	 * @see String#split(String)
	 */
	public String[] parseString(String line) {
		String[] splittedLine= null;
		try{
			splittedLine = line.split(";");
		}catch(PatternSyntaxException p){
			System.out.println("MESSAGGI: " + p.getMessage());
			System.out.println("CAUSA: " + p.getCause());
		}
		return splittedLine;
	}

	/**
	 * <b>Metodo</b> getter del vettore filters
	 * @return vettore contenente filtri del tipo: FilterUpdateDate, FilterCreateDate, FilterIsDead
	 */
	public List<Filter> getFilters() {
		return filters;
	}

	/**
	 * <b>Metodo</b> getter del vettore filtersName
	 * @return vettore contenente filtri del tipo FilterName
	 */
	public List<Filter> getFiltersName() {
		return filtersName;
	}

	/**
	 * <b>Metodo</b> getter del vettore filtersCountry
	 * @return vettore contenente filtri del tipo FilterName
	 */
	public List<Filter> getFiltersCountry() {
		return filtersCountry;
	}

	/**
	 * <b>Metodo</b> getter della stringa value
	 * @return stringa che indica il valore del filtro
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * <b>Metodo</b> toString classe Filter
	 * @return rappresentazione testuale oggetto di tipo Filter
	 */
	public String toString(){
		return null;
	}
}
