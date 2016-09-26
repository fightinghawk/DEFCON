package tpdds.interfaz.componentes;

public class ObsResultadoTotal {
	
	private String fecha;
	private String usuario;
	private String parametros;
	private String pois;
	
	public ObsResultadoTotal(String fecha, String usuario, String parametros, String pois) {
		super();
		this.fecha = fecha;
		this.usuario = usuario;
		this.parametros = parametros;
		this.pois = pois;
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

	public String getPois() {
		return pois;
	}
	
	
	

	
	
}
