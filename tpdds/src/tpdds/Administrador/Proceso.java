package tpdds.Administrador;

import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Proceso implements ComandoEjecutar{	
	private String nombreProceso;
	private String usuario;
	private String resultado;
	private Calendar fechaInicio;
	private Calendar fechaFin;
	
	public Proceso(String nombreProceso){
		this.nombreProceso = nombreProceso;
		this.usuario = null;
		this.resultado = null;
		this.fechaInicio = null;
		this.fechaFin = null;
	}

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
	
	public Calendar getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(Calendar fecha){
		this.fechaInicio = fecha;
	}
	
	public Calendar getFechaFin() {
		return fechaFin;
	}
	
	public void setFechaFin(Calendar fecha){
		this.fechaFin = fecha;
	}
}
