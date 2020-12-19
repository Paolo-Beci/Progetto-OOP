package it.univpm.ProgettoOOP.controller;

import it.univpm.ProgettoOOP.exception.BodyIsEmptyException;
import it.univpm.ProgettoOOP.exception.NoDataException;
import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.univpm.ProgettoOOP.service.*;
/**
 * Rappresenta la classe che gestisce tutte le chiamate al Server
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 * @version 1.0
 * @throws BodyIsEmptyException Eccezione personalizzata la gestione del body vuoto nella richiesta di post
 */
@RestController
public class Controller{

	/**
	 * Stringa che conterrà l'URL delle API di facebook(default) oppure di qualsiasi altro sito a scelta.
	 */
	public String url;
	
	
	@Autowired
	DomainService d;

	/**
	 * <b>Rotta</b> per visualizzare i domini commerciali contenenti la parola chiave "facebook"
	 * @param domain Parola chiave della quale l'utente vuole ottenere i domini.
	 * @param zone Zona dalla quale l'utente vuole ottenere i domini.
	 * @return Vettore di domini
	 * @see DomainServiceImpl#getDomains(String) 
	 */
	@GetMapping("/domains")
		public ResponseEntity<Object> getDomains(@RequestParam(name = "domain", defaultValue = "facebook") String domain,
												 	@RequestParam(name = "zone", defaultValue = "com") String zone)
		{
			domain.toLowerCase();zone.toLowerCase();
				url = "https://api.domainsdb.info/v1/domains/search?page=10&domain=" + domain + "&zone=" + zone + "&limit=";
			return new ResponseEntity<>(d.getDomains(url), HttpStatus.OK);
		}


	/**
	 * <b>Rotta</b> per visualizzare le statistiche elaborate sui domini forniti dall'API.
	 * @param domain Dominio sul quale eseguire statistica.
	 * @param zone Zona sulla quale eseguire statistica.
	 * @return <code>JSONObject</code> contenente la statistica calcolata
	 * @see DomainServiceImpl#getStats(String) 
	 */
	@GetMapping("/stats")
		public ResponseEntity<Object> getStats(@RequestParam(name = "domain", defaultValue = "facebook") String domain,
											   		@RequestParam(name = "zone", defaultValue = "com") String zone)
		{
			domain.toLowerCase();zone.toLowerCase();
			url = "https://api.domainsdb.info/v1/domains/search?page=10&domain=" + domain + "&zone=" + zone + "&limit=50";
			return new ResponseEntity<>(d.getStats(url), HttpStatus.OK); // return statistiche  formato:(JSONObject)
		}

	/**
	 * <b>Rotta</b> per visualizzare le informazioni relative ai domini filtrati.
	 * @return Filtri sui domini
	 * @see DomainServiceImpl#getFilteredDomains(JSONObject, String) 
	 */
	@PostMapping("/filter")
	public Object getFilteredDomains (@RequestBody JSONObject bodyFilter, @RequestParam(name = "domain", defaultValue = "facebook") String domain,
	   		@RequestParam(name = "zone", defaultValue = "com") String zone) {

		domain.toLowerCase();zone.toLowerCase();
		url = "https://api.domainsdb.info/v1/domains/search?page=10&domain=" + domain + "&zone=" + zone + "&limit=50";
		
		try {
			if(bodyFilter.isEmpty())
				throw new BodyIsEmptyException();
			return new ResponseEntity<>(d.getFilteredDomains(bodyFilter, url), HttpStatus.OK); // return filtri  formato:(JSONObject) ?

		} catch (BodyIsEmptyException e) {
			System.out.println("MESSAGGIO: " + e.getMessage());
			System.out.println("CAUSA: " + e.getCause());
			return Error();
		}
	}
	
	/**
	 * 
	 * @param <Object>
	 * @return messaggio errore
	 */
	public <Object> String Error()
	{
		return "IL BODY DELLA CHIAMATA POST NON CONTIENE NESSUN FILTRO";
	}
}