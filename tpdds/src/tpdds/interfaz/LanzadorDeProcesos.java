package tpdds.interfaz;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tpdds.interfaz.componentes.ObsProceso;
import tpdds.proceso.Proceso;

public class LanzadorDeProcesos extends Escena{
	
	long time_start, time_end;
	ArrayList<Proceso> listaProcesos;
	int contAct,contBaja;
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
	
	Stage nuevaStage;
	FXMLLoader loader;
	AnchorPane rootLayout;
	
	@FXML
	
	public void lanzarProcesos(MouseEvent botonApretado){
		for (ObsProceso proc : procesosEnTabla) {
			if (proc.getNombreProceso().equals("Actualizacion Local Comercial")){
				if(contAct>2){
					proc.setResultado("Fallido");
				}
				else{
					proc.setResultado("Exitoso");
				}
			}
			if (proc.getNombreProceso().equals("Baja de Pois")){
				if(contBaja>4){
					proc.setResultado("Fallido");
				}
				else{
					proc.setResultado("Exitoso");	
			}
		}
		}
		aux.addAll(procesosEnTabla);
		procesosrun.getItems().clear();
		procesosrun.setItems(aux);
				
	}
	
	@FXML
	public void agregarAct(ActionEvent evento){
		contAct ++;
		for (Proceso proc : listaProcesos) {
			if (proc.getNombreProceso().equals("Actualizacion Local Comercial")){
				procesosEnTabla.add(new ObsProceso(proc.getNombreProceso()));
			}
		}
		procesosrun.setItems(procesosEnTabla);
	}
	
	public void agregarBaja(ActionEvent evento){
		contBaja ++;
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