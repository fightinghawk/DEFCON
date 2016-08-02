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