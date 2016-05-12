package tpdds;

public class Comercios extends Poi {
	public Comercios(String nombre, Direccion direccion, Location geoloc, double criterioCuadras) {
		super(nombre, "Local Comercial", direccion, geoloc);
		this.setCuadras(criterioCuadras);
	}

}
