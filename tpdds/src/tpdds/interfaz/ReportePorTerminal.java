package tpdds.interfaz;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tpdds.database.Generales;
import tpdds.interfaz.componentes.ObsResultadoFecha;
import tpdds.interfaz.componentes.ObsResultadoTerminal;
import tpdds.interfaz.componentes.reporteFecha;
import tpdds.interfaz.componentes.reporteTerminal;

public class ReportePorTerminal implements Initializable {

	@FXML
	TextField frase;
	@FXML
	TextField terminal;
	@FXML
	TableView<ObsResultadoTerminal> resultadoTL;
	@FXML
	TableColumn<ObsResultadoTerminal,Integer> cantidad;
	
	Stage nuevaStage;
	FXMLLoader loader;
	AnchorPane rootLayout;
	
	public void ReportePorTerminalRender(){
		try{
			nuevaStage = new Stage();
			nuevaStage.initModality(Modality.WINDOW_MODAL);
			nuevaStage.initOwner(Main.primaryStage);
			nuevaStage.setResizable(false);
			nuevaStage.setTitle("Reporte por Búsqueda y Terminal");
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
		cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
		
	}
	
	@FXML
	public void buscar(MouseEvent evento){
		
		resultadoTL.getItems().clear();
		try{
		ObservableList<ObsResultadoTerminal> aMostrar = FXCollections.observableArrayList();
		ArrayList<reporteTerminal> resultados = Generales.obtenerReporteBusquedayTerminal(frase.getText(), terminal.getText());
	    for( reporteTerminal resultado : resultados ) {
	        aMostrar.add(new ObsResultadoTerminal(resultado.getTotales()));
	        	
	    }
		
		resultadoTL.setItems(aMostrar);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

}
