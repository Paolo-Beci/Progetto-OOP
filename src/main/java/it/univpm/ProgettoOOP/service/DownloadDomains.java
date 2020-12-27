package it.univpm.ProgettoOOP.service;

import java.io.*;
import java.net.URL;
import java.util.List;
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
 * <b>Classe</b> che gestisce il download e i relativi dati dei domini forniti dall'API
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 * @version 1.0
 */
public class DownloadDomains {

	/**
	 * <p>
	 * <b>Metodo</b> che effettua il download dei domini dall'api e che li elabora in formato stringa per essere utilizzati
	 * dalle classi di filtri e statistiche.</p>
	 * @param url è l'URL personalizzato della <b>API</b>
	 * @return Vettore downloadedDomains
	 * @see DownloadDomains#buildDomains(JSONObject, List)
	 * @throws NoDataException Eccezione personalizzata che carica il database da locale nel caso di mancata connessione
	 */
	public List<Domain> download(String url) throws NoDataException{
		List<Domain> downloadedDomains= new Vector<>();
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
				buildDomains(stats, downloadedDomains);
			}
	        in.close();
	    }
		catch (FileNotFoundException e) {
			System.out.println("ERRORE: PROBLEMI CON LA GESTIONE DEI FILE");
			System.out.println("MESSAGGIO: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
			return null;
	    }
		catch (IOException e) {
			System.out.println("ERRORE: CONNESSIONE ALL'API NON RIUSCITA...PROCEDO CON IL DATABASE SALVATO IN LOCALE");
			System.out.println("MESSAGGIO: " + e.getMessage());
			System.out.println("CAUSA: L'URL E' CORROTTO..." + e.getCause());

			try{
				// Carico il database predefinito dal file BackupData.txt
				String nome_file = "resources\\BackupData.txt";
				Scanner in = new Scanner(new FileReader(nome_file));

				String inputLine;
				while (in.hasNext()) {
					inputLine = in.nextLine();
					JSONObject stats = (JSONObject) parser.parse(inputLine);
					buildDomains(stats, downloadedDomains);
					System.out.println(downloadedDomains);
				}
			}catch(FileNotFoundException | ParseException f) {
				System.out.println("ERRORE: OPERAZIONE INTERROTTA NELLA GESTIONE DEL FILE DA LOCAL RESOURCES");
				System.out.println("MESSAGGI: " + f.getMessage());
				System.out.println("CAUSA: " + f.getCause());
			}
	    }
		catch (ParseException e) {
			System.out.println("ERRORE: ParseException");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("ERRORE GENERICO.");
			System.out.println("MESSAGGIO: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
		}
		return downloadedDomains;
	}

	/**
	 * <b>Metodo</b> che effettua il get dei vari argomenti contenuti in ogni oggetto di stats
	 * dalle classi di filtri e statistiche.
	 * @param stats contiene l'oggetto da analizzare e smistare
	 * @param downloadedDomains è un vettore di oggetti Domain
	 * @see DownloadDomains#download(String)
	 */
	private void buildDomains(JSONObject stats, List<Domain> downloadedDomains) {
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
