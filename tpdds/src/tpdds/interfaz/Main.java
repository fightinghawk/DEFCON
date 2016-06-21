package tpdds.interfaz;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tpdds.Archivos.archivos;
import tpdds.dispositivo.Dispositivo;
import tpdds.interfaz.mainScene;
import tpdds.pois.Poi;
import tpdds.ubicacion.Comuna;

public class Main extends Application {
    
	public static Stage primaryStage;
	public static Dispositivo tablero;
	public static ArrayList<Poi> pois;
	
	@Override
	public void start(Stage primaryStag) {
		try{
			Comuna.inicializarComunas();
			
			tablero = archivos.obtenerTablero();
			pois = archivos.leerArchivo("example1.dat");
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
