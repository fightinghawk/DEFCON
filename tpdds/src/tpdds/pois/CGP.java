package tpdds.pois;

import javax.persistence.Entity;

import tpdds.ubicacion.Comuna;
import tpdds.ubicacion.Localizable;

@Entity
public class CGP extends Poi  {
	
	public CGP(Poi datos){
		super(datos);
		super.setDtype(this.getClass().getSimpleName());
	}
	
	@Override
	public boolean estaCerca(Localizable localizable) {
		int comunaThis =  Comuna.obtenerNroComunaDelBarrio(this.getDireccion().getBarrio());
		int comunaLocalizable = Comuna.obtenerNroComunaDelBarrio(localizable.getDireccion().getBarrio());
		return comunaThis == comunaLocalizable;	
	}
	
	public String getRubro() {
		return "Tramites";
	}
	
	public CGP(){}
	
}