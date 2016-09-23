package tpdds.pois.componentes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="servicios")
public class Servicios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="serv_id")
	private int id_servicio;
	@Column(name="serv_nombre")
	private String nombreServicio;
	@Column(name="serv_descripcion")
	private String descripcionServicio;
	
	public Servicios(String nombreServicio, String descripcionServicio) {
		super();
		this.nombreServicio = nombreServicio;
		this.descripcionServicio = descripcionServicio;
	}
	
	public int getId_servicio() {
		return id_servicio;
	}
	public String getNombreServicio() {
		return nombreServicio;
	}
	public String getDescripcionServicio() {
		return descripcionServicio;
	}
	
	
	
}
