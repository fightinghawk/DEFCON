package tpdds;

import java.util.ArrayList;
import java.util.Calendar;

import tpdds.dispositivo.Dispositivo;
import tpdds.pois.Bancos;
import tpdds.pois.CGP;
import tpdds.pois.DiaPoi;
import tpdds.pois.ParadaColectivo;
import tpdds.pois.Poi;
import tpdds.ubicacion.Comuna;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Location;

public class Main {

	public static ArrayList<Poi> buscadorPOIS(String palabraClave, ArrayList<Poi> Pois) {
		ArrayList<Poi> coincidencias = new ArrayList<Poi>();
		for (Poi poi : Pois) {
			if (poi.contienePalabraClave(palabraClave)) {
				coincidencias.add(poi);
			}
		}
		return coincidencias;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Genero una coleccion de Pois
		Comuna.inicializarComunas();
		ArrayList<Poi> listaPois = new ArrayList<Poi>();

		// Genero un dispositivo para localizar pois
		Direccion direTablero = new Direccion();
		direTablero.setCallePrincipal("PARAGUAY");
		direTablero.setBarrio("Recoleta");
		direTablero.setAltura(2155);
		Location ubicacionTablero = new Location(-34.598415, -58.398260);
		Dispositivo tablero = new Dispositivo(1, direTablero, ubicacionTablero);
		tablero.setNombre("Dispositivo de prueba");

		// Genero un CGP
		Direccion direccionCGO = new Direccion();
		direccionCGO.setCallePrincipal("URIBURU");
		direccionCGO.setCalleLateralIzq("SANTA FE AV.");
		direccionCGO.setCalleLateralDer("ALVEAR, MARCELO T. DE");
		direccionCGO.setBarrio("Recoleta");
		direccionCGO.setAltura(1020);
		Location ubicacionCGP = new Location(-34.596621, -58.399182);
		CGP cgp = new CGP("CGP Recoleta", direccionCGO, ubicacionCGP);
		String[] keyWordsa = { "cgp", "asesoramiento", "dinero" };
		cgp.setDiasDisp(new DiaPoi(10,20,0,0,7));
		cgp.agregarPalabra(keyWordsa);

		// Genero un BANCO
		Direccion direccionBanco = new Direccion();
		direccionBanco.setCallePrincipal("SANTA FE AV.");
		direccionBanco.setCalleLateralIzq("URIBURU");
		direccionBanco.setCalleLateralDer("AZCUENAGA");
		direccionBanco.setAltura(2201);
		Location ubicacionBanco = new Location(-34.595290, -58.398612);
		Bancos banco = new Bancos("Banco Santander Rio", direccionBanco, ubicacionBanco);
		String[] keyWords = { "banco", "plata", "dinero" };
		banco.agregarPalabra(keyWords);
		banco.setDiasDisp(new DiaPoi(10,15,0,0,5));
		banco.setRadioDeCuadras(0.4f);

		// Genero una Parada de Colectivo 101

		Direccion direccionParada101 = new Direccion();
		direccionParada101.setCallePrincipal("PARAGUAY");
		direccionParada101.setCalleLateralIzq("URIBURU");
		direccionParada101.setCalleLateralDer("URIBURU");
		direccionParada101.setAltura(2200);
		Location ubicacionParada101 = new Location(-34.598283, -58.399035);
		ParadaColectivo parada101 = new ParadaColectivo("Parada 101", direccionParada101, ubicacionParada101);
		String[] keyWords101 = { "colectivo", "101", "parada" };
		parada101.agregarPalabra(keyWords101);

		// Genero una Parada de Colectivo 60

		Direccion direccionParada60 = new Direccion();
		direccionParada60.setCallePrincipal("AYACUCHO");
		direccionParada60.setCalleLateralIzq("PARAGUAY");
		direccionParada60.setCalleLateralDer("PARAGUAY");
		direccionParada60.setAltura(901);
		Location ubicacionParada60 = new Location(-34.598700, -58.395881);
		ParadaColectivo parada60 = new ParadaColectivo("Parada 60", direccionParada60, ubicacionParada60);
		String[] keyWords60 = { "colectivo", "60", "parada" };
		parada60.agregarPalabra(keyWords60);

		// Agrego los pois a la coleccion
		listaPois.add(cgp);
		listaPois.add(banco);
		listaPois.add(parada101);
		listaPois.add(parada60);

		
		// Informo la distancia de un poi al otro
		System.out.println("//////DISTANCIA ENTRE POIS");
		cgp.informarDistanciaA(banco);
		// Requerimiento para la entrega 1 como para ir adelantando
		System.out.println("//////POIS CERCA DE LA TERMINAL");
		tablero.estaCerca(listaPois);
		System.out.println("-------------------------");
		System.out.println("//////PRUEBAS DE BUSQUEDAS");
		System.out.println("empiesa busqueda de: dinero");

		ArrayList<Poi> encontrados = buscadorPOIS("dinero", listaPois);

		for (Poi poi : encontrados) {
			poi.mostrarDatos();
		}

		System.out.println("empieza busqueda de: asesoramiento");

		ArrayList<Poi> encontradosAsesoramiento = buscadorPOIS("asesoramiento", listaPois);

		for (Poi poi : encontradosAsesoramiento) {
			poi.mostrarDatos();
		}

		System.out.println("empieza busqueda de: parada");

		ArrayList<Poi> encontradosParadas = buscadorPOIS("parada", listaPois);

		for (Poi poi : encontradosParadas) {
			poi.mostrarDatos();
		}
		
		for (Poi poi : listaPois) {
			// Ak es un 3 xq toma el dia del mes y no de la semana
			if(poi.estaDisponible(5, 14, 0)){
				System.out.println("El poi " + poi.getNombre() +" esta disponible");
			}
			else
			{
				System.out.println("El poi " + poi.getNombre() +" no esta disponible");
			}
		}
		
		
	}

}
