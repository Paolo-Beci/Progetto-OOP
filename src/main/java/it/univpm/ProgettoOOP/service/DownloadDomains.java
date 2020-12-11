package it.univpm.ProgettoOOP.service;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.univpm.ProgettoOOP.model.Domain;

public class DownloadDomains {
	
	public Vector<Domain> Download() {
		
		Vector<Domain> downloadedDomains= new Vector<Domain>();
		JSONParser parser = new JSONParser();
		try {        
			
	        URL oracle = new URL("https://api.domainsdb.info/v1/domains/search?limit=50&domain=facebook&zone=com"); // URL to Parse
	        HttpsURLConnection yc = (HttpsURLConnection) oracle.openConnection();
			yc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
	        
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	       
	        String inputLine;
	        while ((inputLine = in.readLine()) != null) { 
	        	
	        	//analizzo tutta la risposta dell'api
	        	JSONObject stats = (JSONObject) parser.parse(inputLine);
	        	
	        	//vado a cercare domains nella risposta e lo casto a JSONArray
	        	JSONArray a = (JSONArray) stats.get("domains");
	            
	            for (Object o : a) {
	      
	            	JSONObject domain = (JSONObject) o;

	                String name = (String) domain.get("domain");
	                String createDate = (String) domain.get("create_date");
	                String updateDate = (String) domain.get("update_date");
	                String country = (String) domain.get("country");
	                String isDead = (String) domain.get("isDead");
	                
	                Domain d= new Domain(name, createDate, updateDate, country, isDead);
	                downloadedDomains.add(d);
	            }

	        }
	        in.close();
	    } 
		catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } 
		catch (IOException e) {
	        e.printStackTrace();
	    } 
		catch (ParseException e) {
	        e.printStackTrace();
	    }
		
		return downloadedDomains;
	}
			
}
