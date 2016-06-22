package tpdds.pois;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Localizable;
import tpdds.ubicacion.Location;
import tpdds.usoGlobal.Calculos;

public class ParadaColectivo extends Poi  {

	private double criterioCuadras = 0.1;

	public ParadaColectivo(String nombre, Direccion direccion, Location geoloc) {
		super(nombre, "Parada de Colectivo", direccion, geoloc);
		super.idTipo = 3;
	}
	
	public ParadaColectivo(String nombre, Direccion direccion, Location geoloc,HashSet<String> keywords,ArrayList<DiaPoi> dias) {
		super(nombre, "Parada de Colectivo", direccion, geoloc,keywords,dias);
		super.idTipo = 3;
	}
	
	public boolean estaCerca(Localizable localizable) {
		return Calculos.calcularDistanciaA(this, localizable)<criterioCuadras;
	}

	@Override
	public boolean estaDisponible(Calendar dia) {
		return true;
	}
}
