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
	public void filtra(Vector<Domain> dominiDaFiltrare) { 

		Vector<Domain> domainToRemove= new Vector<Domain>();
		
		for(Domain d: dominiDaFiltrare) {
			if(!d.getCreateDate().contains(value))
				domainToRemove.add(d);
		}
		dominiDaFiltrare.removeAll(domainToRemove);			
	}
}
