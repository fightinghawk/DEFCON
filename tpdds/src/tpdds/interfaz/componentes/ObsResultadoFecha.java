package tpdds.interfaz.componentes;

import java.math.BigDecimal;

public class ObsResultadoFecha {

	private String fecha;
	private BigDecimal cantidad;
	
	public ObsResultadoFecha(String fecha, BigDecimal object) {
		super();
		this.fecha = fecha;
		this.cantidad = object;
	}

	public String getFecha() {
		return fecha;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}
	
	
}
