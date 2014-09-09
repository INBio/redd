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
		PROVINCIA(1), CANTON(2), DISTRITO(3);
		private int value;

        private PolygonCategory(int value) {
                this.value = value;
        }
        public int getValue() {
        	return value;
        }
	};   
}

