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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this); 
	}	
}
