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
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return the var
	 */
	public String getVar() {
		return var;
	}

	/**
	 * @return the indice
	 */
	public int getIndice() {
		return indice;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this); 
	}	
}
