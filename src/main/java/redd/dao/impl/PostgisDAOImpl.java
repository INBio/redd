/**
 * 
 */
package redd.dao.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import redd.dao.PostgisDAO;
import redd.model.CoverageStats;
import redd.model.GeoRow;
import redd.model.mapper.CoverageStatsMapper;

/**
 * @author jose
 * 
 */
public class PostgisDAOImpl extends JdbcDaoSupport implements PostgisDAO {

	static final Logger logger = Logger.getLogger(PostgisDAO.class);

	@SuppressWarnings("unchecked")
	@Override
	public CoverageStats getCoverageStatsByPolygon(String polygon, String tableName) {
		//final Config config = new Config();
		CoverageStats stats = new CoverageStats();

		// transform the polygon from one projection to the other
		String transformedPolygon = transformGeometry(polygon, 3857, 32617);
		
		logger.debug("Transformed polygon: " + transformedPolygon);
				
		// first extract all records that intersect
		// these records will include a true/false flag to determine if the polygon
		// is completely inside the user-selected polygon or whether it only shares a portion
		// true: completely inside
		// false: shares a portion only
		String strQuery = "SELECT land_cover_category_id, geom, km2, ST_Contains(ST_GeomFromText(ST_AsText(?),32617), geom) FROM " + tableName + " WHERE ST_Intersects(geom, ST_GeomFromText(ST_AsText(?),32617)) ";
		List<GeoRow> rows = (List<GeoRow>) getJdbcTemplate().query(strQuery,
				new Object[] { transformedPolygon, transformedPolygon }, new CoverageStatsMapper());		
		
		// list that holds all geometries that share a portion with the user-selected polygon
		// this will serve to iterate over remaining geometries and calculate the shared area.
		List<GeoRow> sharedGeometries = new ArrayList<GeoRow>();
		
		List<GeoRow> containedGeometries = new ArrayList<GeoRow>();
		
		// containedGeometries will contain all polygons residing on the user-selected polygon
		for(GeoRow row: rows) {
			if(!row.isInside()) {
				sharedGeometries.add(row);
			}
			else {
				containedGeometries.add(row);
			}
		}

		for(GeoRow geom: sharedGeometries) {
			String query = "SELECT ST_Area(ST_Intersection(?, ST_GeomFromText(ST_AsText(?),32617)))";
			geom.setKm2(getJdbcTemplate().queryForObject(query, new Object[] { geom.getGeom(), transformedPolygon }, Integer.class));
		}

		// join both lists into one
		List<GeoRow> consolidatedList = new ArrayList<GeoRow>();
		consolidatedList.addAll(sharedGeometries);
		consolidatedList.addAll(containedGeometries);

		stats.setBosque(countCategory(consolidatedList, 1));
		stats.setCuerpoDeAgua(countCategory(consolidatedList, 2));
		stats.setOtrosCultivos(countCategory(consolidatedList, 3));
		stats.setHerbazal(countCategory(consolidatedList, 4));
		stats.setInfraestructura(countCategory(consolidatedList, 5));
		stats.setManglar(countCategory(consolidatedList, 6));		
		stats.setTerrenoDescubierto(countCategory(consolidatedList, 7));		
		stats.setVegetacionAnegada(countCategory(consolidatedList, 8));
		stats.setNubes(countCategory(consolidatedList, 9));
		stats.setSombras(countCategory(consolidatedList, 10));
		stats.setSabana(countCategory(consolidatedList, 11));
		stats.setParamo(countCategory(consolidatedList, 12));
		stats.setBanano(countCategory(consolidatedList, 13));
		stats.setPalmaAceitera(countCategory(consolidatedList, 14));
		stats.setPina(countCategory(consolidatedList, 15));

		return stats;
	}

	public double countCategory(List<GeoRow> fullGeoList, int criteria) {
		double km2 = 0;
		for (GeoRow geoRow : fullGeoList) {
			if (geoRow.getCoverageType() == criteria) {
				km2 += geoRow.getKm2();
			}
		}
		
		double sumToKm2 = km2/1000000.0;
		

		return Double.parseDouble(new DecimalFormat("##.####").format(sumToKm2));
	}


	/**
	 * Transform on polygon from one projection into another one.
	 * 
	 * @param polygon polygon to transform
	 * @param originProj origin projection
	 * @param destinationProj destination projection
	 * @return a polygon with the new projection
	 */
	private String transformGeometry(String polygon, int originProj,
			int destinationProj) {
		String sql = "SELECT ST_Transform(ST_GeomFromText(?, ?), ?)";
		String newPolygon = (String) getJdbcTemplate().queryForObject(sql,
				new Object[] { polygon, originProj, destinationProj },
				String.class);

			return newPolygon;
	}
}
