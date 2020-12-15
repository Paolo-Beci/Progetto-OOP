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
	
	public void filtra(Vector<Domain> dominiDaFiltrare) {
		
		Vector<Domain> domainToRemove= new Vector<Domain>();
		
		for(Domain d: dominiDaFiltrare) {
			if(!d.getUpdateDate().contains(value))
				domainToRemove.add(d);
		}
		dominiDaFiltrare.removeAll(domainToRemove);
	}
}
