package tpdds.interfaz;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

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
import javafx.stage.Stage;
import tpdds.pois.Poi;
import tpdds.usoGlobal.BuscadorPoi;
import tpdds.usoGlobal.Calculos;

public class buscarPoiScene implements Initializable {
	
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
	
	Stage nuevaStage;
	FXMLLoader loader;
	AnchorPane rootLayout;
	HashMap<String, Boolean> palabraOK = new HashMap<>();

	//IDCAMPO - SI ESTA OK O NO
	public void buscarSceneRender(){
		try{
			//Carga archivo FXML  q tiene la interfaz
			loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("buscarScene.fxml"));
			rootLayout = loader.load();
			//
			//Creo Scene y la configuro
			Scene scene = new Scene(rootLayout);
			//Stage a abrirse
			nuevaStage = new Stage();
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
			resultadosTabla.add(new ObsPoi(poi.getNombre(), poi.getDireccion().getCallePrincipal(), poi.getDireccion().getAltura(),Calculos.calcularDistanciaA(poi, Main.tablero)));
		}
		tablaMostrada.setItems(resultadosTabla);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		calle.setCellValueFactory(new PropertyValueFactory<>("calle"));
		altura.setCellValueFactory(new PropertyValueFactory<>("altura"));
		distancia.setCellValueFactory(new PropertyValueFactory<>("distancia"));
	}
}