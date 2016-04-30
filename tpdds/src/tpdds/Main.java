package tpdds;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Generacion a modo de prueba de un poi, faltan armar los contructores
		// de todos salvo localizacion.

		Direccion direccionCasa = new Direccion();

		direccionCasa.setCallePrincipal("Av.Jujuy");
		direccionCasa.setCalleLateralIzq("Humberto 1ro");
		direccionCasa.setCalleLateralDer("Carlos Calvo");
		direccionCasa.setAltura(1218);

		Location ubicacionCasa = new Location(-34.621854, -58.402526);

		Poi casa = new Poi("Mi casa", 7, direccionCasa, ubicacionCasa);
		// El tipo esta con numeros para que despues creemos una tabla de tipos,
		// con una referencia numerica, eso despues lo ponemos
		// creamos un case y listo, para que no carguemos de varias formas
		// distintas lo mismo

		// Otro Poi prueba

		Direccion direccionBombo = new Direccion();

		direccionBombo.setCallePrincipal("Brandsen");
		direccionBombo.setCalleLateralIzq("Iberlucea");
		direccionBombo.setCalleLateralDer("Filiberto");
		direccionBombo.setAltura(1218);

		Location ubicacionBombo = new Location(-34.635926, -58.363816);

		Poi bombonera = new Poi("Bombonera", 5, direccionBombo, ubicacionBombo);

		casa.mostrarDatos();
		bombonera.mostrarDatos();

		casa.informarDistanciaA(bombonera);

		casa.esValido();
		bombonera.esValido();

	}
}
