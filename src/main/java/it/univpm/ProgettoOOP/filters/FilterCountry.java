package it.univpm.ProgettoOOP.filters;

import java.util.Vector;

import it.univpm.ProgettoOOP.model.Domain;

public class FilterCountry extends Filter {
	
	public FilterCountry(String value) {
		super(value);
	}
	
	public String toString() {
		return "\ntipoEffettivo: FilterCountry \nvalue: "+value+"\n";
	}
	
	public void toFilter(Vector<Domain> domainsToFilter) { //FILTRAGGIO AND
		
		Vector<Domain> domainsToRemove= new Vector<>();
			
		for(Domain d: domainsToFilter) {
			if(!d.getCountry().equals(value))
				domainsToRemove.add(d);
		}
		domainsToFilter.removeAll(domainsToRemove);
	}
	
	public void toFilter(Vector<Domain> domainsToFilter, Vector<Domain> filteredDomains) { //FILTRAGGIO OR
	
		for(Domain d: domainsToFilter) {
		 	 if(d.getCountry().equals(value) && !filteredDomains.contains(d))
		 		filteredDomains.add(d);
		}
		
	}
}
