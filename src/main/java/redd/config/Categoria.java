/**
 * 
 */
package redd.config;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author jose
 *
 */
public class Categoria {
	
	String nombre;
	String var;
	int indice;
	
	public Categoria(String nombre, String var, int indice) {
		super();
		this.nombre = nombre;
		this.var = var;
		this.indice = indice;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this); 
	}	
}
