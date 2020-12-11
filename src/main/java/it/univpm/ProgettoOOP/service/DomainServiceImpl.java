package it.univpm.ProgettoOOP.service;

import java.util.Vector;

import org.springframework.stereotype.Service;

import it.univpm.ProgettoOOP.model.Domain;

@Service
public class DomainServiceImpl implements DomainService{

	private Vector<Domain> domains= new Vector<Domain>();
	
	public DomainServiceImpl() {
		DownloadDomains d= new DownloadDomains();
		this.domains = d.Download();
	}
	
	public Vector<Domain> getDomains(){
		return this.domains;		
	}
}
