package it.univpm.ProgettoOOP.filters;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import it.univpm.ProgettoOOP.model.Domain;

/**
 * <b>Sottoclasse</b> che modella il filtro per data di ultimo aggiornamento
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 * @version 1.0
 */
public class FilterUpdateDate extends Filter {
	/**
	 * <b>Costruttore</b>
	 * @param value indica il valore del filtro
	 */
	public FilterUpdateDate(String value) {
		super(value);
	}

	/**
	 * <b>Metodo</b> toString classe FilterUpdateDate
	 * @return rappresentazione testuale oggetto di tipo FilterUpdateDate
	 */
	public String toString() {
		return "\ntipoEffettivo: FilterUpdateDate \nvalue: "+value;
	}

	/**
	 * <b>Metodo</b> che filtra con logica AND in base alla data di ultimo aggiornamento del dominio
	 * @param domainsToFilter indica il vettore da filtrare
	 * @see Domain#getCreateDate()
	 * @see Vector#contains(Object)
	 * @see Vector#add(Object)
	 * @see Vector#removeAll(Collection)
	 */
	public void toFilter(List<Domain> domainsToFilter) {
		Vector<Domain> domainToRemove= new Vector<>();
		for(Domain d: domainsToFilter) {
			if(!d.getUpdateDate().contains(value))
				domainToRemove.add(d);
		}
		domainsToFilter.removeAll(domainToRemove);
	}
}
