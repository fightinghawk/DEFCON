package tpdds.Archivos;

import tpdds.dispositivo.Dispositivo;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Location;

public class archivos {

	public static Dispositivo obtenerTablero() {
		Dispositivo tablero;

		Direccion direTablero = new Direccion("PARAGUAY", 2155, null, null, "RECOLETA");
		Location ubicacionTablero = new Location(-34.598415, -58.398260);
		tablero = new Dispositivo(1, direTablero, ubicacionTablero);
		tablero.setNombre("Terminal Medicina");

		return tablero;
	}
}
