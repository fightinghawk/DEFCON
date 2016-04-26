package tpdds;

public class Poi {

	private String nombre;
	private int tipo;
	private Direccion direccion;
	private Location geoloc;
	private Estadistica estadistica;

	// Constructor POI
	public Poi(String nombre, int tipoPOI, Direccion direccion, Location geoloc) {
		this.nombre = nombre;
		this.tipo = tipoPOI;
		this.direccion = direccion;
		this.geoloc = geoloc;
		this.estadistica = null;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipoPOI) {

		tipo = tipoPOI;
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

	// Realiza Calculos Matematicos para obtener Distancias entre POIS
	public double calcularDistanciaA(Poi otroPoi) {

		double latitud, longitud, miLatitud, miLongitud;
		Location miLocalizacion;
		Location otraLocalizacion = otroPoi.getGeoloc();

		miLocalizacion = this.getGeoloc();

		miLatitud = miLocalizacion.getLatitud();
		miLongitud = miLocalizacion.getLongitud();

		latitud = otraLocalizacion.getLatitud();
		longitud = otraLocalizacion.getLongitud();

		double theta = miLongitud - longitud;
		double dist = Math.sin(deg2rad(miLatitud)) * Math.sin(deg2rad(latitud))
				+ Math.cos(deg2rad(miLatitud)) * Math.cos(deg2rad(latitud)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;

		return (dist);
	}

	// Informa la Distancia entre Pois
	public void informarDistanciaA(Poi otroPoi) {
		System.out.print("La distancia entre " + this.getNombre() + " y " + otroPoi.getNombre() + " es ");
		System.out.printf("%6.3f Kilometros \n", this.calcularDistanciaA(otroPoi));
	}

	// convierte grados a radianes
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	// convierte radianes a grados
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	// Verifica si es Valido el POI
	public void esValido() {
		if (nombre.equals("")) {
			System.out.println("POI: " + nombre + " INVALIDO");
		}
		System.out.println("POI: " + nombre + " VALIDO");
	}

	// Forma de Mostrar el POI
	public String toString() {
		return nombre + " ubicado en " + direccion.infoBasica() + " es una " + tipo + " y su geolocalizacion es"
				+ geoloc;
	}

	// Muestra el POI
	public void mostrarDatos() {
		System.out.println(this);
	}
}