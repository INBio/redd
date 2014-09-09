/**
 * 
 */
package redd.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import redd.config.Config;
import redd.model.GeoRow;

/**
 * @author jose
 *
 */
public class CoverageStatsMapper implements RowMapper<GeoRow>
{
	public GeoRow mapRow(ResultSet rs, int rowNum) throws SQLException {
		 
		GeoRow geoRow = new GeoRow();
		//geoRow.setGid(rs.getInt(1));
		geoRow.setCoverageType(rs.getInt(1));
		geoRow.setGeom(rs.getString(2));
		geoRow.setKm2(rs.getDouble(3));
		geoRow.setInside(rs.getBoolean(4));
		return geoRow;
	}
}
