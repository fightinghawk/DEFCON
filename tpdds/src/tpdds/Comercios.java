package tpdds;

public class Comercios extends Poi {
	public Comercios(String nombre, int tipoPOI, Direccion direccion, Location geoloc, double criterioCuadras) {
		super(nombre, tipoPOI, direccion, geoloc);
		this.setCuadras(criterioCuadras);
	}

}
