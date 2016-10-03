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
import tpdds.Usuarios.User;

public class LoginScene implements Initializable {

	@FXML
	Button VerReporteFecha;
	@FXML
	Button VerReportesTerminal;
	@FXML
	Button LanzarProcesos;
	@FXML
	Button VerReporteTotales;
	@FXML
	Button ModificarPoi;
	@FXML
	Button InsertarPoi;
	@FXML
	Button BuscarPoi;
	@FXML
	Button CrearTipoUsuario;
	@FXML
	Button ModificarTipoUsuario;
	@FXML
	Button ModificarUsuario;
	@FXML
	Button verReporte;	
	@FXML
	Text nombreusuario;
	@FXML
	Text tipousuario;
	
	Stage nuevaStage;
	FXMLLoader loader;
	AnchorPane rootLayout;
	User user;
	String tipo;
	
	public LoginScene(User user){
		this.user = user;
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
		VerReporteFecha.setDisable(!user.hasPermiso("VerReporteFecha"));
		VerReportesTerminal.setDisable(!user.hasPermiso("VerReportesTerminal"));
		LanzarProcesos.setDisable(!user.hasPermiso("LanzarProcesos"));
		VerReporteTotales.setDisable(!user.hasPermiso("VerReporteTotales"));
		ModificarPoi.setDisable(!user.hasPermiso("ModificarPoi"));
		InsertarPoi.setDisable(!user.hasPermiso("InsertarPoi"));
		BuscarPoi.setDisable(!user.hasPermiso("BuscarPoi"));
		CrearTipoUsuario.setDisable(!user.hasPermiso("CrearTipoUsuario"));
		ModificarTipoUsuario.setDisable(!user.hasPermiso("ModificarTipoUsuario"));
		ModificarUsuario.setDisable(!user.hasPermiso("ModificarUsuario"));
		nombreusuario.setText(user.getUsuarioid());
		tipousuario.setText(user.getTipo());
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
		new LanzadorDeProcesos().lanzadorRender();
	}
	
	@FXML
	public void modificarPOI(MouseEvent evento){
		new modfiPoiSceneBuscar().modfiPoiBuscar();
	}
	
	@FXML
	public void insertarPOI(MouseEvent evento){
		new InsertSceneGnr().insertSceneRender();
	}
	
	@FXML
	public void buscarPOI(MouseEvent evento){
		new BuscarPoiScene(this.user).buscarSceneRender();
	}
	
	@FXML
	public void modificarTipoUsuario(MouseEvent evento){
		
	}
	
	@FXML
	public void crearTipoUsuario(MouseEvent evento){
		new AgregarTipoUsuario().render();
	}
	
	@FXML
	public void modificarUsuario(MouseEvent evento){
		new ModificacionDeUsuario().modUserSceneRender();
	}
	
	@FXML
	public void salir(MouseEvent evento){
		new PreLoginScene().loginSceneRender();
		this.nuevaStage.close();
	}
	
}
