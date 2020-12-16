package it.univpm.ProgettoOOP.filters;

import java.util.Vector;

import it.univpm.ProgettoOOP.model.Domain;

public class FilterCountry extends Filter {
	
	private boolean type;
	
	public FilterCountry(String value, boolean type) {
		super(value);
		this.type= type;
	}
	
	public String toString() {
		return "\ntipoEffettivo: FilterCountry \nvalue: "+value;
	}
	
public void filtra(Vector<Domain> domainsToFilter, Vector<Domain> filteredDomains) {
		
		Vector<Domain> domainsToRemove= new Vector<Domain>();
		
		/**
		 * Ciclo che aggiunge elementi al vector domainsToRemove
		 * i suoi elementi verranno poi completamente rimossi dal vettore domainsToFilter
		 */
		if(!this.type) {
			
			for(Domain d: domainsToFilter) {
				if(!d.getCountry().equals(value))
					domainsToRemove.add(d);
			}
			domainsToFilter.removeAll(domainsToRemove);
			filteredDomains= domainsToFilter;
		}
		else 
			
		  for(Domain d: domainsToFilter) {
		 	 	if(d.getCountry().equals(value))
		 			filteredDomains.add(d);
		  }
		
	}
	
}
