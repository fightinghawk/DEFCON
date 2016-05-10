package tpdds;

import java.util.HashSet;
import tpdds.Calculos;

public class Poi implements Localizable  {

	private String nombre;
	private int tipo;
	private Direccion direccion;
	private Location geoloc;
	private Estadistica estadistica;
	private HashSet<String> palabrasClaves;

	// Constructor POI
	public Poi(String nombre, int tipoPOI, Direccion direccion, Location geoloc) {
		this.nombre = nombre;
		this.tipo = tipoPOI;
		this.direccion = direccion;
		this.geoloc = geoloc;
		this.estadistica = null;
		palabrasClaves = new HashSet<String>(); 	
		this.esValido();
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipoPOI) {
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

	// Informa la Distancia entre Pois
	public void informarDistanciaA(Poi otroPoi) {
		System.out.print("La distancia entre " + this.getNombre() + " y " + otroPoi.getNombre() + " es ");
		System.out.printf("%6.3f Kilometros \n", Calculos.calcularDistanciaA(this,otroPoi));
	}

	// Verifica si es Valido el POI
	public void esValido() {
		if ((nombre.equals("")) && (geoloc == null)){
			System.out.println("POI INVALIDO");
		}
		System.out.println("POI VALIDO");
	}

	// Forma de Mostrar el POI
	public String toString() {
		return nombre + " ubicado en " + direccion.infoBasica() + " es un " + tipoDePoi(tipo) + " y su geolocalizacion es"
				+ geoloc;
	}

	// Muestra el POI
	public void mostrarDatos() {
		System.out.println(this);
	}
	
	// Arma el tipo
	public String tipoDePoi(int tipo)
	{
		switch(tipo)
		{
		 case 1:
			 return "CGP";
		 case 2:
			 return "Parada de Colectivo";
		 case 3:
			 return "Sucursal de Banco";
		 case 4:
			 return "Local Comercial";
			 	 
		 default:
			 return "Otro tipo";
		}
		
	}
	
	public boolean contienePalabraClave(String palabra){
		return palabrasClaves.contains(palabra);
	}
	
	public void agregarPalabra(String[] palabras){
		for (String keyWord : palabras) {
			palabrasClaves.add(keyWord.toLowerCase());
		}
	}	
}
	
	
