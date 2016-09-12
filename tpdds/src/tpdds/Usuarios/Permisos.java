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
	String permiso;
	@Column(name="per_descripciom")
	String descripcion;
	@ManyToMany
	@JoinTable(
			name="tipodeusuario_has_permisos",
	        joinColumns=@JoinColumn(name="tipodeusuario_tipodeusuario"),
            inverseJoinColumns=@JoinColumn(name="permisos_per_permisos"))
	public Collection<TipoUsuario> tiposUsuarios;
	
	
	public Permisos(String permiso, String descripcion) {
		super();
		this.permiso = permiso;
		this.descripcion = descripcion;
		this.tiposUsuarios = new ArrayList<>();
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
