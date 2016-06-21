package tpdds.pois;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;

import tpdds.pois.estadisticas.Estadistica;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Localizable;
import tpdds.ubicacion.Location;
import tpdds.usoGlobal.Calculos;
import tpdds.usoGlobal.CalculosHorarios;

public abstract class Poi implements Localizable, Serializable {

	private String nombre;
	private String tipo;
	private Direccion direccion;
	private Location geoloc;
	private Estadistica estadistica;
	private HashSet<String> palabrasClaves;
	private ArrayList<DiaPoi> diasDisp;

	// Constructor POI
	public Poi(String nombre, String tipoPOI, Direccion direccion, Location geoloc) {
		this.nombre = nombre;
		this.tipo = tipoPOI;
		this.direccion = direccion;
		this.geoloc = geoloc;
		this.estadistica = null;
		palabrasClaves = new HashSet<String>();
		diasDisp = new ArrayList<>();
		this.esValido();
	}
	
	public void setPalabrasClaves(HashSet<String> palabrasClaves) {
		this.palabrasClaves = palabrasClaves;
	}

	public void setDiasDisp(ArrayList<DiaPoi> diasDisp){
		this.diasDisp = diasDisp;
	}
	
	public void setDiasDisp(DiaPoi diaDisp){
		this.diasDisp.add(diaDisp);
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

	public String getDireccionToString() {
		return direccion.getCallePrincipal() + " " + direccion.getAltura();
	}

	public Direccion getDireccion(){
		return this.direccion;
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
	public double informarDistanciaA(Poi otroPoi) {
		double distancia = Calculos.calcularDistanciaA(this, otroPoi);
		System.out.print("La distancia entre " + this.getNombre() + " y " + otroPoi.getNombre() + " es ");
		System.out.printf("%6.3f Kilometros \n", distancia);
		return distancia;
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
	public boolean esValido() {
		return !((nombre.equals("")) || (geoloc == null));
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

	public abstract boolean estaCerca(Localizable localizable);
	
	public boolean contienePalabraClave(String palabra) {
		boolean encontrado = false;
		encontrado = (nombre.contains(palabra) ||
				getDireccionToString().contains(palabra) ||
				palabrasClaves.contains(palabra)
		);
			
		return encontrado;
	}

	public void agregarPalabra(String[] palabras) {
		for (String keyWord : palabras) {
			palabrasClaves.add(keyWord.toLowerCase());
		}
	}
	
	
	
	public boolean estaDisponible(){
		return estaDisponible(new GregorianCalendar());
	}
	
	public boolean estaDisponible(int dia, int hora, int min){
		Calendar fecha = new GregorianCalendar(CalculosHorarios.getActualYear(),CalculosHorarios.getActualMonth(),dia,hora,min);
		return estaDisponible(fecha);
	}
	
	public boolean estaDisponible(Calendar time) {
		for (DiaPoi diaPoi : diasDisp) {
			if(CalculosHorarios.disponibilidadDia(diaPoi.getDia(), time)){
				if(CalculosHorarios.disponibilidadHoraria(diaPoi.getHoraApertura(), diaPoi.getHoraClose(), diaPoi.getMinApertura(), diaPoi.getMinClose(), time)){
					return true;
				}
			}
		}
		return false;
	}

}
