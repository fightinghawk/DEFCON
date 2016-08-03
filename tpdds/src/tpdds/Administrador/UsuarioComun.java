package tpdds.Administrador;

import java.io.IOException;
import java.sql.SQLException;

public class UsuarioComun implements Usuario{

	private String nombreUsuario;
	private String usuariopassword;
	private String usuarioid;
	private String apellidoUsuario;
	private String emailUsuario;
	private Proceso procesoSeleccionado; 
	
	
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
		return "Administrador";
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
