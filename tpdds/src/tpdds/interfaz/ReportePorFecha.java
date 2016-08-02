package tpdds.interfaz;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tpdds.Administrador.Usuario;
import tpdds.database.Generales;

public class ReportePorFecha implements Initializable {
	
	@FXML
	TextField diaBuscado;
	@FXML
	TextField mesBuscado;
	@FXML
	TextField anioBuscado;
	@FXML
	TableView<ObsResultadoFecha> resultado;
	
	
	Stage nuevaStage;
	FXMLLoader loader;
	AnchorPane rootLayout;
	
	public void ReportePorFechaSceneRender(){
		try{
			nuevaStage = new Stage();
			nuevaStage.initModality(Modality.WINDOW_MODAL);
			nuevaStage.initOwner(Main.primaryStage);
			nuevaStage.setResizable(false);
			nuevaStage.setTitle("Reporte por fecha");
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("reportesPorFechaScene.fxml"));
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
	public void buscarFecha(MouseEvent evento){
		try{
		ObservableList<ObsResultadoFecha> aMostrar = FXCollections.observableArrayList();
		ArrayList<ObsResultadoFecha> resultados = Generales.obtenerReporteFecha(Integer.parseInt(diaBuscado.getText()), Integer.parseInt(mesBuscado.getText()), Integer.parseInt(anioBuscado.getText()));
		for (ObsResultadoFecha resultado : resultados) 
		{
			aMostrar.add(resultado);
		}
		
		resultado.setItems(aMostrar);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
