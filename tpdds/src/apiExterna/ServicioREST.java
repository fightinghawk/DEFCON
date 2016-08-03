package apiExterna;

import tpdds.pois.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import tpdds.database.*;

public class ServicioREST{
	
	private static ArrayList<Poi> listaPoi;
	
	public ServicioREST() throws ClassNotFoundException, SQLException{
		listaPoi.addAll(Generales.cargarPois());
		}
	
	public static ArrayList<Object> poiInactivo(){
		Random rnd = new Random();
		int numeroRandom = rnd.nextInt(listaPoi.size());
		Poi poiInactivo = listaPoi.get(numeroRandom);
		Calendar fechaBaja = new GregorianCalendar(2016, 5, 07);
		ArrayList<Object> poiYfecha = new ArrayList<Object>();
		poiYfecha.add(poiInactivo);
		poiYfecha.add(fechaBaja);
		return poiYfecha;	
	}
}