package tpdds.interfaz;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tpdds.Administrador.Administrador;
import tpdds.Administrador.Usuario;

public class loginScene implements Initializable {

	@FXML
	Button verReporte;
	
	@FXML
	Text nombreusuario;
	@FXML
	Text tipousuario;
	
	Stage nuevaStage;
	FXMLLoader loader;
	AnchorPane rootLayout;
	String user;
	String tipo;
	
	public loginScene(Usuario user){
		this.user = user.getNombreUsuario();
		this.tipo = user.getTipo();	
	}
	
	public void loginSceneRender(){
		try{
			nuevaStage = new Stage();
			nuevaStage.initModality(Modality.WINDOW_MODAL);
			nuevaStage.initOwner(Main.primaryStage);
			nuevaStage.setResizable(false);
			nuevaStage.setTitle("Insertar POI");
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("loginScene.fxml"));
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
		nombreusuario.setText(user);
		tipousuario.setText(tipo);
		
	}
	
	@FXML
	public void verreportefecha(MouseEvent evento){
		new ReportePorFecha().ReportePorFechaSceneRender();
	}

	@FXML
	public void verreporteterminal(){
		new ReportePorTerminal().ReportePorTerminalRender();
	}
	
	@FXML
	public void verreportetotal(){
		new ReporteTotal().ReportePorTerminalRender();
	}
	
	@FXML
	public void ejecutarprocesos(){
		
	}
}
