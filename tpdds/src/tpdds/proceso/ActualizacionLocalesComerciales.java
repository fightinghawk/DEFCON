package tpdds.proceso;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import tpdds.pois.Poi;
import tpdds.pois.componentes.KeyWords;
import tpdds.database.Generales;
import tpdds.dispositivo.Dispositivo;
import tpdds.pois.LocalesComerciales;

public class ActualizacionLocalesComerciales extends Proceso implements ComandoEjecutar {
	
	private String ubicacionArchivo;
	private ArrayList<Poi> listaPois;
	private BufferedReader bufferedReader;
	private Collection<KeyWords> palabrasClaves = new HashSet<KeyWords>();

	public ActualizacionLocalesComerciales(String ubicacionArchivo) {
		super("Actualizacion Local Comercial");
		this.setUbicacionArchivo(ubicacionArchivo);
	}

	@Override
	public void ejecutarme(){
		System.out.println("miau");
	}
	/*public void ejecutarme() throws IOException, ClassNotFoundException, SQLException {
		listaPois.addAll(Generales.cargarPois());
		int control = 0;
		String lineaArchivo;
		FileReader fr = new FileReader(this.getUbicacionArchivo());
		String nombreSucursal;
		String[] keyWords;
		bufferedReader = new BufferedReader(fr);
		while((lineaArchivo = bufferedReader.readLine())!=null){
			control = 1;
			nombreSucursal = lineaArchivo.split(";")[0];
			keyWords = (lineaArchivo.split(";")[1].split(" "));
			palabrasClaves.addAll(this.deListaAHashSet(keyWords));
			Poi poiBuscado = Dispositivo.buscarPOI(nombreSucursal, listaPois);
			if(poiBuscado!=null){
				poiBuscado.setPalabrasClaves(palabrasClaves);
			}else{
				Poi datosPoi = new Poi(null,);
				LocalesComerciales localNuevo = new LocalesComerciales(null, null);
				localNuevo.setPalabrasClaves(palabrasClaves);
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
	*/
	public String getUbicacionArchivo() {
		return ubicacionArchivo;
	}

	public void setUbicacionArchivo(String ubicacionArchivo) {
		this.ubicacionArchivo = ubicacionArchivo;
	}	
}
