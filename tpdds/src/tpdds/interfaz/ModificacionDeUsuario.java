package tpdds.interfaz;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.hibernate.Session;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.input.KeyEvent;
import tpdds.Usuarios.User;
import tpdds.database.Generales;
import tpdds.hibernate.HibernateSessionFactory;
import tpdds.interfaz.componentes.ObsUser;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent; 

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
	@FXML
	CheckBox modificados;
	@FXML
	TextField buscador;
	
	private ObservableList<ObsUser> todosLosUsuariosSinModificar;
	private ObservableList<ObsUser> todosLosUsuariosModificados;
	private ObservableList<String> tiposUsuarios;
	private String buscado;
	
	public ModificacionDeUsuario() {
		this.todosLosUsuariosModificados = FXCollections.observableArrayList();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		userApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		userNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		userId.setCellValueFactory(new PropertyValueFactory<>("id"));
		userMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
		userTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		
		userNombre.setCellFactory(TextFieldTableCell.forTableColumn());
		userApellido.setCellFactory(TextFieldTableCell.forTableColumn());
		userMail.setCellFactory(TextFieldTableCell.forTableColumn());
		this.tiposUsuarios = this.cargarTipos();
		userTipo.setCellFactory(ChoiceBoxTableCell.forTableColumn(tiposUsuarios));
		
		this.todosLosUsuariosSinModificar = this.cargarUsuarios();
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
		this.buscado = this.buscador.getText();
		this.actualizarTabla(this.buscado);
	}
	@FXML
	public void edicionNombre(CellEditEvent<ObsUser, String> evento){
		ObsUser aEditar = evento.getRowValue();
		aEditar.setNombre(evento.getNewValue());
		this.usuarioActualizado(aEditar);
	}
	@FXML
	public void edicionApellido(CellEditEvent<ObsUser, String> evento){
		ObsUser aEditar = evento.getRowValue();
		aEditar.setApellido(evento.getNewValue());
		this.usuarioActualizado(aEditar);
	}
	@FXML
	public void edicionEmail(CellEditEvent<ObsUser, String> evento){
		ObsUser aEditar = evento.getRowValue();
		aEditar.setMail(evento.getNewValue());
		this.usuarioActualizado(aEditar);
	}
	@FXML
	public void edicionTipo(CellEditEvent<ObsUser, String> evento){
		ObsUser aEditar = evento.getRowValue();
		aEditar.setTipo(evento.getNewValue());
		this.usuarioActualizado(aEditar);
	}
	@FXML
	public void borrarUsuario(ActionEvent evento){
		ObsUser userABorrar = this.userModificacion.getSelectionModel().getSelectedItem();
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		session.delete(userABorrar.getUsuario());
        session.getTransaction().commit();
        session.close();
        this.todosLosUsuariosModificados.remove(userABorrar);
        this.todosLosUsuariosSinModificar.remove(userABorrar);
        actualizarTabla(this.buscado);
	}
	@FXML
	public void finalizarEdicion (ActionEvent evento){
		ArrayList<User> usuariosModificados = new ArrayList<>(); 
		for (ObsUser user : this.todosLosUsuariosModificados) {
			usuariosModificados.add(user.getUsuario());
		}
		if (!usuariosModificados.isEmpty())
			Generales.modificarUsuario(usuariosModificados);
		this.nuevaStage.close();
	}
	
	@FXML
	public void mostrarSoloModificados(ActionEvent evento){
		this.actualizarTabla(this.buscado);
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
		this.actualizarTabla(this.buscado);
	}
	
	private void actualizarTabla(String buscado){
		ObservableList<ObsUser> elementosParaFiltrar = FXCollections.observableArrayList();
		this.userModificacion.setItems(elementosParaFiltrar);
		elementosParaFiltrar.addAll(todosLosUsuariosModificados);
		if(!this.modificados.isSelected())
			elementosParaFiltrar.addAll(this.todosLosUsuariosSinModificar);
		for (ObsUser obsUser : elementosParaFiltrar) {
			if (!obsUser.contieneSubString(buscado)) {
				elementosParaFiltrar.remove(obsUser);
			}
		}
		this.userModificacion.setItems(elementosParaFiltrar);
	}

	
}
