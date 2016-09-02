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
import tpdds.Administrador.Administrador;
import tpdds.Administrador.UsuarioComun;


public class preLoginScene implements Initializable {

	@FXML
	TextField user;
	@FXML
	TextField password;
	@FXML
	Button submit;
	
	
	Stage nuevaStage;
	FXMLLoader loader;
	AnchorPane rootLayout;
	
	public void loginSceneRender(){
		try{
			nuevaStage = new Stage();
			nuevaStage.initModality(Modality.WINDOW_MODAL);
			nuevaStage.initOwner(Main.primaryStage);
			nuevaStage.setResizable(false);
			nuevaStage.setTitle("Insertar POI");
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("preLoginScene.fxml"));
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
		// TODO Auto-generated method stub
		
	}

	@FXML
	public void submitBoton(MouseEvent evento){
		String usuario = user.getText();
		String passWord = password.getText();
		
		try{
		if (usuario.equalsIgnoreCase(Main.usuarioNuevo.getUsuarioid()) && passWord.equalsIgnoreCase(Main.usuarioNuevo.getUsuarioid()))
		{
			new loginSceneUComun(Main.usuarioNuevo).loginSceneRender();
			nuevaStage.close();
		}	 }
		catch(Exception ex){
		
		/*for(int i=0;i<= (Main.usuarios.size());i++) {
			
			UsuarioComun usuarioTemporal = Main.usuarios.get(i);
			
			if (usuario.equalsIgnoreCase(usuarioTemporal.getUsuarioid()) && passWord.equalsIgnoreCase(usuarioTemporal.getUsuarioid()))
				{
					new loginSceneUComun(usuarioTemporal).loginSceneRender();
					nuevaStage.close();
				}	  
		}*/
		
		if(usuario.equalsIgnoreCase("admin") && passWord.equalsIgnoreCase("123456")){
			new loginScene(new Administrador("admin", "123456")).loginSceneRender();
			nuevaStage.close();
		}
		
		if(usuario.equalsIgnoreCase("amichel") && passWord.equalsIgnoreCase("123")){
			new loginSceneUComun(new UsuarioComun("andres", "michel","amichel","123","asd@asd.com")).loginSceneRender();
			nuevaStage.close();
		}
		}
		
		
	}
	
	
	
}
