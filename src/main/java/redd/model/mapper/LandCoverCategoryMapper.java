/**
 * 
 */
package redd.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import redd.model.Fact;
import redd.model.GeographicLayer;
import redd.model.LandCoverCategory;

/**
 * @author jose
 * 
 */
public class LandCoverCategoryMapper implements RowMapper<LandCoverCategory> {

	public LandCoverCategory mapRow(ResultSet rs, int rowNum)
			throws SQLException {

		LandCoverCategory landCoverCategory = new LandCoverCategory();
		landCoverCategory.setId(rs.getInt(1));
		landCoverCategory.setName(rs.getString(2));
		landCoverCategory.setDescription(rs.getString(3));

		return landCoverCategory;
	}
}
