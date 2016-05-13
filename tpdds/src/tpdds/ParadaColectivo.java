package tpdds;

public class ParadaColectivo extends Poi {

	private static double criterioCuadras = 0.1;

	public ParadaColectivo(String nombre, Direccion direccion, Location geoloc) {
		super(nombre, "Parada de Colectivo", direccion, geoloc);
	}
	
	public boolean estaCerca(Localizable localizable) {
		return Calculos.calcularDistanciaA(this, localizable)>criterioCuadras;
	}
}
