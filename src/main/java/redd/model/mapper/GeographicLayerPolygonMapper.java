/**
 * 
 */
package redd.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import redd.model.Fact;
import redd.model.GeographicLayer;
import redd.model.GeographicLayerPolygon;

/**
 * @author jose
 * 
 */
public class GeographicLayerPolygonMapper implements
		RowMapper<GeographicLayerPolygon> {

	public GeographicLayerPolygon mapRow(ResultSet rs, int rowNum)
			throws SQLException {

		GeographicLayerPolygon geographicLayerPolygon = new GeographicLayerPolygon();
		geographicLayerPolygon.setId(rs.getInt(1));
		geographicLayerPolygon.setName(rs.getString(2));
		geographicLayerPolygon.setGeom(rs.getString(3));
		geographicLayerPolygon.setGeographicLayerId(rs.getInt(4));
		return geographicLayerPolygon;
	}
}
