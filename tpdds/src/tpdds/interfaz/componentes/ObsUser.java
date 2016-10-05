package tpdds.interfaz.componentes;

import org.hibernate.Session;

import tpdds.Usuarios.TipoUsuario;
import tpdds.Usuarios.User;
import tpdds.hibernate.HibernateSessionFactory;

public class ObsUser {

	private String nombre;
	private String apellido;
	private String id;
	private String mail;
	private User usuario;
	
	public ObsUser(User usuario){
		this.nombre = usuario.getNombreUsuario();
		this.apellido = usuario.getApellidoUsuario();
		this.id = usuario.getUsuarioid();
		this.mail = usuario.getEmailUsuario();
		this.usuario = usuario;
	}
	
	public User getUsuario() {
		return usuario;
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
		return this.usuario.getTipo();
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
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		this.usuario.setTipoUsuario(session.get(TipoUsuario.class, tipo));
        session.close();
	}
	public boolean contieneSubString(String cosa){
		return (this.apellido+this.id+this.mail+this.nombre).toLowerCase().contains(cosa.toLowerCase());
	}
	
}
