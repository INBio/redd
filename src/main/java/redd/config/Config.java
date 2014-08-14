/**
 * 
 */
package redd.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester3.Digester;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.xml.sax.SAXException;

/**
 * @author jose
 *
 */
public class Config {

	List<Categoria> categorias = new ArrayList<Categoria>();
	List<Cobertura> coberturas = new ArrayList<Cobertura>();
	
	public Config() {
		loadCategorias();
		loadCoberturas();
	}
	
	/**
	 * Retornar el indice de la categoria dado un nombre de categoria.
	 * 
	 * @return el índice numérico de la categoria. -1 si no existe.
	 */
	public int getIndiceCategoria(String nombreCategoria) {
		for(Categoria categoria: categorias) {
			if(categoria.getNombre().equals(nombreCategoria)) {
				return categoria.getIndice();
			}
		}
		return -1;
	}
	
	
	/**
	 * Retornar los nombres de todas las categorias.
	 * 
	 * @return nombre de las categorias.
	 */
	public List<String> getNombreCategorias() {
		List<String> nombres = new ArrayList<String>();
		for(Categoria categoria: categorias) {
			nombres.add(categoria.getNombre());
		}
		return nombres;
	}

	/**
	 * Tomar el archivo de configuración XML y cargar todas las categorias en un {@link List} de {@link Categoria}.
	 * 
	 */
	public void loadCategorias() {
		Digester digester = new Digester();
		digester.push(this);
		digester.addCallMethod("config/coberturas/cobertura", "addCobertura", 2);
	    
	    digester.addCallParam("config/coberturas/cobertura/ano", 0);
	    digester.addCallParam("config/coberturas/cobertura/tabla", 1);
	    try {
	    	File file = new File("src/main/resources/categorias.xml");
	    	System.out.println(file.getAbsolutePath());
	    	digester.parse(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Tomar el archivo de configuración XML y cargar todas las categorias en un {@link List} de {@link Cobertura}.
	 * 
	 */	
	public void loadCoberturas() {
		Digester digester = new Digester();
		digester.push(this);
		digester.addCallMethod("config/categorias/categoria", "addCategoria", 3);
	    
	    digester.addCallParam("config/categorias/categoria/nombre", 0);
	    digester.addCallParam("config/categorias/categoria/var", 1);
	    digester.addCallParam("config/categorias/categoria/indice", 2);
	    try {
	    	File file = new File("src/main/resources/categorias.xml");
	    	System.out.println(file.getAbsolutePath());
	    	digester.parse(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	/**
	 * Extraer un elemento de {@link Cobertura} del archivo XML de configuración
	 * 
	 * @param ano
	 * @param tabla
	 */
    public void addCobertura(String ano, String tabla)
    {
    	Cobertura cobertura = new Cobertura(Integer.parseInt(ano), tabla);
    	coberturas.add(cobertura);
    }	    
    
    /**
     * Extraer un elemento de {@link Categoria} del archivo XML de configuración
     * 
     * @param nombre
     * @param var
     * @param indice
     */
    public void addCategoria(String nombre, String var, String indice)
    {
    	Categoria categoria = new Categoria(nombre, var, Integer.parseInt(indice));
    	categorias.add(categoria);
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this); 
	}
    
}
