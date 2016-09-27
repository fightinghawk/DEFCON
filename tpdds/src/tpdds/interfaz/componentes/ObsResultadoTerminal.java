package tpdds.interfaz.componentes;

import java.math.BigDecimal;

public class ObsResultadoTerminal {

	BigDecimal cantidad;

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public ObsResultadoTerminal(BigDecimal cantidad) {
		super();
		this.cantidad = cantidad;
	}
	
}
