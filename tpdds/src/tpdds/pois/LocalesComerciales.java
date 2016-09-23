package tpdds.pois;

import java.util.Collection;

import tpdds.pois.componentes.KeyWords;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Location;

public class LocalesComerciales extends Poi  {

	public LocalesComerciales(Integer clave,String nombre,String srtTipo, Direccion direccion, Location geoloc,Collection<KeyWords> collection) {
		super(nombre,srtTipo, direccion, geoloc);
		super.setIddb(clave);
		super.setPalabrasClaves(collection);
	}
}
