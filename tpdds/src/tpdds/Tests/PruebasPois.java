package tpdds.Tests;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.stream.FileImageInputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import apiExterna.BancoExterna;
import apiExterna.jsonHTTP;
import tpdds.Archivos.archivos;
import tpdds.dispositivo.Dispositivo;
import tpdds.factory.POIFactory;
import tpdds.factory.horarioFactory;
import tpdds.pois.Bancos;
import tpdds.pois.CGP;
import tpdds.pois.DiaPoi;
import tpdds.pois.LocalesComerciales;
import tpdds.pois.ParadaColectivo;
import tpdds.pois.Poi;
import tpdds.ubicacion.Comuna;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Location;
import tpdds.usoGlobal.BuscadorPoi;

public class PruebasPois {

	ArrayList<Poi> listaPois;
	ArrayList<Poi> encontradosParadas;
	ArrayList<Poi> encontradosTransporte;
	ArrayList<Poi> encontradosAsesoramiento;
	List<BancoExterna> bancoexternalista;
	jsonHTTP apiExterna;
	Dispositivo tablero;
	
	@SuppressWarnings("unchecked")
	@Before
	public void creacionPOI() throws Exception{
		Comuna.inicializarComunas();
		
		tablero = archivos.obtenerTablero();
		listaPois = archivos.leerArchivo("example1.dat");
				
	/*	listaPois = new ArrayList<Poi>();
		
		// Genero un dispositivo para localizar pois
		
		Direccion direTablero = new Direccion();
		direTablero.setCallePrincipal("PARAGUAY");
		direTablero.setBarrio("Recoleta");
		direTablero.setAltura(2155);
		Location ubicacionTablero = new Location(-34.598415, -58.398260);
		tablero = new Dispositivo(1, direTablero, ubicacionTablero);
		tablero.setNombre("Dispositivo de prueba");
		
		Direccion direTablero = new Direccion("PARAGUAY", 2155, null, null, "RECOLETA");
		Location ubicacionTablero = new Location(-34.598415, -58.398260);
		tablero = new Dispositivo(1, direTablero, ubicacionTablero);
		tablero.setNombre("Dispositivo de prueba");
		
		
		// Genero un CGP
		
		Direccion direccionCGO = new Direccion();
		direccionCGO.setCallePrincipal("URIBURU");
		direccionCGO.setCalleLateralIzq("SANTA FE AV.");
		direccionCGO.setCalleLateralDer("ALVEAR, MARCELO T. DE");
		direccionCGO.setBarrio("Recoleta");
		direccionCGO.setAltura(1020);
		Location ubicacionCGP = new Location(-34.596621, -58.399182);
		cgp = new CGP("CGP Recoleta", direccionCGO, ubicacionCGP);
		String[] keyWordsa = { "cgp", "asesoramiento", "dinero" };
		///DOMINGO=1...LUNES=2...SABADO=7
		cgp.setDiasDisp(new DiaPoi(8,19,0,0,2));
		cgp.setDiasDisp(new DiaPoi(8,19,0,0,3));
		cgp.setDiasDisp(new DiaPoi(8,19,0,0,4));
		cgp.setDiasDisp(new DiaPoi(8,19,0,0,5));
		cgp.setDiasDisp(new DiaPoi(8,19,0,0,6));
		cgp.agregarPalabra(keyWordsa);
		
		
		//INICIO CGP Comuna 2
		String[] keyWordsa = { "cgp", "asesoramiento", "dinero" };
		cgp = POIFactory.crearCGP("CGP Recoleta", 
							"URIBURU", 1020, "SANTA FE AV.", "ALVEAR, MARCELO T. DE", 
							"RECOLETA", 
							-34.596621, -58.399182, 
							keyWordsa);
		//FIN CGP Comuna 2
		
		
		Direccion direccionCG1 = new Direccion();
		direccionCG1.setCallePrincipal("DIAZ, CNEL. AV");
		direccionCG1.setCalleLateralIzq("BERUTI");
		direccionCG1.setCalleLateralDer("JUNCAL");
		direccionCG1.setBarrio("Palermo");
		direccionCG1.setAltura(2110);
		Location ubicacionCGP1 = new Location(-34.587158, -58.409308);
		cgp14 = new CGP("CGP Palermo", direccionCG1, ubicacionCGP1);
		String[] keyWordsa14 = { "cgp", "dinero", "rentas", "casamientos"};
		///DOMINGO=1...LUNES=2...SABADO=7
		//cgp14.setDiasDisp(new DiaPoi(8,19,0,0,2));
		cgp14.setDiasDisp(new DiaPoi(8,19,0,0,3));
		cgp14.setDiasDisp(new DiaPoi(8,19,0,0,4));
		cgp14.setDiasDisp(new DiaPoi(8,19,0,0,5));
		cgp14.setDiasDisp(new DiaPoi(8,19,0,0,6));
		cgp14.agregarPalabra(keyWordsa14);
		
		String[] keyWordsa14 = { "cgp", "dinero", "rentas", "casamientos"};
		cgp14 = POIFactory.crearCGP("CGP Palermo", 
							"DIAZ, CNEL. AV", 2110, "BERUTI", "JUNCAL", 
							"PALERMO", 
							-34.587158, -58.409308, 
							keyWordsa14);
		
		
		Direccion direccionCG2 = new Direccion();
		direccionCG2.setCallePrincipal("CABILDO AV.");
		direccionCG2.setCalleLateralIzq("IBERA");
		direccionCG2.setCalleLateralDer("QUESADA");
		direccionCG2.setBarrio("Núnez");
		direccionCG2.setAltura(3067);
		Location ubicacionCGP2 = new Location(-34.553653, -58.463612);
		cgp13 = new CGP("CGP Núñez", direccionCG2, ubicacionCGP2);
		String[] keyWordsa13 = { "cgp", "asesoramiento", "habilitaciones", "partidas"};
		///DOMINGO=1...LUNES=2...SABADO=7
		cgp13.setDiasDisp(new DiaPoi(8,19,0,0,2));
		cgp13.setDiasDisp(new DiaPoi(8,19,0,0,3));
		cgp13.setDiasDisp(new DiaPoi(8,19,0,0,4));
		cgp13.setDiasDisp(new DiaPoi(8,19,0,0,5));
		cgp13.setDiasDisp(new DiaPoi(8,19,0,0,6));
		cgp13.agregarPalabra(keyWordsa13);
		
		String[] keyWordsa13 = { "cgp", "asesoramiento", "habilitaciones", "partidas"};
		cgp13 = POIFactory.crearCGP("CGP Núñez", 
							"CABILDO AV.", 3067, "IBERA", "QUESADA", 
							"Núnez", 
							-34.553653, -58.463612, 
							keyWordsa13);
		
		// Genero un BANCO
		
		Direccion direccionBanco = new Direccion();
		direccionBanco.setCallePrincipal("SANTA FE AV.");
		direccionBanco.setCalleLateralIzq("URIBURU");
		direccionBanco.setCalleLateralDer("AZCUENAGA");
		direccionBanco.setAltura(2201);
		Location ubicacionBanco = new Location(-34.595290, -58.398612);
		banco = new Bancos("Banco Santander Rio", direccionBanco, ubicacionBanco);
		String[] keyWords = { "banco", "plata", "dinero" };
		banco.agregarPalabra(keyWords);
		///BANCO ABIERTO DE LUNES A VIERNES
		banco.setDiasDisp(new DiaPoi(10,15,0,0,2));
		banco.setDiasDisp(new DiaPoi(10,15,0,0,3));
		banco.setDiasDisp(new DiaPoi(10,15,0,0,4));
		banco.setDiasDisp(new DiaPoi(10,15,0,0,5));
		banco.setDiasDisp(new DiaPoi(10,15,0,0,6));
		banco.setRadioDeCuadras(0.4f);
		
		
		//INICIO Banco1
		String[] keyWords = { "banco", "plata", "dinero" };
		banco = POIFactory.crearBanco("SANTANDER RIO", 
								"SANTA FE AV.", 2201, 
								"URIBURU", "AZCUENAGA", 
								"Recoleta", 
								-34.595290, -58.398612, 
								keyWords);
		
		
		//Genero un local comercial
		
		Direccion dirLocal1 = new Direccion();
		dirLocal1.setCallePrincipal("LA PLATA");
		dirLocal1.setCalleLateralIzq("INCLAN");
		dirLocal1.setCalleLateralDer("LAS CASAS");
		dirLocal1.setAltura(1650);
		Location ubicLocal1 = new Location(-34.634733, -58.423749);
		LocalesComerciales local1 = new LocalesComerciales("Carrefour", "Supermercado", dirLocal1, ubicLocal1);
		
		
		Direccion dirLocal2 = new Direccion();
		dirLocal2.setCallePrincipal("JUNIN");
		dirLocal2.setCalleLateralIzq("TUCUMAN");
		dirLocal2.setCalleLateralDer("VIAMONTE");
		dirLocal2.setAltura(611);
		Location ubicLocal2 = new Location(-34.602008, -58.396915);
		LocalesComerciales local2 = new LocalesComerciales("Carnicería Tomasito", "Carnicería", dirLocal2, ubicLocal2);
		local2.setDiasDisp(new DiaPoi(9, 13, 0, 0, 1));
		local2.setDiasDisp(new DiaPoi(9, 13, 0, 0, 2));
		local2.setDiasDisp(new DiaPoi(17, 21, 0, 0, 2));
		local2.setDiasDisp(new DiaPoi(9, 13, 0, 0, 3));
		local2.setDiasDisp(new DiaPoi(17, 21, 0, 0, 3));
		local2.setDiasDisp(new DiaPoi(9, 13, 0, 0, 4));
		local2.setDiasDisp(new DiaPoi(17, 21, 0, 0, 4));
		local2.setDiasDisp(new DiaPoi(9, 13, 0, 0, 5));
		local2.setDiasDisp(new DiaPoi(17, 21, 0, 0, 5));
		local2.setDiasDisp(new DiaPoi(9, 13, 0, 0, 6));
		local2.setDiasDisp(new DiaPoi(17, 21, 0, 0, 6));
		local2.setDiasDisp(new DiaPoi(9, 13, 0, 0, 7));
		local2.setDiasDisp(new DiaPoi(17, 21, 0, 0, 7));
		
		
		// Genero una Parada de Colectivo 101
		
		Direccion direccionParada101 = new Direccion();
		direccionParada101.setCallePrincipal("PARAGUAY");
		direccionParada101.setCalleLateralIzq("URIBURU");
		direccionParada101.setCalleLateralDer("URIBURU");
		direccionParada101.setAltura(2200);
		Location ubicacionParada101 = new Location(-34.598283, -58.399035);
		parada101 = new ParadaColectivo("Parada 101", direccionParada101, ubicacionParada101);
		String[] keyWords101 = { "transporte", "101", "parada" };
		parada101.agregarPalabra(keyWords101);
		
		
		String[] keyWords101 = { "transporte", "101", "parada" };
		parada101 = POIFactory.crearParadaColectivo("Parada 101", 
										"PARAGUAY", 2200, 
										"URIBURU", "URIBURU", 
										"Balvanera", 
										-34.598283, -58.399035, 
										keyWords101);

		// Genero una Parada de Colectivo 60
		
		Direccion direccionParada60 = new Direccion();
		direccionParada60.setCallePrincipal("AYACUCHO");
		direccionParada60.setCalleLateralIzq("PARAGUAY");
		direccionParada60.setCalleLateralDer("PARAGUAY");
		direccionParada60.setAltura(901);
		Location ubicacionParada60 = new Location(-34.598700, -58.395881);
		parada60 = new ParadaColectivo("Parada 60", direccionParada60, ubicacionParada60);
		String[] keyWords60 = { "transporte", "60", "parada" };
		parada60.agregarPalabra(keyWords60);
		
		
		String[] keyWords60 = { "transporte", "60", "parada" };
		parada60 = POIFactory.crearParadaColectivo("Parada 60", 
										"AYACUCHO", 901, 
										"PARAGUAY", "PARAGUAY", 
										"Balvanera", 
										-34.598700, -58.395881, 
										keyWords60);
		
		// Agrego los pois a la coleccion
		listaPois.add(cgp);
		listaPois.add(cgp14);
		listaPois.add(cgp13);
		listaPois.add(banco);
		//listaPois.add(local2);
		listaPois.add(parada101);
		listaPois.add(parada60);*/
	}
	
	
	//Testea si todos los pois son validos
	@Test
	public void poiValido(){
		for (Poi poi : listaPois) {
			Assert.assertTrue("ERROR HAY POIS INVALIDOS",poi.esValido());
		}
	}
	
/*	//Calculador de distancia desde CGP a Banco
	@Test
	public void distanciaCGPBanco(){
		System.out.println("//////////////DISTANCIA ENTRE POIS");
		Assert.assertTrue(cgp.informarDistanciaA(banco) == 0.15691995201716);
		
		Assert.assertTrue(cgp14.informarDistanciaA(banco) == 0.15691995201716);
		Assert.assertTrue(cgp13.informarDistanciaA(banco) == 0.15691995201716);
		
	}*/
	
