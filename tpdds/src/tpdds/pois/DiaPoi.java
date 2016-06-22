package tpdds.pois;

import java.io.Serializable;

public class DiaPoi{
	private int horaApertura;
	private int horaClose;
	private int minApertura = 0;
	private int minClose = 0;
	private int dia;
	
	public DiaPoi(int horaApertura, int horaClose, int minApertura, int minClose, int dia) {
		super();
		this.horaApertura = horaApertura;
		this.horaClose = horaClose;
		this.minApertura = minApertura;
		this.minClose = minClose;
		this.dia = dia;
	}

	public int getHoraApertura() {
		return horaApertura;
	}

	public int getHoraClose() {
		return horaClose;
	}

	public int getMinApertura() {
		return minApertura;
	}

	public int getMinClose() {
		return minClose;
	}

	public int getDia() {
		return dia;
	}
	
	
}
