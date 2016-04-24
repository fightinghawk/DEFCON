package tpdds;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Generacion a modo de prueba de un poi, faltan armar los contructores de todos salvo localizacion.
		
		Direccion direccionCasa = new Direccion();
		
		direccionCasa.setCallePrincipal("Av.Jujuy");
		direccionCasa.setCalleLateralIzq("Humberto 1ro");
		direccionCasa.setCalleLateralDer("Carlos Calvo");
		direccionCasa.setAltura(1218);
		
		Location ubicacionCasa = new Location( -34.621854, -58.402526);

		Poi casa = new Poi();		
		casa.setNombre("Mi casa");		
		//El tipo esta con numeros para que despues creemos una tabla de tipos, con una referencia numerica, eso despues lo ponemos
		//creamos un case y listo, para que no carguemos de varias formas distintas lo mismo
		casa.setTipo(3);
		casa.setDireccion(direccionCasa);
		casa.setGeoloc(ubicacionCasa);
		
		//Otro Poi prueba
		
		Direccion direccionBombo = new Direccion();
		
		direccionBombo.setCallePrincipal("Brandsen");
		direccionBombo.setCalleLateralIzq("Iberlucea");
		direccionBombo.setCalleLateralDer("Filiberto");
		direccionBombo.setAltura(1218);
		
		Location ubicacionBombo = new Location( -34.635926, -58.363816);

		Poi bombonera = new Poi();		
		bombonera.setNombre("Bombonera");		
		//El tipo esta con numeros para que despues creemos una tabla de tipos, con una referencia numerica, eso despues lo ponemos
		//creamos un case y listo, para que no carguemos de varias formas distintas lo mismo
		bombonera.setTipo(2);
		bombonera.setDireccion(direccionBombo);
		bombonera.setGeoloc(ubicacionBombo);
		double distancia = casa.calcularDistanciaA(bombonera.getGeoloc());
		
		
		System.out.println("El Poi "+ casa.getNombre() + " Ubicado en "+ casa.getDireccion() + " es una " + casa.getTipo()+ " y su geolocalizacion es " + casa.getGeoloc());
		System.out.println("El Poi "+ bombonera.getNombre() + " Ubicado en "+ bombonera.getDireccion() + " es una " + bombonera.getTipo()+ " y su geolocalizacion es " + bombonera.getGeoloc());		
		
		System.out.println("La distancia entre " + casa.getNombre() + " y " + bombonera.getNombre() + " es ");
		System.out.printf("%6.3f Kilometros \n", distancia);

		
	}
}