	//Cercanía de Pois desde la Terminal
	@Test
	public void TerminalEstaCerca(){
		System.out.println("//////////////POIS CERCA DE TERMINAL");
		Assert.assertEquals(tablero.estaCerca(listaPois),2);
	}
	
	//TEST DISPONIBILIDAD - QUE POIS ESTAN ABIERTO EL 2 DE MAYO
	//TIENEN QUE ESTAR LOS 4 POIS DISPONIBLES PORQUE ES LUNES
	@Test
	public void EstanDisponibles(){
		System.out.println("//////////////POIS DISPONIBLES");
		Assert.assertEquals(tablero.estanDisponible(listaPois, 2, 14, 0),6);
	}
	
	
	//Test de Búsquedas
	@Test
	public void Busco60(){
		System.out.println("//////////////BUSQUEDA POR 60");
	encontradosParadas = BuscadorPoi.buscar("60", listaPois);
	Assert.assertEquals(encontradosParadas.size(),1);
	}
	@Test
	public void BuscoTransporte(){
		System.out.println("//////////////BUSQUEDA POR RUBRO TRANSPORTE");
	encontradosTransporte = BuscadorPoi.buscar("transporte", listaPois);
	Assert.assertEquals(encontradosTransporte.size(),2);
	}
	@Test
	public void BuscoAsesoramiento(){
		System.out.println("//////////////BUSQUEDA POR CLAVE ASESORAMIENTO");
	encontradosAsesoramiento = BuscadorPoi.buscar("asesoramiento", listaPois);
	Assert.assertEquals(encontradosAsesoramiento.size(),2);
	}
	@Test
	public void agregarColectivo60() throws Exception{
	System.out.println("//////////////AGREGAR/QUITAR POI");
	Assert.assertEquals(listaPois.size(),6);
	}
	@Test
	public void ListaBancosExternos() throws Exception {
		System.out.println("//////////////CANTIDAD BANCOS POR API EXTERNA");
		apiExterna = new jsonHTTP();
		bancoexternalista = apiExterna.obtenerDatos(
				"http://private-96b476-ddsutn.apiary-mock.com/banks?banco=banco&servicio=servicio", BancoExterna.class);
		apiExterna.mostrarLista(bancoexternalista);
		Assert.assertEquals(bancoexternalista.size(), 2);
	}
	
	
	
	
	
	
	
	
	
	
	
}
