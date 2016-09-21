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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tipodeusuario")
public class TipoUsuario {

	public TipoUsuario() {
		super();
	}

	@Id
	@Column(name="tipodeusuario")
	private String tipo;
	@Column(name="tipo_descripcion")
	private String descripcion;
	@Column(name="tipo_mostrar")
	private String aMostrar;
	@ManyToMany
	@JoinTable(name="tipodeusuario_has_permisos",
			inverseJoinColumns=@JoinColumn(name="permisos_per_permisos", referencedColumnName="per_permisos"),
			joinColumns=@JoinColumn(name="tipodeusuario_tipodeusuario", referencedColumnName="tipodeusuario"))
	private Collection<Permisos> permisosUsuarios;
	
	public TipoUsuario(String tipo, String descripcion,String aMostrar) {
		super();
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.permisosUsuarios = new ArrayList<>();
		this.aMostrar = aMostrar;
	}

	public String getTipo() {
		return tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public String getaMostrar() {
		return aMostrar;
	}

	public Collection<Permisos> getPermisosUsuarios() {
		return permisosUsuarios;
	}
	
	
	
}
