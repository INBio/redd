/**
 * 
 */
package redd;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import redd.config.Config;
import redd.model.CoverageStats;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author jose
 * 
 */
public class HomeAction  extends ActionSupport {

	private Config config = new Config();
	private String geojson;
	private CoverageStats stats1986 = new CoverageStats();
	private CoverageStats stats2000 = new CoverageStats();
	private String results;
	

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public CoverageStats getStats2000() {
		return stats2000;
	}

	public void setStats2000(CoverageStats stats2000) {
		this.stats2000 = stats2000;
	}

	public CoverageStats getStats1986() {
		return stats1986;
	}

	public void setStats1986(CoverageStats stats1986) {
		this.stats1986 = stats1986;
	}
	
	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public String execute() {
		System.out.println("Hola Mundo");
		return "SUCCESS";
	}

	public String compare() {
		stats1986 = intersect(geojson,1986);
		stats2000 = intersect(geojson,2000);
		return "SUCCESS";
	}
	
	public String grab() {
		stats1986 = intersect(geojson,1986);
		return "SUCCESS";
	}	
	
	public String getGeojson() {
		return geojson;
	}

	public void setGeojson(String geojson) {
		this.geojson = geojson;
	}	

	private CoverageStats intersect(String geojson2, int anoCobertura) {
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
			
			System.out.println("geojson: " + geojson);

			String strQuery = "select gid, orig_fid, km2, ST_Contains(ST_Transform(ST_GeomFromText('" + geojson2 + "', 3857), 4326), ST_Transform(ST_GeomFromText(ST_AsText(geom),32617), 4326)) from cobertura_" + anoCobertura;		

			System.out.println("strQuery: " + strQuery);
			
			ResultSet r = s.executeQuery(strQuery);
			
			double contador[] = new double[50];
			
			while (r.next()) {

				int gid = r.getInt(1);
				int idCobertura = r.getInt(2);
				double km2 = r.getDouble(3);
				boolean isInside = r.getBoolean(4);
				
				if(isInside) {
					contador[idCobertura] = contador[idCobertura]+km2;
				}

				/*
				System.out.println("inInside " + isInside + ":");
				System.out.println(geom.toString());
				*/
			}	
			System.out.println("----------------------------");
			System.out.println(contador[0]);
			System.out.println(contador[1]);
			System.out.println(contador[2]);
			System.out.println(contador[3]);
			System.out.println(contador[4]);
			System.out.println(contador[5]);
			System.out.println(contador[6]);
			System.out.println(contador[7]);
			System.out.println(contador[8]);
			System.out.println(contador[9]);
			System.out.println(contador[10]);
			System.out.println(contador[11]);
			System.out.println(contador[12]);
			System.out.println(contador[13]);
			System.out.println(contador[14]);
			System.out.println(contador[15]);
			System.out.println(contador[16]);			
			
			
			

			stats.setBanano(contador[0]);
			stats.setBosque(contador[1] + contador[2]);
			stats.setCuerpoDeAgua(contador[3]);
			stats.setHerbazal(contador[4] + contador[5]);
			stats.setInfraestructura(contador[6]);
			stats.setManglar(contador[7]);
			stats.setNubes(contador[8]);
			stats.setOtrosCultivos(contador[9]);
			stats.setPalmaAceitera(contador[10]);
			stats.setParamo(contador[11]);
			stats.setPina(contador[12]);
			stats.setSabana(contador[13]);
			stats.setSombras(contador[14]);
			stats.setTerrenoDescubierto(contador[15]);
			stats.setVegetacionAnegada(contador[16]);
			
			System.out.println(stats.toString());

			r.close();
			s.close();
			//conn.close();
			

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
