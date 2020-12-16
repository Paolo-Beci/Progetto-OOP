package it.univpm.ProgettoOOP.stats;

import it.univpm.ProgettoOOP.model.Domain;
import org.json.simple.JSONObject;

public class ParoleChiave extends Stats{
	private JSONObject jo = new JSONObject();
	
	public ParoleChiave() {
		super();
	}
	
	public JSONObject getJSONObject() {
		return this.jo;
	}

	public void setJo(JSONObject jo) {
		this.jo = jo;
	}

	/**
	 * Getter del numero di parole chiave nei domini.
	 * @return quantitaDoms Ritorna il numero di parole chiave nei domini.
	 */
    @SuppressWarnings("unchecked")
	public void calcoloStatistica() {
    	int contBusiness = 0, contVacances = 0, contPages = 0, contLogin = 0, contMarketing = 0;
    	int contAltro = 0;
    	
    	for(Domain d: dsi.getDomains()) {
    		if(d.getName().contains("business") | d.getName().contains("vacances") 
    				| d.getName().contains("pages") | d.getName().contains("login")
    				| d.getName().contains("marketing")) {
    			
    			if(d.getName().contains("business"))
        			contBusiness++;
        		if(d.getName().contains("vacances"))
        				contVacances++;
            	if(d.getName().contains("pages"))
            				contPages++;
                if(d.getName().contains("login"))
                				contLogin++;
                if(d.getName().contains("marketing"))
                    				contMarketing++;
    		}
    		else
    			contAltro++;		
        }        				
        
    	this.jo.put("business", contBusiness);
    	this.jo.put("vacances", contVacances);
    	this.jo.put("pages", contPages);
    	this.jo.put("login", contLogin);
    	this.jo.put("marketing", contMarketing);
    	this.jo.put("altro", contAltro);
    	
    }	
}
