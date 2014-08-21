package redd.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.postgis.PGgeometry;

public class GeoRow {

	int gid;
	int coverageType;
	double km2;
	double area;
	
	/**
	 * @return the gid
	 */
	public int getGid() {
		return gid;
	}
	/**
	 * @param gid the gid to set
	 */
	public void setGid(int gid) {
		this.gid = gid;
	}
	/**
	 * @return the coverageType
	 */
	public int getCoverageType() {
		return coverageType;
	}
	/**
	 * @param coverageType the coverageType to set
	 */
	public void setCoverageType(int coverageType) {
		this.coverageType = coverageType;
	}
	/**
	 * @return the km2
	 */
	public double getKm2() {
		return km2;
	}
	/**
	 * @param km2 the km2 to set
	 */
	public void setKm2(double km2) {
		this.km2 = km2;
	}
	
	/**
	 * @return the area
	 */
	public double getArea() {
		return area;
	}
	
	/**
	 * @param area the area to set
	 */
	public void setArea(double area) {
		this.area = area;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this); 
	}
}
