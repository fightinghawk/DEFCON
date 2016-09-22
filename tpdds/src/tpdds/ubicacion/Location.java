package tpdds.ubicacion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import tpdds.pois.Poi;

@Entity
@Table(name="geoPos")
public class Location{
	
	protected Location(){}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="geopos_id")
	private int iddb;
	@Column(name="longitud")
	private double longitud;
	@Column(name="latitud")
	private double latitud;
	@OneToOne
	@JoinColumn(name="pois_id")
	private Poi poi;
	
	public void getPoi(Poi poi) {
		this.poi = poi;
	}
	
	public void setPoi(Poi poi) {
		this.poi = poi;
	}


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
