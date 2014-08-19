package redd.dao;

import java.sql.SQLException;

import redd.model.CoverageStats;

public interface PostgisDAO {

	/**
	 * Dado un pol’gono, retornar todos aquellos poligonos que residen dentro de Žl.
	 * Los resultados estar‡n filtrados por el a–o proporcionado.
	 * 
	 * @param polygon el pol’gono que el usuario seleccion— a mano alzada
	 * @param year el a–o de la cobertura
	 * @return estad’sticas de cobertura
	 */
	public CoverageStats getCoverageStatsByPolygon(String polygon, int year);
	

	
	/**
	 * Dado el nombre de un distrito, retornar las estad’sticas de cobertura para
	 * el pol’gono que representa al distrito. Los resultados estar‡n filtrados por el 
	 * a–o proporcionado.
	 * 
	 * @param districtName el nombre del distrito
	 * @param year el a–o de la cobertura
	 * @return estad’sticas de cobertura
	 */
	public CoverageStats getCoverageByDistrict(String districtName, int year);
	
}
