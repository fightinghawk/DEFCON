package tpdds.Administrador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import tpdds.dispositivo.Dispositivo;


public class modLocalesComerciales extends Proceso  {
	
	private String ubicacionArchivo;
	private BufferedReader br;
	
	public modLocalesComerciales(String nombreProceso, String usuario, String resultado, int fechaInicio,
			int fechaFin, String ubicacionArchivo) {
		super(nombreProceso, usuario, resultado, fechaInicio, fechaFin);
		this.setUbicacionArchivo(ubicacionArchivo);
	}

	public String getUbicacionArchivo() {
		return ubicacionArchivo;
	}

	public void setUbicacionArchivo(String ubicacionArchivo) {
		this.ubicacionArchivo = ubicacionArchivo;
	}
	
	@Override
	public void ejecutarme(){
		
		try {
			FileReader fr = new FileReader(this.getUbicacionArchivo());
			br = new BufferedReader(fr);
			String linea;
			while((linea = br.readLine())!=null){
				String nombreSucursal = linea.split(";")[0];
				String[] keyWord = linea.split(";")[1].split(" ");
				
				Dispositivo.buscarPOI(nombreSucursal, listaPois)
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}