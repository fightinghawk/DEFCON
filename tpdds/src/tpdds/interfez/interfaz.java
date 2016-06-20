package tpdds.interfez;

import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tpdds.interfez.mainScene;

public class interfaz extends Application {
    
	public static Stage primaryStage;
	
	
	@Override
	public void start(Stage primaryStag) {
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
