package tpdds.interfaz;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tpdds.proceso.Proceso;

public class LanzadorDeProcesos implements Initializable {
	
	long time_start, time_end;
	
	@FXML
	TableView<Proceso> procesosrun;
	@FXML
	TableColumn<Proceso, String> proceso;
	@FXML
	TableColumn<Proceso, String> estado;
	
	Stage nuevaStage;
	FXMLLoader loader;
	AnchorPane rootLayout;
	
	public void lanzadorRender(){
		try{
			nuevaStage = new Stage();
			nuevaStage.initModality(Modality.WINDOW_MODAL);
			nuevaStage.initOwner(Main.primaryStage);
			nuevaStage.setResizable(false);
			nuevaStage.setTitle("Lanzador de Procesos");
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
	
	@FXML
	
	public void lanzarProceso(MouseEvent botonApretado){
		System.out.println("Funciona!");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Proceso> resultadosTabla =  FXCollections.observableArrayList();
		resultadosTabla.addAll(Main.listaProcesos);
		procesosrun.setItems(resultadosTabla);
	}

	
	
	
}
