package tpdds;

public class Poi {
	
	String nombre;
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	int tipo;
	Direccion direccion;
    GeoLocalizacion geoloc;
    Estadistica estadistica;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public GeoLocalizacion getGeoloc() {
		return geoloc;
	}

	public void setGeoloc(GeoLocalizacion geoloc) {
		this.geoloc = geoloc;
	}

	public Estadistica getEstadistica() {
		return estadistica;
	}

	public void setEstadistica(Estadistica estadistica) {
		this.estadistica = estadistica;
	}
    
}
