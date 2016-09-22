package tpdds.interfaz;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import HIBERNATE.HibernateSessionFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import tpdds.Archivos.archivos;
import tpdds.Usuarios.Permisos;
import tpdds.Usuarios.TipoUsuario;
import tpdds.Usuarios.User;
import tpdds.database.Generales;
import tpdds.dispositivo.Dispositivo;
import tpdds.pois.Poi;
import tpdds.proceso.Proceso;
import tpdds.ubicacion.Comuna;

public class Main extends Application {
    
	public static Stage primaryStage;
	public static Dispositivo tablero;
	public static ArrayList<User> usuarios;
	public static User usuarioNuevo;
	public static ArrayList<Proceso> listaProcesos;
	public static ArrayList<Poi> pois;

	
	@Override
	public void start(Stage primaryStag) {
		try{
			Comuna.inicializarComunas();
			Generales.initDatabase();
			tablero = archivos.obtenerTablero();
			pois = Generales.cargarPois();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		primaryStage = primaryStag;
		primaryStage.setResizable(false);
		primaryStage.setTitle("Pois dds");
		new PreLoginScene().loginSceneRender();
			}
	

	
	public static void main(String[] args) {
		usuarios = new ArrayList<User>();
		launch(args);
	}

}
