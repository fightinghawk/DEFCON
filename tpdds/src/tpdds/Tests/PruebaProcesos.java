package tpdds.Tests;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpdds.proceso.*;


public class PruebaProcesos {
	
	ArrayList<Proceso> listaProcesos;

	@Before
	public void crearProcesos(){
	
		Proceso bajaPoi;
		Proceso actComercial;
		String ubicacionArchivo;
		
		ubicacionArchivo = "/tpdds/src/comercioTest.txt";
		bajaPoi = new BajaDePois();
		actComercial = new ActualizacionLocalesComerciales(ubicacionArchivo);
		listaProcesos = new ArrayList<Proceso>();
		listaProcesos.add(bajaPoi);
		listaProcesos.add(actComercial);
	}
	
	@Test
	public void procesoCreado(){
		Assert.assertEquals(listaProcesos.size(),2);
	}
	@Test
	public void bajaDePoi(){
		for(Proceso proc : listaProcesos){
			if (proc.getNombreProceso().equals("Baja de Pois")){
				try {
					proc.ejecutarme();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}