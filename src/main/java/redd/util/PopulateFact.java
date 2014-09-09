/** 
 * 
 */
package redd.util;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redd.dao.AccessDAO;
import redd.model.GeographicLayerPolygon;
import redd.model.LandCover;

/**
 * @author jose
 * 
 */
public class PopulateFact {
	
	AccessDAO accessDAO;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PopulateFact pf = new PopulateFact();
		pf.populateFacts();

	}

	/**
	 * Responsible for populating the 'fact' table.
	 * 
	 */
	public void populateFacts() {
		
		ExecutorService executor = Executors.newFixedThreadPool(7);
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"datasource.xml");

		accessDAO = (AccessDAO) context.getBean("accessDAO");

		List<GeographicLayerPolygon> polygons = accessDAO
				.getGeographicLayerPolygons(3);

		List<LandCover> landCovers = accessDAO.getLandCovers();

		int polygonCount = 0;
		// first iterate over all available land covers (years)
		for (LandCover landCover : landCovers) {
			int landCoverId = landCover.getId();
			String tableName = landCover.getTableName();

			// get all the polygons available
			// e.g. (Heredia (province) or Guapiles (district) or conservation
			// area
			for (GeographicLayerPolygon polygon : polygons) {
				polygonCount++;
				String geom = polygon.getGeom();
				if (geom == null)
					continue;
				int geographicLayerPolygonId = polygon.getId();
				
				Runnable worker = new WorkerThread(landCoverId, geographicLayerPolygonId, tableName, geom);
		        executor.execute(worker);			
				
				// insert the fact
				//accessDAO.insertFact(landCoverId, geographicLayerPolygonId,
				//		tableName, geom);
			}
	        executor.shutdown();
	        while (!executor.isTerminated()) {
	        }
	        System.out.println("Finished all threads");	
		}
	}
}
