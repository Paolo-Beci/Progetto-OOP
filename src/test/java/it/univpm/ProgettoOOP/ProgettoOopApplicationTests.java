package it.univpm.ProgettoOOP;

import it.univpm.ProgettoOOP.filters.Filter;
import it.univpm.ProgettoOOP.filters.FilterCountry;
import it.univpm.ProgettoOOP.model.Domain;
import it.univpm.ProgettoOOP.service.DomainService;
import it.univpm.ProgettoOOP.service.DownloadDomains;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Vector;

/**
 * <b>Classe</b> per il testing dell'applicazione.
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 */
@SpringBootTest
@Timeout(30)
class ProgettoOopApplicationTests {

	private Vector<Domain> domains = null;
	private Vector<Domain> domainsFiltered = null;
	DownloadDomains d = new DownloadDomains();

	static final String url = "https://api.domainsdb.info/v1/domains/search?page=10&domain=facebook&zone=us&limit=50";

	Domain d1 = new Domain("facebook.com","","","US","");
	Domain d2 = new Domain("facebook.it","","","US","");
	Domain d3 = new Domain("facebook.uk","","","US","");

	/**
	 * Inizializza i componenti prima di ogni Test.
	 * @throws Exception Possibile Eccezione.
	 */
	@BeforeEach
	void setUp() throws Exception
	{
		domains= new Vector<>();
		domains = d.download(url);

		domainsFiltered= new Vector<>();
		
		// Test 2
		domainsFiltered.add(d1);
		domainsFiltered.add(d2);
		domainsFiltered.add(d3);
	}

	/**
	 * Distrugge i componenti dopo ogni Test.
	 * @throws Exception Possibile Eccezione.
	 */
	@AfterEach
	void tearDown() throws Exception
	{
	}

	/**
	 * Verifica che il vettore dei domini non è null
	 */
	/*
	@Test
	@DisplayName("Test 1: Vettore dei domini non è null")
	void domainsNotNull() {
		assertNotNull(domains);
	}
	 */

	/**
	 * Verifica che i domini filtrati sono americani
	 */
	@Test
	@DisplayName("Test 2: filtro domini tutti US")
	void domainsOfUs() {
		for(Domain d : domainsFiltered)
			assertEquals(d.getCountry(),"US");
	}
	/**
	 * Verifica NoDataException
	 */
	/*
	@Test
	@DisplayName("Test 3: ......")
	void nomeTest3() {

	}
	 */
}
