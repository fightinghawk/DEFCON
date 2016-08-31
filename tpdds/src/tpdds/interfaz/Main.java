package tpdds.interfaz;
import java.net.URL;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import tpdds.Administrador.Administrador;
import tpdds.Administrador.ListaUsuarios;
import tpdds.Administrador.Proceso;
import tpdds.Archivos.archivos;
import tpdds.database.Generales;
import tpdds.dispositivo.Dispositivo;
import tpdds.interfaz.mainScene;
import tpdds.pois.Poi;
import tpdds.ubicacion.Comuna;

public class Main extends Application {
    
	public static Stage primaryStage;
	public static Dispositivo tablero;
	public static ArrayList<Poi> pois;
	public static ListaUsuarios todoslosusuarios;
	public static ArrayList<Proceso> listaProcesos;
	
	@Override
	public void start(Stage primaryStag) {
		try{
			Comuna.inicializarComunas();
			Generales.initDatabase();
			tablero = archivos.obtenerTablero();
			pois = Generales.cargarPois();
			//listaProcesos = Administrador.crearProcesos();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		InsertarDia.initDias();
		primaryStage = primaryStag;
		primaryStage.setResizable(false);
		primaryStage.setTitle("Pois dds");
		mainScene.mainSceneRender();
	}
	

	
	public static void main(String[] args) {
		launch(args);
	}

}
