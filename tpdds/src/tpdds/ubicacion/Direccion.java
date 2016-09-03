package tpdds.ubicacion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import tpdds.usoGlobal.Consola;

@Entity(name="direcciones")
@Table(name="direcciones")
public class Direccion {
	@Id
	@GeneratedValue
	@Column(name = "direcciones_id")
	private int iddb;
	@Column(name = "principal")
	private String callePrincipal;
	@Column(name = "izquierda")
	private String calleLateralIzq;
	@Column(name = "derecha")
	private String calleLateralDer;
	@Column(name = "altura")
	private int altura;
	@Column(name = "codpostal")
	private int codPostal;
	@Column(name = "piso")
	private int piso;
	@Column(name = "dpto")
	private char dpto;
	@Column(name = "unidad")
	private int unidad;
	@Column(name = "barrio")
	private String barrio;
	@Column(name = "localidad")
	private String localidad;


	public String getCallePrincipal() {
		return callePrincipal;
	}

	public void setCallePrincipal(String callePrincipal) {
		this.callePrincipal = callePrincipal;
	}

	public String getCalleLateralIzq() {
		return calleLateralIzq;
	}

	public void setCalleLateralIzq(String calleLateralIzq) {
		this.calleLateralIzq = calleLateralIzq;
	}

	public String getCalleLateralDer() {
		return calleLateralDer;
	}

	public void setCalleLateralDer(String calleLateralDer) {
		this.calleLateralDer = calleLateralDer;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(int codPostal) {
		this.codPostal = codPostal;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public char getDpto() {
		return dpto;
	}

	public void setDpto(char dpto) {
		this.dpto = dpto;
	}

	public int getUnidad() {
		return unidad;
	}

	public void setUnidad(int unidad) {
		this.unidad = unidad;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	// Muestra Info Basica del Domicilio usado por POI
	public String infoBasica() {
		return (callePrincipal + " " + this.altura);
	}

	//Constructores
	
	/*
	 * Lugar ubicado en dirección sin código postal
	 */
	public Direccion(String callePrincipal, int altura, 
			String calleLateralIzquierda, String calleLateralDerecha, 
			String barrio)
	{
	this.callePrincipal = callePrincipal;
	this.altura = altura;
	this.calleLateralIzq = calleLateralIzquierda;
	this.calleLateralDer = calleLateralDerecha;
	this.barrio = barrio;
	}
	
	/*
	 * Lugar ubicado en dirección con código postal
	 */
	public Direccion(String callePrincipal, int altura, 
					String calleLateralIzquierda, String calleLateralDerecha, 
					int codigoPostal,
					String barrio)
	{
		this.callePrincipal = callePrincipal;
		this.altura = altura;
		this.calleLateralIzq = calleLateralIzquierda;
		this.calleLateralDer = calleLateralDerecha;
		this.codPostal = codigoPostal;
		this.barrio = barrio;
	}
	
	/*
	 * Lugar ubicado en propiedad horizontal
	 */
	public Direccion(String callePrincipal, int altura, 
			String calleLateralIzquierda, String calleLateralDerecha, 
			int piso,
			char departamento,
			int codigoPostal,
			String barrio)
	{
	this.callePrincipal = callePrincipal;
	this.altura = altura;
	this.calleLateralIzq = calleLateralIzquierda;
	this.calleLateralDer = calleLateralDerecha;
	this.piso = piso;
	this.dpto = departamento;
	this.codPostal = codigoPostal;
	this.barrio = barrio;
	}
	
	/*Un constructor vaci�.*/
	public Direccion()
	{
		
	}
	
	public void DireccionConsola()
	{
		System.out.println("Calle Principal:");
		this.setCallePrincipal(Consola.input.nextLine().toString());
		System.out.println("Altura:");
		this.setAltura(Consola.input.nextInt());
		System.out.println("Calle Lateral Derecha:");
		this.setCalleLateralDer(Consola.input.nextLine());
		System.out.println("Calle Lateral Izquierda:");
		this.setCalleLateralIzq(Consola.input.nextLine());
		System.out.println("Piso:");
		this.setPiso(Consola.input.nextInt());
		System.out.println("Departamento:");
		this.setDpto(Consola.input.nextLine().charAt(0));
		System.out.println("Unidad:");
		this.setUnidad(Consola.input.nextInt());
		System.out.println("Codigo Postal:");
		this.setCodPostal(Consola.input.nextInt());
		System.out.println("Barrio:");
		this.setBarrio(Consola.input.nextLine());
		System.out.println("Localidad:");
		this.setLocalidad(Consola.input.nextLine());
	}

	public int getIddb() {
		return iddb;
	}

	public void setIddb(int iddb) {
		this.iddb = iddb;
	}
	
	
	
	//FIN Constructores
	//TODO más contructores
	
	
}
