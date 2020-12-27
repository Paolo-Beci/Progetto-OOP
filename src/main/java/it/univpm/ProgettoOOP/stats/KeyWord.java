package it.univpm.ProgettoOOP.stats;

import it.univpm.ProgettoOOP.model.Domain;

import java.util.List;
import org.json.simple.JSONObject;

/**
 * <b>Sottoclasse</b> rappresenta la statistica parole chiave 
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 * @version 1.0
 */
public class KeyWord extends Stats{
	
	/**
	 * Statistica parole chiave
	 */
	private JSONObject keyWords = new JSONObject();
	
	/**
     * <b>Costruttore</b> della classe stessa
     * @param domains vettore di domini sui quali effetture statistica 
     */
	public KeyWord(List<Domain> domains) {
			super(domains);
	}

	/**
     * <b>Metodo</b> che torna una statistica di tipo <code>JSONObject</code>.
     * @return <code>double</code> rappresentante le parole chiave dei domini
     */
	public JSONObject getJSONObject() {
		return this.keyWords;
	}

	/**
	 * <b>Metodo</b> che effettua il calcolo delle parole chiave.
	 * @see Domain#getName()
	 */
    @SuppressWarnings("unchecked")
	public void calculateStat() {
    	int contBusiness = 0, contVacances = 0, contPages = 0, contLogin = 0, contMarketing = 0;
    	int contAltro = 0;
    	
    	for(Domain d: super.domains) {
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
        
    	this.keyWords.put("business", contBusiness);
    	this.keyWords.put("vacances", contVacances);
    	this.keyWords.put("pages", contPages);
    	this.keyWords.put("login", contLogin);
    	this.keyWords.put("marketing", contMarketing);
    	this.keyWords.put("altro", contAltro);
    	
    }	
}
