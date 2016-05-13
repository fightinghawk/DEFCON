package tpdds;

import java.util.ArrayList;

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
	public void estaCerca(ArrayList<Poi> listaPois) {
		int encontrado = 0;

		for (Poi poi : listaPois) {
			if (estaCercaDe(poi)) {
				System.out.println(this.nombre + " se encuentra cerca de " + poi.getNombre());
				encontrado += 1;
			}
		}
		if (encontrado == 0) {
			System.out.println("No hay ningun Poi Cercano");
		}
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
	
	public boolean estaCercaDe(Poi poi){
		return poi.estaCerca(this);
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
}
