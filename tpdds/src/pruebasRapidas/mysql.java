package pruebasRapidas;

import tpdds.database.Generales;
import tpdds.factory.POIFactory;
import tpdds.pois.Bancos;
import tpdds.pois.CGP;
import tpdds.pois.ParadaColectivo;
import tpdds.pois.Poi;

public class mysql {

	public static void main(String[] args) {

		CGP cgp14;
		CGP cgp13;
		Bancos banco;
		ParadaColectivo parada101;
		String[] keyWordsa = { "cgp", "asesoramiento", "dinero" };
		CGP cgp = POIFactory.crearCGP("CGP Recoleta", 
							"URIBURU", 1020, "SANTA FE AV.", "ALVEAR, MARCELO T. DE", 
							"RECOLETA", 
							-34.596621, -58.399182, 
							keyWordsa);
		String[] keyWordsa14 = { "cgp", "dinero", "rentas", "casamientos"};
		cgp14 = POIFactory.crearCGP("CGP Palermo", 
							"DIAZ, CNEL. AV", 2110, "BERUTI", "JUNCAL", 
							"PALERMO", 
							-34.587158, -58.409308, 
							keyWordsa14);
		String[] keyWordsa13 = { "cgp", "asesoramiento", "habilitaciones", "partidas"};
		cgp13 = POIFactory.crearCGP("CGP Núñez", 
							"CABILDO AV.", 3067, "IBERA", "QUESADA", 
							"Núnez", 
							-34.553653, -58.463612, 
							keyWordsa13);
		String[] keyWords = { "banco", "plata", "dinero" };
		banco = POIFactory.crearBanco("SANTANDER RIO", 
								"SANTA FE AV.", 2201, 
								"URIBURU", "AZCUENAGA", 
								"Recoleta", 
								-34.595290, -58.398612, 
								keyWords);
		String[] keyWords101 = { "transporte", "101", "parada" };
		parada101 = POIFactory.crearParadaColectivo("Parada 101", 
										"PARAGUAY", 2200, 
										"URIBURU", "URIBURU", 
										"Balvanera", 
										-34.598283, -58.399035, 
										keyWords101);
		
		ParadaColectivo parada60;
		String[] keyWords60 = { "transporte", "60", "parada" };
		parada60 = POIFactory.crearParadaColectivo("Parada 60", 
										"AYACUCHO", 901, 
										"PARAGUAY", "PARAGUAY", 
										"Balvanera", 
										-34.598700, -58.395881, 
										keyWords60);
		
		try{
		Generales.initDatabase();
		Generales.almacenarPOI(cgp13);
		Generales.almacenarPOI(cgp14);
		Generales.almacenarPOI(parada101);
		Generales.almacenarPOI(parada60);
		Generales.almacenarPOI(banco);
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
