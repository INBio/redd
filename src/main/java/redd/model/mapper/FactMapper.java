/**
 * 
 */
package redd.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import redd.model.Fact;

/**
 * @author jose
 * 
 */
public class FactMapper implements RowMapper<Fact> {

	public Fact mapRow(ResultSet rs, int rowNum) throws SQLException {

		Fact fact = new Fact();
		fact.setId(rs.getInt(1));
		fact.setLandCoverCategoryId(rs.getInt(2));
		fact.setLandCoverId(rs.getInt(3));
		fact.setGeographicLayerPolygonId(rs.getInt(4));
		fact.setArea(rs.getDouble(5));

		return fact;
	}
}
