package it.univpm.ProgettoOOP.stats;

public class TempoMedioVita extends Stats{
	private int tMV;
	
	public TempoMedioVita() {
		super();
		this.tMV = 0;
	}

	public int getInt() {
		return tMV;
	}

	public void setTMV(int tMV) {
		this.tMV = tMV;
	}
	
    /**
	 * Getter del tempo medio di vita dei domini.
	 * @return tempoMedioVita Ritorna il tempo medio di vita dei domini.
	 */
   public void calcoloStatistica() {
   	int tVDom, tvDoms = 0;
   	for(int i = 0; i < super.dsi.getDomains().size(); i++) {
   		//tVDom = dataodierna - this.dsi.getDomains().get(i).getCreateDate()
   		//tvDoms += tVDom;
   	}
   	//this.tMV = tvDoms / this.s.quantitaDoms;
   }
	
}
