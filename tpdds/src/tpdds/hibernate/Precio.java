package tpdds.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Precio {
	
	@Id
	@GeneratedValue
	@Column(name="precio_id")
	int id;
	@Column(name="precio")
	float valor;
	@ManyToOne
	Algo idPrecio;
	
	
	public Precio(float valor) {
		super();
		this.valor = valor;
	}
	
	public Algo getIdPrecio() {
		return idPrecio;
	}

	public void setIdPrecio(Algo idPrecio) {
		this.idPrecio = idPrecio;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	
	
	
	
	
}
