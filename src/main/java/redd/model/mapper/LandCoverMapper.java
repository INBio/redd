/**
 * 
 */
package redd.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import redd.model.LandCover;

/**
 * @author jose
 * 
 */
public class LandCoverMapper implements RowMapper<LandCover> {

	public LandCover mapRow(ResultSet rs, int rowNum) throws SQLException {

		LandCover landCover = new LandCover();
		landCover.setId(rs.getInt(1));
		landCover.setName(rs.getString(2));
		landCover.setDescription(rs.getString(3));
		landCover.setTableName(rs.getString(4));
		landCover.setYear(rs.getInt(5));

		return landCover;
	}
}
