package tpdds.pois;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="keywords")
public class keyWords {
	
	protected keyWords(){}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="keyWords_id")
	private int iddb;
	
	@Column(name="clave")
	private String clave;
	
	@ManyToOne
	@JoinColumn(name="pois_id")
	private Poi poi;
	
	keyWords(String clave, Poi Poi)
	{
		this.clave = clave;
		this.poi = Poi;
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
