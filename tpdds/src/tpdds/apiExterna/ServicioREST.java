package tpdds.apiExterna;

import tpdds.pois.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import tpdds.database.*;

public class ServicioREST{
	
	private static ArrayList<Poi> listaPoi = Generales.cargarPois();
	
	public ServicioREST() throws ClassNotFoundException, SQLException{
		}
	
	public static Poi poiInactivo(){
		Random rnd = new Random();
		int numeroRandom = rnd.nextInt(listaPoi.size());
		Poi poiInactivo = listaPoi.get(numeroRandom);
		return poiInactivo;	
	}
}