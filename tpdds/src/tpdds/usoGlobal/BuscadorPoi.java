package tpdds.usoGlobal;

import java.util.ArrayList;
import java.util.List;

import tpdds.pois.Poi;

public class BuscadorPoi {
	public static ArrayList<Poi> buscar(String palabraClave, List<Poi> Pois) {
		ArrayList<Poi> coincidencias = new ArrayList<Poi>();
		for (Poi poi : Pois) {
			if (poi.contienePalabraClave(palabraClave)) {
				coincidencias.add(poi);
			}
		}
		return coincidencias;
	}
}
