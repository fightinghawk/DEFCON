package tpdds.buscadores;

import tpdds.pois.Poi;

public class TipoSearch extends Buscador {

	private String tipoBuscado;
	
	public TipoSearch(Buscador busc,String tipo) {
		super();
		this.addedBuscador = busc;
		this.tipoBuscado = tipo;
	}
	
	@Override
	public boolean cumpleCriterio(Poi poi) {
		return poi.getTipo().equalsIgnoreCase(this.tipoBuscado);
	}

}
