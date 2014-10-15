/**
 * 
 */
package redd;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redd.config.Config;
import redd.constants.Constants;
import redd.dao.AccessDAO;
import redd.dao.PostgisDAO;
import redd.model.CoverageStats;
import redd.model.Fact;
import redd.model.GeographicLayerPolygon;
import redd.model.LandCover;
import redd.model.LandCoverCategory;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.config.entities.Parameterizable;

/**
 * @author jose
 * 
 */
public class HomeAction extends ActionSupport  implements Parameterizable {

	private static final long serialVersionUID = 3320964248147576677L;

	static final Logger logger = Logger.getLogger(HomeAction.class);
	
	private Map<String, String> params = new HashMap<String, String>();

	// DAOs
	AccessDAO accessDAO;
	PostgisDAO postgisDAO;

	// for navigating through the Provincia/Canton/Distrito select boxes
	String id;

	// for filling up a select box with all the Land Covers available
	List<LandCover> landCovers;
	
	// return all the possible land cover categories
	List<LandCoverCategory> landCoverCategories;

	// for getting stats for a geographic polygon (province, district, conserv.
	// area, etc)
	String polygonId;
	String coverageId;

	// for comparing two years
	String preCoverageId;
	String postCoverageId;

	// stats object returned to the view
	// This is the only object returned by all queries concerning stats
	CoverageStats stats;

	// lists for holding geographic polygons (province, district, conserv. area,
	// etc)
	private List<GeographicLayerPolygon> geoPolygons = new ArrayList<GeographicLayerPolygon>();

	private GeographicLayerPolygon geoPolygon = new GeographicLayerPolygon();

	/**
	 * @return the geoPolygon
	 */
	public GeographicLayerPolygon getGeoPolygon() {
		return geoPolygon;
	}

