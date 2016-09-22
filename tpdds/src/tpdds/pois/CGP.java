package tpdds.pois;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import tpdds.ubicacion.Comuna;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Localizable;
import tpdds.ubicacion.Location;

public class CGP extends Poi  {
	
	public CGP(Integer clave,String nombre,String srtTipo, Direccion direccion, Location geoloc,Collection<keyWords> collection) {
		super(nombre,srtTipo, direccion, geoloc);
		super.setIddb(clave);
		super.setPalabrasClaves(collection);
	}

	@Override
	public boolean estaCerca(Localizable localizable) {
		int comunaThis =  Comuna.obtenerNroComunaDelBarrio(this.getDireccion().getBarrio());
		int comunaLocalizable = Comuna.obtenerNroComunaDelBarrio(localizable.getDireccion().getBarrio());
		return comunaThis == comunaLocalizable;	
	}
	
	
}