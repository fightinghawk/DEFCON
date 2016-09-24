package tpdds.interfaz;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.hibernate.Session;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tpdds.hibernate.HibernateSessionFactory;
import tpdds.pois.Bancos;
import tpdds.pois.CGP;
import tpdds.pois.LocalesComerciales;
import tpdds.pois.ParadaColectivo;
import tpdds.pois.Poi;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Location;

public class InsertSceneGnr implements Initializable{
	
	@FXML
	TextField nombre, calle, izquierda, barrio, altura, derecha,
	op1, op2, latitud, Longitud, nombreDia, horaUnoInicio,
	horaUnoFin, horaDosInicio, horaDosFin, nombreServ;
	@FXML
	TextArea keyWord, errorDia, descServ;
	@FXML
	ComboBox<String> Tipo, demasInfo;
	@FXML
	Text top1,top2;
	@FXML
	Pane infoDias, infoServs;
	
	
	Poi nuevo;
	FXMLLoader loader;
	AnchorPane rootLayout;
	HashMap<String, Boolean> palabraOK = new HashMap<>();
	Stage nuevaStage;
	//IDCAMPO - SI ESTA OK O NO
	
	public void insertSceneRender(){
		try{
			nuevaStage = new Stage();
			nuevaStage.initModality(Modality.WINDOW_MODAL);
			nuevaStage.initOwner(Main.primaryStage);
			nuevaStage.setResizable(false);
			nuevaStage.setTitle("Insertar POI");
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("insertSceneGnr.fxml"));
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
	private void seleccionTipo(ActionEvent evento){
		if(Tipo.getSelectionModel().getSelectedItem()==null)
			return;
		switch (Tipo.getSelectionModel().getSelectedItem()) {
		case "Colectivo":
			mostrarOpcional(top1, op1, true);
			top1.setText("Nro Parada:");
			break;
		case "L.Comerciales":
			mostrarOpcional(top1, op1, true);
			top1.setText("Rubro");
			break;
		default:
			mostrarOpcionales(false);
			break;
		}
	}
	
	@FXML
	private void seleccionInfo(ActionEvent evento){
		if(demasInfo.getSelectionModel().getSelectedItem()==null)
			return;
		switch (demasInfo.getSelectionModel().getSelectedItem()) {
		case "Dias":
			mostrarPane(infoDias, true);
			mostrarPane(infoServs, false);
			break;
		case "Servicios":
			mostrarPane(infoDias, false);
			mostrarPane(infoServs, true);
			break;

		default:
			break;
		} 
	}
	
	@FXML
	private void guardarServicio(MouseEvent evento){
		
	}
	
	@FXML
	private void guardarDia(MouseEvent evento){
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Tipo.getItems().addAll("Colectivo","Bancos","CGP","L.Comerciales");
		demasInfo.getItems().addAll("Dias","Servicios");
		mostrarOpcionales(false);
		mostrarPane(infoDias, false);
		mostrarPane(infoServs, false);
	}
	
	private void mostrarPane(Pane panel, boolean valor){
		for (Node node : panel.getChildren()) {
			node.setVisible(valor);
			node.setDisable(!valor);
		}
		panel.setVisible(valor);
		panel.setDisable(!valor);
	}
	
	private void mostrarOpcionales(boolean valor){
		op1.setVisible(valor);
		op2.setVisible(valor);
		top1.setVisible(valor);
		top2.setVisible(valor);
		op1.setDisable(!valor);
		op2.setDisable(!valor);
		top1.setDisable(!valor);
		top2.setDisable(!valor);
	}
	
	private void mostrarOpcional(Text top,TextField op, boolean valor){
		op.setVisible(valor);
		op.setDisable(!valor);
		top.setVisible(valor);
		top.setDisable(!valor);
	}
}
