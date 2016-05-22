package tpdds.factory;

import java.util.ArrayList;

import tpdds.pois.DiaPoi;

public class horarioFactory {
	/*Los días están numerados:
	 * Domingo = 1,
	 * Lunes = 2,
	 * Martes = 3,
	 * Miércoles = 4;
	 * Jueves = 5;
	 * Viernes = 6;
	 * Sábado = 7.
	 */
	
	/*si bien se cubre en general los tipos de horario,
	 *un horario en particular va a haber que agregarlo a mano.
	 *Ejemplo: 
	 * que un CGP abra un sábado necesitará agregar a la lista de horarios de CGP
	 * un nuevo DiaPoi
	 */
	
	/*
	 * NO SE CONTEMPLAN FERIADOS. Agregar a mano.
	 */
	
	public static ArrayList<DiaPoi> horarioBanco()
	{
		/*Crea horario de banco:
		 * lunes a viernes (2,3,4,5,6)
		 * de 9.00 a 15.00 Hs.
		 */
		int apertura = 9;
		int aperturaMinutos = 0;
		int cierre = 15;
		int cierreMinutos = 0;
		ArrayList<DiaPoi> diasDisponibles = new ArrayList<DiaPoi>();
		for (int dia = 2; dia < 7; dia++) {
			diasDisponibles.add(new DiaPoi(apertura,cierre,aperturaMinutos,cierreMinutos,dia));
		}
		
		return diasDisponibles;
	}
	
	public static ArrayList<DiaPoi> horarioCGP(int apertura, int aperturaMinutos, 
												int cierre, int cierreMinutos)
	{
		/*Crea horarios de atencion de CGP:
		 * lunes a viernes (2,3,4,5,6)
		 * en los horarios (apertura, aperturaMinutos) y (cierre, cierreMinutos).
		 * Este es el horario de la sección informes de un CGP en particular,
		 * otros servicios tienen otros horarios y días,
		 * por ejemplo: comuna 14
		 *  informes lunes a viernes de 9 a 19,
		 *  rentas: lunes a viernes de 8.30 a 18, 
		 *  registro civil: lunes a viernes
		 * pero quedó el más largo para hacerlo uniforme.
		 */
		ArrayList<DiaPoi> diasDisponibles = new ArrayList<DiaPoi>();
		
		for (int dia = 2; dia <= 6; dia++) {
			diasDisponibles.add(new DiaPoi(apertura,cierre,aperturaMinutos,cierreMinutos,dia));
		}
		
		return diasDisponibles;
	}
	
	public static ArrayList<DiaPoi> horarioLocalAbreDeCorridoYFinDeSemana(int apertura, int aperturaMinutos, 
																			int cierre, int cierreMinutos)
	{
		/*Crea horarios de atención para un local comercial que abre de corrido:
		 * Lunes a domingos (por convención de domingos a sábados),
		 * en los horarios (apertura, aperturaMinutos) y (cierre, cierreMinutos).
		 * 
		 * Sirve para locales como shoppings y supermercados.
		 */
		ArrayList<DiaPoi> diasDisponibles = new ArrayList<DiaPoi>();

		for (int dia = 1; dia <= 7; dia++) {
			diasDisponibles.add(new DiaPoi(apertura,cierre,aperturaMinutos,cierreMinutos,dia));
		}
		
		return diasDisponibles;
	}
	
	public static ArrayList<DiaPoi> horarioLocalAbreDosTurnos(int aperturaManiana, int aperturaManianaMinutos, 
																int cierreManiana, int cierreManianaMinutos,
																int aperturaTarde, int aperturaTardeMinutos,
																int cierreTarde, int cierreTardeMinutos,
																boolean abreFinDeSemana)
	{
	/*Crea horarios de atención para un local comercial con turnos mañana/tarde:
	* Lunes a domingos (por convención de domingos a sábados) si abreFinDeSemana == true,
	* en los horarios (apertura, aperturaMinutos) y (cierre, cierreMinutos).
	* Ejemplo:
	*  lunes a viernes de 9 a 13 y 17 a 21 Hs.
	* 
	* Sirve para pequeños locales.
	*/
		ArrayList<DiaPoi> diasDisponibles = new ArrayList<DiaPoi>();
		int diaInicio;
		int diaFin;
		if(abreFinDeSemana)
		{
			diaInicio = 1; /*abre domingo*/
			diaFin = 7; /*abre sábado*/
		}
		else
		{
			diaInicio = 2; /*no abre domingo*/
			diaFin = 6; /*no abre sábado*/
		}
		for (int dia = diaInicio; dia <= diaFin; dia++) {
			diasDisponibles.add(new DiaPoi(aperturaManiana,cierreManiana,aperturaManianaMinutos,cierreManianaMinutos,dia));
			diasDisponibles.add(new DiaPoi(aperturaTarde,cierreTarde,aperturaTardeMinutos,cierreTardeMinutos,dia));
		}
		
		return diasDisponibles;
	}	
	
	
}
