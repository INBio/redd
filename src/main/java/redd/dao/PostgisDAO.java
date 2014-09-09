package redd.dao;

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
	
	
}
