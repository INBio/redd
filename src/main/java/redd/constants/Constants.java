/**
 * 
 */
package redd.constants;

/**
 * @author jose
 * 
 */
public class Constants {

	public enum PolygonCategory {
		PROVINCIA(1), CANTON(2), DISTRITO(3), AREA_CONSERVACION(4), AREA_SILVESTRE(5), CUENCA_HIDROGRAFICA(6);
		private int value;

        private PolygonCategory(int value) {
                this.value = value;
        }
        public int getValue() {
        	return value;
        }
	};   
	
	
}

