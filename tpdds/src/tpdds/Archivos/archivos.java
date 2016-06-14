package tpdds.Archivos;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import tpdds.dispositivo.Dispositivo;
import tpdds.factory.POIFactory;
import tpdds.pois.Bancos;
import tpdds.pois.CGP;
import tpdds.pois.ParadaColectivo;
import tpdds.pois.Poi;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Location;

public class archivos {
	
	public static void main(String[] args) throws Exception
	{
		ArrayList<Poi> listaPois;
		CGP cgp;
		CGP cgp14;
		CGP cgp13;
		Bancos banco;
		ParadaColectivo parada101;
		ParadaColectivo parada60;
		Dispositivo tablero;
		
		String arch = "example.dat";
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(arch));
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		
		Direccion direTablero = new Direccion("PARAGUAY", 2155, null, null, "RECOLETA");
		Location ubicacionTablero = new Location(-34.598415, -58.398260);
		tablero = new Dispositivo(1, direTablero, ubicacionTablero);
		tablero.setNombre("Dispositivo de prueba");
		
		String[] keyWordsa = { "cgp", "asesoramiento", "dinero" };
		cgp = POIFactory.crearCGP("CGP Recoleta", 
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
		String[] keyWords60 = { "transporte", "60", "parada" };
		parada60 = POIFactory.crearParadaColectivo("Parada 60", 
										"AYACUCHO", 901, 
										"PARAGUAY", "PARAGUAY", 
										"Balvanera", 
										-34.598700, -58.395881, 
										keyWords60);
		
		listaPois = new ArrayList<Poi>();
		listaPois.add(cgp);
		listaPois.add(cgp14);
		listaPois.add(cgp13);
		listaPois.add(banco);
		//listaPois.add(local2);
		listaPois.add(parada101);
		listaPois.add(parada60);
		
		oos.writeObject(tablero);
		oos.writeObject(listaPois);
		
		oos.close();
		bos.close();
	}

}
