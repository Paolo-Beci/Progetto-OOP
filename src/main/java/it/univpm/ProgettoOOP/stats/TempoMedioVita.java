package it.univpm.ProgettoOOP.stats;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TempoMedioVita extends Stats{
	private double tMVDoms;
	
	public TempoMedioVita() {
		super();
		this.tMVDoms = 0;
	}

	public double getDouble() {
		return tMVDoms;
	}

	/**
	 * Getter del tempo medio di vita dei domini.
	 * @return tempoMedioVita Ritorna il tempo medio di vita dei domini.
	 */
   public void calcoloStatistica() {
	LocalDateTime today = LocalDateTime.now();
	long tVDom, tVDoms = 0;

	   for (it.univpm.ProgettoOOP.model.Domain domainsStat : super.domainsStats) {
		   if (domainsStat.getCreateDate() == null) {
			   tVDom = 0;
		   } else {
			   LocalDateTime createDate = LocalDateTime.parse(domainsStat.getCreateDate());
			   tVDom = ChronoUnit.DAYS.between(createDate, today);
		   }
		   tVDoms += tVDom;
	   }
		try {
			this.tMVDoms = tVDoms / super.domainsStats.size();
			System.out.println(tMVDoms);
		}catch(ArithmeticException e) {
			System.out.println("ERRORE: ARITMETICO");
			System.out.println("MESSAGGI: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
		}
	   }
}
