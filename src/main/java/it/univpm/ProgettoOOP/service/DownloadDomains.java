package it.univpm.ProgettoOOP.service;

import java.io.*;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Vector;

import javax.net.ssl.HttpsURLConnection;

import it.univpm.ProgettoOOP.exception.NoConnectionException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.univpm.ProgettoOOP.model.Domain;

/**
 * Classe che gestisce il download e i relativi dati dei domini forniti dall'API
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 */
public class DownloadDomains {

	// URL dell'API
	public static String url = "https://api.domainsdb.info/v1/domains/search?limit=50&domain=facebook&zone=com";

	/**
	 * Metodo che effettua il download dei domini dall'api e che li elabora in formato stringa in modo tale da essere elaborati
	 * dalle classi di filtri e statistiche.
	 * @return Ritorna il vettore downloadedDomains.
	 * @throws NoConnectionException Eccezione personalizzata che carica il database da locale nel caso di mancata connessione
	 */
	public Vector<Domain> Download() {
		Vector<Domain> downloadedDomains= new Vector<Domain>();
		JSONParser parser = new JSONParser();

		try {
			// Chiamata dell'API
			URL oracle = new URL(url);
			HttpsURLConnection yc = (HttpsURLConnection) oracle.openConnection();
			yc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");

			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

	        String inputLine;
	        while ((inputLine = in.readLine()) != null) { 
	        	
	        	//analizzo tutta la risposta dell'api
	        	JSONObject stats = (JSONObject) parser.parse(inputLine);
	        	
	        	//vado a cercare domains nella risposta e lo casto a JSONArray
				BuildDomains(stats, downloadedDomains);
				if(downloadedDomains == null)
					throw new NoConnectionException();
			}
	        in.close();
	    }catch(NoConnectionException e)
		{
			// NON FUNZIA --> Da chiedere se si può forzare springboot ad avviarsi anche senza connessione
			try{
				Scanner in = new Scanner(new BufferedReader(new FileReader("\\Progetto-OOP\\src\\main\\resources\\BackupData.jar")));
				while ((in.hasNext())) {

					//analizzo il file jar in input
					JSONObject stats = (JSONObject) parser.parse(String.valueOf(in));

					//vado a cercare domains nella risposta e lo casto a JSONArray
					BuildDomains(stats, downloadedDomains);
				}
				in.close();
			}catch(IOException | ParseException f) {
				System.out.println("ERRORE: OPERAZIONE INTERROTTA NELLA GESTIONE DEL FILE DA LOCAL RESOURCES");
				System.out.println("MESSAGGIO: " + f.getMessage());
				System.out.println("CAUSA: " + f.getCause());
			}
			//
		}
		catch (FileNotFoundException e) {
			System.out.println("ERRORE: PROBLEMI CON LA GESTIONE DEI FILE");
			System.out.println("MESSAGGIO: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
	        e.printStackTrace();
	    }
		catch (IOException e) {
			System.out.println("ERRORE: OPERAZIONE INTERROTTA NELLA GESTIONE DEI FILE");
			System.out.println("MESSAGGIO: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
	        e.printStackTrace();
	    } 
		catch (ParseException e) {
			System.out.println("ERRORE GENERICO.");
			System.out.println("MESSAGGIO: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
			e.printStackTrace();
		}
		
		return downloadedDomains;
	}

	private void BuildDomains(JSONObject stats, Vector<Domain> downloadedDomains) {
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

}
