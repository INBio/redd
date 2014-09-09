package redd.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redd.dao.AccessDAO;

public class WorkerThread implements Runnable {

	AccessDAO accessDAO;
	int landCoverId;
	int geographicLayerPolygonId;
	String tableName;
	String geom;
	static int counter = 0;
	

	public WorkerThread(int landCoverId, int geographicLayerPolygonId, String tableName, String geom) {
		this.landCoverId = landCoverId;
		this.geographicLayerPolygonId = geographicLayerPolygonId;
		this.tableName = tableName;
		this.geom = geom;
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"datasource.xml");

		accessDAO = (AccessDAO) context.getBean("accessDAO");		
	}

	@Override
	public void run() {
		counter++;
		System.out.println(Thread.currentThread().getName());
		// insert the fact
		accessDAO.insertFact(landCoverId, geographicLayerPolygonId,
				tableName, geom);
		System.out.println(Thread.currentThread().getName() + " End.");
		System.out.println("counter: " + counter);
	}


}
