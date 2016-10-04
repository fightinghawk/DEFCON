package tpdds.interfaz.componentes;

import tpdds.Usuarios.User;

public class ObsUser {

	private String nombre;
	private String apellido;
	private String id;
	private String mail;
	private String tipo;
	private User usuario;
	public boolean modificado;
	
	public ObsUser(User usuario){
		this.nombre = usuario.getNombreUsuario();
		this.apellido = usuario.getApellidoUsuario();
		this.id = usuario.getUsuarioid();
		this.mail = usuario.getEmailUsuario();
		this.tipo = usuario.getTipo();
		this.modificado = false;
		this.usuario = usuario;
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
		this.usuario.setNombreUsuario(nombre);
		this.nombre = nombre;
	}
	public void setApellido(String apellido) {
		this.usuario.setApellidoUsuario(apellido);
		this.apellido = apellido;
	}
	public void setId(String id) {
		this.usuario.setUsuarioid(id);
		this.id = id;
	}
	public void setMail(String mail) {
		this.usuario.setEmailUsuario(mail);
		this.mail = mail;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
