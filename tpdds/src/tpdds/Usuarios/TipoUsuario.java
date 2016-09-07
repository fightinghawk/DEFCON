package tpdds.Usuarios;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	@Transient
	private ArrayList<Permisos> permisosUsuarios;
	
	public TipoUsuario(String tipo, String descripcion) {
		super();
		this.tipo = tipo;
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public ArrayList<Permisos> getPermisosUsuarios() {
		return permisosUsuarios;
	}
	
	
	
}
