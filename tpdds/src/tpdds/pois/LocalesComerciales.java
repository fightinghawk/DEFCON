package tpdds.pois;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Localizable;
import tpdds.ubicacion.Location;
import tpdds.usoGlobal.Calculos;
import tpdds.usoGlobal.CalculosHorarios;

public class LocalesComerciales extends Poi {
	
	private double radioDeCuadras;
	
	public LocalesComerciales(String nombre, String tipoPOI, Direccion direccion, Location geoloc) {
		super(nombre, tipoPOI, direccion, geoloc);
	}

	public double getRadioDeCuadras() {
		return radioDeCuadras;
	}

	public void setRadioDeCuadras(double radioDeCuadras) {
		this.radioDeCuadras = radioDeCuadras;
	}

	@Override
	public boolean estaCerca(Localizable localizable) {
		return Calculos.calcularDistanciaA(this, localizable)<radioDeCuadras;
	}


	
	
	

}
