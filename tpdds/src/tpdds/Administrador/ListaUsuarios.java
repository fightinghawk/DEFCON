package tpdds.Administrador;

import java.util.ArrayList;

import tpdds.Usuarios.UsuarioComun;


public class ListaUsuarios {
	
	ArrayList<UsuarioComun> usuarios;
	
	public void agregarusuario(UsuarioComun usuario)
	{
		usuarios.add(usuario);
	}
	
	public void eliminarusuario(UsuarioComun usuario)
	{
		usuarios.remove(usuarios);
	}
	
	public int mostrarusuarios()
	{
		return usuarios.size();
	}

}
