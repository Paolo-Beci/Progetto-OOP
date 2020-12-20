package it.univpm.ProgettoOOP.stats;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Vector;

import org.json.simple.parser.ParseException;

import it.univpm.ProgettoOOP.model.Domain;

/**
 * <b>Sottoclasse</b> rappresenta la statistica tempo medio di vita 
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 * @version 1.0
 */
public class AverageLifeTime extends Stats{
	
	/**
	 * Statistica tempo medio di vita
	 */
	private double averageLifeTime;
	
	/**
     * <b>Costruttore</b> della classe stessa
     * @param domains vettore di domini sui quali effetture statistica 
     */
	public AverageLifeTime(Vector<Domain> domains) {
		super(domains);
		this.averageLifeTime = 0;
	}
	
	/**
     * <b>Metodo</b> che torna una statistica di tipo <code>double</code>.
     * @return <code>double</code> rappresentante il tempo di vita medio dei domini
     */
	public double getDouble() {
		return averageLifeTime;
	}

	/**
	 * <b>Metodo</b> che effettua il calcolo del tempo di vita medio dei domini.
	 * @see Domain#getCreateDate()
	 */
   public void calculateStat() {
	LocalDateTime today = LocalDateTime.now();
	long lifeTime, lifeTimes = 0;

	   for (Domain domainsStat : super.domains) {
		   if (domainsStat.getCreateDate() != null) {
			   LocalDateTime createDate = LocalDateTime.parse(domainsStat.getCreateDate());
			   lifeTime = ChronoUnit.DAYS.between(createDate, today); 
		   } else
			   lifeTime = 0;
			   
		   lifeTimes += lifeTime;
	   }
		try {
			this.averageLifeTime = lifeTimes / super.domains.size();
		}catch(ArithmeticException e) {
			System.out.println("ERRORE: ARITMETICO");
			System.out.println("MESSAGGI: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
		}
	   }
}
