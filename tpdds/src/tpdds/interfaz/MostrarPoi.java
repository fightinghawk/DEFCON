package tpdds.interfaz;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tpdds.pois.Poi;
import tpdds.pois.componentes.Servicios;

public class MostrarPoi implements Initializable{

	@FXML
	Text nombre;
	@FXML
	Text tipo;
	@FXML
	Text callePrincipal;
	@FXML
	Text rubro;
	@FXML
	Text altura;
	@FXML
	Text comuna;
	@FXML
	Text numerodoParada;
	@FXML
	Text calleDerecho;
	@FXML
	Text calleIzquierda;
	@FXML
	TextArea servicios;
	private Stage nuevaStage;
	private FXMLLoader loader;
	private AnchorPane rootLayout;
	private Poi poi;
	
	public void mostraPoiRender(){
		try{
			nuevaStage = new Stage();
			nuevaStage.initModality(Modality.WINDOW_MODAL);
			nuevaStage.initOwner(Main.primaryStage);
			nuevaStage.setResizable(false);
			nuevaStage.setTitle("Informar pois");
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MostrarPoi.fxml"));
			loader.setController(this);
			rootLayout = loader.load();
			Scene scene = new Scene(rootLayout);
			nuevaStage.setScene(scene);
			nuevaStage.show();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public MostrarPoi(Poi poi) {
		this.poi = poi;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.nombre.setText(poi.getNombre());
		this.tipo.setText(poi.getTipo());
		this.calleIzquierda.setText(poi.getDireccion().getCalleLateralIzq());
		this.calleDerecho.setText(poi.getDireccion().getCalleLateralDer());
		this.altura.setText(poi.getDireccion().getAltura()+"");
		this.comuna.setText(poi.getDireccion().getBarrio());
		this.numerodoParada.setText(poi.getParada()+"");
		this.rubro.setText(poi.getRubro());
		this.callePrincipal.setText(poi.getDireccion().getCallePrincipal());	
		String strServs = "";
//		if(!poi.getServicios().isEmpty()){
//			for (Servicios serv : poi.getServicios()) {
//				strServs.concat(" || "+serv.getNombreServicio());
//			}
//		}
//		this.servicios.setText(strServs);
	}
	
	@FXML
	private void cerrar(){
		this.nuevaStage.close();
	}
}
