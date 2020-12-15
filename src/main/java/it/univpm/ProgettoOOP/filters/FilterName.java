package it.univpm.ProgettoOOP.filters;

import java.util.Vector;

import it.univpm.ProgettoOOP.model.Domain;

public class FilterName extends Filter {
	
	public FilterName(String value) {
		super(value);
	}
	
	public String toString() {
		return "\ntipoEffettivo: FilterName \nvalue: "+value;
	}
	
	public void filtra(Vector<Domain> domainsToFilter) {
		
		Vector<Domain> domainsToRemove= new Vector<Domain>();
		
		/**
		 * Ciclo che aggiunge elementi al vector domainsToRemove
		 * i suoi elementi verranno poi completamente rimossi dal vettore domainsToFilter
		 */
		System.out.println("\n################### PRIMA:");
		for(Domain d2: domainsToFilter)
			System.out.println(d2);
		
		for(Domain d: domainsToFilter) {
			if(!d.getName().contains(value))
				domainsToRemove.add(d);
		}
		domainsToFilter.removeAll(domainsToRemove);
		
		System.out.println("\n################### DOPO:");
			for(Domain d1: domainsToFilter) {
				System.out.println(d1);
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
		
		/**
		 * Ciclo for-each che aggiunge anziche rimuovere
		 * for(Domain d: domainsToFilter) {
		 *	 	if(d.getUpdateDate().contains(value))
		 *			filteredDomains.add(d);
		 * }
		 */
		
		
	}
}
