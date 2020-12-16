package it.univpm.ProgettoOOP.stats;

public class TempoMedioUpdate extends Stats{
	private int tMU;
	
	public TempoMedioUpdate() {
		super();
		this.tMU = 0;
	}

	public int getInt() {
		return this.tMU;
	}

	public void setTMU(int tMU) {
		this.tMU = tMU;
	}

	/**
	 * Getter del tempo medio di update dei domini.
	 * @return tempoMedioUpdate Ritorna il tempo medio di update dei domini.
	 */
    public void calcoloStatistica() {
    	int tUDom, tUDoms = 0;
    	for(int i = 0; i < super.dsi.getDomains().size(); i++) {
    		//tUDom = dataodierna - this.dsi.getDomains().get(i).getUpdateDate()
    		//tUDoms += tUDom;
    	}
    	//this.tMU = tUDoms / this.s.quantitaDoms;
    }
	
	
}
