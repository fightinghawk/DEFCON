package tpdds.usoGlobal;

import java.util.ArrayList;
import java.util.Calendar;

public class Constantes {
	public static ArrayList<Integer> diasBancos;
	
	public static int horaBancoApertura = 10;
	public static int horaBancoCierra = 15;
	
	public static void inicializarConstantes(){
		diasBancos.add(Calendar.MONDAY);
		diasBancos.add(Calendar.TUESDAY);
		diasBancos.add(Calendar.WEDNESDAY);
		diasBancos.add(Calendar.THURSDAY);
		diasBancos.add(Calendar.FRIDAY);
	}
}
