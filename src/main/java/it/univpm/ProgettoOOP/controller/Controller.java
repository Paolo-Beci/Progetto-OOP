package it.univpm.ProgettoOOP.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.ProgettoOOP.model.Domain;
import it.univpm.ProgettoOOP.service.DomainService;
import it.univpm.ProgettoOOP.service.DomainServiceImpl;
import it.univpm.ProgettoOOP.service.DownloadDomains;

@RestController
public class Controller {
	
	@RequestMapping(value= "/domains", method= RequestMethod.GET) //specifico la richiesta da fare con Postman
	public ResponseEntity<Object> getDomains(){

		Vector<Domain> v= new Vector<Domain>();
		
		JSONParser parser = new JSONParser(); {

		try {        
			
	        URL oracle = new URL("https://api.domainsdb.info/v1/domains/search?limit=100&domain=facebook&zone=com"); // URL to Parse
	        HttpsURLConnection yc = (HttpsURLConnection) oracle.openConnection();
			yc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
	        
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	       
	        String inputLine;
	        while ((inputLine = in.readLine()) != null) { 
	        	
	        	//analizzo tutta la risposta dell'api
	        	JSONObject stats = (JSONObject) parser.parse(inputLine);
	        	
	        	//vado a cercare domains nella risposta e lo casto a JSONArray
	        	JSONArray a = (JSONArray) stats.get("domains");
	            
	            int counter= 0;
	            // Loop through each item
	            for (Object o : a) {
	      
	            	
	                JSONObject domain = (JSONObject) o;

	                String name = (String) domain.get("domain");
	                //System.out.println("Domain : " + name);
	                
	                String createDate = (String) domain.get("create_date");
	                //System.out.println("create_date : " + createDate);
	                
	                String updateDate = (String) domain.get("update_date");
	                //System.out.println("update_date : " + updateDate);
	                
	                String country = (String) domain.get("country");
	                //System.out.println("Country : " + country);
	                
	                String isDead = (String) domain.get("isDead");
	                //System.out.println("IsDead : " + isDead);
	                
	                Domain d= new Domain(name, createDate, updateDate, country, isDead);
	                System.out.println("isDead "+ counter+ " "+ d.getIsDead());
	                v.add(d);
	                counter++;
	            }
	            
	        }
	        
	        in.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }

	 }
		return new ResponseEntity<>(v, HttpStatus.OK);
	}

}
