package tpdds.pois;

import java.util.Calendar;
import java.util.Collection;

import tpdds.pois.componentes.KeyWords;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Location;

public class ParadaColectivo extends Poi  {

	
	public ParadaColectivo(Integer clave,String nombre,String srtTipo, Direccion direccion, Location geoloc,Collection<KeyWords> collection) {
		super(nombre,srtTipo, direccion, geoloc);
		super.setIddb(clave);
		super.setPalabrasClaves(collection);
	}
	
	@Override
	public boolean estaDisponible(Calendar dia) {
		return true;
	}
}
