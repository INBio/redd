/**
 * 
 */
package redd.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgis.PGgeometry;
import org.springframework.jdbc.core.RowMapper;

import redd.config.Config;

/**
 * @author jose
 *
 */
public class CoverageStatsMapper implements RowMapper
{
	
	final Config config = new Config();
	
	public GeoRow mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		GeoRow geoRow = new GeoRow();
		geoRow.setGid(rs.getInt(1));
		geoRow.setCoverageType(rs.getInt(2));
		geoRow.setKm2(rs.getDouble(3));
		geoRow.setArea(rs.getDouble(4));
		return geoRow;
	}
}
