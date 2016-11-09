package tpdds.apiExterna;

import tpdds.pois.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import tpdds.database.*;
import tpdds.interfaz.Main;

public class ServicioREST{
	
	private static ArrayList<Poi> listaPoi = Main.pois;
	
	public ServicioREST() throws ClassNotFoundException, SQLException{
		}
	
	public static int poiInactivo(){
		Random rnd = new Random();
		int numeroRandom = rnd.nextInt(2);
		return numeroRandom;
	}
}