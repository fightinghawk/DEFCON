package tpdds.ubicacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Comuna {
	
	private static HashSet<Comuna> comunas = new HashSet<>();
	private int numeroComuna;
	public HashSet<String> barrios;
	
	public Comuna(int numero, String[] barrios){
		this.numeroComuna = numero;
		this.barrios = new HashSet<String>(Arrays.asList(barrios));
		comunas.add(this);
	}
	
	public static int obtenerNroComunaDelBarrio(String barrio){
		for (Comuna comuna : comunas) {
			if(comuna.barrios.contains(barrio.toLowerCase()))
			return comuna.numeroComuna;
		}	
		return -1;
	}

	public static void inicializarComunas(){
		String[] barriosCm1 = {"retiro","san nicolas","puerto Madero","san Telmo","montserrat","constitucion"};
		String[] barriosCm2 = {"recoleta"};
		String[] barriosCm3 = {"balvanera","san cristobal"};
		String[] barriosCm4 = {"la boca", "barracas","parque patricios","nueva pompeya"};
		String[] barriosCm5 = {"almagro","boedo"};
		String[] barriosCm6 = {"caballito"};
		String[] barriosCm7 = {"flores", "parque caballito"};
		String[] barriosCm8 = {"villa soladati", "villa riachuelo", "villa lugano"};
		String[] barriosCm9 = {"liniers", "mataderos","parque avellaneda"};
		String[] barriosCm10 = {"villa Real","monte castro","versalles","floresta","velez sarsfield","villa luro"};
		String[] barriosCm11 = {"villa general mitre", "villa devoto", "villa del parque" , "villa santa rita"};
		String[] barriosCm12 = {"coghlan", "saavedra", "villa urquiza" , "villa pueyrredón"};
		String[] barriosCm13 = {"núñez", "belgrano" , "colegiales"};
		String[] barriosCm14 = {"palermo"};
		String[] barriosCm15 = {"chacarita", "villa crespo", "la paternal", "villa ortúzar", "agronomía" , "parque chas"};
	
		new Comuna(1, barriosCm1);
		new Comuna(2, barriosCm2);
		new Comuna(3, barriosCm3);
		new Comuna(4, barriosCm4);
		new Comuna(5, barriosCm5);
		new Comuna(6, barriosCm6);
		new Comuna(7, barriosCm7);
		new Comuna(8, barriosCm8);
		new Comuna(9, barriosCm9);
		new Comuna(10, barriosCm10);
		new Comuna(11, barriosCm11);
		new Comuna(12, barriosCm12);
		new Comuna(13, barriosCm13);
		new Comuna(14, barriosCm14);
		new Comuna(15, barriosCm15);	
	}
	
}
