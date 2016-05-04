package tpdds;

import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Generacion a modo de prueba de un poi, faltan armar los contructores
		// de todos salvo localizacion.
		
		ArrayList<Poi> lPois = new ArrayList<Poi>();
		

		Direccion direccionCGO = new Direccion();

		direccionCGO.setCallePrincipal("Av.Jujuy");
		direccionCGO.setCalleLateralIzq("Humberto 1ro");
		direccionCGO.setCalleLateralDer("Carlos Calvo");
		direccionCGO.setAltura(765);

		Location ubicacionCGP = new Location(-34.621854, -58.402526);

		Poi cgp = new Poi("CGP San Cristobal", 1, direccionCGO, ubicacionCGP);
		// El tipo esta con numeros para que despues creemos una tabla de tipos,
		// con una referencia numerica, eso despues lo ponemos
		// creamos un case y listo, para que no carguemos de varias formas
		// distintas lo mismo

		// Otro Poi prueba

		Direccion direccionBanco = new Direccion();

		direccionBanco.setCallePrincipal("Brandsen");
		direccionBanco.setCalleLateralIzq("Iberlucea");
		direccionBanco.setCalleLateralDer("Filiberto");
		direccionBanco.setAltura(1218);

		Location ubicacionBanco = new Location(-34.635926, -58.363816);

		Poi banco = new Poi("Banco Nacion", 3, direccionBanco, ubicacionBanco);

		cgp.mostrarDatos();
		banco.mostrarDatos();

		cgp.informarDistanciaA(banco);

		cgp.esValido();
		banco.esValido();
		
		lPois.add(cgp);
		lPois.add(banco);
		
		//Es para la entrega 1 una primera prueba del esta cerca todavia no esta ni pulido es una primera idea
		//casa.estaCerca(lPois);

	}
}
