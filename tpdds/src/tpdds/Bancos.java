package tpdds;

public class Bancos extends Poi {
	
	private float radioDeCuadras;
	
	public Bancos(String nombre, Direccion direccion, Location geoloc) {
		super(nombre, "Banco", direccion, geoloc);
	}
	
	public float getRadioDeCuadras() {
		return radioDeCuadras;
	}

	public void setRadioDeCuadras(float radioDeCuadras) {
		this.radioDeCuadras = radioDeCuadras;
	}
	
	public boolean estaCerca(Localizable localizable) {
		return Calculos.calcularDistanciaA(this, localizable)>radioDeCuadras;
	}

}
