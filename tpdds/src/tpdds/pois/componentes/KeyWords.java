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
	
	@Column(name="owner_pois_id")
	private int pois_id;
	
	public int getPois_id() {
		return pois_id;
	}

	public void setPois_id(int pois_id) {
		this.pois_id = pois_id;
	}

	public KeyWords(String clave)
	{
		this.clave = clave;
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
