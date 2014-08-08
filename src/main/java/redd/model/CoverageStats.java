/**
 * 
 */
package redd.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author jose
 *
 */
public class CoverageStats {

	private double banano;
	private double bosque;
	private double cuerpoDeAgua;
	private double herbazal;
	private double infraestructura;
	private double manglar;
	private double nubes;
	private double otrosCultivos;
	private double palmaAceitera;
	private double paramo;
	private double pina;
	private double sabana;
	private double sombras;
	private double terrenoDescubierto;
	private double vegetacionAnegada;
	
	
	
	/**
	 * @return the banano
	 */
	public double getBanano() {
		return banano;
	}



	/**
	 * @param banano the banano to set
	 */
	public void setBanano(double banano) {
		this.banano = banano;
	}



	/**
	 * @return the bosque
	 */
	public double getBosque() {
		return bosque;
	}



	/**
	 * @param bosque the bosque to set
	 */
	public void setBosque(double bosque) {
		this.bosque = bosque;
	}



	/**
	 * @return the cuerpoDeAgua
	 */
	public double getCuerpoDeAgua() {
		return cuerpoDeAgua;
	}



	/**
	 * @param cuerpoDeAgua the cuerpoDeAgua to set
	 */
	public void setCuerpoDeAgua(double cuerpoDeAgua) {
		this.cuerpoDeAgua = cuerpoDeAgua;
	}



	/**
	 * @return the herbazal
	 */
	public double getHerbazal() {
		return herbazal;
	}



	/**
	 * @param herbazal the herbazal to set
	 */
	public void setHerbazal(double herbazal) {
		this.herbazal = herbazal;
	}



	/**
	 * @return the infraestructura
	 */
	public double getInfraestructura() {
		return infraestructura;
	}



	/**
	 * @param infraestructura the infraestructura to set
	 */
	public void setInfraestructura(double infraestructura) {
		this.infraestructura = infraestructura;
	}



	/**
	 * @return the manglar
	 */
	public double getManglar() {
		return manglar;
	}



	/**
	 * @param manglar the manglar to set
	 */
	public void setManglar(double manglar) {
		this.manglar = manglar;
	}



	/**
	 * @return the nubes
	 */
	public double getNubes() {
		return nubes;
	}



	/**
	 * @param nubes the nubes to set
	 */
	public void setNubes(double nubes) {
		this.nubes = nubes;
	}



	/**
	 * @return the otrosCultivos
	 */
	public double getOtrosCultivos() {
		return otrosCultivos;
	}



	/**
	 * @param otrosCultivos the otrosCultivos to set
	 */
	public void setOtrosCultivos(double otrosCultivos) {
		this.otrosCultivos = otrosCultivos;
	}



	/**
	 * @return the palmaAceitera
	 */
	public double getPalmaAceitera() {
		return palmaAceitera;
	}



	/**
	 * @param palmaAceitera the palmaAceitera to set
	 */
	public void setPalmaAceitera(double palmaAceitera) {
		this.palmaAceitera = palmaAceitera;
	}



	/**
	 * @return the paramo
	 */
	public double getParamo() {
		return paramo;
	}



	/**
	 * @param paramo the paramo to set
	 */
	public void setParamo(double paramo) {
		this.paramo = paramo;
	}



	/**
	 * @return the pina
	 */
	public double getPina() {
		return pina;
	}



	/**
	 * @param pina the pina to set
	 */
	public void setPina(double pina) {
		this.pina = pina;
	}



	/**
	 * @return the sabana
	 */
	public double getSabana() {
		return sabana;
	}



	/**
	 * @param sabana the sabana to set
	 */
	public void setSabana(double sabana) {
		this.sabana = sabana;
	}



	/**
	 * @return the sombras
	 */
	public double getSombras() {
		return sombras;
	}



	/**
	 * @param sombras the sombras to set
	 */
	public void setSombras(double sombras) {
		this.sombras = sombras;
	}



	/**
	 * @return the terrenoDescubierto
	 */
	public double getTerrenoDescubierto() {
		return terrenoDescubierto;
	}



	/**
	 * @param terrenoDescubierto the terrenoDescubierto to set
	 */
	public void setTerrenoDescubierto(double terrenoDescubierto) {
		this.terrenoDescubierto = terrenoDescubierto;
	}



	/**
	 * @return the vegetacionAnegada
	 */
	public double getVegetacionAnegada() {
		return vegetacionAnegada;
	}



	/**
	 * @param vegetacionAnegada the vegetacionAnegada to set
	 */
	public void setVegetacionAnegada(double vegetacionAnegada) {
		this.vegetacionAnegada = vegetacionAnegada;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this); 
	}
	
}
