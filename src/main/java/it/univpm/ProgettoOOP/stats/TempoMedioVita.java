package it.univpm.ProgettoOOP.stats;

import java.time.LocalDateTime;
import java.time.Period;
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

	public void setTMV(double tMVDoms) {
		this.tMVDoms = tMVDoms;
	}
	
    /**
	 * Getter del tempo medio di vita dei domini.
	 * @return tempoMedioVita Ritorna il tempo medio di vita dei domini.
	 */
   public void calcoloStatistica(String url) {
	LocalDateTime today = LocalDateTime.now();
	long tVDom, tVDoms = 0;
	
   	for(int i = 0; i < super.dsi.getDomains(url).size(); i++) {
   		LocalDateTime createDate = LocalDateTime.parse(super.dsi.getDomains(url).get(i).getCreateDate());
   		tVDom = ChronoUnit.DAYS.between(createDate, today);
   		tVDoms += tVDom;
    }
   	this.tMVDoms = tVDoms / super.dsi.getDomains(url).size();
   }
}
