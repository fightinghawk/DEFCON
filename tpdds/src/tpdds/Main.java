package tpdds;

import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
		//Genero una coleccion de Pois
		
		ArrayList<Poi> lPois = new ArrayList<Poi>();
		
		//Genero un dispositivo para localizar pois
		Direccion direTablero = new Direccion();
		direTablero.setCallePrincipal("Boedo");
		direTablero.setAltura(900);
		Location ubicacionTablero = new Location(-34.623938, -58.416206);
		Dispositivo tablero = new Dispositivo(1,direTablero,ubicacionTablero);
		
		
		//Genero un Poi
		Direccion direccionCGO = new Direccion();
		direccionCGO.setCallePrincipal("Av.Jujuy");
		direccionCGO.setCalleLateralIzq("Humberto 1ro");
		direccionCGO.setCalleLateralDer("Carlos Calvo");
		direccionCGO.setAltura(765);
		Location ubicacionCGP = new Location(-34.621536, -58.416334);
		Poi cgp = new Poi("CGP San Cristobal", 1, direccionCGO, ubicacionCGP);
		

		//Genero Otro Poi 

		Direccion direccionBanco = new Direccion();
		direccionBanco.setCallePrincipal("Brandsen");
		direccionBanco.setCalleLateralIzq("Iberlucea");
		direccionBanco.setCalleLateralDer("Filiberto");
		direccionBanco.setAltura(1218);
		Location ubicacionBanco = new Location(-34.635926, -58.363816);
		Poi banco = new Poi("Banco Nacion", 3, direccionBanco, ubicacionBanco);
		
		//Agrego los pois a la coleccion
		lPois.add(cgp);
		lPois.add(banco);

		//Muestro datos de los pois
		
		cgp.mostrarDatos();
		banco.mostrarDatos();

		//Informo la distancia de un poi al otro
		cgp.informarDistanciaA(banco);

		//Valido los poi
		cgp.esValido();
		banco.esValido();
		
		//Es para la entrega 1 una primera prueba del esta cerca todavia no esta ni pulido es una primera idea
		tablero.estaCerca(lPois);

	}
}
