package tpdds.interfaz;

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
import javafx.scene.control.ComboBox;
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
import tpdds.interfaz.componentes.reporteFecha;

public class ReportePorFecha extends Escena implements Initializable {
	
	@FXML
	ComboBox<String> diaBuscado;
	@FXML
	ComboBox<String> mesBuscado;
	@FXML
	TextField anioBuscado;
	@FXML
	TableView<ObsResultadoFecha> resultado;
	@FXML
	TableColumn<ObsResultadoFecha, String> fecha;
	@FXML
	TableColumn<ObsResultadoFecha, Object> cantidad;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		diaBuscado.getItems().addAll("0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31");
		mesBuscado.getItems().addAll("0","1","2","3","4","5","6","7","8","9","10","11","12");
		fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
		
	}

	@FXML
	public void buscarFecha(MouseEvent evento){
		resultado.getItems().clear();
		try{
		ObservableList<ObsResultadoFecha> aMostrar = FXCollections.observableArrayList();
		ArrayList<reporteFecha> resultados = Generales.obtenerReporteFecha(Integer.parseInt(diaBuscado.getSelectionModel().getSelectedItem()), Integer.parseInt(mesBuscado.getSelectionModel().getSelectedItem()), Integer.parseInt(anioBuscado.getText()));
	    for( reporteFecha resultado : resultados ) {
	        aMostrar.add(new ObsResultadoFecha(resultado.getFecha(),resultado.getTotales()));
	        	
	    }
		
		resultado.setItems(aMostrar);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
