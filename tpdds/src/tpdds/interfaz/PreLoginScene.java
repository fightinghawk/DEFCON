package tpdds.interfaz;

import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.scene.BoundsAccessor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tpdds.Usuarios.TipoUsuario;
import tpdds.Usuarios.User;
import tpdds.hibernate.HibernateSessionFactory;


public class PreLoginScene extends Escena implements Initializable {

	private boolean registroValido;
	
	@FXML
	TextField user;
	@FXML
	TextField password;
	@FXML
	Button submit;
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
	TextArea erroresNotificacion;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@FXML
	public void submitBoton(MouseEvent evento){
		String usuario = user.getText();
		String passWord = password.getText();
		Session cargar = HibernateSessionFactory.getSession();
		cargar.beginTransaction();
		try{
			User user = cargar.get(User.class, usuario);
			if(passWord != null && user.getUsuariopassword().equals(passWord)){
				System.out.println("Password correcta");
				new LoginScene(user).render("Home","loginScene.fxml");
				this.cerrar();
			}else{
				System.out.println("Error de password");
				erroresNotificacion.setText("Error de password o usuario");
				user = null;
			};
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			cargar.close();
		}
	}
	
	@FXML
	public void finalizarRegistro(MouseEvent eventoRegistro){
		
		if(!registroValido || !camposLlenos()){
			if(!registroValido){
				erroresNotificacion.setText("El ID ya se encuentra disponible, prueba otro.");
			}else{
				erroresNotificacion.setText("Completa todos los campos");
			}
			return;
		}
		User usuario = 
				new User(nombreusuario.getText(), apellidousuario.getText(), 
				idusuario.getText(), passwordusuario.getText(), correousuario.getText());
		usuario.setTipoUsuario(new TipoUsuario("userComun", "Usuario con los permisos basicos.", "Usuario Comun"));
		Session aGuardar = HibernateSessionFactory.getSession();
		aGuardar.beginTransaction();
		try{
			aGuardar.save(usuario);
			aGuardar.getTransaction().commit();
			new LoginScene(usuario).render("Home","loginScene.fxml");
			this.cerrar();
		}catch(Exception ex){
			ex.printStackTrace();
			aGuardar.getTransaction().rollback();
		}finally{
			erroresNotificacion.clear();
			aGuardar.close();
		}

	}
	
	@FXML
	public void ingresoDatos(KeyEvent ingresaAlgo){
		/*TextField campoDeTexto = (TextField) ingresaAlgo.getSource();
		String aComprobar = campoDeTexto.getText().concat(ingresaAlgo.getCharacter());
		aComprobar = aComprobar.substring(0, aComprobar.length()-1);
		SessionFactory fact = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session factSesion = fact.openSession();
		factSesion.beginTransaction();
		try{
			Criteria criteria = factSesion.createCriteria(User.class);
			criteria.add(Restrictions.eq("usuarioid",aComprobar));
			criteria.setProjection(Projections.rowCount());
			long count = (Long) criteria.uniqueResult();
			factSesion.getTransaction().commit();
			if(count!=0){
				System.out.println("Presente: " + aComprobar);
				idusuario.setStyle("-fx-text-fill: red");
				registroValido = false;
				erroresNotificacion.setText("El ID ya se encuentra disponible, prueba otro.");
			}else{
				System.out.println("Ausente: "+ aComprobar);
				idusuario.setStyle("-fx-text-fill: black");
				registroValido = true;
				erroresNotificacion.clear();
			}
		}catch(Exception ex){
			ex.printStackTrace();
			factSesion.getTransaction().rollback();
		}finally {
			factSesion.close();
		}*/
	}
	
	public boolean camposLlenos(){
		return !(apellidousuario.getText().isEmpty() ||
		nombreusuario.getText().isEmpty() ||
		idusuario.getText().isEmpty() ||
		correousuario.getText().isEmpty()|| 
		passwordusuario.getText().isEmpty());
	}
	
	private void cerrar(){
		this.nuevaStage.close();
	}
	
	
	
}
