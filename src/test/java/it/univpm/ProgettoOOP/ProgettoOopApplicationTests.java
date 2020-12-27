package it.univpm.ProgettoOOP;

import it.univpm.ProgettoOOP.exception.NoDataException;
import it.univpm.ProgettoOOP.filters.Filter;
import it.univpm.ProgettoOOP.filters.FilterCountry;
import it.univpm.ProgettoOOP.model.Domain;
import it.univpm.ProgettoOOP.service.DomainService;
import it.univpm.ProgettoOOP.service.DomainServiceImpl;
import it.univpm.ProgettoOOP.service.DownloadDomains;
import it.univpm.ProgettoOOP.stats.Quantity;
import it.univpm.ProgettoOOP.stats.Stats;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Vector;

/**
 * <b>Classe</b> per il testing dell'applicazione.
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 * @version 1.0
 */
@SpringBootTest
class ProgettoOopApplicationTests {
	
	/**
	 * Vettore che conterra' i domini.
	 */
	private List<Domain> domains = null;
	
	/**
	 * Vettore che conterra' i domini filtrati.
	 */
	private List<Domain> domainsFiltered = null;
	
	/**
	 * Url che consente il collegameto alle API.
	 */
	static final String url1 = "https://api.domainsdb.info/v1/domains/search?page=10&domain=facebook&zone=us&limit=50";
	
	/**
	 * Istanza della classe Stats.
	 */
	Stats q = null;
	
	/**
	 * Url che consente il collegameto alle API.
	 */
	DomainService ds = new DomainServiceImpl();
	
	/**
	 * Url per non ottenere dati dalle API.
	 */
	static final String url2 = "https://api.domainsdb.info/v1/domains/search?page=10&domain=Morrone&zone=uk&limit=50";
	
	/**
	 * <b>Metodo</b> che inizializza i componenti prima di ogni Test.
	 * @see DownloadDomains#download(String)
	 * @see Filter#toFilter(java.util.List, java.util.List)
	 * @see Quantity#calculateStat()
	 * @throws Exception Possibile Eccezione
	 */
	@BeforeEach
	void setUp() throws Exception
	{   
		DownloadDomains d = new DownloadDomains();
		
		Filter f = new FilterCountry("US");
		
		//Dati relativi al Test1
		domains = new Vector<>();
		domains = d.download(url1);

		//Dati relativi al Test2
		domainsFiltered = new Vector<>();
	    f.toFilter(domains, domainsFiltered);
	    
	    //Dati relativi al Test3
	    q = new Quantity(domains);
	    q.calculateStat(); 
	}

	/**
	 * Distrugge i componenti dopo ogni Test.
	 * @throws Exception Possibile Eccezione.
	 */
	@AfterEach
	void tearDown() throws Exception{}

	/**
	 * <b>Test</b> che verifica se il vettore domains non è null.
	 */
	@Test
	@DisplayName("Test 1: Vettore dei domini non è null")
	void test1() {
		assertNotNull(domains);
	}

	/**
	 * <b>Test</b> che verifica se il filtro FilterCountry viene correttamente applicato.
	 * @see Domain#getCountry()
	 */
	@Test
	@DisplayName("Test 2: Corretto funzionamento FilterCountry")
	void test2() {
		for(Domain d : domainsFiltered)
			assertEquals(d.getCountry(),"US");
	}
	
	/**
	 * <b>Test</b> che verifica se la statistica Quantity viene calcolata correttamente.
	 * @see Quantity#getInt()
	 */
	@Test
	@DisplayName("Test 3: Corretto funzionamento statistica Quantity")
	void test3() {
		assertTrue(domains.size() == q.getInt());
	}
	
	/**
	 * <b>Test</b> che verifica se l'eccezione NoDataException viene lanciata correttamente.
	 * @see DomainServiceImpl#getDomains(String)
	 */
	@Test
	@DisplayName("Test 4: Lancio eccezione NoDataException")
	void test4() {
		try {
			domains.clear();
			domains = ds.getDomains(url2);
			fail("Eccezione non generata");
		} catch (NoDataException e) {
			e.printStackTrace();
		}
	}
	
}
