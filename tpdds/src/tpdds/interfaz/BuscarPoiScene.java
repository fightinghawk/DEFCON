package tpdds.interfaz;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import java.util.List;

import apiExterna.BancoExterna;
import apiExterna.jsonBancos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tpdds.buscadores.Buscador;
import tpdds.buscadores.KeySearch;
import tpdds.buscadores.NameSeach;
import tpdds.database.Generales;
import tpdds.pois.Poi;
import tpdds.usoGlobal.BuscadorPoi;
import tpdds.usoGlobal.Calculos;

public class BuscarPoiScene implements Initializable {
	
	private static final double SEGUNDOS_PARAMETRIZADOS = 1;
	long time_start, time_end;
	double time;
	Integer resultados_final;
	private Buscador filtro;
	
	@FXML
	ChoiceBox<String> criterio;
	@FXML
	TextField campoDeBusqueda;
	@FXML
	TextField contenidoCriterio;
	@FXML
	TableView<ObsPoi> tablaMostrada;
	@FXML
	TableView<ObsBuscador> tablaBuscadores;
	@FXML
	TableColumn<ObsPoi,String> nombre; 
	@FXML
	TableColumn<ObsPoi, String> calle;
	@FXML
	TableColumn<ObsPoi, Integer> altura;
	@FXML
	TableColumn<ObsPoi, Double> distancia;
	@FXML
	TableColumn<ObsBuscador, String> tipo;
	@FXML
	TableColumn<ObsBuscador, String> content;
	
	Stage nuevaStage;
	FXMLLoader loader;
	AnchorPane rootLayout;
	HashMap<String, Boolean> palabraOK = new HashMap<>();
	ArrayList<BancoExterna> bancos;
	ArrayList<Poi> resultados;

	//IDCAMPO - SI ESTA OK O NO
	public void buscarSceneRender(){
		try{
			//Carga archivo FXML  q tiene la interfaz
			loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("buscarScene.fxml"));
			loader.setController(this);
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
	private void buscar(MouseEvent event){
		ArrayList<Poi> resultados;
		if(filtro !=null){
			resultados = filtro.aplicarBuscador(Main.pois);
		}else{
			resultados = Main.pois;
		}	
		ObservableList<ObsPoi> resultadosTabla =  FXCollections.observableArrayList();
		for (Poi poi : resultados) {
			resultadosTabla.add(new ObsPoi(poi.getNombre(), poi.getDireccion().getCallePrincipal(), poi.getDireccion().getAltura(),Calculos.calcularDistanciaA(poi, Main.tablero),poi.getIddb()));
		}
		tablaMostrada.setItems(resultadosTabla);
		
//		tablaMostrada.getItems().clear();
//		time_start = System.currentTimeMillis();
//		String buscado;
//		ArrayList<Poi> resultados = BuscadorPoi.buscar(buscado, Main.pois);
//		ObservableList<ObsPoi> resultadosTabla =  FXCollections.observableArrayList();
//		for (Poi poi : resultados) {
//			resultadosTabla.add(new ObsPoi(poi.getNombre(), poi.getDireccion().getCallePrincipal(), poi.getDireccion().getAltura(),Calculos.calcularDistanciaA(poi, Main.tablero),poi.getIddb()));
//		}
//		//Negrada
//		bancos = null;
//		try{
//			bancos = new ArrayList<>(new jsonBancos().FiltrarBancos("http://private-96b476-ddsutn.apiary-mock.com","banks","bancos",buscado));
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
//		// mas Negrada
//		if(bancos!=null){
//			for (Object bank : bancos){
//				String[] temp = bank.toString().split("=");
//				String[] temp2 = temp[1].split(",");
//				String[] temp3 = temp[4].split(",");
//				resultadosTabla.add(new ObsPoi(temp2[0],temp3[0],0,0,-1));
//			}	
//		}
//		time_end = System.currentTimeMillis();
//		time = (time_end - time_start)/1e6;
//		
//		if(time > SEGUNDOS_PARAMETRIZADOS/1e3)
//		{
//			frameworkEmails.Email.enviar("testingdds@fighthawk.com", "testuser", "jvillalba@fighthawk.com", "BUSQUEDA DEMORADA", "PROBLEMAS DE PERFOMANCE AL BUSCAR LA PALABRA: " + buscado);
//		}
//		
//		tablaMostrada.setItems(resultadosTabla);
//		resultados_final = resultadosTabla.size();
//		if(!buscado.isEmpty()){
//			Generales.registrarBusqueda(Main.tablero, buscado, resultados_final , time);
//		}	
	}
	
	@FXML
	public void agregarCriterio(MouseEvent evento){
		tablaBuscadores.getItems().add(this.chooseBuscador(criterio.getValue(), contenidoCriterio.getText()));
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		calle.setCellValueFactory(new PropertyValueFactory<>("calle"));
		altura.setCellValueFactory(new PropertyValueFactory<>("altura"));
		distancia.setCellValueFactory(new PropertyValueFactory<>("distancia"));
		tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		content.setCellValueFactory(new PropertyValueFactory<>("content"));
		resultados = Main.pois;
		if(resultados == null){
			resultados = new ArrayList<>();
		}
		ObservableList<ObsPoi> resultadosTabla =  FXCollections.observableArrayList();
		for (Poi poi : resultados) {
			resultadosTabla.add(new ObsPoi(poi.getNombre(), poi.getDireccion().getCallePrincipal(), poi.getDireccion().getAltura(),Calculos.calcularDistanciaA(poi, Main.tablero),poi.getIddb()));
		}
		tablaMostrada.setItems(resultadosTabla);
		criterio.getItems().addAll("Nombre", "KeyWord");
	}
	
	@FXML
	public void reiniciarCriterios(MouseEvent evento){
		filtro = null;
		tablaBuscadores.getItems().clear();
		this.buscar(null);
	}
	
	private ObsBuscador chooseBuscador(String nombre,String contenido){
		switch (nombre) {
		case "Nombre":
			filtro = new NameSeach(filtro, contenido);
			return new ObsBuscador(nombre, contenido);
		case "KeyWord":
			filtro = new KeySearch(filtro, contenido);
			return new ObsBuscador(nombre, contenido);
		default:
			return null;
		}
	}
}