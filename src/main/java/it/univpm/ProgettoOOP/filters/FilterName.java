package it.univpm.ProgettoOOP.filters;

import java.util.Vector;

import it.univpm.ProgettoOOP.model.Domain;
import it.univpm.ProgettoOOP.service.DomainService;
import it.univpm.ProgettoOOP.service.DomainServiceImpl;

public class FilterName extends Filter {
	
	private boolean type; //True OR, False AND
	
	public FilterName(String value, boolean type) {
		super(value);
		this.type= type;
	}
	
	public String toString() {
		return "\ntipoEffettivo: FilterName \nvalue: "+value;
	}
	
	public void filtra(Vector<Domain> domainsToFilter, Vector<Domain> filteredDomains) {
		
		Vector<Domain> domainsToRemove= new Vector<Domain>();
		DomainService d0= new DomainServiceImpl();
		/**
		 * Ciclo che aggiunge elementi al vector domainsToRemove
		 * i suoi elementi verranno poi completamente rimossi dal vettore domainsToFilter
		 */
		if(!this.type) {
			
			for(Domain d: domainsToFilter) {
				if(!d.getName().contains(value))
					domainsToRemove.add(d);
			}
			domainsToFilter.removeAll(domainsToRemove);
			filteredDomains.addAll(domainsToFilter);
		}
		else 
			
		  for(Domain d: domainsToFilter) {
		 	 	if(d.getName().contains(value))
		 			filteredDomains.add(d);
		  }
		 		/**
		 * Ciclo for senza Iteratore
		 * 
		 * for(int i=0; i< domainsToFilter.size(); i++) {
		 *  	if(domainsToFilter.get(i).getName().contains(value))
		 *   		domainsToFilter.remove(i);
		 * }
		 */
		
		/**
		 * Ciclo con Iteratore
		 * 
		 *Iterator<Domain> it= domainsToFilter.iterator();
		 * while(it.hasNext()) {
		 * 		Domain d= (Domain) it.next();
		 *		if(!d.getName().contains(value))
		 *			it.remove();
		 * }
		 */
	}
}
