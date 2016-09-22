package tpdds.pois;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Localizable;
import tpdds.ubicacion.Location;
import tpdds.usoGlobal.Calculos;

public class LocalesComerciales extends Poi  {
	
	public LocalesComerciales(Integer clave,String nombre,String srtTipo, Direccion direccion, Location geoloc,Collection<keyWords> collection) {
		super(nombre,srtTipo, direccion, geoloc);
		super.setIddb(clave);
		super.setPalabrasClaves(collection);
	}


	
	
	

}
