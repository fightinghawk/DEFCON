package tpdds.interfaz;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.hibernate.Session;

import HIBERNATE.HibernateSessionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tpdds.database.Generales;
import tpdds.pois.Poi;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Location;

public class modifPoiSceneFields implements Initializable {

	@FXML
	private TextField nombre;
	@FXML
	private TextField cPrincipal;
	@FXML
	private TextField cIzquierda;
	@FXML
	private TextField cDerecha;
	@FXML
	private TextField altura;
	@FXML
	private TextField barrio;
	@FXML
	private TextField latitud;
	@FXML
	private TextField longitud;
	@FXML
	private SplitMenuButton tipo;
	
	private String tipoStr;
	private Poi poi;
	private FXMLLoader loader;
	private AnchorPane rootLayout;
	private Stage stg;
	
	//IDCAMPO - SI ESTA OK O NO
	public void modfSceneRender(Stage nuevaStage, Poi poi){
		this.poi = poi;
		try{
			stg = nuevaStage;
			// Carga archivo FXML q tiene la interfaz
			loader = new FXMLLoader(getClass().getResource("modifSceneCampos.fxml"));
			
			//loader.setLocation(getClass().getResource("modifSceneCampos.fxml"));
			loader.setController(this);
			rootLayout = loader.load();
			
			nombre.setText(poi.getNombre());
			cPrincipal.setText(poi.getDireccion().getCallePrincipal());
			cIzquierda.setText(poi.getDireccion().getCalleLateralIzq());
			cDerecha.setText(poi.getDireccion().getCalleLateralDer());
			altura.setText("" + poi.getDireccion().getAltura());
			barrio.setText(poi.getDireccion().getBarrio());
			latitud.setText("" + poi.getGeoloc().getLatitud());
			longitud.setText("" + poi.getGeoloc().getLongitud());
			// Creo Scene y la configuro
			Scene scene = new Scene(rootLayout);
			//Stage a abrirse
			nuevaStage.setScene(scene);
			tipo.setText(poi.getTipo());
			nuevaStage.show();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	@FXML
	public void cambio(KeyEvent evento){
		
	}
	
	@FXML
	public void aceptar(MouseEvent evento){
		Session aGuardar = HibernateSessionFactory.getSession();
		aGuardar.beginTransaction();
		poi.setNombre(nombre.getText());
		poi.setTipo(tipo.getText());
		Direccion direccion  = new Direccion(cPrincipal.getText(), Integer.parseInt(altura.getText()), cIzquierda.getText(), cDerecha.getText(), barrio.getText());
		direccion.setPoi(poi);
		direccion.setIddb(poi.getDireccion().getIddb());
		aGuardar.saveOrUpdate(direccion);
		Location geoloc = new Location(Double.parseDouble(latitud.getText()), Double.parseDouble(longitud.getText()));
		geoloc.setPoi(poi);
		geoloc.setIddb(poi.getGeoloc().getIddb());
		aGuardar.saveOrUpdate(geoloc);
		poi.setDireccion(direccion);
		poi.setGeoloc(geoloc);
		try{
		aGuardar.saveOrUpdate(poi);
		aGuardar.getTransaction().commit();
		aGuardar.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		stg.close();
		new modfiPoiSceneBuscar().modfiPoiBuscar();
		
		return;
	}

	@FXML
	public void chgtipo(ActionEvent event){
		MenuItem menu = (MenuItem) event.getSource();
		tipoStr = menu.getText();
		tipo.setText(tipoStr);
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
