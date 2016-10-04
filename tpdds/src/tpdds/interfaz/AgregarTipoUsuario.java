package tpdds.interfaz;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.hibernate.Session;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tpdds.Usuarios.Permisos;
import tpdds.Usuarios.TipoUsuario;
import tpdds.hibernate.HibernateSessionFactory;

public class AgregarTipoUsuario extends Escena implements Initializable {

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
	@FXML
	TextField nombre;
	@FXML
	TextField aMostrar;
	@FXML
	TextArea desc;
	
	private Stage nuevaStage;
	private FXMLLoader loader;
	private AnchorPane rootLayout;
	private ArrayList<Permisos> permisos;

	public AgregarTipoUsuario() {
		super();
		this.permisos = new ArrayList<>();
	}
	
	@FXML
	public void confirmar(){
		if(permBusc.isSelected()){permisos.add(new Permisos("buscarPoi","Permite buscar un poi"));}
		if(permCTU.isSelected()){permisos.add(new Permisos("CrearTipoUsuario","permite crear un tipo de usuario"));}
		if(permInsPoi.isSelected()){permisos.add(new Permisos("InsertarPoi","permite crear poi"));}
		if(permLP.isSelected()){permisos.add(new Permisos("Lanzarprocesos","Permite lanzar procesos"));}
		if(permModPoi.isSelected()){permisos.add(new Permisos("ModificarPoi","permite modificar poi"));}
		if(permModUser.isSelected()){permisos.add(new Permisos("ModificarUsuario","Permite modificar un usuario"));}
		if(permMTU.isSelected()){permisos.add(new Permisos("ModificarTipoUsuario","permite modificar un tipo de usuario"));}
		if(permVRF.isSelected()){permisos.add(new Permisos("VerReporteFecha","Habilita boton reporte fecha"));}
		if(permVRTM.isSelected()){permisos.add(new Permisos("VerReportesTerminal","Habilita boton ver reportes por terminal"));}
		if(permVRTT.isSelected()){permisos.add(new Permisos("VerReporteTotales","permite ver todos los reportes"));}
		String nombreTU = nombre.getText();
		String aMostrarTU = aMostrar.getText();
		String descTU = desc.getText();
		TipoUsuario tuNuevo = new TipoUsuario(nombreTU, descTU, aMostrarTU);
		tuNuevo.setPermisosUsuarios(permisos);
		Session aGuardar = HibernateSessionFactory.getSession();
		aGuardar.beginTransaction();
		try{
			aGuardar.save(tuNuevo);
			aGuardar.getTransaction().commit();
			this.nuevaStage.close();
		}catch(Exception ex){
			ex.printStackTrace();
			aGuardar.getTransaction().rollback();
		}finally{
			aGuardar.close();
		}
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
