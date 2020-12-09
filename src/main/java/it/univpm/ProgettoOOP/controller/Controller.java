package it.univpm.ProgettoOOP.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@RequestMapping(value= "/domains", method= RequestMethod.GET) //specifico la richiesta da fare con Postman
	public ResponseEntity<Object> getDomains(){
		return null;
	}
}
