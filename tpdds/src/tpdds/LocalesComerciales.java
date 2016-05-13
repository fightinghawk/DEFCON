package tpdds;

public class LocalesComerciales extends Poi {
	
	
	private float radioDeCuadras;
	
	public LocalesComerciales(String nombre, String tipoPOI, Direccion direccion, Location geoloc) {
		super(nombre, tipoPOI, direccion, geoloc);
	}

	public float getRadioDeCuadras() {
		return radioDeCuadras;
	}

	public void setRadioDeCuadras(float radioDeCuadras) {
		this.radioDeCuadras = radioDeCuadras;
	}

	@Override
	public boolean estaCerca(Localizable localizable) {
		return Calculos.calcularDistanciaA(this, localizable)>radioDeCuadras;
	}
	
	
	

}
