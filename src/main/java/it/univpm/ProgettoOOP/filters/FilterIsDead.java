package it.univpm.ProgettoOOP.filters;

import java.util.Vector;

import it.univpm.ProgettoOOP.model.Domain;

public class FilterIsDead extends Filter {
	
	public FilterIsDead(String value) {
		super(value);
	}
	
	public String toString() {
		return "\ntipoEffettivo: FilteIsDead \nvalue: "+value;
	}
	/**
	 * E' importante inserire True o False con l'iniziale maiuscola nel bodyFilter
	 */
	public void filtra(Vector<Domain> domainsToFilter) { 

		Vector<Domain> domainToRemove= new Vector<Domain>();
		
		for(Domain d: domainsToFilter) {
			if(!d.getIsDead().contains(value))
				domainToRemove.add(d);
		}
		domainsToFilter.removeAll(domainToRemove);			
	}
}
