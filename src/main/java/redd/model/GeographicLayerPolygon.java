/**
 * 
 */
package redd.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author jose
 * 
 */
public class GeographicLayerPolygon {

	int id;
	String name;
	String geom;
	int geographicLayerId;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the geom
	 */
	public String getGeom() {
		return geom;
	}

	/**
	 * @param geom
	 *            the geom to set
	 */
	public void setGeom(String geom) {
		this.geom = geom;
	}

	/**
	 * @return the geographicLayerId
	 */
	public int getGeographicLayerId() {
		return geographicLayerId;
	}

	/**
	 * @param geographicLayerId
	 *            the geographicLayerId to set
	 */
	public void setGeographicLayerId(int geographicLayerId) {
		this.geographicLayerId = geographicLayerId;
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
