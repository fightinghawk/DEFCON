package tpdds.Administrador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class modLocalesComerciales extends Proceso  {
	
	private String ubicacionArchivo;
	
	public modLocalesComerciales(String nombreProceso, String usuario, String resultado, int fechaInicio,
			int fechaFin) {
		super(nombreProceso, usuario, resultado, fechaInicio, fechaFin);
	}
	
}