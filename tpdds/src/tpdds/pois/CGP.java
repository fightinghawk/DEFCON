package tpdds.pois;

import java.util.ArrayList;
import java.util.HashSet;

import tpdds.ubicacion.Comuna;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Localizable;
import tpdds.ubicacion.Location;

public class CGP extends Poi  {
	
	public CGP(String nombre, Direccion direccion, Location geoloc) {
		super(nombre,2, "CGP", direccion, geoloc);
	}
	
	public CGP(String nombre, Direccion direccion, Location geoloc,HashSet<String> keywords,ArrayList<DiaPoi> dias,int iddb) {
		super(nombre, "CGP", direccion, geoloc,keywords,dias,iddb);
		super.idTipo = 2;
	}

	@Override
	public boolean estaCerca(Localizable localizable) {
		int comunaThis =  Comuna.obtenerNroComunaDelBarrio(this.getDireccion().getBarrio());
		int comunaLocalizable = Comuna.obtenerNroComunaDelBarrio(localizable.getDireccion().getBarrio());
		return comunaThis == comunaLocalizable;	
	}
	
	
}