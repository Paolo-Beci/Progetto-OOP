package it.univpm.ProgettoOOP.controller;

import it.univpm.ProgettoOOP.exception.BodyIsEmptyException;
import org.json.simple.JSONObject;
import it.univpm.ProgettoOOP.service.DownloadDomains;

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
 * @throws BodyIsEmptyException Eccezione personalizzata la gestione del body vuoto nella richiesta di post
 */
@RestController
public class Controller{

	public String url;
	@Autowired
	DomainService d;

	/**
	 * Rotta per visualizzare i domini commerciali contenenti la parola chiave "facebook" (limite 50)
	 * @return il Vector contenente i domain
	 */
	@GetMapping("/domains")
		public ResponseEntity<Object> getDomains(@RequestParam(required = true) String domain, @RequestParam(required = true) String zone){
			url = "https://api.domainsdb.info/v1/domains/search?page=10&domain=" + domain + "&zone=" + zone + "&limit=50";
			return new ResponseEntity<>(d.getDomains(url), HttpStatus.OK);
		}


	/**
	 * Rotta per visualizzare le statistiche elaborate sui domini forniti dall'API
	 * @return Statistiche sui domini
	 */
	@GetMapping("/stats")
		public ResponseEntity<Object> getStats(@RequestParam(required = true) String domain, @RequestParam(required = true) String zone){
			url = "https://api.domainsdb.info/v1/domains/search?page=10&domain=" + domain + "&zone=" + zone + "&limit=50";
			return new ResponseEntity<>(d.getStats(url), HttpStatus.OK); // return statistiche  formato:(JSONObject)
		}

	/**
	 * Rotta per visualizzare i domini o le informazioni filtrate
	 * @return Filtri sui domini
	 */
	@PostMapping("/filter")
	public Object getFilteredDomains (@RequestBody JSONObject bodyFilter) {
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
	public <Object> String Error()
	{
		return "IL BODY DELLA CHIAMATA POST NON CONTIENE NESSUN FILTRO";
	}
}