package tpdds.interfaz;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tpdds.Administrador.Usuario;

public class LanzadorDeProcesos implements Initializable {
	
	@FXML
	TableView<ObsPoi> procesosrun;
	@FXML
	TableColumn<ObsPoi, String> proceso;
	@FXML
	TableColumn<ObsPoi, String> estado;
	
	Stage nuevaStage;
	FXMLLoader loader;
	AnchorPane rootLayout;
	
	public void lanzadorRender(){
		try{
			nuevaStage = new Stage();
			nuevaStage.initModality(Modality.WINDOW_MODAL);
			nuevaStage.initOwner(Main.primaryStage);
			nuevaStage.setResizable(false);
			nuevaStage.setTitle("Lanzar proceso");
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("lanzadorDeProcesos.fxml"));
			loader.setController(this);
			rootLayout = loader.load();
			Scene scene = new Scene(rootLayout);
			nuevaStage.setScene(scene);
			nuevaStage.show();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

	
	
	
}