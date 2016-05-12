package tpdds;

import java.util.HashSet;

public class Poi implements Localizable {

	private String nombre;
	private String tipo;
	private Direccion direccion;
	private Location geoloc;
	private Estadistica estadistica;
	private HashSet<String> palabrasClaves;
	private double cuadras = 0.5;

	// Constructor POI
	public Poi(String nombre, String tipoPOI, Direccion direccion, Location geoloc) {
		this.nombre = nombre;
		this.tipo = tipoPOI;
		this.direccion = direccion;
		this.geoloc = geoloc;
		this.estadistica = null;
		palabrasClaves = new HashSet<String>();
		this.esValido();
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipoPOI) {
		this.tipo = tipoPOI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion.getCallePrincipal() + " " + direccion.getAltura();
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Location getGeoloc() {
		return geoloc;
	}

	public void setGeoloc(Location geoloc) {
		this.geoloc = geoloc;
	}

	public Estadistica getEstadistica() {
		return estadistica;
	}

	public void setEstadistica(Estadistica estadistica) {
		this.estadistica = estadistica;
	}

	public double getCuadras() {
		return cuadras;
	}

	public void setCuadras(double cuadras) {
		this.cuadras = cuadras;
	}

	// Informa la Distancia entre Pois
	public void informarDistanciaA(Poi otroPoi) {
		System.out.print("La distancia entre " + this.getNombre() + " y " + otroPoi.getNombre() + " es ");
		System.out.printf("%6.3f Kilometros \n", Calculos.calcularDistanciaA(this, otroPoi));
	}

	// Informa Cercania
	public void informarCercania(Poi unPoi, boolean estaCerca) {
		if (estaCerca) {
			System.out.print("El Poi " + unPoi.getNombre() + " esta cerca\n");
		} else {
			System.out.print("El Poi " + unPoi.getNombre() + " NO esta cerca\n");
		}
	}

	// Verifica si es Valido el POI
	public void esValido() {
		if ((nombre.equals("")) && (geoloc == null)) {
			System.out.println("POI INVALIDO");
		}
		System.out.println("POI VALIDO");
	}

	// Forma de Mostrar el POI
	public String toString() {
		return nombre + " ubicado en " + direccion.infoBasica() + " es un " + this.getTipo()
				+ " y su geolocalizacion es" + geoloc;
	}

	// Muestra el POI
	public void mostrarDatos() {
		System.out.println(this);
	}

	public void estaCerca(Poi unPoi, double criteriocuadras) {
		boolean estaCerca = Calculos.compararConCriterio(unPoi, this, criteriocuadras);
		this.informarCercania(unPoi, estaCerca);
	}

	public boolean contienePalabraClave(String palabra) {
		return palabrasClaves.contains(palabra);
	}

	public void agregarPalabra(String[] palabras) {
		for (String keyWord : palabras) {
			palabrasClaves.add(keyWord.toLowerCase());
		}
	}
}
