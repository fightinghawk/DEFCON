package tpdds;

public class CGP extends Poi {
	
	public CGP(String nombre, Direccion direccion, Location geoloc) {
		super(nombre, "CGP", direccion, geoloc);
	}

	@Override
	public boolean estaCerca(Localizable localizable) {
		int comunaThis =  Comuna.obtenerNroComunaDelBarrio(this.getDireccion().getBarrio());
		int comunaLocalizable = Comuna.obtenerNroComunaDelBarrio(localizable.getDireccion().getBarrio());
		return comunaThis == comunaLocalizable;	
	}
	
	
}