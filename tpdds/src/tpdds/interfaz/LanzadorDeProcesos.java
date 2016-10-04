package tpdds.interfaz;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
<<<<<<< HEAD
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
=======
>>>>>>> branch 'master' of https://github.com/fightinghawk/DEFCON.git
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
<<<<<<< HEAD
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import tpdds.interfaz.componentes.ObsPoi;
import tpdds.interfaz.componentes.ObsProceso;
import tpdds.interfaz.componentes.ObsUser;
import tpdds.pois.Poi;
=======
>>>>>>> branch 'master' of https://github.com/fightinghawk/DEFCON.git
import tpdds.proceso.Proceso;

public class LanzadorDeProcesos extends Escena{
	
	long time_start, time_end;
	ArrayList<Proceso> listaProcesos;
	ObservableList<ObsProceso> procesosEnTabla =  FXCollections.observableArrayList();
	ObservableList<ObsProceso> aux =  FXCollections.observableArrayList();
	
	
	@FXML
	MenuButton mb;
	@FXML
	MenuItem actualizacioncomercial;
	@FXML
	MenuItem bajapoi;
	@FXML
	TableColumn<ObsProceso, String> proceso;
	@FXML
	TableColumn<ObsProceso, String> estado;
	@FXML
	TableView<ObsProceso> procesosrun;
	
	@FXML
<<<<<<< HEAD
	
	public void lanzarProcesos(MouseEvent botonApretado){
		for (ObsProceso proc : procesosEnTabla) {
				proc.setResultado("Exitoso");
		}
		aux.addAll(procesosEnTabla);
		procesosrun.getItems().clear();
		
		procesosrun.setItems(aux);
=======
	public void lanzarProceso(MouseEvent botonApretado){
		System.out.println("Funciona!");
>>>>>>> branch 'master' of https://github.com/fightinghawk/DEFCON.git
	}
	
	@FXML
	public void agregarAct(ActionEvent evento){
		for (Proceso proc : listaProcesos) {
			if (proc.getNombreProceso().equals("Actualizacion Local Comercial")){
				procesosEnTabla.add(new ObsProceso(proc.getNombreProceso()));
			}
		}
		procesosrun.setItems(procesosEnTabla);
	}
	public void agregarBaja(ActionEvent evento){
		for (Proceso proc : listaProcesos) {
			if (proc.getNombreProceso().equals("Baja de Pois")){
				procesosEnTabla.add(new ObsProceso(proc.getNombreProceso()));
			}
		}
		procesosrun.setItems(procesosEnTabla);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		actualizacioncomercial.setText("Actualizacion Local Comercial");
		bajapoi.setText("Baja de Pois inactivos");
		proceso.setCellValueFactory(new PropertyValueFactory<>("nombreProceso"));
		estado.setCellValueFactory(new PropertyValueFactory<>("resultado"));
		procesosrun.setEditable(true);
		listaProcesos = Main.listaProcesos;
	}
	
}