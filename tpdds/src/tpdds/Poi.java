package tpdds;

public class Poi {
	
	String nombre;
	int tipo;
	Direccion direccion;
    Location geoloc;
    Estadistica estadistica;
    
    
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		
		this.tipo = tipo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion.callePrincipal + " " + direccion.altura;
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

	public double calcularDistanciaA(Location geoloc) 
	{
		
		double  latitud, longitud, miLatitud, miLongitud;
		Location miLocalizacion;
		
		miLocalizacion = this.getGeoloc();
		
		miLatitud = miLocalizacion.getLatitud();
		miLongitud = miLocalizacion.getLongitud();
		
		latitud = geoloc.getLatitud();
		longitud = geoloc.getLongitud();
		
		double theta = miLongitud - longitud;
		double dist = Math.sin(deg2rad(miLatitud)) * Math.sin(deg2rad(latitud)) + Math.cos(deg2rad(miLatitud)) * Math.cos(deg2rad(latitud)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;
	
		return (dist);
		}

		//convierte grados a radianes
		private static double deg2rad(double deg) {
			return (deg * Math.PI / 180.0);
		}

		//convierte radianes a grados
		private static double rad2deg(double rad) {
			return (rad * 180 / Math.PI);
	}
	
	public boolean esValido(Poi poi)
	{
		String nom;
		nom = poi.getNombre();
		
		if (nom == null || nom == "")
		{
			return (false);
		}
		return(true);		
	}
}		
		
