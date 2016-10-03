package tpdds.interfaz.componentes;

import tpdds.Usuarios.User;

public class ObsUser {

	private String nombre;
	private String apellido;
	private String id;
	private String mail;
	private String tipo;
	
	public ObsUser(User usuario){
		this.nombre = usuario.getNombreUsuario();
		this.apellido = usuario.getApellidoUsuario();
		this.id = usuario.getUsuarioid();
		this.mail = usuario.getEmailUsuario();
		this.tipo = usuario.getTipo();
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public String getId() {
		return id;
	}
	public String getMail() {
		return mail;
	}
	public String getTipo() {
		return tipo;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
