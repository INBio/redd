/**
 * 
 */
package redd.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author jose
 * 
 */
public class Fact {

	int id;
	int landCoverCategoryId;
	int landCoverId;
	int geographicLayerPolygonId;
	double area;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the landCoverCategoryId
	 */
	public int getLandCoverCategoryId() {
		return landCoverCategoryId;
	}

	/**
	 * @param landCoverCategoryId
	 *            the landCoverCategoryId to set
	 */
	public void setLandCoverCategoryId(int landCoverCategoryId) {
		this.landCoverCategoryId = landCoverCategoryId;
	}

	/**
	 * @return the landCoverId
	 */
	public int getLandCoverId() {
		return landCoverId;
	}

	/**
	 * @param landCoverId
	 *            the landCoverId to set
	 */
	public void setLandCoverId(int landCoverId) {
		this.landCoverId = landCoverId;
	}

	/**
	 * @return the geographicLayerPolygonId
	 */
	public int getGeographicLayerPolygonId() {
		return geographicLayerPolygonId;
	}

	/**
	 * @param geographicLayerPolygonId
	 *            the geographicLayerPolygonId to set
	 */
	public void setGeographicLayerPolygonId(int geographicLayerPolygonId) {
		this.geographicLayerPolygonId = geographicLayerPolygonId;
	}

	/**
	 * @return the area
	 */
	public double getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(double area) {
		this.area = area;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
