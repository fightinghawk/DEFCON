package tpdds.interfaz;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.hibernate.Session;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tpdds.pois.Bancos;
import tpdds.pois.CGP;
import tpdds.pois.LocalesComerciales;
import tpdds.pois.ParadaColectivo;
import tpdds.pois.Poi;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Location;
import HIBERNATE.HibernateSessionFactory;

public class InsertSceneGnr implements Initializable{
	
	@FXML
	TextField nombre;
	@FXML
	TextField calle;
	@FXML
	TextField izquierda;
	@FXML
	TextField barrio;
	@FXML
	TextField altura;
	@FXML
	TextField derecha;
	@FXML
	TextField keyWord;
	@FXML
	TextField latitud;
	@FXML
	TextField Longitud;
	@FXML
	SplitMenuButton Tipo;
	
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
	private void insert(KeyEvent evento){
		TextField textField = (TextField) evento.getSource();
		String idTF = textField.getId();
		String str = textField.getText().concat(evento.getCharacter());
		if(idTF.equals("calle")){
			
		}
		else if(idTF.equals("nombre") || idTF.equals("barrio") || idTF.equals("derecha") || idTF.equals("izquierda") ){
			if(str.matches(".*\\d+.*")){
				textField.setStyle("-fx-text-fill: red");
				palabraOK.put(idTF, false);
			}
			else{
				textField.setStyle("-fx-text-fill: black");
				palabraOK.put(idTF,true);
			}			
		}
		else{
			if(!str.matches(".*\\d+.*")){
				textField.setStyle("-fx-text-fill: red");
				palabraOK.put(idTF, false);
			}
			else{
				textField.setStyle("-fx-text-fill: black");
				palabraOK.put(idTF, true);
			}			
		}
	}	
	
	@FXML
	private void continuar(MouseEvent evento){
		boolean ok = true;
		String mensajeError = "ERROR: \n";
		if(!(palabraOK.size()==9)){
			mensajeError = mensajeError.concat(("Faltan: " + (9-palabraOK.size()) + " campos\n"));
			ok = false;
		}
		Collection<Boolean> estadosPalabras = palabraOK.values();
		for (Boolean boolean1 : estadosPalabras) {
			if(!boolean1){
				ok = false;
				mensajeError = mensajeError.concat(("Hay campos invalidos"));
				break;
			}
		}
		ok = true;//para test
		if(!ok){
			AnchorPane errorPane = (AnchorPane) rootLayout.lookup("#error");
			TextArea textError = (TextArea) errorPane.lookup("#texterror");
			textError.setText(mensajeError);
			errorPane.setVisible(true);
		}
		else{
			String tipoStr = Tipo.getText();
			Session aGuardar = HibernateSessionFactory.getSession();
			aGuardar.beginTransaction();
			Direccion direc  = new Direccion(calle.getText(), Integer.parseInt(altura.getText()), izquierda.getText(), derecha.getText(), barrio.getText());	
			Location geo = new Location(Double.parseDouble(latitud.getText()), Double.parseDouble(Longitud.getText()));
			String keys = keyWord.getText();
			aGuardar.save(direc);
			aGuardar.save(geo);
			switch (Tipo.getText().toLowerCase()) {
			case "cgp":
				nuevo =new Poi(nombre.getText(),tipoStr, direc, geo);
				break;
			case "colectivo":
				nuevo = new Poi(nombre.getText(),tipoStr, direc, geo);
				break;
			case "bancos":
				nuevo = new Poi(nombre.getText(),tipoStr, direc, geo);
				break;
			case "comercios":
				nuevo = new Poi(nombre.getText(),tipoStr, direc, geo);
				break;
			}
			aGuardar.save(nuevo);
			nuevo.agregarPalabra(keys.split(","));
			aGuardar.beginTransaction().commit();
			new InsertarDia(nuevo).insertDiaRender(nuevaStage, 0);
		}
		
	}		
	
	@FXML
	public void cambiaTipo(ActionEvent event){
		MenuItem menu = (MenuItem) event.getSource();
		String tipoStr = menu.getText();
		Tipo.setText(tipoStr);
	}
	
	@FXML
	public void okboton(MouseEvent botonApretado){
		AnchorPane errorPane = (AnchorPane) rootLayout.lookup("#error");
		errorPane.setVisible(false);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}	
}
