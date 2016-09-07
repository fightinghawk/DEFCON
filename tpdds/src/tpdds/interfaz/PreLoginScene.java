package tpdds.interfaz;

import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
import tpdds.Usuarios.TipoUsuario;
import tpdds.Usuarios.UsuarioComun;


public class PreLoginScene implements Initializable {

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
			nuevaStage.setTitle("PreLogin");
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
		if(usuario.equalsIgnoreCase("admin") && passWord.equalsIgnoreCase("123456")){
			new LoginScene(new Administrador("admin", "123456")).loginSceneRender();
			nuevaStage.close();
			this.cerrar();
		}	
	}
	
	@FXML
	public void finalizarRegistro(MouseEvent eventoRegistro){
		UsuarioComun eze = new UsuarioComun("Ezequiel", "Castro", "Ezec96", "123456", "ezec09@hotmail.com");
		TipoUsuario tipo = new TipoUsuario("userComun", "Usuario con permisos para buscar");
		eze.setTipoUsuario(tipo);
		SessionFactory guardarUsuario = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session aGuardar = guardarUsuario.openSession();
		aGuardar.beginTransaction();
		aGuardar.saveOrUpdate(tipo);
		aGuardar.save(eze);
		aGuardar.getTransaction().commit();
		aGuardar.close();
		this.cerrar();
	}
	
	private void cerrar(){
		this.nuevaStage.close();
	}
	
	
	
}
