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

public class LoginScene extends Escena implements Initializable {

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
	
	User user;
	String tipo;
	
	public LoginScene(User user){
		this.user = user;
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
		new ReportePorFecha().render("Reportes Por Fecha","reportesPorFechaScene.fxml");
	}

	@FXML
	public void verreporteterminal(){
		new ReportePorTerminal().render("Reporte por Terminal","reportePorTerminaScene.fxml");
	}
	
	@FXML
	public void verreportetotal(){
		new ReporteTotal().render("Reportes Por Usuario", "reporteTotal.fxml");
	}
	
	@FXML
	public void ejecutarprocesos(){
		new LanzadorDeProcesos().render("Ejecucion de Procesos","lanzadorDeProcesos.fxml");
	}
	
	@FXML
	public void modificarPOI(MouseEvent evento){
		new modfiPoiSceneBuscar().render("Modificar POI", "modifSceneBuscar.fxml");
	}
	
	@FXML
	public void insertarPOI(MouseEvent evento){
		new InsertSceneGnr().render("Insertar POI", "insertSceneGnr.fxml");
	}
	
	@FXML
	public void buscarPOI(MouseEvent evento){
		new BuscarPoiScene(this.user).render("Buscar POI","buscarScene.fxml");
	}
	
	@FXML
	public void modificarTipoUsuario(MouseEvent evento){
		
	}
	
	@FXML
	public void crearTipoUsuario(MouseEvent evento){
		new AgregarTipoUsuario().render("Crear Tipo Usuario", "CrearTipoUsuariaScene.fxml");
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
