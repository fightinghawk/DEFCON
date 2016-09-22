package tpdds.pois;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Localizable;
import tpdds.ubicacion.Location;
import tpdds.usoGlobal.Calculos;

public class ParadaColectivo extends Poi  {

	public ParadaColectivo(Integer clave,String nombre,String srtTipo, Direccion direccion, Location geoloc,Collection<keyWords> collection) {
		super(nombre,srtTipo, direccion, geoloc);
		super.setIddb(clave);
		super.setPalabrasClaves(collection);
	}

	@Override
	public boolean estaDisponible(Calendar dia) {
		return true;
	}
}
