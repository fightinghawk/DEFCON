package tpdds.dispositivo;

import java.util.ArrayList;

import tpdds.pois.Poi;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Localizable;
import tpdds.ubicacion.Location;

public class Dispositivo implements Localizable {

	private Location geoloc;
	private int id;
	private Direccion direccion;
	private String nombre;

	public Dispositivo(int suid, Direccion sudireccion, Location sugeoloc) {
		this.direccion = sudireccion;
		this.id = suid;
		this.geoloc = sugeoloc;

	}

	/*
	 * Primera aproximacion al esta cerca que pide para la entrega uno, todavia
	 * falta saber si esto es desde un poi a otro o si es desde una ubicacion X
	 * a el poi en caso que haya uno
	 */
	public int estaCerca(ArrayList<Poi> listaPois) {
		int encontrados = 0;

		for (Poi poi : listaPois) {
			if (estaCercaDe(poi)) {
				System.out.println(this.nombre + " se encuentra cerca de " + poi.getNombre());
				encontrados += 1;
			}
		}
		if (encontrados == 0) {
			System.out.println("No hay ningun Poi Cercano");
		}
		return encontrados;
	}

	public int estanDisponible(ArrayList<Poi> listaPois, int dia, int hora, int min) {
		int disponibles = 0;

		for (Poi poi : listaPois) {
			if (poi.estaDisponible(dia, hora, min)) {
				System.out.println("El poi " + poi.getNombre() + " esta disponible");
				disponibles += 1;
			} else {
				System.out.println("El poi " + poi.getNombre() + " no esta disponible");
			}
		}

		return disponibles;
	}

	public Location getGeoloc() {
		return geoloc;
	}

	public void setGeoloc(Location geoloc) {
		this.geoloc = geoloc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public boolean estaCercaDe(Poi poi) {
		return poi.estaCerca(this);
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
