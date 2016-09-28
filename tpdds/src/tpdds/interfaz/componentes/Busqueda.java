package tpdds.interfaz.componentes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import tpdds.pois.Poi;

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
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=true)
	@JoinColumn(name="busquedaid")
	private Collection<Criterio> criterios = new ArrayList<Criterio>();
	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@CollectionTable(name = "poi_resultados", joinColumns=@JoinColumn(name = "busqueda_id"))
	@Column(name = "id_poi")
	private Collection<Integer> pois_encontrados = new ArrayList<>();
	
	public Busqueda(int cantResultados, double duracion, int terminal, String usuario,
			Collection<Criterio> criterios, Collection<Poi> resultados) {
		super();
		this.cantResultados = cantResultados;
		this.duracion = duracion;
		this.terminal = terminal;
		this.usuario = usuario;
		this.criterios = criterios;
		this.fechaRealizada = new Date(System.currentTimeMillis());
		for (Poi poi : resultados) {
			pois_encontrados.add(poi.getIddb());
		}
	}
	
	public Busqueda() {}

	public Collection<Integer> getPois_encontrados() {
		return pois_encontrados;
	}
	public int getId() {
		return id;
	}

	public Date getFechaRealizada() {
		return fechaRealizada;
	}

	public int getCantResultados() {
		return cantResultados;
	}

	public double getDuracion() {
		return duracion;
	}

	public int getTerminal() {
		return terminal;
	}

	public String getUsuario() {
		return usuario;
	}

	public Collection<Criterio> getCriterios() {
		return criterios;
	}
	
	public String criteriosToShow(){
		String aMostrar = "";
		for (Criterio criterio : criterios) {
			aMostrar += ("Tipo: " + criterio.getTipo()+ " Contenido: " + criterio.getContenido()+"\n");
		}
		return aMostrar;
	}
	
	
	
	
	

}
