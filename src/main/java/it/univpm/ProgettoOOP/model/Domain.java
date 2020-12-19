package it.univpm.ProgettoOOP.model;

/**
 * Classe che modella il dominio con i suoi dati
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 */
public class Domain {

	// variabili d'istanza
	private String name;
	private String createDate;
	private String updateDate;
	private String country;
	private String isDead;

	// costruttore
	public Domain(String name, String createDate, String updateDate,String country, String isDead) {
		this.name= name;
		this.createDate= createDate;
		this.updateDate= updateDate;
		this.country= country;
		this.isDead= isDead;		
	}

	/**
	 * Metodo che restituisce il toString del dominio
	 * @return Ritorna la stringa che visualizza i parametri del dominio.
	 */
	public String toString() {
		return "\nname: "+name+"\ncreateDate: "+createDate+"\nupdateDate: "+updateDate
				+"\ncountry: "+country+"\nisDead: "+isDead;
	}

	/**
	 * Metodo che restituisce il getName del dominio
	 * @return Ritorna il nome del dominio.
	 */
	public String getName() { return name; }

	/**
	 * Metodo che permette la modifica del nome del dominio
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Metodo che restituisce il getCreateDate del dominio
	 * @return Ritorna la data di creazione.
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * Metodo che permette la modifica della data di creazione del dominio
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * Metodo che restituisce il getUpdateDate del dominio
	 * @return Ritorna l'ultima data di update del dominio.
	 */
	public String getUpdateDate() {
		return updateDate;
	}

	/**
	 * Metodo che permette la modifica della data di ultimo update del dominio
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * Metodo che restituisce il getCountry del dominio
	 * @return Ritorna il paese di host del dominio.
	 */
	public String getCountry() {
		if(this.country!=null)
			return country;
		return "null";
	}

	/**
	 * Metodo che permette la modifica del paese di host del dominio
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Metodo che restituisce il getIsDead del dominio
	 * @return Ritorna lo stato del dominio.
	 */
	public String getIsDead() {
		return isDead;
	}

	/**
	 * Metodo che permette la modifica dello stato del dominio
	 */
	public void setIsDead(String isDead) {
		this.isDead = isDead;
	}
}
