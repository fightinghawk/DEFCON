package tpdds.pois;

import tpdds.ubicacion.Comuna;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Localizable;
import tpdds.ubicacion.Location;

public class CGP extends Poi  {
	
	public CGP(String nombre, Direccion direccion, Location geoloc) {
		super(nombre, "CGP", direccion, geoloc);
		super.idTipo = 2;
	}

	@Override
	public boolean estaCerca(Localizable localizable) {
		int comunaThis =  Comuna.obtenerNroComunaDelBarrio(this.getDireccion().getBarrio());
		int comunaLocalizable = Comuna.obtenerNroComunaDelBarrio(localizable.getDireccion().getBarrio());
		return comunaThis == comunaLocalizable;	
	}
	
	
}