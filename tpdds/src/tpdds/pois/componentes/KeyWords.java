package tpdds.pois.componentes;

import java.util.Collection;
import java.util.HashSet;

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
@Table (name="keywords")
public class KeyWords {
	
	protected KeyWords(){}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="keyWords_id")
	private int iddb;
	
	@Column(name="clave")
	private String clave;
	
	@ManyToOne
	@JoinColumn(name="pois_id")
	private Poi poi;
	
	public KeyWords(String clave, Poi poi)
	{
		this.clave = clave;
		this.poi = poi;
	}
	
	public String getClave() {
		return clave;
	}
	
	
	public int getIddb() {
		return iddb;
	}
	
	public int setIddb(int id) {
		return this.iddb = id;
	}

}
