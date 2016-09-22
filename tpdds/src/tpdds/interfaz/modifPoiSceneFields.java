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
import tpdds.pois.Bancos;
import tpdds.pois.CGP;
import tpdds.pois.LocalesComerciales;
import tpdds.pois.ParadaColectivo;
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
		String tipoStr = tipo.getText();
		Poi PoiAEditar = new Poi();
		Direccion direccion  = new Direccion(cPrincipal.getText(), Integer.parseInt(altura.getText()), cIzquierda.getText(), cDerecha.getText(), barrio.getText());
		direccion.setIddb(poi.getDireccion().getIddb());
		aGuardar.saveOrUpdate(direccion);
		Location geoloc = new Location(Double.parseDouble(latitud.getText()), Double.parseDouble(longitud.getText()));
		geoloc.setIddb(poi.getGeoloc().getIddb());
		aGuardar.saveOrUpdate(geoloc);
		PoiAEditar = new Poi(nombre.getText(),tipoStr,direccion,geoloc);
		PoiAEditar.setIddb(poi.getIddb());
		PoiAEditar.setPalabrasClaves(poi.getPalabrasClaves());
		
		
		
		if(poi.getTipo().equals(tipoStr))
		{
		poi.setDireccion(direccion);
		poi.setGeoloc(geoloc);
		poi.setPalabrasClaves(poi.getPalabrasClaves());
		}
		else
		{
			Main.pois.remove(poi);
			switch (poi.getTipo().toLowerCase()) {

			case "cgp":
				CGP nuevocgp =new CGP(poi.getIddb(),nombre.getText(),tipoStr,direccion,geoloc,poi.getPalabrasClaves());
				Main.pois.add(nuevocgp);
				break;
			case "colectivo":
				ParadaColectivo nuevaparada = new ParadaColectivo(poi.getIddb(),nombre.getText(),tipoStr,direccion,geoloc,poi.getPalabrasClaves());
				Main.pois.add(nuevaparada);
				break;
			case "bancos":
				Bancos nuevobancos = new Bancos(poi.getIddb(),nombre.getText(),tipoStr,direccion,geoloc,poi.getPalabrasClaves());
				Main.pois.add(nuevobancos);
				break;
			case "comercios":
				LocalesComerciales nuevolocal = new LocalesComerciales(poi.getIddb(),nombre.getText(),tipoStr,direccion,geoloc,poi.getPalabrasClaves());
				Main.pois.add(nuevolocal);
				break;
			}
			
		}
		
		
		
		
		
		
		try{
		aGuardar.saveOrUpdate(PoiAEditar);
		aGuardar.getTransaction().commit();
		aGuardar.close();
		}catch(Exception ex){
			ex.printStackTrace();
			aGuardar.getTransaction().rollback();
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
