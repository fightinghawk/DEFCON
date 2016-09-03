package tpdds.ubicacion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="geoPos")
@Table(name="geoPos")
public class Location{
	@Id
	@GeneratedValue
	@Column(name="geopos_id")
	private int iddb;
	@Column(name="longitud")
	private double longitud;
	@Column(name="latitud")
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

	public int getIddb() {
		return iddb;
	}

	public void setIddb(int iddb) {
		this.iddb = iddb;
	}

}
