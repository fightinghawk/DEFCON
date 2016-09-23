package tpdds.buscadores;

import tpdds.pois.Poi;

public class NameSeach extends Buscador {
	
	private String nombre_buscado;
	
	public NameSeach(String nombre_buscado){this.nombre_buscado = nombre_buscado;}
	public NameSeach(Buscador busc,String nombre_buscado){this.addedBuscador = busc; this.nombre_buscado = nombre_buscado;}
	@Override
	public boolean cumpleCriterio(Poi poi) {
		return nombre_buscado.contains(poi.getNombre()) || poi.getNombre().contains(nombre_buscado);
	}
	
	
	
}
