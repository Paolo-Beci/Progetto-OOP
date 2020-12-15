package it.univpm.ProgettoOOP.service;

import java.util.Vector;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.ProgettoOOP.filters.Filter;
import it.univpm.ProgettoOOP.filters.FilterName;
import it.univpm.ProgettoOOP.model.Domain;

/**
 * ...
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 */

@Service
public class DomainServiceImpl implements DomainService {

	private Vector<Domain> domains= new Vector<Domain>();
	private Vector<Domain> filteredDomains= new Vector<Domain>();

	public DomainServiceImpl() {
		DownloadDomains d= new DownloadDomains();
		this.domains = d.Download();
	}
	
	public Vector<Domain> getDomains(){
		return this.domains;		
	}

	public Vector<Domain> getFilteredDomains(JSONObject bodyFilter) {

		Vector<Domain> dominiDaFiltrare= new Vector<Domain>();
		DownloadDomains d= new DownloadDomains();
		dominiDaFiltrare= d.Download();
		
		this.filteredDomains.clear();
		Filter f0= new Filter();
		
		f0.parsingFilters(bodyFilter);
		
		System.out.println("\n################## FILTRI");
		for(Filter f : f0.getFilters()) {
			System.out.println(f);
		}

		for(Filter f : f0.getFilters()) {
			f.filtra(dominiDaFiltrare);
		}
		
		filteredDomains= dominiDaFiltrare;
		return this.filteredDomains;
	}

}
