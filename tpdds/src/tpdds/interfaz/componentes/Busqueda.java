package tpdds.interfaz.componentes;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="busqueda")
public class Busqueda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="fecha")
	private Date fechaRealizada;
	@Column(name="resultados")
	private int cantResultados;
	@Column(name="time")
	private double duracion;
	@Column(name="terminales_id")
	private int terminal;
	@Column(name="usuarios_user_id")
	private String usuario;
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name="busquedaid")
	private Collection<Criterio> criterios;
	
	public Busqueda(int cantResultados, double duracion, int terminal, String usuario,
			Collection<Criterio> criterios) {
		super();
		this.cantResultados = cantResultados;
		this.duracion = duracion;
		this.terminal = terminal;
		this.usuario = usuario;
		this.criterios = criterios;
		this.fechaRealizada = new Date(System.currentTimeMillis());
	}
	
	public Busqueda() {}
	
	
	
	
	
	

}