	public HomeAction() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"datasource.xml");
		accessDAO = (AccessDAO) context.getBean("accessDAO");
		postgisDAO = (PostgisDAO) context.getBean("postgisDAO");
		geoPolygons = accessDAO
				.getGeographicLayerPolygons(Constants.PolygonCategory.PROVINCIA
						.getValue());
	}

	/**
	 * @return the stats
	 */
	public CoverageStats getStats() {
		return stats;
	}

	/**
	 * @return the landCovers
	 */
	public List<LandCover> getLandCovers() {
		return accessDAO.getLandCovers();
	}

	/**
	 * @return the geoPolygons
	 */
	public List<GeographicLayerPolygon> getGeoPolygons() {
		return geoPolygons;
	}

	/**
	 * @param provinciaId
	 *            the provinciaId to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param polygonId
	 *            the polygonId to set
	 */
	public void setPolygonId(String polygonId) {
		this.polygonId = polygonId;
	}

	/**
	 * @param coverageId
	 *            the coverageId to set
	 */
	public void setCoverageId(String coverageId) {
		this.coverageId = coverageId;
	}

	/**
	 * @param preCoverageId
	 *            the preCoverageId to set
	 */
	public void setPreCoverageId(String preCoverageId) {
		this.preCoverageId = preCoverageId;
	}

	/**
	 * @param postCoverageId
	 *            the postCoverageId to set
	 */
	public void setPostCoverageId(String postCoverageId) {
		this.postCoverageId = postCoverageId;
	}

	/**
	 * Generates the stats for particular {@link GeographicLayerPolygon}
	 * 
	 * @return
	 */
	public String grabStats() {
		List<Fact> facts = accessDAO.getFacts(Integer.parseInt(coverageId),
				Integer.parseInt(polygonId));

		// convert from list of Facts to a CoverageStats object
		// TODO: move this to a proper method or transformer class

		stats = new CoverageStats();
		for (Fact fact : facts) {
			if (fact.getLandCoverCategoryId() == 1) {
				stats.setBosque(returnKm2(fact.getArea()));
			} else if (fact.getLandCoverCategoryId() == 2) {
				stats.setCuerpoDeAgua(returnKm2(fact.getArea()));
			} else if (fact.getLandCoverCategoryId() == 3) {
				stats.setOtrosCultivos(returnKm2(fact.getArea()));
			} else if (fact.getLandCoverCategoryId() == 4) {
				stats.setHerbazal(returnKm2(fact.getArea()));
			} else if (fact.getLandCoverCategoryId() == 5) {
				stats.setInfraestructura(returnKm2(fact.getArea()));
			} else if (fact.getLandCoverCategoryId() == 6) {
				stats.setManglar(returnKm2(fact.getArea()));
			} else if (fact.getLandCoverCategoryId() == 7) {
				stats.setTerrenoDescubierto(returnKm2(fact.getArea()));
			} else if (fact.getLandCoverCategoryId() == 8) {
				stats.setVegetacionAnegada(returnKm2(fact.getArea()));
			} else if (fact.getLandCoverCategoryId() == 9) {
				stats.setNubes(returnKm2(fact.getArea()));
			} else if (fact.getLandCoverCategoryId() == 10) {
				stats.setSombras(returnKm2(fact.getArea()));
			} else if (fact.getLandCoverCategoryId() == 11) {
				stats.setSabana(returnKm2(fact.getArea()));
			} else if (fact.getLandCoverCategoryId() == 12) {
				stats.setParamo(returnKm2(fact.getArea()));
			} else if (fact.getLandCoverCategoryId() == 13) {
				stats.setBanano(returnKm2(fact.getArea()));
			} else if (fact.getLandCoverCategoryId() == 14) {
				stats.setPalmaAceitera(returnKm2(fact.getArea()));
			} else if (fact.getLandCoverCategoryId() == 15) {
				stats.setPina(returnKm2(fact.getArea()));
			}
		}
		return "SUCCESS";
	}

	private double returnKm2(double area) {
		if (area > 0) {
			return new BigDecimal(area / 1000000).setScale(2, RoundingMode.HALF_UP).doubleValue();
		}
		return area;
	}

	public String grab() {
		LandCover landCover = accessDAO.getLandCoverById(Integer
				.parseInt(coverageId));
		String tableName = landCover.getTableName();
		stats = postgisDAO.getCoverageStatsByPolygon(geojson, tableName);
		return "SUCCESS";
	}

	public String displayPolygon() {
		geoPolygon = accessDAO.getGeographicLayerPolygonById(
				Integer.parseInt(polygonId), true, 3857);
		return "SUCCESS";
	}

	public String compare() {
		CoverageStats preStats;
		CoverageStats postStats;
		stats = new CoverageStats();
		LandCover preLandCover = accessDAO.getLandCoverById(Integer
				.parseInt(preCoverageId));
		LandCover postLandCover = accessDAO.getLandCoverById(Integer
				.parseInt(postCoverageId));

		preStats = postgisDAO.getCoverageStatsByPolygon(geojson,
				preLandCover.getTableName());
		postStats = postgisDAO.getCoverageStatsByPolygon(geojson,
				postLandCover.getTableName());

		System.out.println(preStats);
		System.out.println(postStats);

		// compare values from both years
		stats.setBanano(postStats.getBanano() - preStats.getBanano());
		stats.setBosque(postStats.getBosque() - preStats.getBosque());
		stats.setCuerpoDeAgua(postStats.getCuerpoDeAgua()
				- preStats.getCuerpoDeAgua());
		stats.setHerbazal(postStats.getHerbazal() - preStats.getHerbazal());
		stats.setInfraestructura(postStats.getInfraestructura()
				- preStats.getInfraestructura());
		stats.setManglar(postStats.getManglar() - preStats.getManglar());
		stats.setNubes(postStats.getNubes() - preStats.getNubes());
		stats.setOtrosCultivos(postStats.getOtrosCultivos()
				- preStats.getOtrosCultivos());
		stats.setPalmaAceitera(postStats.getPalmaAceitera()
				- preStats.getPalmaAceitera());
		stats.setParamo(postStats.getParamo() - preStats.getParamo());
		stats.setPina(postStats.getPina() - preStats.getPina());
		stats.setSabana(postStats.getSabana() - preStats.getSabana());
		stats.setSombras(postStats.getSombras() - preStats.getSombras());
		stats.setTerrenoDescubierto(postStats.getTerrenoDescubierto()
				- preStats.getTerrenoDescubierto());
		stats.setVegetacionAnegada(postStats.getVegetacionAnegada()
				- preStats.getVegetacionAnegada());

		return "SUCCESS";
	}

	/**
	 * Return a list of all cantones given its parent id (provinciaId)
	 * 
	 * @return the cantones
	 */
	public String selectGeoPolygons() {
		geoPolygons = accessDAO.getGeographicLayerPolygons(
				Constants.PolygonCategory.valueOf(params.get("geoPolygonFilter")).getValue(),
				Integer.parseInt(id));
		return "SUCCESS";
	}

	static Config config = new Config();

	private String geojson;

	// private CoverageStats stats1986 = new CoverageStats();
	// private CoverageStats stats2000 = new CoverageStats();

	/*
	 * public CoverageStats getStats2000() { return stats2000; }
	 * 
	 * public CoverageStats getStats1986() { return stats1986; }
	 */

	public String execute() {
		return "SUCCESS";
	}

	public void setGeojson(String geojson) {
		this.geojson = geojson;
	}

	/**
	 * @return the landCoverCategories
	 */
	public List<LandCoverCategory> getLandCoverCategories() {
		return accessDAO.getLandCoverCategories();
	}

    @Override
    public void addParam(String key, String value) {
        this.params.put(key, value);
    }
 
    @Override
    public Map<String, String> getParams() {
        return this.params;
    }
 
    @Override
    public void setParams(Map<String, String> params) {
        this.params = params;
    }
	
	
}
