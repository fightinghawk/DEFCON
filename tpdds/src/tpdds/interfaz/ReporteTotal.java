package tpdds.interfaz;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tpdds.database.Generales;

public class ReporteTotal implements Initializable {

	
	@FXML
	TableView<ObsResultadoTotal> resultadosTL;
	@FXML
	TableColumn<ObsResultadoTotal, String> terminal;
	@FXML
	TableColumn<ObsResultadoTotal,Object> totales;
	
	Stage nuevaStage;
	FXMLLoader loader;
	AnchorPane rootLayout;
	
	public void ReportePorTerminalRender(){
		try{
			nuevaStage = new Stage();
			nuevaStage.initModality(Modality.WINDOW_MODAL);
			nuevaStage.initOwner(Main.primaryStage);
			nuevaStage.setResizable(false);
			nuevaStage.setTitle("Reporte por TERMINALES");
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("reporteTotal.fxml"));
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
		terminal.setCellValueFactory(new PropertyValueFactory<>("terminal"));
		totales.setCellValueFactory(new PropertyValueFactory<>("totales"));
		resultadosTL.getItems().clear();
		try{
		ObservableList<ObsResultadoTotal> aMostrar = FXCollections.observableArrayList();
		ResultSet resultados = Generales.obtenerReporteTerminales();
	    while ( resultados.next() ) {
	        	aMostrar.add(new ObsResultadoTotal(resultados.getObject("nombre"),resultados.getObject("totales")));
	        	
	    }
		
		resultadosTL.setItems(aMostrar);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

}
