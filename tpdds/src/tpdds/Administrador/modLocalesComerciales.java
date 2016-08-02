package tpdds.Administrador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import tpdds.database.Generales;
import tpdds.dispositivo.Dispositivo;
import tpdds.pois.LocalesComerciales;
import tpdds.pois.Poi;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Location;


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
			Poi poiBuscado = null;
			String linea;
			while((linea = br.readLine())!=null){
				String nombreSucursal = linea.split(";")[0];
				String[] keyWord = linea.split(";")[1].split(" ");
				HashSet<String> palabrasClave = this.deListaAHashSet(String[] keyword);
				ArrayList<Poi> pois = Generales.cargarPois();
				poiBuscado = buscarPOI(nombreSucursal, pois);
				if((poiBuscado)!=null){
					poiBuscado.setPalabrasClaves(palabrasClave);
				}else	{
					LocalesComerciales(String nombreSucursal, null, null, null,HashSet<String> palabrasClave,null,null)
					}
				}
			}catch (IOException e) {
									e.printStackTrace();
								   }
			
		
	}

	public HashSet<String> deListaAHashSet(String[] listaPalabras) {
		HashSet<String> hs = new HashSet<String>();
		int i;
		for(i=0; i<listaPalabras.length; i+=1){
			hs.add(listaPalabras[i]);
		}
		return hs;
	}
	
}
