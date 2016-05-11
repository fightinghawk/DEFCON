package tpdds;

public class ParadaColectivo extends Poi {

	private final double criterioCuadras = 0.1;

	public ParadaColectivo(String nombre, int tipoPOI, Direccion direccion, Location geoloc) {
		super(nombre, tipoPOI, direccion, geoloc);
		this.setCuadras(criterioCuadras);
	}
}
