package factory;

import tpdds.pois.Bancos;
import tpdds.pois.CGP;
import tpdds.pois.ParadaColectivo;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Location;


public class POIFactory {
	
	public static Bancos crearBanco(String nombre, 
									String callePrincipal,int altura, 
									String calleLateralIzquierda, String calleLateralDerecha,
									String barrio,
									double latitud,
									double longitud,
									String[] palabrasClave)
	{
		Direccion direccion = new Direccion(callePrincipal, altura, calleLateralIzquierda, calleLateralDerecha, barrio);
		Location ubicacion = new Location(latitud, longitud);
		
		Bancos banco = new Bancos(nombre, direccion, ubicacion);
		/*
		 * Por ahora los horarios y las palabras claves se van a ingresar por setter. 
		 */
		banco.setDiasDisp(horarioFactory.horarioBanco());
		banco.agregarPalabra(palabrasClave);
		
		return banco;
	}
	
	public static CGP crearCGP(String nombre,
								String callePrincipal,int altura, 
								String calleLateralIzquierda, String calleLateralDerecha,
								String barrio,
								double latitud,
								double longitud,
								String[] palabrasClave)
	{
		Direccion direccion = new Direccion(callePrincipal, altura, calleLateralIzquierda, calleLateralDerecha, barrio);
		Location ubicacion = new Location(latitud, longitud);
		
		CGP cgp = new CGP(nombre, direccion, ubicacion);
		cgp.setDiasDisp(horarioFactory.horarioCGP(8, 0, 19, 0));
		cgp.agregarPalabra(palabrasClave);
		
		return cgp;
	}
		
	public static ParadaColectivo crearParadaColectivo(String nombre,
														String callePrincipal,int altura, 
														String calleLateralIzquierda, String calleLateralDerecha,
														String barrio,
														double latitud,
														double longitud,
														String[] palabrasClave)
	{
		Direccion direccion = new Direccion(callePrincipal, altura, calleLateralIzquierda, calleLateralDerecha, barrio);
		Location ubicacion = new Location(latitud, longitud);
		ParadaColectivo paradaColectivo = new ParadaColectivo(nombre, direccion, ubicacion);
		paradaColectivo.agregarPalabra(palabrasClave);
		
		return paradaColectivo;
	}
	
}
