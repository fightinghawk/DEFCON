package tpdds.interfaz;

public class ObsResultadoFecha {

	private String fecha;
	private Integer cantidad;
	
	public ObsResultadoFecha(String fecha, Integer cantidad) {
		super();
		this.fecha = fecha;
		this.cantidad = cantidad;
	}

	public String getFecha() {
		return fecha;
	}

	public Integer getCantidad() {
		return cantidad;
	}
	
	
}
