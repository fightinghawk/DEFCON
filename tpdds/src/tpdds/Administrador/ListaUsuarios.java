package tpdds.Administrador;

import java.util.Collection;

public class ListaUsuarios {
	
	Collection<UsuarioComun> usuarios;
	
	public void agregarusuario(UsuarioComun usuario)
	{
		usuarios.add(usuario);
	}
	
	public void eliminarusuario(UsuarioComun usuario)
	{
		usuarios.remove(usuarios);
	}

}
