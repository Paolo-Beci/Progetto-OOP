package it.univpm.ProgettoOOP.controller;

import it.univpm.ProgettoOOP.exception.BodyIsEmptyException;
import it.univpm.ProgettoOOP.exception.NoDataException;
import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.univpm.ProgettoOOP.service.*;
import org.springframework.web.bind.annotation.*;

/**
 * <b>Classe</b> controller che gestisce tutte le chiamate al Server
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 * @version 1.0
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
												 	@RequestParam(name = "zone", defaultValue = "com") String zone) throws NoDataException
		{
			domain= domain.toLowerCase();zone= zone.toLowerCase();
				url = "https://api.domainsdb.info/v1/domains/search?page=10&domain=" + domain + "&zone=" + zone + "&limit=50";
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
											   		@RequestParam(name = "zone", defaultValue = "com") String zone) throws NoDataException
		{
			domain= domain.toLowerCase();zone= zone.toLowerCase();
			url = "https://api.domainsdb.info/v1/domains/search?page=10&domain=" + domain + "&zone=" + zone + "&limit=50";
				return new ResponseEntity<>(d.getStats(url), HttpStatus.OK);
		}

	/**
	 * <b>Rotta</b> per visualizzare i domini filtrati.
	 * @param bodyFilter contiene i filtri inseriti dall'utente in fase di chiamata
	 * @param domain Dominio sul quale eseguire statistica.
	 * @param zone Zona sulla quale eseguire statistica.
	 * @return vettore di domini filtrati
	 * @see DomainServiceImpl#getFilteredDomains(JSONObject, String)
	 */
	@PostMapping("/filter")
	public Object getFilteredDomains (@RequestBody JSONObject bodyFilter, @RequestParam(name = "domain", defaultValue = "facebook") String domain,
									  @RequestParam(name = "zone", defaultValue = "com") String zone) throws BodyIsEmptyException{

		domain= domain.toLowerCase();zone= zone.toLowerCase();
		url = "https://api.domainsdb.info/v1/domains/search?page=10&domain=" + domain + "&zone=" + zone + "&limit=50";
			if(bodyFilter.isEmpty())
				throw new BodyIsEmptyException();
			return new ResponseEntity<>(d.getFilteredDomains(bodyFilter, url), HttpStatus.OK);
	}

	/**
	 * Metodo per gestire la NoDataException
	 * @param e eccezione da gestire
	 * @return errore cattiva richiesta
	 */
	@ExceptionHandler(NoDataException.class)
	public ResponseEntity<Object> handleIOException(NoDataException e) {
		return new ResponseEntity<>(DomainError(), HttpStatus.BAD_REQUEST);
	}
	/**
	 * @return messaggio errore richiesta non valida
	 */
	public <Object> String DomainError()
	{
		return "I CAMPI DELLA RICHIESTA NON PRODUCONO ALCUN RISULTATO...\n Riprova con diversi campi domain e zone!";
	}

	/**
	 * Metodo per gestire la BodyIsEmptyException
	 * @param e eccezione da gestire
	 * @return errore body vuoto (BodyError)
	 */
	@ExceptionHandler(BodyIsEmptyException.class)
	public ResponseEntity<Object> handleIOException(BodyIsEmptyException e) {
		return new ResponseEntity<>(BodyError(), HttpStatus.BAD_REQUEST);
	}
	/**
	 * @return messaggio errore body richiesta vuoto
	 */
	public <Object> String BodyError()
	{
		return "IL BODY DELLA CHIAMATA POST NON CONTIENE NESSUN FILTRO";
	}
}