package tpdds.interfaz.componentes;

import java.util.Date;

import tpdds.buscadores.Buscador;

public class ObsResultadoTotal {
	
	private String fecha;
	private String usuario;
	private String parametros;
	private Integer totales;
	private Busqueda buscado;
	
	public ObsResultadoTotal(String fecha, String usuario, String parametros, Integer totales, Busqueda buscador) {
		super();
		this.fecha = fecha;
		this.usuario = usuario;
		this.parametros = parametros;
		this.totales = totales;
		this.buscado = buscador;
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

	public String getTotales() {
		return totales.toString();
	}
	
	public Busqueda getBuscado() {
		return buscado;
	}

	
	
}
