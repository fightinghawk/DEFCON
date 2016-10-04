package tpdds.interfaz;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class Escena {

	protected Stage nuevaStage;
	protected FXMLLoader loader;
	protected AnchorPane rootLayout;
	protected Scene escena;
	
	public void render(String title,String pathScene){
		try{
			nuevaStage = new Stage();
			loader = new FXMLLoader();
			//Configuracion stage
			nuevaStage.initModality(Modality.WINDOW_MODAL);
			nuevaStage.initOwner(Main.primaryStage);
			nuevaStage.setResizable(false);
			
			nuevaStage.setTitle(title);
			//configuracion loader
			loader.setLocation(getClass().getResource(pathScene));
			loader.setController(this);
			//Creo la escena y la muestro
			rootLayout = loader.load();
			escena = new Scene(rootLayout);
			nuevaStage.setScene(escena);
			nuevaStage.show();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
