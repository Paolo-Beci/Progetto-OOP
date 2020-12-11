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
	
	@Autowired
	DomainService d;
	
	/**
	 * Rotta per visualizzare i domini commerciali contenenti la parola chiave "facebook" (limite 50)
	 * @return il Vector di domain
	 */
	@RequestMapping(value= "/domains", method= RequestMethod.GET) //specifico la richiesta da fare con Postman
	public ResponseEntity<Object> getDomains(){
		return new ResponseEntity<>(d.getDomains(), HttpStatus.OK);
	}
}
