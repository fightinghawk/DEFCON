package tpdds.interfaz.componentes;

import java.util.Date;

public class ObsResultadoTotal {
	
	private String fecha;
	private String usuario;
	private String parametros;
	private Integer totales;
	
	public ObsResultadoTotal(String fecha, String usuario, String parametros, Integer totales) {
		super();
		this.fecha = fecha;
		this.usuario = usuario;
		this.parametros = parametros;
		this.totales = totales;
	}

	public String getFecha() {
		return fecha;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getParametros() {
		return parametros;
	}

	public Integer getTotales() {
		return totales;
	}
	
	
	

	
	
}
