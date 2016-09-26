package tpdds.interfaz.componentes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="criteriosdebusqueda")
public class Criterio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cri_id")
	private int id;
	@Column(name="cri_tipo")
	private String tipo;
	@Column(name="cri_contenido")
	private String contenido;
	
	public Criterio() {}

	public Criterio(String tipo, String contenido) {
		super();
		this.tipo = tipo;
		this.contenido = contenido;
	}
	
	
	
	
	
}
