package it.univpm.ProgettoOOP.model;

/**
 * Classe che modella il dominio con i suoi dati
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 */
public class Domain {
	
	private String name;
	private String createDate;
	private String updateDate;
	private String country;
	private String isDead;
	
	public Domain(String name, String createDate, String updateDate,String country, String isDead) {
		this.name= name;
		this.createDate= createDate;
		this.updateDate= updateDate;
		this.country= country;
		this.isDead= isDead;		
	}
	
	public String toString() {
		return "\nname: "+name+"\ncreateDate: "+createDate+"\nupdateDate: "+updateDate
				+"\ncountry: "+country+"\nisDead: "+isDead;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getCountry() {
		if(this.country!=null)
			return country;
		return "null";
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIsDead() {
		return isDead;
	}

	public void setIsDead(String isDead) {
		this.isDead = isDead;
	}
}
