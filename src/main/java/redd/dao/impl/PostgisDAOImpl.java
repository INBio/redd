/**
 * 
 */
package redd.dao.impl;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import redd.config.Config;
import redd.dao.PostgisDAO;
import redd.model.CoverageStats;


/**
 * @author jose
 *
 */
public class PostgisDAOImpl implements PostgisDAO {

	@Override
	public CoverageStats getCoverageStatsByPolygon(String polygon, int year) {
		final Config config = new Config();
		CoverageStats stats = new CoverageStats();
		try {
			java.sql.Connection conn;

			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/redd_v1";
			conn = DriverManager.getConnection(url, "postgres", "postgres");
			conn.setAutoCommit(false);

			/*
			 * Add the geometry types to the connection. Note that you must cast
			 * the connection to the pgsql-specific connection implementation
			 * before calling the addDataType() method.
			 */
			((org.postgresql.PGConnection) conn).addDataType("geometry",
					org.postgis.PGgeometry.class);
			((org.postgresql.PGConnection) conn).addDataType("box3d",
					org.postgis.PGbox3d.class);

			Statement s = conn.createStatement();
			s.setFetchSize(1000);
			
			//INICIO PRUEBA AQUI
			String strQueryTemp = "SELECT ST_Transform(ST_GeomFromText('" + polygon + "', 3857), 32617)";
			ResultSet rTemp = s.executeQuery(strQueryTemp);
			rTemp.next();
			String transformationTemp = rTemp.getString(1);
			//FIN PRUEBA AQUI
			
			String strQuery = "select gid, orig_fid, km2, ST_Contains('" + transformationTemp + "', ST_GeomFromText(ST_AsText(geom),32617)) from cobertura_" + year;		

			System.out.println("strQuery: " + strQuery);
			
			ResultSet r = s.executeQuery(strQuery);
			
			Map<Integer, Double> categoriasMap = new HashMap<Integer, Double>();
			// initialize category map
			for(int i=0; i<20; i++) {
				categoriasMap.put(i, 0.0);
			}
			
			while (r.next()) {

				int gid = r.getInt(1);
				int idCobertura = r.getInt(2);
				double km2 = r.getDouble(3);
				boolean isInside = r.getBoolean(4);
				
				if(isInside) {
					Double suma = categoriasMap.get(idCobertura);
					if(suma!=null) {
						categoriasMap.put(idCobertura, suma+km2);
					}
					else{
						categoriasMap.put(idCobertura, km2);
					}
				}

			}	
			
			System.out.println(categoriasMap);

			stats.setBanano(categoriasMap.get(config.getIndiceCategoria("Banano")));
			stats.setBosque(categoriasMap.get(config.getIndiceCategoria("Bosque")));
			stats.setCuerpoDeAgua(categoriasMap.get(config.getIndiceCategoria("Cuerpo de Agua")));
			stats.setHerbazal(categoriasMap.get(config.getIndiceCategoria("Herbazal")));
			stats.setInfraestructura(categoriasMap.get(config.getIndiceCategoria("Infraestructura")));
			stats.setManglar(categoriasMap.get(config.getIndiceCategoria("Manglar")));
			stats.setNubes(categoriasMap.get(config.getIndiceCategoria("Nubes")));
			stats.setOtrosCultivos(categoriasMap.get(config.getIndiceCategoria("Otros Cultivos")));
			stats.setPalmaAceitera(categoriasMap.get(config.getIndiceCategoria("Palma Aceitera")));
			stats.setParamo(categoriasMap.get(config.getIndiceCategoria("Paramo")));
			stats.setPina(categoriasMap.get(config.getIndiceCategoria("Pina")));
			stats.setSabana(categoriasMap.get(config.getIndiceCategoria("Sabana")));
			stats.setSombras(categoriasMap.get(config.getIndiceCategoria("Sombras")));
			stats.setTerrenoDescubierto(categoriasMap.get(config.getIndiceCategoria("Terreno Descubierto")));
			stats.setVegetacionAnegada(categoriasMap.get(config.getIndiceCategoria("Vegetacion Anegada")));

			System.out.println(stats.toString());

			r.close();
			s.close();
			//conn.close();
			return stats;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stats;
	}

	/* (non-Javadoc)
	 * @see redd.dao.PostgisDAO#getCoverageByDistrict(java.lang.String, int)
	 */
	@Override
	public CoverageStats getCoverageByDistrict(String districtName, int year) {
		final Config config = new Config();
		CoverageStats stats = new CoverageStats();
		try {
			java.sql.Connection conn;

			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/redd_v1";
			conn = DriverManager.getConnection(url, "postgres", "postgres");
			conn.setAutoCommit(false);

			/*
			 * Add the geometry types to the connection. Note that you must cast
			 * the connection to the pgsql-specific connection implementation
			 * before calling the addDataType() method.
			 */
			((org.postgresql.PGConnection) conn).addDataType("geometry",
					org.postgis.PGgeometry.class);
			((org.postgresql.PGConnection) conn).addDataType("box3d",
					org.postgis.PGbox3d.class);

			Statement s = conn.createStatement();
			s.setFetchSize(100);
			
			System.out.println("entrando aca");
			// recuperar el geom del distrito deseado
			String getPoligonoQuery = "select ST_AsText(geom) from distritos_2014 where nom_dist='" + districtName + "'";
			ResultSet poligonoResultSet = s.executeQuery(getPoligonoQuery);
			poligonoResultSet.next();
			String poligonoDistrito = poligonoResultSet.getString(1);
			
			System.out.println("poligonoNombre" + districtName);
			System.out.println("poligonoDistrito" + poligonoDistrito);
			
			String strQuery = "select gid, orig_fid, km2, ST_Contains(ST_Transform(ST_GeomFromText('" + poligonoDistrito + "', 5367), 4326), ST_Transform(ST_GeomFromText(ST_AsText(geom),32617), 4326)) from cobertura_" + year;		

			System.out.println("strQuery: " + strQuery);
			
			ResultSet r = s.executeQuery(strQuery);
			
			Map<Integer, Double> categoriasMap = new HashMap<Integer, Double>();
			// initialize category map
			for(int i=0; i<20; i++) {
				categoriasMap.put(i, 0.0);
			}
			
			while (r.next()) {

				int gid = r.getInt(1);
				int idCobertura = r.getInt(2);
				double km2 = r.getDouble(3);
				boolean isInside = r.getBoolean(4);
				
				if(isInside) {
					Double suma = categoriasMap.get(idCobertura);
					if(suma!=null) {
						categoriasMap.put(idCobertura, suma+km2);
					}
					else{
						categoriasMap.put(idCobertura, km2);
					}
				}

			}	
			
			System.out.println(categoriasMap);

			stats.setBanano(categoriasMap.get(config.getIndiceCategoria("Banano")));
			stats.setBosque(categoriasMap.get(config.getIndiceCategoria("Bosque")));
			stats.setCuerpoDeAgua(categoriasMap.get(config.getIndiceCategoria("Cuerpo de Agua")));
			stats.setHerbazal(categoriasMap.get(config.getIndiceCategoria("Herbazal")));
			stats.setInfraestructura(categoriasMap.get(config.getIndiceCategoria("Infraestructura")));
			stats.setManglar(categoriasMap.get(config.getIndiceCategoria("Manglar")));
			stats.setNubes(categoriasMap.get(config.getIndiceCategoria("Nubes")));
			stats.setOtrosCultivos(categoriasMap.get(config.getIndiceCategoria("Otros Cultivos")));
			stats.setPalmaAceitera(categoriasMap.get(config.getIndiceCategoria("Palma Aceitera")));
			stats.setParamo(categoriasMap.get(config.getIndiceCategoria("Paramo")));
			stats.setPina(categoriasMap.get(config.getIndiceCategoria("Pina")));
			stats.setSabana(categoriasMap.get(config.getIndiceCategoria("Sabana")));
			stats.setSombras(categoriasMap.get(config.getIndiceCategoria("Sombras")));
			stats.setTerrenoDescubierto(categoriasMap.get(config.getIndiceCategoria("Terreno Descubierto")));
			stats.setVegetacionAnegada(categoriasMap.get(config.getIndiceCategoria("Vegetacion Anegada")));
			
			System.out.println(stats.toString());

			r.close();
			s.close();
			//conn.close();
			return stats;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stats;
	}

}
