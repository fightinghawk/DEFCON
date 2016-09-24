package tpdds.interfaz;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tpdds.database.Generales;
import tpdds.pois.Bancos;
import tpdds.pois.CGP;
import tpdds.pois.LocalesComerciales;
import tpdds.pois.ParadaColectivo;
import tpdds.pois.Poi;
import tpdds.pois.componentes.DiaPoi;
import tpdds.pois.componentes.Servicios;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Location;
public class InsertSceneGnr implements Initializable{
	
	@FXML
	TextField nombre, calle, izquierda, barrio, altura, derecha,
	op1, op2, latitud, Longitud, nombreDia, horaUnoInicio,
	horaUnoFin, horaDosInicio, horaDosFin, nombreServ;
	@FXML
	TextArea keyWord, errorDia, descServ;
	@FXML
	ComboBox<String> Tipo, demasInfo;
	@FXML
	Text top1,top2;
	@FXML
	Pane infoDias, infoServs;
		
	private Poi nuevo;
	private FXMLLoader loader;
	private AnchorPane rootLayout;
	private HashMap<String, Boolean> palabraOK = new HashMap<>();
	private Stage nuevaStage;
	private Collection<Servicios> servicios;
	private Collection<DiaPoi> diasAbiertos;
	
	//IDCAMPO - SI ESTA OK O NO
	public void insertSceneRender(){
		try{
			nuevaStage = new Stage();
			nuevaStage.initModality(Modality.WINDOW_MODAL);
			nuevaStage.initOwner(Main.primaryStage);
			nuevaStage.setResizable(false);
			nuevaStage.setTitle("Insertar POI");
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("insertSceneGnr.fxml"));
			loader.setController(this);
			rootLayout = loader.load();
			Scene scene = new Scene(rootLayout);
			nuevaStage.setScene(scene);
			nuevaStage.show();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public InsertSceneGnr() {
		diasAbiertos = new ArrayList<>();
		servicios = new ArrayList<>();
	}
	
	@FXML
	private void seleccionTipo(ActionEvent evento){
		if(Tipo.getSelectionModel().getSelectedItem()==null)
			return;
		switch (Tipo.getSelectionModel().getSelectedItem()) {
		case "Colectivo":
			mostrarOpcional(top1, op1, true);
			top1.setText("Nro Parada:");
			break;
		case "L.Comerciales":
			mostrarOpcional(top1, op1, true);
			top1.setText("Rubro");
			break;
		default:
			mostrarOpcionales(false);
			break;
		}
	}
	
	@FXML
	private void seleccionInfo(ActionEvent evento){
		if(demasInfo.getSelectionModel().getSelectedItem()==null)
			return;
		switch (demasInfo.getSelectionModel().getSelectedItem()) {
		case "Dias":
			mostrarPane(infoDias, true);
			mostrarPane(infoServs, false);
			break;
		case "Servicios":
			mostrarPane(infoDias, false);
			mostrarPane(infoServs, true);
			break;

		default:
			break;
		} 
	}
	
	@FXML
	private void guardarServicio(MouseEvent evento){
		if(!nombreServ.getText().isEmpty()&&!descServ.getText().isEmpty()){
			servicios.add(new Servicios(nombreServ.getText(), descServ.getText()));
		}
	}
	
	@FXML
	private void insertarPoi(MouseEvent evento){
	Poi poi;
	String tipoPoi;
		tipoPoi = Tipo.getSelectionModel().getSelectedItem();
		Direccion direccion  = new Direccion(calle.getText(), Integer.parseInt(altura.getText()), izquierda.getText(), derecha.getText(), barrio.getText());
		Location geo = new Location(Double.parseDouble(latitud.getText()), Double.parseDouble(Longitud.getText()));
		switch (tipoPoi.toLowerCase()) {
		case "cgp":
			nuevo = new CGP(poi);
			break;
		case "colectivo":
			nuevo = new ParadaColectivo(poi,Integer.parseInt(op1.getText()));
			break;
		case "bancos":
			nuevo = new Bancos(poi);
			break;
		case "l.comerciales":
			nuevo = new LocalesComerciales(poi,op1.getText());
			break;
		
		}
		Generales.agregarPoi(nuevo);
	}
	
	@FXML
	private void guardarDia(MouseEvent evento){
		try{
			boolean borrarCampos = false;
			int dia = this.calcularDia(nombreDia.getText());
			if(dia==-1){
				this.mostrarError("Dia invalido", errorDia);
			}
			if(!horaUnoFin.getText().isEmpty() && !horaUnoInicio.getText().isEmpty()){
				Integer horaI = Integer.parseInt(horaUnoInicio.getText().substring(0, 2));
				Integer minI = Integer.parseInt(horaUnoInicio.getText().substring(2, 4));
	
				Integer horaF = Integer.parseInt(horaUnoFin.getText().substring(0, 2));
				Integer minF = Integer.parseInt(horaUnoFin.getText().substring(2, 4));
				DiaPoi diaAgregar = new DiaPoi(horaI, horaF, minI, minF, dia, null);
				diasAbiertos.add(diaAgregar);
				errorDia.setText("Dia agregado");
				borrarCampos = true;
			}
			
			if(!horaDosFin.getText().isEmpty() && !horaDosInicio.getText().isEmpty()){
				Integer horaI = Integer.parseInt(horaDosInicio.getText().substring(0, 2));
				Integer minI = Integer.parseInt(horaDosInicio.getText().substring(2, 4));
	
				Integer horaF = Integer.parseInt(horaDosFin.getText().substring(0, 2));
				Integer minF = Integer.parseInt(horaDosFin.getText().substring(2, 4));
				DiaPoi diaAgregar = new DiaPoi(horaI, horaF, minI, minF, dia, null);
				diasAbiertos.add(diaAgregar);
				errorDia.appendText("Dia agregado");
				borrarCampos = true;
			}
			if(borrarCampos){
				horaDosFin.clear();horaDosInicio.clear();horaUnoFin.clear();horaUnoInicio.clear();
				nombreDia.clear();
			}
			
		}catch(Exception ex){
			this.mostrarError("Hubo un error", errorDia);
			ex.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Tipo.getItems().addAll("Colectivo","Bancos","CGP","L.Comerciales");
		demasInfo.getItems().addAll("Dias","Servicios");
		mostrarOpcionales(false);
		mostrarPane(infoDias, false);
		mostrarPane(infoServs, false);
	}
	
	private void mostrarPane(Pane panel, boolean valor){
		for (Node node : panel.getChildren()) {
			node.setVisible(valor);
			node.setDisable(!valor);
		}
		panel.setVisible(valor);
		panel.setDisable(!valor);
	}
	
	private void mostrarOpcionales(boolean valor){
		op1.setVisible(valor);
		op2.setVisible(valor);
		top1.setVisible(valor);
		top2.setVisible(valor);
		op1.setDisable(!valor);
		op2.setDisable(!valor);
		top1.setDisable(!valor);
		top2.setDisable(!valor);
	}
	
	private void mostrarOpcional(Text top,TextField op, boolean valor){
		op.setVisible(valor);
		op.setDisable(!valor);
		top.setVisible(valor);
		top.setDisable(!valor);
	}
	
	private int calcularDia(String dia){
		if(dia.equalsIgnoreCase("lunes"))
			return 2;
		if(dia.equalsIgnoreCase("martes"))
			return 3;
		if(dia.equalsIgnoreCase("miercoles"))
			return 4;
		if(dia.equalsIgnoreCase("jueves"))
			return 5;
		if(dia.equalsIgnoreCase("viernes"))
			return 6;
		if(dia.equalsIgnoreCase("sabado"))
			return 7;
		if(dia.equalsIgnoreCase("domingo"))
			return 1;
		return -1;
	}
	
	private void mostrarError(String error, TextArea donde){
		donde.setText(error);
	}
}
