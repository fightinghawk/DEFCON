package tpdds.proceso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import tpdds.apiExterna.ServicioREST;
import tpdds.database.Generales;
import tpdds.interfaz.Main;
import tpdds.pois.Poi;

public class BajaDePois extends Proceso {
	
	private ArrayList<Poi> listaPois = Main.pois;
	
	public BajaDePois() {
		super("Baja de Pois");
	}
	
	public void ejecutarmeTest(ArrayList<Poi> listaPois){
		int cantPois = listaPois.size();
		listaPois.remove(ServicioREST.poiInactivo());
		if(listaPois.size()<cantPois){
			this.setResultado("OK");
		}else{
			this.setResultado("ERROR, NO SE ELIMINO POI");
		}
	}

	@Override
	public void ejecutarme() {
		int cantPois = listaPois.size();
		listaPois.remove(ServicioREST.poiInactivo());
		if(listaPois.size()<cantPois){
			this.setResultado("OK");
		}else{
			this.setResultado("ERROR, NO SE ELIMINO POI");
		}
		Calendar fecha = GregorianCalendar.getInstance();
		this.setFechaFin(fecha);
	}
	
}
