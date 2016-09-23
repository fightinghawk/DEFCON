package tpdds.buscadores;

import java.util.ArrayList;

import tpdds.pois.Poi;

public abstract class Buscador {

	protected Buscador addedBuscador;
	
	public ArrayList<Poi> aplicarBuscador(ArrayList<Poi> pois){
		ArrayList<Poi> filtrados = new ArrayList<>();
		for (Poi temp : pois) {
			if(cumpleCriterio(temp)){
				filtrados.add(temp);
			}
		}
		if(addedBuscador==null)
			return filtrados;
		else
			return addedBuscador.aplicarBuscador(filtrados);
	}
	
	public abstract boolean cumpleCriterio(Poi poi);
	
	}
