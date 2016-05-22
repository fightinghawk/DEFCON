package tpdds.ubicacion;

public class Direccion {

	private String callePrincipal;
	private String calleLateralIzq;
	private String calleLateralDer;
	private int altura;
	private int codPostal;
	private int piso;
	private char dpto;
	private int unidad;
	private String barrio;
	private String provincia;
	private String localidad;
	private String pais;

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

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	// Muestra Info Basica del Domicilio usado por POI
	public String infoBasica() {
		return (callePrincipal + " " + this.altura);
	}

	//Constructores
	
	/*
	 * Lugar ubicado en direcci�n sin c�digo postal
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
	 * Lugar ubicado en direcci�n con c�digo postal
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
	//FIN Constructores
	//TODO m�s contructores
	
	
}
