package tpdds.interfaz.componentes;

import java.util.Calendar;

public class ObsProceso {
	
	private String nombreProceso;
	private String resultado;
	
	public ObsProceso(String nombreProceso){
		this.setNombreProceso(nombreProceso);
		this.setResultado("Sin empezar");
	}

	public String getNombreProceso() {
		return nombreProceso;
	}

	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

}
