package tpdds.interfaz;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tpdds.pois.DiaPoi;

public class InsertarDia {

	
	static ArrayList<DiaPoi> diasCargados = new ArrayList<>();
	static ArrayList<String> diasPos = new ArrayList<>();
	static FXMLLoader loader;
	static AnchorPane rootLayout;
	static HashMap<String, Boolean> palabraOK = new HashMap<>();
	static int nrodia;
	static Stage second;
	public static void initDias(){
			diasPos.add(0, "Lunes");
			diasPos.add(1, "Martes");
			diasPos.add(2, "Miercoles");
			diasPos.add(3, "Jueves");
			diasPos.add(4, "Viernes");
			diasPos.add(5, "Sabados");
			diasPos.add(6, "Domingos");
	}
	
	
	public static void insertDiaRender(Stage escenario,int nrodiap){
		try{
			second = escenario;
			nrodia = nrodiap;
			String dia = diasPos.get(nrodia);
			loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("insertSceneDiaz.fxml"));
			rootLayout = loader.load();
			//Seteo el titulo, osea el dia
			Label titulo = (Label) (rootLayout.lookup("#Dia"));
			titulo.setText(dia);
			//Veo q botones poner
			if(nrodia == 0){
				rootLayout.lookup("#Anterior").setVisible(false);
				rootLayout.lookup("#Siguiente").setVisible(true);
				rootLayout.lookup("#fin").setVisible(false);
			}else if(nrodia==6){
				rootLayout.lookup("#Siguiente").setVisible(false);
				rootLayout.lookup("#Anterior").setVisible(true);
				rootLayout.lookup("#fin").setVisible(true);
			}else{
				rootLayout.lookup("#Siguiente").setVisible(true);
				rootLayout.lookup("#Anterior").setVisible(true);
				rootLayout.lookup("#fin").setVisible(false);
			}
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
	
	
	
	
	
	
	
	
}
