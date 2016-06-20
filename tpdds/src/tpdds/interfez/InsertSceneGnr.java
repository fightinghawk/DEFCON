package tpdds.interfez;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InsertSceneGnr {
	

	static FXMLLoader loader;
	static AnchorPane rootLayout;
	static HashMap<String, Boolean> palabraOK = new HashMap<>();
	static Stage nuevaStage;
	//IDCAMPO - SI ESTA OK O NO
	public static void insertSceneRender(){
		try{
			nuevaStage = new Stage();
			nuevaStage.setResizable(false);
			nuevaStage.setTitle("Insertar POI");
			loader = new FXMLLoader();
			loader.setLocation(interfaz.class.getResource("insertSceneGnr.fxml"));
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
		if(idTF.equals("nombre") || idTF.equals("calle") || idTF.equals("barrio") || idTF.equals("derecha") || idTF.equals("izquierda") ){
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
			InsertarDia.insertDiaRender(nuevaStage, 0);
		}
		
	}		
	
	@FXML
	public void okboton(MouseEvent botonApretado){
		AnchorPane errorPane = (AnchorPane) rootLayout.lookup("#error");
		errorPane.setVisible(false);
	}	
}
