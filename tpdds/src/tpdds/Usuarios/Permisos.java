package tpdds.Usuarios;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="permisos")
public class Permisos {
	
	@Id
	@Column(name="per_permisos")
	private String permiso;
	@Column(name="per_descripciom")
	private String descripcion;
	
	public Permisos(String permiso) {
		super();
		this.permiso = permiso;
	}

	public Permisos(String permiso, String descripcion) {
		super();
		this.permiso = permiso;
		this.descripcion = descripcion;
		//this.tiposUsuarios = new ArrayList<>();
	}
	
	public Permisos(){
		super();
	}
	
	public String getPermiso() {
		return permiso;
	}
	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
