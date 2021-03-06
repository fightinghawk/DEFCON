package tpdds.interfaz.componentes;

public class ObsPoi {
	
	private String nombre;
	private String calle;
	private int altura;
	private double distancia;
	private int id;
	
	public ObsPoi(String nombre, String calle, int altura, double distancia,int id) {
		super();
		this.setId(id);
		this.nombre = nombre;
		this.calle = calle;
		this.altura = altura;
		this.distancia = distancia;
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getCalle() {
		return calle;
	}
	public int getAltura() {
		return altura;
	}
	public double getDistancia() {
		return distancia;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
