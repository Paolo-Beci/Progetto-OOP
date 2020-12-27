package it.univpm.ProgettoOOP.stats;

import java.util.List;
import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.model.Domain;

/**
 * <b>Sottoclasse</b> rappresentante la statistica nazioni di hosting 
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 * @version 1.0
 */
public class HostCountry extends Stats{
	
	/**
	 * Statistica nazioni di hosting
	 */
	private final JSONObject hostCountries = new JSONObject();
	
	/**
     * <b>Costruttore</b> della classe stessa
     * @param domains Vettore di domini sui quali effetture statistica. 
     */
	public HostCountry(List<Domain> domains) {
		super(domains);
	}
	
	/**
     * <b>Metodo</b> che torna una statistica di tipo <code>JSONObject</code>.
     * @return <code>double</code> rappresentante le nazioni di hosting dei domini
     */
	public JSONObject getJSONObject() {
		return this.hostCountries;
	}
	
	/**
	 * <b>Metodo</b> che effettua il calcolo delle nazioni di hosting.
	 * @return Nazioni di hosting dei domini
	 * @see Domain#getCountry()
	 */
    @SuppressWarnings("unchecked")
	public void calculateStat() {
    	
    	int contUS = 0, contJP = 0, contNL = 0, contDE = 0, contTR = 0, contIT = 0;
    	int contNull = 0, contAltro = 0;
    	
    	for(Domain d: super.domains) {
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
    		             break;
                       
    		default:  contAltro++; 
    		}
        }
    	this.hostCountries.put("US", contUS);
    	this.hostCountries.put("JP", contJP);
    	this.hostCountries.put("NL", contNL);
    	this.hostCountries.put("DE", contDE);
    	this.hostCountries.put("TR", contTR);
    	this.hostCountries.put("IT", contIT);
    	this.hostCountries.put("null", contNull);
    	this.hostCountries.put("altro", contAltro);
    }
}