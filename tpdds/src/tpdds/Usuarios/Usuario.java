package tpdds.Usuarios;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DiscriminatorFormula;

@MappedSuperclass
public interface Usuario {

	public String getNombreUsuario();
	public String getTipo();
	public String getUsuariopassword();
	
}
