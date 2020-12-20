package it.univpm.ProgettoOOP.stats;

import it.univpm.ProgettoOOP.model.Domain;
import java.util.Vector;

/**
 * <b>Sottoclasse</b> rappresenta la statistica quantita' 
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 * @version 1.0
 */
public class Quantity extends Stats{
	
	/**
	 * Statistica quantita'
	 */
	private int quantity;
	
	/**
     * <b>Costruttore</b> della classe stessa
     * @param domains Vettore di domini sui quali effetture statistica 
     */
	public Quantity(Vector<Domain> domains) {
		super(domains);
		this.quantity = 0;
	}
	
	/**
     * <b>Metodo</b> che torna una statistica di tipo <code>int</code>.
     * @return <code>int</code> rappresentante la quantita'
     */
	public int getInt() {
		return this.quantity;
	}

	/**
	 * <b>Metodo</b> setter della quantita'.
	 * @param quantity Quantita'
	 */
	public void setQuantita(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * <b>Metodo</b> che effettua il calcolo della quantita' di domini.
	 * @return Numero dei domini
	 */
    public void calculateStat() {
    	this.quantity = super.domains.size();
    }
	
	
}
