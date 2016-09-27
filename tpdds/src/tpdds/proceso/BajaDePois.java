package tpdds.proceso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import tpdds.apiExterna.ServicioREST;
import tpdds.pois.Poi;

public class BajaDePois extends Proceso {
	
	private ArrayList<Poi> listaPois;
	
	public BajaDePois() {
		super("Baja de Pois");
	}

	@Override
	public void ejecutarme() {
		int cantPois = listaPois.size();
		listaPois.remove(ServicioREST.poiInactivo().get(0));
		if(listaPois.size()<cantPois){
			this.setResultado("OK");
		}else{
			this.setResultado("ERROR, NO SE ELIMINO POI");
		}
		Calendar fecha = GregorianCalendar.getInstance();
		this.setFechaFin(fecha);
	}
	
}
