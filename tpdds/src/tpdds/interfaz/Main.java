package tpdds.interfaz;
import java.net.URL;
import java.util.ArrayList;

import org.hibernate.Session;

import HIBERNATE.HibernateSessionFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import tpdds.Administrador.Administrador;
import tpdds.Administrador.ListaUsuarios;
import tpdds.Archivos.archivos;
import tpdds.Usuarios.Permisos;
import tpdds.Usuarios.TipoUsuario;
import tpdds.Usuarios.UsuarioComun;
import tpdds.database.Generales;
import tpdds.dispositivo.Dispositivo;
import tpdds.pois.Poi;
import tpdds.proceso.Proceso;
import tpdds.ubicacion.Comuna;

public class Main extends Application {
    
	public static Stage primaryStage;
	public static Dispositivo tablero;
	public static ArrayList<Poi> pois;

	public static ArrayList<UsuarioComun> usuarios;
	public static UsuarioComun usuarioNuevo;

	public static ListaUsuarios todoslosusuarios;
	public static ArrayList<Proceso> listaProcesos;

	
	@Override
	public void start(Stage primaryStag) {
		try{
			Comuna.inicializarComunas();
			Generales.initDatabase();
			tablero = archivos.obtenerTablero();
			pois = Generales.cargarPois();
			listaProcesos = Administrador.crearProcesos();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		Permisos perm = new Permisos("buscarPoi", "Permite buscar un poi");
		TipoUsuario admin = new TipoUsuario("Admin", "Tiene todo habilitado");
		admin.permisosUsuarios.add(perm);
		perm.tiposUsuarios.add(admin);
		Session aGuardar = HibernateSessionFactory.getSession();
		aGuardar.beginTransaction();
		//aGuardar.save(perm);
		aGuardar.save(admin);
		aGuardar.getTransaction().commit();
		aGuardar.close();
		
		InsertarDia.initDias();
		primaryStage = primaryStag;
		primaryStage.setResizable(false);
		primaryStage.setTitle("Pois dds");
		new PreLoginScene().loginSceneRender();
	}
	

	
	public static void main(String[] args) {
		usuarios = new ArrayList<UsuarioComun>();
		launch(args);
	}

}
