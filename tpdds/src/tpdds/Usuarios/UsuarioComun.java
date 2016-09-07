package tpdds.Usuarios;

import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import tpdds.proceso.Proceso;

@Entity
@Table(name="usuarios")
public class UsuarioComun implements Usuario{

	@Column(name="user_nombre")
	private String nombreUsuario;
	@Column(name="user_password")
	private String usuariopassword;
	@Id
	@Column(name="user_id")
	private String usuarioid;
	@Column(name="user_apellido")
	private String apellidoUsuario;
	@Column(name="user_mail")
	private String emailUsuario;
	@OneToOne
	@JoinColumn(name = "usua_tipodeusuario")
	private TipoUsuario tipoUsuario;
	@Transient
	private Proceso procesoSeleccionado; 
	
	public UsuarioComun(){
		super();
	}
	
	public UsuarioComun(String nombre, String apellido, String userid, String password, String email){
		this.nombreUsuario = nombre;
		this.apellidoUsuario = apellido;
		this.usuarioid = userid;
		this.usuariopassword = password;
		this.emailUsuario = email;
	}
	
	public void otorgarPermisos(String usuario, String permiso)
	{
		if (this.existeUsuario(usuario))
		{
			this.habilitar(permiso);
		}
		
	}
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	private void habilitar(String permiso) {
		// TODO Auto-generated method stub
		
	}

	private boolean existeUsuario(String usuario) {
		return false;
		// TODO Auto-generated method stub
		
	}

	public void ejecutarProceso() throws IOException, ClassNotFoundException, SQLException{
		this.getProcesoSeleccionado().ejecutarme();
	}

	@Override
	public String getTipo() {
		// TODO Auto-generated method stub
		return "Usuario Comun";
	}

	public Proceso getProcesoSeleccionado() {
		return procesoSeleccionado;
	}

	public void setProcesoSeleccionado(Proceso procesoSeleccionado) {
		this.procesoSeleccionado = procesoSeleccionado;
	}



	public String getNombreUsuario() {
		return nombreUsuario;
	}



	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}



	public String getUsuariopassword() {
		return usuariopassword;
	}



	public void setUsuariopassword(String usuariopassword) {
		this.usuariopassword = usuariopassword;
	}



	public String getUsuarioid() {
		return usuarioid;
	}



	public void setUsuarioid(String usuarioid) {
		this.usuarioid = usuarioid;
	}



	public String getApellidoUsuario() {
		return apellidoUsuario;
	}



	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}



	public String getEmailUsuario() {
		return emailUsuario;
	}



	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
}
