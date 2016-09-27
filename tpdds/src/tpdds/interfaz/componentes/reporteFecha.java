package tpdds.interfaz.componentes;

import java.math.BigDecimal;

public class reporteFecha {
	
	String fecha; 
	BigDecimal totales;
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the totales
	 */
	public BigDecimal getTotales() {
		return totales;
	}
	/**
	 * @param totales the totales to set
	 */
	public void setTotales(BigDecimal totales) {
		this.totales = totales;
	}

}
