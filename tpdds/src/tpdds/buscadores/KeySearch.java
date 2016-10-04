package tpdds.buscadores;

import tpdds.pois.Poi;

public class KeySearch extends Buscador {

	private String keyWord;
	public KeySearch(String keyWord) {
		super();
		this.keyWord = keyWord;
	}
	public KeySearch(Buscador busc,String keyWord) {
		super();
		this.addedBuscador = busc;
		this.keyWord = keyWord;
	}

	@Override
	public boolean cumpleCriterio(Poi poi) {		
		return poi.contienePalabraClave(keyWord);
	}

}
