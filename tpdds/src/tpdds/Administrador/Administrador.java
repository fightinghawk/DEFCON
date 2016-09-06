package tpdds.Administrador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import tpdds.proceso.ActualizacionLocalesComerciales;
import tpdds.proceso.BajaDePois;
import tpdds.proceso.Proceso;

public class Administrador implements Usuario{
	
	private String nombreUsuario;
	private String contrasenia;
	private Proceso procesoSeleccionado;
	private static ArrayList<Proceso> listaProcesos;
	
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

	public void ejecutarProceso() throws IOException, ClassNotFoundException, SQLException{
		Calendar fecha = GregorianCalendar.getInstance();
		this.getProcesoSeleccionado().ejecutarme();
		this.getProcesoSeleccionado().setFechaInicio(fecha);
		this.getProcesoSeleccionado().setUsuario(this.getNombreUsuario());
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
	
	public static ArrayList<Proceso> crearProcesos(){
		String ubicacionArchivo;
		ubicacionArchivo = "/tpdds/src/comercioTest.txt";
		ActualizacionLocalesComerciales procesoActualizacion = new ActualizacionLocalesComerciales("Actualizacion Locales Comerciales", "user", "En Espera", 0, 0, ubicacionArchivo);
		BajaDePois procesoBajaPoi = new BajaDePois("Baja de Pois", "user", "En Espera", 0, 0);
		listaProcesos.add(procesoActualizacion);
		listaProcesos.add(procesoBajaPoi);
		return listaProcesos;
	}
}