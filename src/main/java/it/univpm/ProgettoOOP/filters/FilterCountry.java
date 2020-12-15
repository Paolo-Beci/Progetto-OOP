package it.univpm.ProgettoOOP.filters;

import java.util.Vector;

import it.univpm.ProgettoOOP.model.Domain;

public class FilterCountry extends Filter {
	
	public FilterCountry(String value) {
		super(value);
	}
	
	public String toString() {
		return "\ntipoEffettivo: FilterCountry \nvalue: "+value;
	}
	
	public void filtra(Vector<Domain> domainsToFilter) {
		
		Vector<Domain> domainsToRemove= new Vector<Domain>();
		
		for(Domain d: domainsToFilter) {
			if(!d.getCountry().equals(value))
				domainsToRemove.add(d);
		}
		domainsToFilter.removeAll(domainsToRemove);
	}
	
}
