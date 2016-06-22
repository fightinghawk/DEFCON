package tpdds.interfaz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class mainScene {
	
	public static void mainSceneRender(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("mainScene.fxml"));
			AnchorPane rootLayout = loader.load();
			Scene scene = new Scene(rootLayout);
            Main.primaryStage.setScene(scene);
            Main.primaryStage.show();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@FXML
	public void insertarBoton(MouseEvent botonApretado){
		InsertSceneGnr.insertSceneRender();
	}
	
	@FXML
	public void buscarBoton(MouseEvent botonApretado){
		new buscarPoiScene().buscarSceneRender();
	}	
	
	@FXML
	public void registrarseBoton(MouseEvent botonApretado){

	}	
	
	@FXML
	public void modificarBoton(MouseEvent botonApretado){
		
	}	
}