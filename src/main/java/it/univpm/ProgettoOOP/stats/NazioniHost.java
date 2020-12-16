package it.univpm.ProgettoOOP.stats;

import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.model.Domain;


public class NazioniHost extends Stats{
	private JSONObject jo = new JSONObject();
	
	public NazioniHost() {
		super();
	}
	
	public JSONObject getJSONObject() {
		return this.jo;
	}
	
	public void setJo(JSONObject jo) {
		this.jo = jo;
	}
	
	/**
	 * Getter del numero di nazioni di hosting dei domini.
	 * @return nazioniHost Ritorna il numero di nazioni di hosting dei domini.
	*/ 
    @SuppressWarnings("unchecked")
	public void calcoloStatistica() {
    	
    	int contUS = 0, contJP = 0, contNL = 0, contDE = 0, contTR = 0, contIT = 0;
    	int contNull = 0, contAltro = 0;
    	
    	for(Domain d: dsi.getDomains()) {
    		switch(d.getCountry()) {
    	
    		case "US": contUS++;
    			       break;
    		
    		case "JP": contJP++;
    	               break;
    	               
    		case "NL": contNL++;
    	               break;
    	               
    		case "DE": contDE++;
    	               break;
    	               
    		case "TR": contTR++;
    	               break;
    	               
    		case "IT": contIT++;
    		           break;
                       
    		case "null": contNull++;           
                       
    		default:  contAltro++; 
    		}
        }
    	this.jo.put("US", contUS);
    	this.jo.put("JP", contJP);
    	this.jo.put("NL", contNL);
    	this.jo.put("DE", contDE);
    	this.jo.put("TR", contTR);
    	this.jo.put("IT", contIT);
    	this.jo.put("null", contNull);
    	this.jo.put("altro", contAltro);
    }
}