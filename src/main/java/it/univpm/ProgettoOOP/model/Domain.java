package it.univpm.ProgettoOOP.model;

/**
 * <b>Classe</b> che modella il dominio con i suoi dati
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 * @version 1.0
 */
public class Domain {

	/**
	 * Nome del dominio
	 */
	private String name;
	
	/**
	 * Data di creazione del dominio
	 */
	private String createDate;
	
	/**
	 * Data dell'ultimo update del dominio
	 */
	private String updateDate;
	
	/**
	 * Nazione di hosting del dominio
	 */
	private String country;
	
	/**
	 * Stato del dominio
	 */
	private String isDead;

	/**
	 * <b>Costruttore</b> della classe stessa
	 * @param name Nome del dominio
	 * @param createDate Data di creazione del dominio
	 * @param updateDate Data dell'ultimo update del dominio
	 * @param country Nazione di hosting del dominio
	 * @param isDead Stato del dominio
	 */
	public Domain(String name, String createDate, String updateDate,String country, String isDead) {
		this.name= name;
		this.createDate= createDate;
		this.updateDate= updateDate;
		this.country= country;
		this.isDead= isDead;		
	}

	/**
	 * <b>Metodo</b> che restituisce il toString del dominio.
	 * @return Ritorna la stringa con le informazioni sul dominio.
	 */
	public String toString() {
		return "\nname: "+name+"\ncreateDate: "+createDate+"\nupdateDate: "+updateDate
				+"\ncountry: "+country+"\nisDead: "+isDead;
	}

	/**
	 * <b>Metodo</b> che restituisce il nome del dominio.
	 * @return Nome del dominio
	 */
	public String getName() { return name; }

	/**
	 * <b>Metodo</b> che permette la modifica del nome del dominio.
	 * @param name Nuovo nome del dominio
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <b>Metodo</b> che restituisce la data di creazione del dominio.
	 * @return Data di creazione
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * <b>Metodo</b> che permette la modifica della data di creazione del dominio.
	 * @param createDate Nuova data di creazione del dominio
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * <b>Metodo</b> che restituisce la data dell'ultimo update del dominio.
	 * @return Data dell'ultimo update del dominio
	 */
	public String getUpdateDate() {
		return updateDate;
	}

	/**
	 * <b>Metodo</b> che permette la modifica della data dell'ultimo update del dominio.
	 * @param updateDate Nuova data dell'update del dominio
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * <b>Metodo</b> che restituisce la nazione di hosting del dominio.
	 * @return Nazione di hosting del dominio
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * <b>Metodo</b> che permette la modifica della nazione di hosting del dominio.
	 * @param country Nuova nazione di hosting del dominio
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * <b>Metodo</b> che restituisce l'info sull'attivazione o meno del dominio.
	 * @return Stato del dominio
	 */
	public String getIsDead() {
		return isDead;
	}

	/**
	 * <b>Metodo</b> che permette la modifica dello stato del dominio.
	 * @param isDead Nuovo stato del dominio
	 */
	public void setIsDead(String isDead) {
		this.isDead = isDead;
	}
}
