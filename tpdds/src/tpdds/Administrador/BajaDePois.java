package tpdds.Administrador;

import java.util.ArrayList;

import apiExterna.ServicioREST;
import tpdds.pois.Poi;

public class BajaDePois extends Proceso {
	
	private ArrayList<Poi> listaPois;
	
	public BajaDePois(String nombreProceso, String usuario, String resultado, int fechaInicio, int fechaFin) {
		super("Baja de Pois", usuario, resultado, fechaInicio, fechaFin);
	}

	@Override
	public void ejecutarme() {
		listaPois.remove(ServicioREST.poiInactivo().get(0));
	}
}
