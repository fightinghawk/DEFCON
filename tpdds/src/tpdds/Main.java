package tpdds;

import java.util.ArrayList;

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

		ArrayList<Poi> listaPois = new ArrayList<Poi>();

		// Genero un dispositivo para localizar pois
		Direccion direTablero = new Direccion();
		direTablero.setCallePrincipal("PARAGUAY");
		direTablero.setAltura(2155);
		Location ubicacionTablero = new Location(-34.598415, -58.398260);
		Dispositivo tablero = new Dispositivo(1, direTablero, ubicacionTablero);

		// Genero un CGP
		Direccion direccionCGO = new Direccion();
		direccionCGO.setCallePrincipal("URIBURU");
		direccionCGO.setCalleLateralIzq("SANTA FE AV.");
		direccionCGO.setCalleLateralDer("ALVEAR, MARCELO T. DE");
		direccionCGO.setAltura(1020);
		Location ubicacionCGP = new Location(-34.596621, -58.399182);
		Poi cgp = new Poi("CGP Recoleta", 1, direccionCGO, ubicacionCGP);
		String[] keyWordsa = { "cgp", "asesoramiento", "dinero" };
		cgp.agregarPalabra(keyWordsa);

		// Genero un BANCO

		Direccion direccionBanco = new Direccion();
		direccionBanco.setCallePrincipal("SANTA FE AV.");
		direccionBanco.setCalleLateralIzq("URIBURU");
		direccionBanco.setCalleLateralDer("AZCUENAGA");
		direccionBanco.setAltura(2201);
		Location ubicacionBanco = new Location(-34.595290, -58.398612);
		Poi banco = new Poi("Banco Santander Rio", 3, direccionBanco, ubicacionBanco);
		String[] keyWords = { "banco", "plata", "dinero" };
		banco.agregarPalabra(keyWords);

		// Genero una Parada de Colectivo 101

		Direccion direccionParada101 = new Direccion();
		direccionParada101.setCallePrincipal("URIBURU");
		direccionParada101.setCalleLateralIzq("PARAGUAY");
		direccionParada101.setCalleLateralDer("PARAGUAY");
		direccionParada101.setAltura(901);
		Location ubicacionParada101 = new Location(-34.598283, -58.399035);
		ParadaColectivo parada101 = new ParadaColectivo("Parada 101", 2, direccionParada101, ubicacionParada101);
		String[] keyWords101 = { "colectivo", "101", "parada" };
		parada101.agregarPalabra(keyWords101);

		// Genero una Parada de Colectivo 60

		Direccion direccionParada60 = new Direccion();
		direccionParada60.setCallePrincipal("AYACUCHO");
		direccionParada60.setCalleLateralIzq("PARAGUAY");
		direccionParada60.setCalleLateralDer("PARAGUAY");
		direccionParada60.setAltura(2200);
		Location ubicacionParada60 = new Location(-34.598700, -58.395881);
		ParadaColectivo parada60 = new ParadaColectivo("Parada 60", 2, direccionParada60, ubicacionParada60);
		String[] keyWords60 = { "colectivo", "60", "parada" };
		parada60.agregarPalabra(keyWords60);

		// Agrego los pois a la coleccion
		listaPois.add(cgp);
		listaPois.add(banco);
		listaPois.add(parada101);
		listaPois.add(parada60);

		// Muestro datos de los pois
		System.out.println("-------------------------");
		System.out.println("//////MOSTRAR DATOS POIS");
		cgp.mostrarDatos();
		banco.mostrarDatos();
		parada101.mostrarDatos();
		parada60.mostrarDatos();
		System.out.println("-------------------------");
		// Informo la distancia de un poi al otro
		System.out.println("//////DISTANCIA ENTRE POIS");
		cgp.informarDistanciaA(banco);
		System.out.println("-------------------------");
		// Informo la distancia de un poi al otro
		System.out.println("//////FUNCION EXTRA: DESDE EL CGP HASTA EL BANCO HAY MENOS DE 160 METROS?");
		cgp.estaCerca(banco,0.16);
		System.out.println("-------------------------");
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

		System.out.println("empiesa busqueda de: asesoramiento");

		ArrayList<Poi> encontradosAsesoramiento = buscadorPOIS("asesoramiento", listaPois);

		for (Poi poi : encontradosAsesoramiento) {
			poi.mostrarDatos();
		}

		System.out.println("empiesa busqueda de: parada");

		ArrayList<Poi> encontradosParadas = buscadorPOIS("parada", listaPois);

		for (Poi poi : encontradosParadas) {
			poi.mostrarDatos();
		}
	}

}
