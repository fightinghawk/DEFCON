package tpdds.interfaz;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import tpdds.interfaz.componentes.ObsPoi;
import tpdds.proceso.Proceso;

public class LanzadorDeProcesos implements Initializable {
	
	long time_start, time_end;
	ObservableList<Proceso> listaProcesos =  FXCollections.observableArrayList();
	
	@FXML
	MenuButton mb;
	@FXML
	MenuItem actualizacioncomercial;
	@FXML
	MenuItem bajapoi;
	@FXML
	TableColumn<Proceso, String> proceso;
	@FXML
	TableColumn<Proceso, String> estado;
	@FXML
	TableView<Proceso> procesosrun;
	
	Stage nuevaStage;
	FXMLLoader loader;
	AnchorPane rootLayout;
	
	public void lanzadorRender(){
		try{
			//Carga archivo FXML  q tiene la interfaz
			loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("lanzadorDeProcesos.fxml"));
			loader.setController(this);
			rootLayout = loader.load();
			//Creo Scene y la configuro
			Scene scene = new Scene(rootLayout);
			//Stage a abrirse
			nuevaStage = new Stage();
			nuevaStage.initModality(Modality.WINDOW_MODAL);
			nuevaStage.initOwner(Main.primaryStage);
			nuevaStage.setResizable(false);
			nuevaStage.setTitle("Lanzador de Procesos");
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
	
	@FXML
	
	public void agregarProceso(ActionEvent evento){
		System.out.println("Funciona!");
		procesosrun.setItems(listaProcesos);
		System.out.println(listaProcesos.get(0).getNombreProceso());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		actualizacioncomercial.setText("Actualizacion Local Comercial");
		bajapoi.setText("Baja de Pois inactivos");
		listaProcesos.addAll(Main.listaProcesos);
	}
}