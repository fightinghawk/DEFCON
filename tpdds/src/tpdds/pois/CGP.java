package tpdds.pois;

import tpdds.ubicacion.Comuna;
import tpdds.ubicacion.Localizable;
public class CGP extends Poi  {
	
	public CGP(Poi datos){
		super(datos);
	}

	@Override
	public boolean estaCerca(Localizable localizable) {
		int comunaThis =  Comuna.obtenerNroComunaDelBarrio(this.getDireccion().getBarrio());
		int comunaLocalizable = Comuna.obtenerNroComunaDelBarrio(localizable.getDireccion().getBarrio());
		return comunaThis == comunaLocalizable;	
	}
	
}