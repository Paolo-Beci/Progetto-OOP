package it.univpm.ProgettoOOP.stats;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TempoMedioUpdate extends Stats{
	private double tMUDoms;
	
	public TempoMedioUpdate() {
		super();
		this.tMUDoms = 0;
	}

	public double getDouble() {
		return this.tMUDoms;
	}

	/**
	 * Getter del tempo medio di update dei domini.
	 * tempoMedioUpdate Ritorna il tempo medio di update dei domini.
	 */
    public void calcoloStatistica() {
    	LocalDateTime today = LocalDateTime.now();
    	long tUDom, tUDoms = 0;

		for (it.univpm.ProgettoOOP.model.Domain domainsStat : super.domainsStats) {
			if (domainsStat.getCreateDate() != null) {
				LocalDateTime createDate = LocalDateTime.parse(domainsStat.getCreateDate());
				tUDom = ChronoUnit.DAYS.between(createDate, today);
			} else
				tUDom = 0;
			tUDoms += tUDom;
		}
		try{
			this.tMUDoms = tUDoms / super.domainsStats.size();
		}catch(ArithmeticException e) {
			System.out.println("ERRORE: ARITMETICO");
			System.out.println("MESSAGGI: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
		}
    }
}
