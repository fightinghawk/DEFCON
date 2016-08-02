package tpdds.Administrador;

import java.io.FileNotFoundException;

public abstract class Proceso {
	
	private String nombreProceso;
	private String usuario;
	private String resultado;
	private int fechaInicio;
	private int fechaFin;
	
	public Proceso(String nombreProceso, String usuario, String resultado, int fechaInicio, int fechaFin){
		this.nombreProceso = nombreProceso;
		this.usuario = usuario;
		this.resultado = resultado;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public abstract void ejecutarme();

	public String getNombreProceso() {
		return nombreProceso;
	}
	
	public void setNombreProceso(String nombreProceso){
		this.nombreProceso = nombreProceso;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario){
		this.usuario = usuario;
	}
	public String getResultado() {
		return resultado;
	}
	
	public void setResultado(String resultado){
		this.resultado = resultado;
	}
	
	public int getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(int fecha){
		this.fechaInicio = fecha;
	}
	
	public int getFechaFin() {
		return fechaFin;
	}
	
	public void setFechaFin(int fecha){
		this.fechaFin = fecha;
	}
}
