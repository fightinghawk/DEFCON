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
import apiExterna.jsonBancos;
import apiExterna.jsonHTTP;
import tpdds.Archivos.archivos;
import tpdds.database.Generales;
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

	static boolean listo = false;
	static ArrayList<Poi> listaPois;
	ArrayList<Poi> encontradosParadas;
	ArrayList<Poi> encontradosTransporte;
	ArrayList<Poi> encontradosAsesoramiento;
	List<BancoExterna> bancoexternalista;
	jsonBancos apiBancos;
	static Dispositivo tablero;
	Integer resultado;
	
	@SuppressWarnings("unchecked")
	@Before
	public void creacionPOI() throws Exception{
		if(listo){
			return;
		}
		Comuna.inicializarComunas();
		Generales.initDatabase();
		tablero = archivos.obtenerTablero();
		listaPois = Generales.cargarPois();
		listo=true;
	}
	
	
	//Testea si todos los pois son validos
	@Test
	public void poiValido(){
		for (Poi poi : listaPois) {
			Assert.assertTrue("ERROR HAY POIS INVALIDOS",poi.esValido());
		}
	}
	
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
		apiBancos = new jsonBancos();
		bancoexternalista = apiBancos.FiltrarBancos("http://private-96b476-ddsutn.apiary-mock.com","banks","bancos","servicio");
		apiBancos.mostrarLista(bancoexternalista);
		Assert.assertEquals(bancoexternalista.size(), 2);
	}
	
	
	
	
	
	
	
	
	
}
