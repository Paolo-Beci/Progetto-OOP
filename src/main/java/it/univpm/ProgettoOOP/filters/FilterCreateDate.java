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
	
	public void filtra(Vector<Domain> dominiDaFiltrare) {

		Vector<Domain> domainToRemove= new Vector<Domain>();
		
		for(Domain d: dominiDaFiltrare) {
			if(!d.getCreateDate().contains(value))
				domainToRemove.add(d);
		}
		dominiDaFiltrare.removeAll(domainToRemove);
	}
}
