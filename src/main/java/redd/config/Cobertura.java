/**
 * 
 */
package redd.config;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author jose
 *
 */
public class Cobertura {
	
	int ano;
	String tabla;	
	
	public Cobertura(int ano, String tabla) {
		super();
		this.ano = ano;
		this.tabla = tabla;
	}
	
	/**
	 * @return the ano
	 */
	public int getAno() {
		return ano;
	}

	/**
	 * @return the tabla
	 */
	public String getTabla() {
		return tabla;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this); 
	}	
}
