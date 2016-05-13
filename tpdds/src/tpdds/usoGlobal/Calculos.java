package tpdds.usoGlobal;

import java.text.DecimalFormat;

import tpdds.pois.Poi;
import tpdds.ubicacion.Localizable;
import tpdds.ubicacion.Location;

public class Calculos {

	public static double calcularDistanciaA(Localizable unLocalizable, Localizable otroLocalizable) {

		double latitud, longitud, miLatitud, miLongitud;
		Location miLocalizacion;
		Location otraLocalizacion = otroLocalizable.getGeoloc();

		miLocalizacion = unLocalizable.getGeoloc();

		miLatitud = miLocalizacion.getLatitud();
		miLongitud = miLocalizacion.getLongitud();

		latitud = otraLocalizacion.getLatitud();
		longitud = otraLocalizacion.getLongitud();

		double theta = miLongitud - longitud;
		double dist = Math.sin(deg2rad(miLatitud)) * Math.sin(deg2rad(latitud))
				+ Math.cos(deg2rad(miLatitud)) * Math.cos(deg2rad(latitud)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;

		return (dist);
	}

	public static boolean compararConCriterio(Poi unPoi, Localizable unDispositivo, double criterio) {
		double distancia = calcularDistanciaA(unPoi, unDispositivo);
		DecimalFormat decimales = new DecimalFormat("0.000");
		boolean resultado = distancia < criterio;

		if (resultado) {
			System.out.println(
					"El Poi " + unPoi.getNombre() + " esta a " + decimales.format(distancia) + " metros de aqui");
		}
		;

		return resultado;
	}

	// convierte grados a radianes
	public static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	// convierte radianes a grados
	public static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
	
	public static boolean entre(double min,double max, double test){
		return (min<test && max>test);
	}
}
