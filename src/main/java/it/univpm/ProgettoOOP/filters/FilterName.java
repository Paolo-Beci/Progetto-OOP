package it.univpm.ProgettoOOP.filters;

import java.util.Vector;

import it.univpm.ProgettoOOP.model.Domain;

public class FilterName extends Filter {
	
	public FilterName(String value) {
		super(value);
	}
	
	public String toString() {
		return "\ntipoEffettivo: FilterName \nvalue: "+value+"\n";
	}
	
	public void filtra(Vector<Domain> domainsToFilter) { //FILTRAGGIO AND
		
		Vector<Domain> domainsToRemove= new Vector<>();
			
		for(Domain d: domainsToFilter) {
			if(!d.getName().contains(value))
				domainsToRemove.add(d);
		}
		domainsToFilter.removeAll(domainsToRemove);
	}
	
	public void filtra(Vector<Domain> domainsToFilter, Vector<Domain> filteredDomains) { //FILTRAGGIO OR
	
		for(Domain d: domainsToFilter) {
		 	 if(d.getName().contains(value) && !filteredDomains.contains(d))
		 		filteredDomains.add(d);
		}
		
	}
}
