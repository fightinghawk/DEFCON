package tpdds.interfaz;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tpdds.database.Generales;
import tpdds.interfaz.componentes.ObsResultadoTerminal;
import tpdds.interfaz.componentes.reporteTerminal;

public class ReportePorTerminal extends Escena{

	@FXML
	TextField frase;
	@FXML
	ComboBox<String> terminal;
	@FXML
	TableView<ObsResultadoTerminal> resultadoTL;
	@FXML
	TableColumn<ObsResultadoTerminal,Integer> cantidad;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		terminal.getItems().addAll(Generales.obtenerTerminales());
		cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
		
	}
	
	@FXML
	public void buscar(MouseEvent evento){
		
		resultadoTL.getItems().clear();
		try{
		ObservableList<ObsResultadoTerminal> aMostrar = FXCollections.observableArrayList();
		ArrayList<reporteTerminal> resultados = Generales.obtenerReporteBusquedayTerminal(frase.getText(), terminal.getSelectionModel().getSelectedItem());
	    for( reporteTerminal resultado : resultados ) {
	        aMostrar.add(new ObsResultadoTerminal(resultado.getResultados()));
	        	
	    }
		
		resultadoTL.setItems(aMostrar);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

}
