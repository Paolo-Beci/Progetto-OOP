package it.univpm.ProgettoOOP.filters;

import java.util.Vector;

import it.univpm.ProgettoOOP.model.Domain;

/**
 * <b>Sottoclasse</b> che modella il filtro per paese
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 * @version 1.0
 */
public class FilterCountry extends Filter {
	/**
	 * <b>Costruttore</b>
	 * @param value indica il valore del filtro
	 */
	public FilterCountry(String value) {
		super(value);
	}

	/**
	 * <b>Metodo</b> toString classe FilterCountry
	 * @return rappresentazione testuale oggetto di tipo FilterCountry
	 */
	@Override
	public String toString() {
		return "\ntipoEffettivo: FilterCountry \nvalue: "+value+"\n";
	}

	/**
	 * <b>Metodo</b> che filtra con logica OR in base al paese del dominio
	 * @param domainsToFilter indica il vettore da filtrare
	 * @param filteredDomains indica il vettore filtrato
	 * @see Domain#getCountry()
	 * @see String#equals(Object)
	 * @see Vector#contains(Object)
	 * @see Vector#add(Object)
	 */
	@Override
	public void toFilter(Vector<Domain> domainsToFilter, Vector<Domain> filteredDomains) { //FILTRAGGIO OR
	
		for(Domain d: domainsToFilter) {
		 	 if(d.getCountry().equals(value) && !filteredDomains.contains(d))
				 try{
					 filteredDomains.add(d);
				 }catch(ClassCastException c){
					 System.out.println("MESSAGGI: " + c.getMessage());
					 System.out.println("CAUSA: " + c.getCause());
				 }
		}
		
	}
}
