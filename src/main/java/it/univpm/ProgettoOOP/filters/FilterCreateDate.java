package it.univpm.ProgettoOOP.filters;

import java.util.Vector;

import it.univpm.ProgettoOOP.model.Domain;

public class FilterCreateDate extends Filter {
	
	public FilterCreateDate(String value) {
		super(value);
	}
	
	public String toString() {
		return "\ntipoEffettivo: FilterCreateDate \nvalue: "+value;
	}
	
	public void filtra(Vector<Domain> domainsToFilter) {

		Vector<Domain> domainToRemove= new Vector<Domain>();
		
		for(Domain d: domainsToFilter) {
			if(!d.getCreateDate().contains(value))
				domainToRemove.add(d);
		}
		domainsToFilter.removeAll(domainToRemove);
	}
}
