package tpdds.Administrador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import tpdds.pois.Poi;
import tpdds.database.Generales;
import tpdds.dispositivo.Dispositivo;
import tpdds.pois.LocalesComerciales;

public class ActualizacionLocalesComerciales extends Proceso implements ComandoEjecutar {
	
	private String ubicacionArchivo;
	private ArrayList<Poi> listaPois;
	private BufferedReader bufferedReader;

	public ActualizacionLocalesComerciales(String nombreProceso, String usuario, String resultado, int fechaInicio,
			int fechaFin, String ubicacionArchivo) {
		super(nombreProceso);
		this.setUbicacionArchivo(ubicacionArchivo);
	}

	@SuppressWarnings("null")
	@Override
	public void ejecutarme() throws IOException, ClassNotFoundException, SQLException {
		listaPois.addAll(Generales.cargarPois());
		int control = 0;
		String lineaArchivo;
		FileReader fr = new FileReader(this.getUbicacionArchivo());
		String nombreSucursal;
		String[] keyWords;
		HashSet<String> palabrasClave = null;
		bufferedReader = new BufferedReader(fr);
		while((lineaArchivo = bufferedReader.readLine())!=null){
			control = 1;
			nombreSucursal = lineaArchivo.split(";")[0];
			keyWords = (lineaArchivo.split(";")[1].split(" "));
			palabrasClave.addAll(this.deListaAHashSet(keyWords));
			Poi poiBuscado = Dispositivo.buscarPOI(nombreSucursal, listaPois);
			if(poiBuscado!=null){
				poiBuscado.setPalabrasClaves(palabrasClave);
			}else{
				LocalesComerciales localNuevo = new LocalesComerciales(nombreSucursal, null, null, null);
				localNuevo.setPalabrasClaves(palabrasClave);
			}
		}
		if(control==1){
			this.setResultado("OK");
		}else{
			this.setResultado("ERROR, NO SE EJECUTO CORRECTAMENTE");
			}
		Calendar fecha = GregorianCalendar.getInstance();
		this.setFechaFin(fecha);
	}

	public String getUbicacionArchivo() {
		return ubicacionArchivo;
	}

	public void setUbicacionArchivo(String ubicacionArchivo) {
		this.ubicacionArchivo = ubicacionArchivo;
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
