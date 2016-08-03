package tpdds.interfaz;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ReportePorTerminal implements Initializable {

	@FXML
	TextField frase;
	@FXML
	TextField terminal;
	@FXML
	TableView<ObsResultadoTerminal> resultadoTL;
	@FXML
	TableColumn<ObsResultadoTerminal,Integer> CRP;
	
	Stage nuevaStage;
	FXMLLoader loader;
	AnchorPane rootLayout;
	
	public void ReportePorTerminalRender(){
		try{
			nuevaStage = new Stage();
			nuevaStage.initModality(Modality.WINDOW_MODAL);
			nuevaStage.initOwner(Main.primaryStage);
			nuevaStage.setResizable(false);
			nuevaStage.setTitle("Reporte por terminal");
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("reportePorTerminaScene.fxml"));
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
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void buscar(MouseEvent evento){
		
	}
}
