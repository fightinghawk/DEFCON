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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import tpdds.Usuarios.User;
import tpdds.database.Generales;
import tpdds.hibernate.HibernateSessionFactory;
import tpdds.interfaz.componentes.ObsUser;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler; 

public class ModificacionDeUsuario extends Escena{

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
	@FXML
	ContextMenu opciones;
	@FXML
	MenuItem editar;
	@FXML
	MenuItem finalizar;
	private ObservableList<ObsUser> todosLosUsuariosSinModificar;
	private ObservableList<ObsUser> todosLosUsuariosModificados;
	private ObservableList<String> tiposUsuarios;
	ObsUser usuariofinal;
	
	public ModificacionDeUsuario() {
		this.todosLosUsuariosModificados = FXCollections.observableArrayList();
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.userModificacion.setRowFactory(new Callback<TableView<ObsUser>, TableRow<ObsUser>>(){
			@Override
			public TableRow<ObsUser> call(TableView<ObsUser> param) {
				return new TableRow<ObsUser>(){
					@Override
					protected void updateItem(ObsUser usuario, boolean valorBooleano){
						if(usuario!=null){
							if(usuario.modificado){
		                        setStyle("-fx-background-color:red");
							}

						}
                        super.updateItem(usuario, valorBooleano);
					}
				};
			}
			
		});

		userApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		userNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		userId.setCellValueFactory(new PropertyValueFactory<>("id"));
		userMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
		userTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		
		userNombre.setCellFactory(TextFieldTableCell.forTableColumn());
		userApellido.setCellFactory(TextFieldTableCell.forTableColumn());
		userMail.setCellFactory(TextFieldTableCell.forTableColumn());
		userTipo.setCellFactory(ChoiceBoxTableCell.forTableColumn(tiposUsuarios));
		
		this.todosLosUsuariosSinModificar = this.cargarUsuarios();
		this.tiposUsuarios = this.cargarTipos();
		this.userModificacion.setItems(this.todosLosUsuariosSinModificar);
		this.userModificacion.setEditable(true);

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
	public void edicionNombre(CellEditEvent<ObsUser, String> evento){
		ObsUser aEditar = evento.getRowValue();
		aEditar.modificado = true;
		aEditar.setNombre(evento.getNewValue());
		this.usuarioActualizado(aEditar);
	}
	@FXML
	public void edicionApellido(CellEditEvent<ObsUser, String> evento){
		ObsUser aEditar = evento.getRowValue();
		aEditar.modificado = true;
		aEditar.setApellido(evento.getNewValue());
		this.usuarioActualizado(aEditar);
	}
	@FXML
	public void edicionEmail(CellEditEvent<ObsUser, String> evento){
		ObsUser aEditar = evento.getRowValue();
		aEditar.modificado = true;
		aEditar.setMail(evento.getNewValue());
		this.usuarioActualizado(aEditar);
	}
	@FXML
	public void edicionTipo(CellEditEvent<ObsUser, String> evento){
		ObsUser aEditar = evento.getRowValue();
		aEditar.modificado = true;
		aEditar.setTipo(evento.getNewValue());
		this.usuarioActualizado(aEditar);
	}
	
	@FXML
	public void finalizarEdicion (ActionEvent evento){
		 userModificacion.setEditable(false);
		 Generales.modificarUsuario(usuariofinal);
	}
	
	private ObservableList<ObsUser> cargarUsuarios(){
		ObservableList<ObsUser> usuariosAMostrar = FXCollections.observableArrayList();
		ArrayList<User> resultados = Generales.cargarUsuarios();
		for (User user : resultados) {
			usuariosAMostrar.add(new ObsUser(user));
		}
		return usuariosAMostrar;
	}
	
	private void usuarioActualizado(ObsUser userModificado){
		if(this.todosLosUsuariosSinModificar.contains(userModificado)){
			this.todosLosUsuariosSinModificar.remove(userModificado);
			this.todosLosUsuariosModificados.add(userModificado);
		}
		this.actualizarTabla(null);
	}
	
	@FXML
	private void cambiado(Event evento){
		System.out.println("hola");
	}
	
	private void actualizarTabla(String buscado){
		this.userModificacion.setItems(this.todosLosUsuariosSinModificar);
		this.userModificacion.getItems().addAll(todosLosUsuariosModificados);
		for(ObsUser userMostrado : this.userModificacion.getItems()){
		}
	}

	
}
