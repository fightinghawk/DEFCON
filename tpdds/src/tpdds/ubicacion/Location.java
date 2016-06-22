package tpdds.ubicacion;

import java.io.Serializable;

public class Location{

	private double longitud;
	private double latitud;

	// Constructor Latitud y longitud se colocan en grados
	public Location(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	// devuelve un string para poder contrar la latitud y la longitd juntas
	public String toString() {
		return " (" + latitud + ", " + longitud + ")";
	}

}
