package tpdds.pois;

import java.util.Calendar;

public class ParadaColectivo extends Poi  {

	
	public ParadaColectivo(Poi datos) {
		super(datos);
	}
	
	@Override
	public boolean estaDisponible(Calendar dia) {
		return true;
	}
}
