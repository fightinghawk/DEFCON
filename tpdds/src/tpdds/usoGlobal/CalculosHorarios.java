package tpdds.usoGlobal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalculosHorarios {
	
	public static int getActualYear(){
		return new GregorianCalendar().get(Calendar.YEAR);
	}
	
	public static int getActualMonth(){
		return new GregorianCalendar().get(Calendar.MONTH);
	}
	
	public static boolean disponibilidadHoraria(int horaApertura,int horaClose,int minApertura, int minClose,Calendar time){
		if(Calculos.entre(horaApertura, horaClose,time.get(Calendar.HOUR_OF_DAY) )){
			return true;
		}
		else if(time.get(Calendar.HOUR_OF_DAY)==horaClose){
			if(time.get(Calendar.MINUTE)<minClose){
				return true;
			}
			else 
				return false;
		}
		else if(time.get(Calendar.HOUR_OF_DAY)==horaApertura){
			if(time.get(Calendar.MINUTE)>minApertura){
				return true;
			}
			else 
				return false;
		}
		else
			return false;
	}

	public static boolean disponibilidadDia(int diasDisp, Calendar time){	
		return diasDisp == Calendar.DAY_OF_WEEK;
	}
}
