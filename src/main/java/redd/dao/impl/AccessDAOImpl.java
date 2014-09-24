/**
 * 
 */
package redd.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import redd.dao.AccessDAO;
import redd.model.Fact;
import redd.model.GeographicLayer;
import redd.model.GeographicLayerPolygon;
import redd.model.LandCover;
import redd.model.LandCoverCategory;
import redd.model.mapper.FactMapper;
import redd.model.mapper.GeographicLayerPolygonMapper;
import redd.model.mapper.LandCoverCategoryMapper;
import redd.model.mapper.LandCoverMapper;

/**
 * @author jose
 * 
 */
public class AccessDAOImpl extends JdbcDaoSupport implements AccessDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see redd.dao.AccessDAO#getFacts()
	 */
	@Override
	public List<Fact> getFacts() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see redd.dao.AccessDAO#getGeographicLayers()
	 */
	@Override
	public List<GeographicLayer> getGeographicLayers() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see redd.dao.AccessDAO#getGeographicLayerPolygons()
	 */
	@Override
	public List<GeographicLayerPolygon> getGeographicLayerPolygons() {
		String strQuery = "SELECT id, name, geom, geographic_layer_id from geographic_layer_polygon";
		List<GeographicLayerPolygon> rows = (List<GeographicLayerPolygon>) getJdbcTemplate()
				.query(strQuery, new GeographicLayerPolygonMapper());
		return rows;
	}

	@Override
	public List<GeographicLayerPolygon> getGeographicLayerPolygons(int category) {
		String strQuery = "SELECT id, name, geom, geographic_layer_id from geographic_layer_polygon WHERE geographic_layer_id=?";
		List<GeographicLayerPolygon> rows = (List<GeographicLayerPolygon>) getJdbcTemplate()
				.query(strQuery, new Object[] { category },
						new GeographicLayerPolygonMapper());
		return rows;
	}

	@Override
	public List<GeographicLayerPolygon> getGeographicLayerPolygons(
			int category, int parentId) {
		String strQuery = "SELECT id, name, geom, geographic_layer_id from geographic_layer_polygon WHERE geographic_layer_id=? AND parent_id=?";
		List<GeographicLayerPolygon> rows = (List<GeographicLayerPolygon>) getJdbcTemplate()
				.query(strQuery, new Object[] { category, parentId },
						new GeographicLayerPolygonMapper());
		return rows;
	}
	
	@Override
	public GeographicLayerPolygon getGeographicLayerPolygonById(int id, boolean asWKT) {
		String strQuery;
		if(asWKT) {
			strQuery = "SELECT id, name, ST_AsText(geom), geographic_layer_id from geographic_layer_polygon WHERE id=?";
		}
		else {
			strQuery = "SELECT * from geographic_layer_polygon WHERE id=?";
		}		
		GeographicLayerPolygon polygon = (GeographicLayerPolygon) getJdbcTemplate()
				.queryForObject(strQuery, new Object[] { id },
						new GeographicLayerPolygonMapper());
		return polygon;
	}		
	
	@Override
	public GeographicLayerPolygon getGeographicLayerPolygonById(int id, boolean asWKT, int projection) {
		String strQuery;
		if(asWKT) {
			strQuery = "SELECT id, name, ST_AsText(ST_Transform(geom, " + projection + ")), geographic_layer_id from geographic_layer_polygon WHERE id=?";
		}
		else {
			strQuery = "SELECT * from geographic_layer_polygon WHERE id=?";
		}		
		GeographicLayerPolygon polygon = (GeographicLayerPolygon) getJdbcTemplate()
				.queryForObject(strQuery, new Object[] { id },
						new GeographicLayerPolygonMapper());
		return polygon;
	}	

	/*
	 * (non-Javadoc)
	 * 
	 * @see redd.dao.AccessDAO#getLandCovers()
	 */
	@Override
	public List<LandCover> getLandCovers() {
		String strQuery = "SELECT id, name, description, table_name, year from land_cover";
		List<LandCover> rows = (List<LandCover>) getJdbcTemplate().query(
				strQuery, new LandCoverMapper());
		return rows;
	}
	
	@Override
	public LandCover getLandCoverById(int landCoverId) {
		String strQuery = "SELECT id, name, description, table_name, year from land_cover WHERE id=?";
		LandCover landCover = (LandCover) getJdbcTemplate()
				.queryForObject(strQuery, new Object[] { landCoverId },
						new LandCoverMapper());
		return landCover;
	}	

	/*
	 * (non-Javadoc)
	 * 
	 * @see redd.dao.AccessDAO#getLandCoverCategories()
	 */
	@Override
	public List<LandCoverCategory> getLandCoverCategories() {
		String strQuery = "SELECT id, name, description from land_cover_category";
		List<LandCoverCategory> rows = (List<LandCoverCategory>) getJdbcTemplate()
				.query(strQuery, new LandCoverCategoryMapper());
		return rows;
	}

	@Override
	public void insertFact(int landCoverId, int geographicLayerPolygonId,
			String tableName, String geom) {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO fact(land_cover_category_id, "
				+ "land_cover_id, geographic_layer_polygon_id, area) ");
		query.append("SELECT land_cover_category_id, ");
		query.append(landCoverId);
		query.append(", ");
		query.append(geographicLayerPolygonId);
		query.append(", ");
		query.append("sum(ST_Area( ST_Intersection( '");
		query.append(geom);
		query.append("', geom))) FROM ");
		query.append(tableName);
		query.append(" group by land_cover_category_id");

		int success = getJdbcTemplate().update(query.toString());
		System.out.println(success);
	}

	@Override
	public List<Fact> getFacts(int landCoverId, int geographicLayerPolygonId) { 
		String strQuery = "SELECT * FROM fact WHERE land_cover_id=? AND geographic_layer_polygon_id=?";
		List<Fact> rows = (List<Fact>) getJdbcTemplate()
				.query(strQuery, new Object[] { landCoverId, geographicLayerPolygonId },
						new FactMapper());
		return rows;
	}

}
