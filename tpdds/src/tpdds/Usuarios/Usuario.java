package tpdds.Usuarios;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public interface Usuario {

	public String getNombreUsuario();
	public String getTipo();
	
}
