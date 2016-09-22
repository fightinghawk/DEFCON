package tpdds.interfaz;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.hibernate.Session;

import HIBERNATE.HibernateSessionFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tpdds.database.Generales;
import tpdds.pois.DiaPoi;
import tpdds.pois.Poi;

public class InsertarDia implements Initializable {

	@FXML
	Button Siguiente;
	@FXML
	Button Anterior;
	@FXML
	Button fin;
	ArrayList<DiaPoi> diasCargados;
	ArrayList<String> diasPos;
	FXMLLoader loader;
	AnchorPane rootLayout;
	HashMap<String, Boolean> palabraOK = new HashMap<>();
	int nrodia;
	Stage second;
	Poi poiNuevo;
	public InsertarDia(Poi nuevo){
		this.initDias();
		this.poiNuevo = nuevo;
	}
	
	
	
	public void initDias(){
			diasPos = new ArrayList<>();
			diasPos.add(0, "Lunes");
			diasPos.add(1, "Martes");
			diasPos.add(2, "Miercoles");
			diasPos.add(3, "Jueves");
			diasPos.add(4, "Viernes");
			diasPos.add(5, "Sabados");
			diasPos.add(6, "Domingos");
	}
	
	
	public void insertDiaRender(Stage escenario,int nrodiap){
		try{
			second = escenario;
			nrodia = nrodiap;
			String dia = diasPos.get(nrodia);
			loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("insertSceneDiaz.fxml"));
			loader.setController(this);
			rootLayout = loader.load();
			//Seteo el titulo, osea el dia
			Label titulo = (Label) (rootLayout.lookup("#Dia"));
			titulo.setText(dia);
			//Veo q botones poner
			Scene scene = new Scene(rootLayout);
			escenario.setScene(scene);
			escenario.show();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@FXML
	public void sigBoton(){
		insertDiaRender(second, nrodia+1);
	}
	
	@FXML
	public void antBoton(){
		insertDiaRender(second, nrodia-1);
	}
	
	@FXML
	public void finBoton(){
		try{
			Session aGuardar = HibernateSessionFactory.getSession();
			
		Main.pois.add(poiNuevo);
		aGuardar.close();
		}
		catch(Exception ex){
			
		}
		second.close();
	}
	
	@FXML
	public void abre(){
		
	}
	
	@FXML
	public void ponehora(KeyEvent evento) {
		String key = evento.getCharacter();
		try{
			Integer.parseInt(key);
		}catch(Exception ex){
			evento.consume();
			return;
		}
		String texto = ((TextField) evento.getSource()).getText().concat(evento.getCharacter());
		int valor = Integer.parseInt(texto);
		if (valor > 2399) {
			evento.consume();
		}
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(nrodia == 0){
			Anterior.setVisible(false);
			Siguiente.setVisible(true);
			fin.setVisible(false);
		}else if(nrodia==6){
			Siguiente.setVisible(false);
			Anterior.setVisible(true);
			fin.setVisible(true);
		}else{
			Siguiente.setVisible(true);
			Anterior.setVisible(true);
			fin.setVisible(false);
		}
		
	}
	
	
	
	
	
	
	
	
}
