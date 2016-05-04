package tpdds;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Dispositivo 
{
	
	private Location geoloc;
	private int id;
	private Direccion direccion;
	DecimalFormat decimales = new DecimalFormat("0.000");
	
	public Dispositivo(int suid, Direccion sudireccion,Location sugeoloc)
	{
		init( id,direccion,sugeoloc);
		
	}	
	
	private void init(int suid, Direccion sudireccion, Location sugeoloc)
	{
		this.direccion = sudireccion;
		this.id = suid;
		this.geoloc = sugeoloc;
		
		
	}
	
	/*
	 * Primera aproximacion al esta cerca que pide para la entrega uno, todavia falta
	 * saber si esto es desde un poi a otro o si es desde una ubicacion X a el poi en caso
	 * que haya uno
	 */
	public void estaCerca(ArrayList<Poi> lPois)
	{
		double miDistancia;
		int encontrado = 0;
				
		for(int i=0; i< lPois.size() ; i++)
		{
			
			miDistancia = this.calcularDistanciaA(lPois.get(i));
			
			
			if (miDistancia <= (0.5))
			{
				System.out.println("El Poi "+ lPois.get(i).getNombre() +" esta a "+ decimales.format(miDistancia) + " metros de aqui");
				encontrado = 1;
			}
		}
		if (encontrado == 0)
		{
			System.out.println("No hay ningun Poi a menos de 500 metros de usted");
		}
	}
	
	public double calcularDistanciaA(Poi otroPoi) {

		double latitud, longitud, miLatitud, miLongitud;
		Location miLocalizacion;
		Location otraLocalizacion = otroPoi.getGeoloc();

		miLocalizacion = this.getGeoloc();

		miLatitud = miLocalizacion.getLatitud();
		miLongitud = miLocalizacion.getLongitud();

		latitud = otraLocalizacion.getLatitud();
		longitud = otraLocalizacion.getLongitud();
		
		double theta = miLongitud - longitud;
		double dist = Math.sin(deg2rad(miLatitud)) * Math.sin(deg2rad(latitud))
				+ Math.cos(deg2rad(miLatitud)) * Math.cos(deg2rad(latitud)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;
		
		return (dist);
	}
	
	// convierte grados a radianes
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	// convierte radianes a grados
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	public Location getGeoloc() {
		return geoloc;
	}

	public void setGeoloc(Location geoloc) {
		this.geoloc = geoloc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
}


