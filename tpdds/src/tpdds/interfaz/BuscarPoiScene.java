package tpdds.interfaz;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import org.hibernate.Session;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tpdds.Usuarios.User;
import tpdds.buscadores.Buscador;
import tpdds.buscadores.KeySearch;
import tpdds.buscadores.NameSeach;
import tpdds.buscadores.TipoSearch;
import tpdds.frameworkEmails.Email;
import tpdds.hibernate.HibernateSessionFactory;
import tpdds.interfaz.componentes.Busqueda;
import tpdds.interfaz.componentes.Criterio;
import tpdds.interfaz.componentes.ObsBuscador;
import tpdds.interfaz.componentes.ObsPoi;
import tpdds.pois.Poi;
import tpdds.usoGlobal.Calculos;

public class BuscarPoiScene extends Escena{
	
	private static final double SEGUNDOS_PARAMETRIZADOS = 1;
	long time_start, time_end;
	double tiempoTotal;
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

	private ArrayList<Poi> resultados;
	private Collection<Criterio> criterios;
	private User usuario;

	public BuscarPoiScene(User usuario) {
		criterios = new ArrayList<>();
		this.usuario = usuario;
	}
	
	@FXML
	private void tablaPrecionada(MouseEvent evento){
		if(evento.getClickCount()==2 && tablaMostrada.getSelectionModel().getSelectedItem()!=null){
			ObsPoi pepe = tablaMostrada.getSelectionModel().getSelectedItem();
			//Me canse de pensar nombres
			Poi pepee = null;
			for (Poi poi : Main.pois) {
				if(poi.getIddb()==pepe.getId()){
				pepee = poi;	
				}
			}
			new MostrarPoi(pepee).mostraPoiRender();
		}
	}
	
	@FXML
	private void buscar(MouseEvent event){
		time_start = System.currentTimeMillis();
		ArrayList<Poi> resultados;
		if(filtro !=null){
			resultados = filtro.aplicarBuscador(Main.pois);
		}else{
			resultados = Main.pois;
		}
		time_end = System.currentTimeMillis();
		tiempoTotal = (time_end - time_start)/1e6;
		ObservableList<ObsPoi> resultadosTabla =  FXCollections.observableArrayList();
		for (Poi poi : resultados) {
			resultadosTabla.add(new ObsPoi(poi.getNombre(), poi.getDireccion().getCallePrincipal(), poi.getDireccion().getAltura(),Calculos.calcularDistanciaA(poi, Main.tablero),poi.getIddb()));
		}
		tablaMostrada.setItems(resultadosTabla);
		if(tiempoTotal > SEGUNDOS_PARAMETRIZADOS/1e3)
		{
			Email.enviar("testingdds@fighthawk.com", "testuser", "jvillalba@fighthawk.com", "BUSQUEDA DEMORADA", "PROBLEMAS DE PERFOMANCE");
		}
		if(event!=null)
			this.guardarBusqueda(new Busqueda(resultados.size(), tiempoTotal, 1, usuario.getUsuarioid() , criterios, resultados));
	}
	
	@FXML
	public void agregarCriterio(MouseEvent evento){
		tablaBuscadores.getItems().add(this.chooseBuscador(criterio.getValue(), contenidoCriterio.getText()));
		criterios.add(new Criterio(criterio.getValue(), contenidoCriterio.getText()));
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
		criterio.getItems().addAll("Nombre", "KeyWord", "Tipo");
	}
	
	@FXML
	public void reiniciarCriterios(MouseEvent evento){
		filtro = null;
		tablaBuscadores.getItems().clear();
		criterios.clear();
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
		case "Tipo":
			filtro = new TipoSearch(filtro, contenido);
			return new ObsBuscador(nombre, contenido);
		default:
			return null;
		}
	}
	
	private void guardarBusqueda(Busqueda busc){
		Session ses = HibernateSessionFactory.getSession();
		try{
			ses.beginTransaction();
			ses.save(busc);
			ses.getTransaction().commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			ses.close();
		}
	}
}