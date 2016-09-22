package tpdds.pois;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Session;

import HIBERNATE.HibernateSessionFactory;
import tpdds.pois.estadisticas.Estadistica;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Localizable;
import tpdds.ubicacion.Location;
import tpdds.usoGlobal.Calculos;
import tpdds.usoGlobal.CalculosHorarios;


@Entity
@Table (name="pois")
public class Poi implements Localizable {
	
	public Poi(){}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pois_id")
	private int iddb;
	@Column(name="nombre")
	private String nombre;
	@Column(name="strtipo")
	private String tipo;
	@Column(name="crtCuadras")
	private double radioDeCuadras;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="direcciones_id")
	private Direccion direccion;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="geoPos_id")
	private Location geoloc;
	@OneToMany(fetch = FetchType.EAGER, mappedBy="poi",cascade= CascadeType.ALL)
	private Collection<keyWords> palabrasClaves = new HashSet<keyWords>();
	@OneToMany(mappedBy="poi",cascade= CascadeType.ALL)
	private Collection<DiaPoi> diasDisp = new ArrayList<DiaPoi>();

	public Collection<keyWords> getPalabrasClaves() {
		return palabrasClaves;
	}

	public Collection<DiaPoi> getDiasDisp() {
		return diasDisp;
	}
	
	public int getIddb() {
		return iddb;
	}
	
	public int setIddb(Integer clave) {
		return this.iddb=clave;
	}
	// Constructor POI
	public Poi(String nombre,String tipoPOI, Direccion direccion, Location geoloc) {
		this.nombre = nombre;
		this.tipo = tipoPOI;
		this.direccion = direccion;
		this.geoloc = geoloc;
		this.iddb = -1;
		palabrasClaves = new HashSet<keyWords>();
		diasDisp = new ArrayList<>();
		this.esValido();
	}
	
	// Constructor POI
	public Poi(String nombre, String tipoPOI, Direccion direccion, Location geoloc,HashSet<keyWords> keywords,ArrayList<DiaPoi> dias,int iddb) {
		this.nombre = nombre;
		this.tipo = tipoPOI;
		this.direccion = direccion;
		this.geoloc = geoloc;
		this.iddb = -1;
		palabrasClaves = keywords;
		diasDisp = dias;
		this.iddb = iddb;
		this.esValido();
	}
	
	public void setPalabrasClaves(Collection<keyWords> collection) {
		this.palabrasClaves = collection;
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

	public double getRadioDeCuadras() {
		return radioDeCuadras;
	}

	public void setRadioDeCuadras(double radioDeCuadras) {
		this.radioDeCuadras = radioDeCuadras;
	}
	
	public boolean estaCerca(Localizable localizable) {
		return Calculos.calcularDistanciaA(this, localizable)<radioDeCuadras;
	}
	
	public boolean contienePalabraClave(String palabra) {
		boolean encontrado = false;
		palabra = palabra.toLowerCase();
		encontrado = (nombre.toLowerCase().contains(palabra) ||
				getDireccionToString().toLowerCase().contains(palabra) ||
				this.PalabrasClaves(palabra)
		);
		return encontrado;
	}

	private boolean PalabrasClaves(String palabra) {
		boolean encontrado = false;
		palabra = palabra.toLowerCase();
		for(keyWords keywords : this.palabrasClaves)
		{
			if(keywords.getClave().toLowerCase().equals(palabra))
			{
				encontrado=true;
			}
		}
		return encontrado;
	}

	public void agregarPalabra(String[] palabras) {
		keyWords keywords;
		Session aGuardar = HibernateSessionFactory.getSession();
		aGuardar.beginTransaction();
		for (String keyWord : palabras) {
			keywords = new keyWords(keyWord,this);
			aGuardar.save(keywords);
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
