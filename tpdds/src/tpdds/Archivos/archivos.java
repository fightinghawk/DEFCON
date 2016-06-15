package tpdds.Archivos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
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
		Dispositivo tablero;
		tablero = archivos.obtenerTablero();

		
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
		
		listaPois = new ArrayList<Poi>();
		tablero.agregarPOI(cgp, listaPois);
		tablero.agregarPOI(cgp14, listaPois);
		tablero.agregarPOI(cgp13, listaPois);
		tablero.agregarPOI(banco, listaPois);
		tablero.agregarPOI(parada101, listaPois);
		
		ParadaColectivo parada60;
		String[] keyWords60 = { "transporte", "60", "parada" };
		parada60 = POIFactory.crearParadaColectivo("Parada 60", 
										"AYACUCHO", 901, 
										"PARAGUAY", "PARAGUAY", 
										"Balvanera", 
										-34.598700, -58.395881, 
										keyWords60);
		
		tablero.agregarPOI(parada60, listaPois);
		tablero.agregarPOI(parada60, listaPois);
		tablero.eliminarPOI("parada 60", listaPois);
		archivos.grabarArchivo(listaPois);
	}
	
	public static void grabarArchivo(ArrayList<Poi> listaPois) throws Exception
	{
		String arch = "example1.dat";
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(arch));
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		
		oos.writeObject(listaPois);
		
		oos.close();
		bos.close();
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Poi> leerArchivo(String archivo) throws Exception
	{
		String arch = archivo;
		ArrayList<Poi> listaPois;
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(arch));
		ObjectInputStream ois = new ObjectInputStream(bis);
		
		listaPois = (ArrayList<Poi>)ois.readObject();

		ois.close();
		bis.close();
		
		return listaPois;
	}

	public static Dispositivo obtenerTablero()
	{
		Dispositivo tablero;
		
		Direccion direTablero = new Direccion("PARAGUAY", 2155, null, null, "RECOLETA");
		Location ubicacionTablero = new Location(-34.598415, -58.398260);
		tablero = new Dispositivo(1, direTablero, ubicacionTablero);
		tablero.setNombre("Dispositivo de prueba");
		
		return tablero;
	}
}
