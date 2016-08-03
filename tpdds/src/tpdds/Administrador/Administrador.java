package tpdds.Administrador;

import java.util.ArrayList;

public class Administrador implements Usuario{
	
	private String nombreUsuario;
	private String contrasenia;
	
	public Administrador(String usuario, String contrasenia){
		this.nombreUsuario = usuario;
		this.contrasenia = contrasenia;
	}
	
	public void setNombreUsuario(String usuario){
		this.nombreUsuario = usuario;
	}
	
	public String getNombreUsuario(){
		return nombreUsuario;
	}
	
	public void setContrasenia(String contrasenia){
		this.contrasenia = contrasenia;
	}
	
	public String getContrasenia(){
		return contrasenia;
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

	public void ejecutarProceso(String nombreProceso, ArrayList<Proceso> listaProcesos){
		for (Proceso proceso : listaProcesos){
			if(nombreProceso.toUpperCase().equals(proceso.getNombreProceso().toUpperCase())){
				proceso.ejecutarme();
			}
		}
	}

	@Override
	public String getTipo() {
		// TODO Auto-generated method stub
		return "Administrador";
	}
}