/**
 * 
 */
package redd.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import redd.model.Fact;
import redd.model.GeographicLayer;

/**
 * @author jose
 * 
 */
public class GeographicLayerMapper implements RowMapper<GeographicLayer> {

	public GeographicLayer mapRow(ResultSet rs, int rowNum) throws SQLException {

		GeographicLayer geographicLayer = new GeographicLayer();
		geographicLayer.setId(rs.getInt(1));
		geographicLayer.setName(rs.getString(2));
		geographicLayer.setDescription(rs.getString(3));

		return geographicLayer;
	}
}
