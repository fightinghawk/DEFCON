package tpdds;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Dispositivo implements Localizable 
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
			
			miDistancia = Calculos.calcularDistanciaA(this,lPois.get(i));
			
			
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


