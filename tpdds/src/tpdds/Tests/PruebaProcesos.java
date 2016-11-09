package tpdds.Tests;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpdds.pois.Poi;
import tpdds.pois.componentes.DiaPoi;
import tpdds.pois.componentes.Servicios;
import tpdds.proceso.*;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Location;


public class PruebaProcesos {
	
	ArrayList<BajaDePois> listaProcesos;
	ArrayList<Poi> listaPoi;

	@Before
	public void inicializar(){
	
		BajaDePois bajaPoi;
		bajaPoi = new BajaDePois();
		listaProcesos = new ArrayList<BajaDePois>();
		listaProcesos.add(bajaPoi);
		Poi banco = new Poi(0, "BancoTest", "1", 0, null, null, null, null, null);
		Poi parada = new Poi(1, "ParadaTest", "2", 0, null, null, null, null, null);
		listaPoi = new ArrayList<Poi>();
		listaPoi.add(banco);
		listaPoi.add(parada);
	}
	
	@Test
	public void procesoCreado(){
		Assert.assertEquals(listaProcesos.size(),1);
		Assert.assertEquals(listaPoi.size(),2);
	}
	@Test
	public void bajaDePoi(){
		try {
					listaProcesos.get(0).ejecutarmeTest(listaPoi);
					Assert.assertEquals(listaPoi.size(),1);
			}finally{}
	}
}