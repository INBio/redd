package redd.dao;

import java.util.List;

import redd.model.Fact;
import redd.model.GeographicLayer;
import redd.model.GeographicLayerPolygon;
import redd.model.LandCover;
import redd.model.LandCoverCategory;

public interface AccessDAO {

	/**
	 * Returns a list of all available {@link Fact}.
	 * 
	 * @return list of {@link Fact}
	 */
	public List<Fact> getFacts();

	/**
	 * Returns a list of all available {@link GeographicLayer}.
	 * 
	 * @return list of {@link GeographicLayer}
	 */
	public List<GeographicLayer> getGeographicLayers();

	/**
	 * Returns a list of all available {@link GeographicLayerPolygon}.
	 * 
	 * @return list of {@link GeographicLayerPolygon}
	 */
	public List<GeographicLayerPolygon> getGeographicLayerPolygons();

	/**
	 * Returns a list of all available {@link GeographicLayerPolygon} filtered
	 * by category.
	 * 
	 * @return list of {@link GeographicLayerPolygon}
	 */
	public List<GeographicLayerPolygon> getGeographicLayerPolygons(int category);

	/**
	 * Returns a list of all available {@link GeographicLayerPolygon} filtered
	 * by category and belonging to a specific parent.
	 * 
	 * @return list of {@link GeographicLayerPolygon}
	 */
	public List<GeographicLayerPolygon> getGeographicLayerPolygons(
			int category, int parentId);

	/**
	 * Returns a list of all available {@link LandCover}.
	 * 
	 * @return list of {@link LandCover}
	 */
	public List<LandCover> getLandCovers();

	/**
	 * Returns a list of all available {@link LandCoverCategory}.
	 * 
	 * @return list of {@link LandCoverCategory}
	 */
	public List<LandCoverCategory> getLandCoverCategories();

	/**
	 * Inserts a new fact.
	 * 
	 * @param landCoverId
	 *            The land coverage identifier
	 * @param geographicLayerPolygonId
	 *            The polygon identifier (e.g province, district, etc)
	 * @param tableName
	 *            Table name for the land cover to be intersected
	 * @param geom
	 *            Polygon for the province, district, conservation area, etc,
	 *            that needs to be intersected with the field from 'tableName'
	 */
	public void insertFact(int landCoverId, int geographicLayerPolygonId,
			String tableName, String geom);

	/**
	 * Returns a list of all available {@link Fact} given a {@link LandCover}
	 * and a {@link GeographicLayerPolygon}.
	 * 
	 * @param landCoverId
	 *            the identifier for the land cover
	 * @param geographicLayerPolygonId
	 *            the identifier for the polygon
	 * @return a list of {@link Fact} related to the criteria provided
	 */
	public List<Fact> getFacts(int landCoverId, int geographicLayerPolygonId);

}
