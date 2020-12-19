package it.univpm.ProgettoOOP.stats;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Vector;

import it.univpm.ProgettoOOP.model.Domain;

/**
 * <b>Sottoclasse</b> rappresenta la statistica tempo medio di update 
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 * @version 1.0
 */
public class AverageUpdateTime extends Stats{
	
	/**
	 * Statistica tempo medio di update
	 */
	private double averageUpdateTime;
	
	/**
     * <b>Costruttore</b> della classe stessa
     * @param domains Vettore di domini sui quali effetture statistica. 
     */
	public AverageUpdateTime(Vector<Domain> domains) {
		super(domains);
		this.averageUpdateTime = 0;
	}
	
	/**
     * <b>Metodo</b> che torna una statistica di tipo <code>double</code>.
     * @return <code>double</code> rappresentante il tempo medio di update dei domini
     */
	public double getDouble() {
		return this.averageUpdateTime;
	}

	/**
	 * <b>Metodo</b> che effettua il calcolo del tempo medio di update dei domini.
	 * @return il tempo medio di update dei domini.
	 * @see Domain#getCreateDate()
	 */
    public void calculateStat() {
    	LocalDateTime today = LocalDateTime.now();
    	long UpdateTime, UpdateTimes = 0;

		for (Domain domainsStat : super.domains) {
			if (domainsStat.getCreateDate() != null) {
				LocalDateTime createDate = LocalDateTime.parse(domainsStat.getCreateDate());
				UpdateTime = ChronoUnit.DAYS.between(createDate, today);
			} else
				UpdateTime = 0;
			UpdateTimes += UpdateTime;
		}
		try{
			this.averageUpdateTime = UpdateTimes / super.domains.size();
		}catch(ArithmeticException e) {
			System.out.println("ERRORE: ARITMETICO");
			System.out.println("MESSAGGI: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
		}
    }
}
