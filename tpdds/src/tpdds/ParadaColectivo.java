package tpdds;

public class ParadaColectivo extends Poi {

	private final double criterioCuadras = 0.1;

	public ParadaColectivo(String nombre, Direccion direccion, Location geoloc) {
		super(nombre, "Parada de Colectivo", direccion, geoloc);
		this.setCuadras(criterioCuadras);
	}
}
