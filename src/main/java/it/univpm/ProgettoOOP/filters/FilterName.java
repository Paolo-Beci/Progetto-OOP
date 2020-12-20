package it.univpm.ProgettoOOP.filters;

import java.util.Vector;

import it.univpm.ProgettoOOP.model.Domain;

/**
 * <b>Sottoclasse</b> che modella il filtro per nome, cioe per una sottostringa contenuta nel nome del dominio
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 * @version 1.0
 */
public class FilterName extends Filter {
	/**
	 * <b>Costruttore</b>
	 * @param value indica il valore del filtro
	 */
	public FilterName(String value) {
		super(value);
	}

	/**
	 * <b>Metodo</b> toString classe FilterName
	 * @return rappresentazione testuale oggetto di tipo FilterName
	 */
	@Override
	public String toString() {
		return "\ntipoEffettivo: FilterName \nvalue: "+value+"\n";
	}

	/**
	 * <b>Metodo</b> che filtra con logica OR in base ad una sottostringa contenuta nel nome del dominio
	 * @param domainsToFilter indica il vettore da filtrare
	 * @param filteredDomains indica il vettore filtrato
	 * @see Domain#getName()
	 * @see String#contains(CharSequence)
	 * @see Vector#add(Object)
	 */
	@Override
	public void toFilter(Vector<Domain> domainsToFilter, Vector<Domain> filteredDomains) {
	
		for(Domain d: domainsToFilter) {
		 	 if(d.getName().contains(value) && !filteredDomains.contains(d))
				try{
					filteredDomains.add(d);
				}catch(ClassCastException c){
					System.out.println("MESSAGGI: " + c.getMessage());
					System.out.println("CAUSA: " + c.getCause());
			}
		}
		
	}
}
