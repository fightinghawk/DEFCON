package pruebasRapidas;

import tpdds.database.Generales;
import tpdds.factory.POIFactory;
import tpdds.pois.CGP;
import tpdds.pois.Poi;

public class mysql {

	public static void main(String[] args) {

		String[] keyWordsa = { "cgp", "asesoramiento", "dinero" };
		CGP cgp = POIFactory.crearCGP("CGP Recoleta", 
							"URIBURU", 1020, "SANTA FE AV.", "ALVEAR, MARCELO T. DE", 
							"RECOLETA", 
							-34.596621, -58.399182, 
							keyWordsa);
		
		try{
		Generales.initDatabase();
		Generales.almacenarPOI(cgp);
		Poi nuevo = Generales.cargarPois().get(0);
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
