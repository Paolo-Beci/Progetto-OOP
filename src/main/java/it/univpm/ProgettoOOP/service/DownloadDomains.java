package it.univpm.ProgettoOOP.service;

import java.io.*;
import java.net.URL;
//import java.net.http.HttpTimeoutException;
import java.util.Scanner;
import java.util.Vector;

import javax.net.ssl.HttpsURLConnection;

import it.univpm.ProgettoOOP.exception.NoDataException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.univpm.ProgettoOOP.model.Domain;

import java.io.IOException;

/**
 * Classe che gestisce il download e i relativi dati dei domini forniti dall'API
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 */
public class DownloadDomains {

	/**
	 * Metodo che effettua il download dei domini dall'api e che li elabora in formato stringa in modo tale da essere elaborati
	 * dalle classi di filtri e statistiche.
	 * @return Ritorna il vettore downloadedDomains.
	 * @throws NoDataException Eccezione personalizzata che carica il database da locale nel caso di mancata connessione
	 */
	public Vector<Domain> Download(String url) {
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
				//if(downloadedDomains == null)
					//throw new NoConnectionException();
			}
	        in.close();
	    }catch (FileNotFoundException e) {
			System.out.println("ERRORE: PROBLEMI CON LA GESTIONE DEI FILE");
			System.out.println("MESSAGGIO: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
	        e.printStackTrace();
	    }
		catch (IOException e) {
			System.out.println("ERRORE: OPERAZIONE INTERROTTA NELLA GESTIONE DEI FILE");
			System.out.println("MESSAGGIO: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());


			// provo a caricare da file locale le info sui domini
			try{
				String path_file = "C:\\Users\\becic\\IdeaProjects\\Progetto-OOP\\src\\main\\resources\\BackupData.txt";
				Scanner in = new Scanner(new FileReader(path_file));

				String inputLine;
				while ((inputLine = in.nextLine()) != null) {

					//analizzo tutta la risposta dell'api
					JSONObject stats = (JSONObject) parser.parse(inputLine);
					System.out.println(inputLine);
					//vado a cercare domains nella risposta e lo casto a JSONArray
					BuildDomains(stats, downloadedDomains);
					//if(downloadedDomains == null)
					//throw new NoConnectionException();
				}
					
				in.close();
			}catch(IOException | ParseException f) {
				System.out.println("ERRORE: OPERAZIONE INTERROTTA NELLA GESTIONE DEL FILE DA LOCAL RESOURCES");
				System.out.println("MESSAGGI: " + f.getMessage());
				System.out.println("CAUSA: " + f.getCause());
			}
	    } 
		catch (Exception e) {
			System.out.println("ERRORE GENERICO.");
			System.out.println("MESSAGGIO: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
			e.printStackTrace();
		}
		
		return downloadedDomains;
	}

	/**
	 * Metodo che effettua il get dei vari argomenti contenuti in ogni oggetto di stats
	 * dalle classi di filtri e statistiche.
	 * @throws NoDataException Eccezione personalizzata che carica il database da locale nel caso di mancata connessione
	 * @param stats contiene l'oggetto da analizzare e smistare
	 * @param downloadedDomains è un vettore di oggetti Domain
	 */
	private void BuildDomains(JSONObject stats, Vector<Domain> downloadedDomains) {
		JSONArray a = (JSONArray) stats.get("domains");

			for (Object o : a) {
				try {
				JSONObject domain = (JSONObject) o;

				String name = (String) domain.get("domain");
				String createDate = (String) domain.get("create_date");
				String updateDate = (String) domain.get("update_date");
				String country = (String) domain.get("country");
				String isDead = (String) domain.get("isDead");

				Domain d = new Domain(name, createDate, updateDate, country, isDead);
				downloadedDomains.add(d);

				}catch (Exception e)
				{
					System.out.println("ERRORE GENERICO.");
					System.out.println("MESSAGGIO: " + e.getMessage());
					System.out.println("CAUSA: " + e.getCause());
				}
			}

	}

}
