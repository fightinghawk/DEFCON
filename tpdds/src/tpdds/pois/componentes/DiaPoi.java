package tpdds.pois.componentes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import tpdds.pois.Poi;

@Entity
@Table (name="diaspoi")
public class DiaPoi{
	
	protected DiaPoi(){}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="diasPoi_id")
	private int iddb;
	@Column(name="horainicio")
	private int horaApertura;
	@Column(name="horafin")
	private int horaClose;
	@Column(name="minutoinicio")
	private int minApertura = 0;
	@Column(name="minutofin")
	private int minClose = 0;
	@Column(name="dia")
	private int dia;
	@Column(name="owner_pois_id")
	private int pois_id;
	
	public int getPois_id() {
		return pois_id;
	}

	public void setPois_id(int pois_id) {
		this.pois_id = pois_id;
	}

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
	
	public int getIddb() {
		return iddb;
	}
	
	public int setIddb(int id) {
		return this.iddb = id;
	}
	
	
}
