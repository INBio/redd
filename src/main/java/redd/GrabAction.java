/**
 * 
 */
package redd;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author jose
 * 
 */
public class GrabAction extends ActionSupport {

	private String geojson;
	
	public String grab() {
		System.out.println(geojson);
		return "SUCCESS";
	}

	public String getGeojson() {
		return geojson;
	}

	public void setGeojson(String geojson) {
		this.geojson = geojson;
	}
	
	
}
