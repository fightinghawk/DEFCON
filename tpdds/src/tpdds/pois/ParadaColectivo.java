package tpdds.pois;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class ParadaColectivo extends Poi  {
	
	@Column(name="numero_parada")
	private int parada;
	
	public int getParada() {
		return parada;
	}

	public void setParada(int parada) {
		this.parada = parada;
	}

	public String getRubro() {
		return "Parada Colectivo";
	}
	
	public ParadaColectivo(Poi datos,int parada) {
		super(datos);
		super.setDtype(this.getClass().getSimpleName());
		this.parada = parada;
	}

	public ParadaColectivo(){}
	
	@Override
	public boolean estaDisponible(Calendar dia) {
		return true;
	}
}
