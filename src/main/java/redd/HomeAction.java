/**
 * 
 */
package redd;

import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redd.config.Config;
import redd.dao.PostgisDAO;
import redd.model.CoverageStats;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author jose
 * 
 */
public class HomeAction  extends ActionSupport {

	static Config config = new Config();
	PostgisDAO dao;
	DataSource ds;
	private String geojson;
	private String distrito;
	private CoverageStats stats1986 = new CoverageStats();
	private CoverageStats stats2000 = new CoverageStats();
	private String results;
	
	public String verDistrito() {
		stats1986 = dao.getCoverageByDistrict("ACAPULCO", 1986);
		return "SUCCESS";
	}	
	
	static final Logger logger = Logger.getLogger(HomeAction.class);
	
	public String compare() {
		stats1986 = dao.getCoverageStatsByPolygon(geojson, 1986);
		stats2000 = dao.getCoverageStatsByPolygon(geojson, 2000);
		return "SUCCESS";
	}
	
	public String grab() {
   	 ApplicationContext context = 
   		new ClassPathXmlApplicationContext("datasource.xml");
   	 
   	 ds = (DataSource) context.getBean("dataSource");
   	 dao = (PostgisDAO) context.getBean("postgisDAO");
   	 System.out.println(geojson);
		stats1986 = dao.getCoverageStatsByPolygon(geojson, 1986);
		return "SUCCESS";
	}		
	

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public CoverageStats getStats2000() {
		return stats2000;
	}

	public void setStats2000(CoverageStats stats2000) {
		this.stats2000 = stats2000;
	}

	public CoverageStats getStats1986() {
		return stats1986;
	}

	public void setStats1986(CoverageStats stats1986) {
		this.stats1986 = stats1986;
	}
	
	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public String execute() {
		System.out.println("Hola Mundo");
		return "SUCCESS";
	}


	
	public String getGeojson() {
		return geojson;
	}

	public void setGeojson(String geojson) {
		this.geojson = geojson;
	}

	/**
	 * @return the distrito
	 */
	public String getDistrito() {
		return distrito;
	}

	/**
	 * @param distrito the distrito to set
	 */
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}	

}
