package tpdds.dispositivo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import tpdds.pois.DiaPoi;
import tpdds.pois.Poi;
import tpdds.pois.estadisticas.Estadistica;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Localizable;
import tpdds.ubicacion.Location;

import tpdds.usoGlobal.Consola;

public class Dispositivo implements Localizable{

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

	public ArrayList<Poi> agregarPOI(Poi poi, ArrayList<Poi> listaPois) {
		listaPois.add(poi);
		return listaPois;
	}
	
	public static Poi buscarPOI(String nombrePOI, ArrayList<Poi> listaPois) {
			Poi poiEncontrado = null;
    		for (Poi poi : listaPois){
    			if(nombrePOI.toUpperCase().equals(poi.getNombre().toUpperCase())){
    				poiEncontrado = poi;
    		}
    	}
	return poiEncontrado;
    	}
    	
	public ArrayList<Poi> eliminarPOI(String nombrePOI, ArrayList<Poi> listaPois) {
    		Poi poiEncontrado = buscarPOI(nombrePOI,listaPois);
    		listaPois.remove(poiEncontrado);
    		
    	return listaPois;
    }
	
	/*
	 * modificarPOI: Primero lo busca, luego pregunta qué modificar,
	 * finalmente lo modifica y lo guarda. Modifica un solo dato por vez.
	 * Sobreescribe lo anterior.
	 */
	public ArrayList<Poi> modificarPOI(String nombrePOI, ArrayList<Poi> listaPois)
	{
		Poi poi = buscarPOI(nombrePOI, listaPois);
		if(poi != null)
		{
			int opcion = 0;
			do {
				System.out.println("Seleccionar dato a modificar: ");
				this.mostrarOpciones();
				opcion = this.opcionValida(Consola.input.nextInt());
				switch (opcion) {
				case 1:
					System.out.println("Escribir el nuevo nombre");
					poi.setNombre(Consola.input.nextLine());
					break;
				case 2:
					System.out.println("Escribir el nuevo tipo de POI");
					poi.setTipo(Consola.input.nextLine());
					break;
				case 3:
					System.out.println("Escribir nueva Dirección");
					Direccion direccion = new Direccion();
					//Se meten TODOS los datos por consola.
					direccion.DireccionConsola();
					poi.setDireccion(direccion);
					break;
				case 4:
					System.out.println("Escribir nueva geoloc");
					System.out.println("Ingresar Latitud");
					double latitud = Consola.input.nextDouble();
					System.out.println("Ingresar Longitud");
					double longitud = Consola.input.nextDouble();
					poi.setGeoloc(new Location(latitud, longitud));
					break;
				case 5:
					/*Estadisticas sin implementar.*/
					break;
				case 6:
					System.out.println("Ingresar conjunto de palabras clave");
					HashSet<String> palabras = new HashSet<>();
					String p = "x";
					do {
						System.out.println("Palabra (x para dejar de ingresar: ");
						p = Consola.input.nextLine().toString();
						if(!p.toLowerCase().equals("x"))
							palabras.add(p);
						
					} while (!p.toLowerCase().equals("x"));
					poi.setPalabrasClaves(palabras);
				case 7:
					//TODO: la parte de los diasPOI.
					break;
				default:
					break;
				}
				
				
			} while (opcion == 8);
		}
		
		return listaPois;
	}
	
	public void mostrarOpciones()
	{
		System.out.println("1. Nombre;");
		System.out.println("2. Tipo;");
		System.out.println("3. Dirección;");
		System.out.println("4. Location (geoloc);");
		System.out.println("5. Estadística (WIP);");
		System.out.println("6. Palabras Clave;");
		System.out.println("7. Días Disponibles;");
		System.out.println("8. Salir.");
	}
	
	public int opcionValida( int opcion)
	{
		if(opcion < 1 && opcion > 8)
		{
			opcion = 8;
		}
		return opcion;
	}
}
