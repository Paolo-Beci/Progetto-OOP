package it.univpm.ProgettoOOP.filters;

import java.util.Collection;
import java.util.Vector;

import it.univpm.ProgettoOOP.model.Domain;

/**
 * <b>Sottoclasse</b> che modella il filtro per data di creazione
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 * @version 1.0
 */
public class FilterCreateDate extends Filter {
	/**
	 * <b>Costruttore</b>
	 * @param value indica il valore del filtro
	 */
	public FilterCreateDate(String value) {
		super(value);
	}

	/**
	 * <b>Metodo</b> toString classe FilterCreateDate
	 * @return rappresentazione testuale oggetto di tipo FilterCreateDate
	 */
	@Override
	public String toString() {
		return "\ntipoEffettivo: FilterCreateDate \nvalue: "+value;
	}

	/**
	 * <b>Metodo</b> che filtra con logica AND in base alla data di creazione del dominio
	 * @param domainsToFilter indica il vettore da filtrare
	 * @see Domain#getCreateDate()
	 * @see Vector#contains(Object)
	 * @see Vector#add(Object)
	 * @see Vector#removeAll(Collection)
	 */
	@Override
	public void toFilter(Vector<Domain> domainsToFilter) {
		Vector<Domain> domainToRemove= new Vector<>();
		for(Domain d: domainsToFilter) {
			if(!d.getCreateDate().contains(value))
				domainToRemove.add(d);
		}
		domainsToFilter.removeAll(domainToRemove);
	}
}
