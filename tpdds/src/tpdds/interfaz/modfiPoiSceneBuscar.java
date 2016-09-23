package tpdds.interfaz;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.Query;
import org.hibernate.Session;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tpdds.database.Generales;
import tpdds.hibernate.HibernateSessionFactory;
import tpdds.pois.Poi;
import tpdds.usoGlobal.BuscadorPoi;
import tpdds.usoGlobal.Calculos;

public class modfiPoiSceneBuscar implements Initializable {
	@FXML
	TextField campoDeBusqueda;
	@FXML
	TableView<ObsPoi> tablaMostrada;
	@FXML
	TableColumn<ObsPoi,String> nombre; 
	@FXML
	TableColumn<ObsPoi, String> calle;
	@FXML
	TableColumn<ObsPoi, Integer> altura;
	@FXML
	TableColumn<ObsPoi, Double> distancia;
	
	ArrayList<Poi> resultados;
	
	static Stage nuevaStage;
	FXMLLoader loader;
	AnchorPane rootLayout;
	HashMap<String, Boolean> palabraOK = new HashMap<>();

	//IDCAMPO - SI ESTA OK O NO
	public void modfiPoiBuscar(){
		try{
			//Carga archivo FXML  q tiene la interfaz
			loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("modifSceneBuscar.fxml"));
			rootLayout = loader.load();

			//Creo Scene y la configuro
			Scene scene = new Scene(rootLayout);
			//Stage a abrirse
			nuevaStage = new Stage();
			nuevaStage.initModality(Modality.WINDOW_MODAL);
			nuevaStage.initOwner(Main.primaryStage);
			nuevaStage.setResizable(false);
			nuevaStage.setTitle("Buscar POI");
			nuevaStage.setScene(scene);
			nuevaStage.show();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@FXML
	private void buscar(KeyEvent evento){
		tablaMostrada.getItems().clear();
		String buscado;
		if(!evento.getCharacter().equals("\b")){
			buscado = campoDeBusqueda.getText().concat(evento.getCharacter());
		}
		else{
			buscado = campoDeBusqueda.getText();
		}
		//String buscado = pre.concat(evento.getCharacter());
		ArrayList<Poi> resultados = BuscadorPoi.buscar(buscado, Main.pois);
		ObservableList<ObsPoi> resultadosTabla =  FXCollections.observableArrayList();;
		for (Poi poi : resultados) {
			resultadosTabla.add(new ObsPoi(poi.getNombre(), poi.getDireccion().getCallePrincipal(), poi.getDireccion().getAltura(),Calculos.calcularDistanciaA(poi, Main.tablero),poi.getIddb()));
		}
		tablaMostrada.setItems(resultadosTabla);
	}

	@FXML
	private void modifOn(MouseEvent event){
		int id;
		try{
		 id = tablaMostrada.getSelectionModel().getSelectedItem().getId();
		}
		catch(Exception ex){
			id = -1;
			return;
		}
		for(Poi poi : Main.pois){
			 if (poi.getIddb() == id){
				 new modifPoiSceneFields().modfSceneRender(nuevaStage, poi);
				 return;
			 }
		 }
	}
	
	@FXML
	private void borrar(MouseEvent event){
		int id;
		try{
		 id = tablaMostrada.getSelectionModel().getSelectedItem().getId();
		}
		catch(Exception ex){
			id = -1;
			return;
		}
		for(Poi poi : Main.pois){
			 if (poi.getIddb() == id){
				 try{
				Poi PoiABorrar = new Poi(poi.getNombre(),poi.getTipo(),poi.getDireccion(),poi.getGeoloc());
				PoiABorrar.setIddb(poi.getIddb());
				PoiABorrar.setPalabrasClaves(poi.getPalabrasClaves());
				 Generales.borrarPoi(PoiABorrar);
				 Main.pois.remove(poi);
				 }catch(Exception ex){
					 ex.printStackTrace();
				 }finally{
					 this.actualizarLista();
				 }
				 return;
			 }
		 }
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		calle.setCellValueFactory(new PropertyValueFactory<>("calle"));
		altura.setCellValueFactory(new PropertyValueFactory<>("altura"));
		distancia.setCellValueFactory(new PropertyValueFactory<>("distancia"));
		this.actualizarLista();
	}

	private void actualizarLista(){
		resultados = Main.pois;
		ObservableList<ObsPoi> resultadosTabla =  FXCollections.observableArrayList();;
		for (Poi poi : resultados) {
			resultadosTabla.add(new ObsPoi(poi.getNombre(), poi.getDireccion().getCallePrincipal(), poi.getDireccion().getAltura(),Calculos.calcularDistanciaA(poi, Main.tablero),poi.getIddb()));
		}
		tablaMostrada.setItems(resultadosTabla);
	}
}