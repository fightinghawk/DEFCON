package tpdds.Tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpdds.dispositivo.Dispositivo;
import tpdds.pois.Bancos;
import tpdds.pois.CGP;
import tpdds.pois.DiaPoi;
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
	CGP cgp;
	Bancos banco;
	ParadaColectivo parada101;
	ParadaColectivo parada60;
	Dispositivo tablero;
	
	@Before
	public void creacionPOI(){
		Comuna.inicializarComunas();
		listaPois = new ArrayList<Poi>();
		
		// Genero un dispositivo para localizar pois
		Direccion direTablero = new Direccion();
		direTablero.setCallePrincipal("PARAGUAY");
		direTablero.setBarrio("Recoleta");
		direTablero.setAltura(2155);
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
		
		Direccion direccionCG1 = new Direccion();
		direccionCG1.setCallePrincipal("DIAZ, CNEL. AV");
		direccionCG1.setCalleLateralIzq("BERUTI");
		direccionCG1.setCalleLateralDer("JUNCAL");
		direccionCG1.setBarrio("Recoleta");
		direccionCG1.setAltura(2110);
		Location ubicacionCGP1 = new Location(-34.587158, -58.409308);
		CGP cgp14 = new CGP("CGP Palermo", direccionCG1, ubicacionCGP1);
		String[] keyWordsa14 = { "cgp", "asesoramiento", "dinero", "rentas", "casamientos"};
		///DOMINGO=1...LUNES=2...SABADO=7
		cgp14.setDiasDisp(new DiaPoi(8,19,0,0,2));
		cgp14.setDiasDisp(new DiaPoi(8,19,0,0,3));
		cgp14.setDiasDisp(new DiaPoi(8,19,0,0,4));
		cgp14.setDiasDisp(new DiaPoi(8,19,0,0,5));
		cgp14.setDiasDisp(new DiaPoi(8,19,0,0,6));
		cgp14.agregarPalabra(keyWordsa14);
		
		Direccion direccionCG2 = new Direccion();
		direccionCG2.setCallePrincipal("CABILDO AV.");
		direccionCG2.setCalleLateralIzq("IBERA");
		direccionCG2.setCalleLateralDer("QUESADA");
		direccionCG2.setBarrio("N�nez");
		direccionCG2.setAltura(3067);
		Location ubicacionCGP2 = new Location(-34.553653, -58.463612);
		CGP cgp13 = new CGP("CGP N��ez", direccionCG2, ubicacionCGP2);
		String[] keyWordsa13 = { "cgp", "asesoramiento", "habilitaciones", "partidas"};
		///DOMINGO=1...LUNES=2...SABADO=7
		cgp13.setDiasDisp(new DiaPoi(8,19,0,0,2));
		cgp13.setDiasDisp(new DiaPoi(8,19,0,0,3));
		cgp13.setDiasDisp(new DiaPoi(8,19,0,0,4));
		cgp13.setDiasDisp(new DiaPoi(8,19,0,0,5));
		cgp13.setDiasDisp(new DiaPoi(8,19,0,0,6));
		cgp13.agregarPalabra(keyWordsa13);
		
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

		// Agrego los pois a la coleccion
		listaPois.add(cgp);
		listaPois.add(cgp14);
		listaPois.add(cgp13);
		listaPois.add(banco);
		listaPois.add(parada101);
		listaPois.add(parada60);
	}
	
	
	//Testea si todos los pois son validos
	@Test
	public void poiValido(){
		for (Poi poi : listaPois) {
			Assert.assertTrue("ERROR HAY POIS INVALIDOS",poi.esValido());
		}
	}
	
	//Calculador de distancia desde CGP a Banco
	@Test
	public void distanciaCGPBanco(){
		System.out.println("//////////////DISTANCIA ENTRE POIS");
		Assert.assertTrue(cgp.informarDistanciaA(banco) == 0.15691995201716);
	}
	
	//Cercan�a de Pois desde la Terminal
	@Test
	public void TerminalEstaCerca(){
		System.out.println("//////////////POIS CERCA DE TERMINAL");
		Assert.assertEquals(tablero.estaCerca(listaPois),3);
	}
	
	//TEST DISPONIBILIDAD - QUE POIS ESTAN ABIERTO EL 2 DE MAYO
	//TIENEN QUE ESTAR LOS 4 POIS DISPONIBLES PORQUE ES LUNES
	@Test
	public void EstanDisponibles(){
		System.out.println("//////////////POIS DISPONIBLES");
		Assert.assertEquals(tablero.estanDisponible(listaPois, 2, 14, 0),4);
	}
	
	
	//Test de B�squedas
	@Test
	public void Busco60(){
		System.out.println("//////////////BUSQUEDA POR 60");
	encontradosParadas = BuscadorPoi.buscar("60", listaPois);
	Assert.assertEquals(encontradosParadas.size(),1);
	}
	@Test
	public void BuscoTransporte(){
		System.out.println("//////////////BUSQUEDA POR RUBO TRANSPORTE");
	encontradosTransporte = BuscadorPoi.buscar("transporte", listaPois);
	Assert.assertEquals(encontradosTransporte.size(),2);
	}
	@Test
	public void BuscoAsesoramiento(){
		System.out.println("//////////////BUSQUEDA POR CLAVE ASESORAMIENTO");
	encontradosAsesoramiento = BuscadorPoi.buscar("asesoramiento", listaPois);
	Assert.assertEquals(encontradosAsesoramiento.size(),1);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
