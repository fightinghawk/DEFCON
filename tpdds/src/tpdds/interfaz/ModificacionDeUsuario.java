package tpdds.interfaz;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tpdds.Usuarios.User;
import tpdds.database.Generales;
import tpdds.hibernate.HibernateSessionFactory;
import tpdds.interfaz.componentes.ObsUser;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler; 

public class ModificacionDeUsuario implements Initializable{

	@FXML
	TableView<ObsUser> userModificacion;
	@FXML
	TableColumn<ObsUser, String> userNombre;
	@FXML
	TableColumn<ObsUser, String> userApellido;
	@FXML
	TableColumn<ObsUser, String> userId;
	@FXML
	TableColumn<ObsUser, String> userMail;
	@FXML
	TableColumn<ObsUser, String> userTipo;
	
	private Stage nuevaStage;
	private FXMLLoader loader;
	private AnchorPane rootLayout;
	private ObservableList<ObsUser> todosLosUsuarios;
	private ObservableList<String> tiposUsuarios;
	ObsUser usuariofinal;
	
	public void modUserSceneRender(){
		try{
			nuevaStage = new Stage();
			nuevaStage.initModality(Modality.WINDOW_MODAL);
			nuevaStage.initOwner(Main.primaryStage);
			nuevaStage.setResizable(false);
			nuevaStage.setTitle("Modificacion de Usuario");
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ModificacionDeUsuarios.fxml"));
			loader.setController(this);
			rootLayout = loader.load();
			Scene scene = new Scene(rootLayout);
			nuevaStage.setScene(scene);
			nuevaStage.show();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public ModificacionDeUsuario() {

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		userApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		userNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		userId.setCellValueFactory(new PropertyValueFactory<>("id"));
		userMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
		userTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		this.todosLosUsuarios = this.cargarUsuarios();
		this.tiposUsuarios = this.cargarTipos();
		this.userModificacion.setItems(this.todosLosUsuarios);
<<<<<<< HEAD
		this.userModificacion.setEditable(true);
		userNombre.setCellFactory(TextFieldTableCell.forTableColumn());
		userApellido.setCellFactory(TextFieldTableCell.forTableColumn());
		userId.setCellFactory(TextFieldTableCell.forTableColumn());
		userMail.setCellFactory(TextFieldTableCell.forTableColumn());
		userTipo.setCellFactory(ChoiceBoxTableCell.forTableColumn(tiposUsuarios));
=======

	
	     ContextMenu menu = new ContextMenu();
	     MenuItem editar = new MenuItem("Editar");
	     MenuItem finalizar = new MenuItem("Finalizar");
	     menu.getItems().add(editar);
	     menu.getItems().add(finalizar);
	     userModificacion.setContextMenu(menu);
	     
	     editar.setOnAction(new EventHandler<ActionEvent>(){
	     
	    	 @Override 
	    	 public void handle(ActionEvent event) {
	    		 	userModificacion.setEditable(true);
	    			userNombre.setCellFactory(TextFieldTableCell.forTableColumn());
	    			userApellido.setCellFactory(TextFieldTableCell.forTableColumn());
	    			userId.setCellFactory(TextFieldTableCell.forTableColumn());
	    			userMail.setCellFactory(TextFieldTableCell.forTableColumn());
	    			userTipo.setCellFactory(ChoiceBoxTableCell
	    			        .forTableColumn(tiposUsuarios));
	    		 ObsUser usuario = userModificacion.getSelectionModel().getSelectedItem();
	    		 userNombre.setOnEditCommit(data -> {
	    			 usuario.setNombre(data.getNewValue()); 
	    		 });
	    		 userApellido.setOnEditCommit(data -> {
	    			 usuario.setApellido(data.getNewValue());
	    		 });
	    		 userId.setOnEditCommit(data -> {

	    		 });
	    		 userMail.setOnEditCommit(data -> {
	    			 usuario.setMail(data.getNewValue());
	    		 });
	    		 userTipo.setOnEditCommit(data -> {
	    			 usuario.setTipo(data.getNewValue()); 
	    		 });
	    		 usuariofinal = usuario;
	     }});
	     
	     
	     finalizar.setOnAction(new EventHandler<ActionEvent>(){
	     
	    	 @Override 
	    	 public void handle(ActionEvent event) {
	    		 userModificacion.setEditable(false);
	    		 Generales.modificarUsuario(usuariofinal);
	    	
	     }});

>>>>>>> branch 'master' of https://github.com/fightinghawk/DEFCON.git
	}
	
	private ObservableList<String> cargarTipos() {
		ObservableList<String> tipos = FXCollections.observableArrayList();
		tipos.addAll(Generales.cargarTiposUsuarios());
		return tipos;
	}

	@FXML
	public void buscarUsuario(KeyEvent ingresoDeTecla){
		
	}
	
	@FXML
	public void modificacionUsuario(KeyEvent modificacionUsuario){
		/*if(modificacionUsuario.getCode() == KeyCode.ENTER) { 
		Generales.modificarUsuario(usuario);
		this.usuario = new User();
		}*/
	}
	
	private ObservableList<ObsUser> cargarUsuarios(){
		ObservableList<ObsUser> usuariosAMostrar = FXCollections.observableArrayList();
		ArrayList<User> resultados = Generales.cargarUsuarios();
		for (User user : resultados) {
			usuariosAMostrar.add(new ObsUser(user));
		}
		return usuariosAMostrar;
	}

}