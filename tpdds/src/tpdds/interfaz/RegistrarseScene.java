package tpdds.interfaz;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tpdds.Administrador.*;
import tpdds.Usuarios.UsuarioComun;

public class RegistrarseScene implements Initializable {
	
	@FXML
	TextField nombreusuario;
	@FXML
	TextField apellidousuario;
	@FXML
	TextField idusuario;
	@FXML
	TextField correousuario;
	@FXML
	TextField passwordusuario;
	@FXML
	Button registrarsedb;
	
	
	Stage nuevaStage;
	FXMLLoader loader;
	AnchorPane rootLayout;
	UsuarioComun usuarionuevo;

	
	public RegistrarseScene(){
			
	}
	
	public void RegistrarseSceneRender(){
		try{
			nuevaStage = new Stage();
			nuevaStage.initModality(Modality.WINDOW_MODAL);
			nuevaStage.initOwner(Main.primaryStage);
			nuevaStage.setResizable(false);
			nuevaStage.setTitle("Registrar");
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("registrarScene.fxml"));
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

		
	}
	

	
	@FXML
	public void registrarsedb(MouseEvent evento){
		

		
		

		
	}
	
	@FXML
	public void finalizar(MouseEvent evento)
	{
		Main.usuarioNuevo = new UsuarioComun(nombreusuario.getText(), apellidousuario.getText(), idusuario.getText(), passwordusuario.getText(), correousuario.getText());
		Main.usuarios.add(usuarionuevo);
		nuevaStage.close();
	}
	
	
	
}