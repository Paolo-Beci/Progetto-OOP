package it.univpm.ProgettoOOP.filters;

import java.util.Vector;

import it.univpm.ProgettoOOP.model.Domain;

public class FilterUpdateDate extends Filter {
	
	public FilterUpdateDate(String value) {
		super(value);
	}
	
	public String toString() {
		return "\ntipoEffettivo: FilterUpdateDate \nvalue: "+value;
	}
	
	public void toFilter(Vector<Domain> domainsToFilter) {
		
		Vector<Domain> domainToRemove= new Vector<>();
		
		for(Domain d: domainsToFilter) {
			if(!d.getUpdateDate().contains(value))
				domainToRemove.add(d);
		}
		domainsToFilter.removeAll(domainToRemove);
	}
}
