package it.univpm.ProgettoOOP.service;

import java.util.Vector;

import it.univpm.ProgettoOOP.model.Domain;

public class DomainServiceImpl {

	private Vector<Domain> domainsVec= new Vector<Domain>();
	
	public DomainServiceImpl(Vector<Domain> domainsVec) {
		this.domainsVec= domainsVec;
	}
	
	public Vector<Domain> getDomainsVec(){
		return domainsVec;
	}
}
