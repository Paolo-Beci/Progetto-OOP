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

	public void setTMU(int tMUDoms) {
		this.tMUDoms = tMUDoms;
	}

	/**
	 * Getter del tempo medio di update dei domini.
	 * @return tempoMedioUpdate Ritorna il tempo medio di update dei domini.
	 */
    public void calcoloStatistica() {
    	LocalDateTime today = LocalDateTime.now();
    	long tUDom, tUDoms = 0;
    	
       	for(int i = 0; i < super.dsi.getDomains().size(); i++) {
       		LocalDateTime updateDate = LocalDateTime.parse(super.dsi.getDomains().get(i).getCreateDate());
       		tUDom = ChronoUnit.DAYS.between(updateDate, today);
       		tUDoms += tUDom;
        }
       	this.tMUDoms = tUDoms / super.dsi.getDomains().size();
    }
	
	
}
