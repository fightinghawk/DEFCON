package tpdds.interfaz;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AgregarTipoUsuario implements Initializable {

	@FXML
	CheckBox permInsPoi;
	@FXML
	CheckBox permVRF;
	@FXML
	CheckBox permCTU;
	@FXML
	CheckBox permMTU;
	@FXML
	CheckBox permVRTT;
	@FXML
	CheckBox permBusc;
	@FXML
	CheckBox permLP;
	@FXML
	CheckBox permVRTM;
	@FXML
	CheckBox permModPoi;
	@FXML
	CheckBox permModUser;
	@FXML
	Pane panelConfirmacion;
	
	private Stage nuevaStage;
	private FXMLLoader loader;
	private AnchorPane rootLayout;
	
	public void render(){
		try{
			nuevaStage = new Stage();
			nuevaStage.initModality(Modality.WINDOW_MODAL);
			nuevaStage.initOwner(Main.primaryStage);
			nuevaStage.setResizable(false);
			nuevaStage.setTitle("Insertar Tipo Usuario");
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("CrearTipoUsuariaScene.fxml"));
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
	public void confirmar(){
		
	}
	
	@FXML
	public void cancelar(){
		this.mostrarConfirmacion(false);
	}
	
	@FXML
	public void guardar(){
		this.mostrarConfirmacion(true);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.mostrarConfirmacion(false);
	}
	
	private void mostrarConfirmacion(boolean mostrar){
		if(rootLayout!=null){
			for (Node nodo : rootLayout.getChildren()) {
				nodo.setDisable(mostrar);
			}
		}
		panelConfirmacion.setVisible(mostrar);
		panelConfirmacion.setDisable(!mostrar);
		for (Node nodo :panelConfirmacion.getChildren()) {
			nodo.setDisable(!mostrar);
			nodo.setVisible(mostrar);
		}
	}

}
