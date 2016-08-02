package tpdds.interfaz;

public class ObsResultadoFecha {

	private String fecha;
	private Object cantidad;
	
	public ObsResultadoFecha(String fecha, Object object) {
		super();
		this.fecha = fecha;
		this.cantidad = object;
	}

	public String getFecha() {
		return fecha;
	}

	public Object getCantidad() {
		return cantidad;
	}
	
	
}
