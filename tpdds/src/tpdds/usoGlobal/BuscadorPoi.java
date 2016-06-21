package tpdds.usoGlobal;

import java.util.ArrayList;

import tpdds.pois.Poi;

public class BuscadorPoi {
	public static ArrayList<Poi> buscar(String palabraClave, ArrayList<Poi> Pois) {
		ArrayList<Poi> coincidencias = new ArrayList<Poi>();
		for (Poi poi : Pois) {
			if (poi.contienePalabraClave(palabraClave)) {
				//poi.mostrarDatos();
				coincidencias.add(poi);
			}
		}
		return coincidencias;
	}
}
