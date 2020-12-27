package it.univpm.ProgettoOOP.filters;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import it.univpm.ProgettoOOP.model.Domain;

/**
 * <b>Sottoclasse</b> che modella il filtro per stato di scadenza, abbandono del dominio
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 * @version 1.0
 */
public class FilterIsDead extends Filter {
	/**
	 * <b>Costruttore</b>
	 * @param value indica il valore del filtro
	 */
	public FilterIsDead(String value) {
		super(value);
	}

	/**
	 * <b>Metodo</b> toString classe FilterIsDead
	 * @return rappresentazione testuale oggetto di tipo FilterIsDead
	 */
	public String toString() {
		return "\ntipoEffettivo: FilteIsDead \nvalue: "+value;
	}

	/**
	 * <b>Metodo</b> che filtra con logica AND in base allo stato di abbandono del dominio
	 * @param domainsToFilter indica il vettore da filtrare
	 * @see Domain#getCreateDate()
	 * @see Vector#contains(Object)
	 * @see Vector#add(Object)
	 * @see Vector#removeAll(Collection)
	 */
	public void toFilter(List<Domain> domainsToFilter) {
		Vector<Domain> domainToRemove= new Vector<>();
		for(Domain d: domainsToFilter) {
			if(!d.getIsDead().contains(value))
				domainToRemove.add(d);
		}
		domainsToFilter.removeAll(domainToRemove);			
	}
}
