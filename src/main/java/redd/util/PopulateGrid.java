/**
 * 
 */
package redd.util;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jose
 * 
 */
public class PopulateGrid {

	public class Consultar implements Runnable {
		private String threadNumber;
		public String str;

		public Consultar(String s, String str) {

			this.threadNumber = s;
			this.str = str;

		}

		public void run() {
			System.out.println(Thread.currentThread().getName()
					+ " Start. ThreadNumber = " + threadNumber);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				java.sql.Connection conn;

				Class.forName("org.postgresql.Driver");

				String url = "jdbc:postgresql://172.16.16.82:5432/redd_v1";
				conn = DriverManager.getConnection(url, "postgres", "puravida");
				conn.setAutoCommit(false);


				Statement s = conn.createStatement();
				s.setFetchSize(2000);

				StringBuffer query = new StringBuffer();
				query.append("SELECT orig_fid, (ST_Dump(ST_Intersection(geom, '"
						+ str + "'))).geom FROM cobertura_1986_001");
				query.append(" WHERE ST_Intersects(geom, '" + str + "')");
				ResultSet miniPolygon = s.executeQuery(query.toString());
				while (miniPolygon.next()) {
					GridRow row = new GridRow();
					row.setCoverageType(miniPolygon.getInt(1));
					row.setGeom(miniPolygon.getString(2));
					addRow(row);
				}

				miniPolygon.close();
				s.close();
				conn.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " End.");

		}

		public synchronized void addRow(GridRow row) {

			try {
				java.sql.Connection conn;

				Class.forName("org.postgresql.Driver");

				String url = "jdbc:postgresql://172.16.16.82:5432/redd_v1";
				conn = DriverManager.getConnection(url, "postgres", "puravida");
				conn.setAutoCommit(false);



				Statement s = conn.createStatement();
				
				// recuperar el geom del distrito deseado
				String insertGrid = "insert into public.nuevatabla(orig_fid, geom) VALUES(" + row.getCoverageType() + ", '" + row.getGeom() + "')";
				int insertFlag = s.executeUpdate(insertGrid);
				conn.commit();
				
				s.close();
				conn.close();

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public PopulateGrid() {

		ExecutorService executor = Executors.newFixedThreadPool(5);

		List<String> gridBoxes = new ArrayList<String>();
		try {
			java.sql.Connection conn;

			Class.forName("org.postgresql.Driver");

			String url = "jdbc:postgresql://172.16.16.82:5432/redd_v1";
			conn = DriverManager.getConnection(url, "postgres", "puravida");
			conn.setAutoCommit(false);

			Statement s = conn.createStatement();
			s.setFetchSize(2000);

			// recuperar el geom del distrito deseado
			String getGrids = "select geom from grid_costa_rica";
			ResultSet gridRS = s.executeQuery(getGrids);

			while (gridRS.next()) {
				System.out.println("generando lista");
				gridBoxes.add(gridRS.getString(1));
			}

			System.out.println("termino lista");

			gridRS.close();
			s.close();
			conn.close();

			int contador = 0;
			for (String gridBox : gridBoxes) {
				contador++;
				Runnable worker = new Consultar("" + contador, gridBox);
				executor.execute(worker);

			}
			executor.shutdown();
			while (!executor.isTerminated()) {

			}
			System.out.println("Finished all threads");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		PopulateGrid pg = new PopulateGrid();
	}

}
