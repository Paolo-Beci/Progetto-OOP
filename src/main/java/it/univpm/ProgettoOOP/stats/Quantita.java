package it.univpm.ProgettoOOP.stats;

public class Quantita extends Stats{
	private int quantita;
	
	public Quantita() {
		super();
		this.quantita = 0;
	}

	public int getInt() {
		return this.quantita;
	}

	public void setQuantitaDoms(int quantita) {
		this.quantita = quantita;
	}
	
	/**
	 * Getter del numero dei domini.
	 * @return quantitaDoms Ritorna il numero dei domini.
	 */
    public void calcoloStatistica(String url) {
    	this.quantita = super.dsi.getDomains(url).size();
    }
	
	
}
