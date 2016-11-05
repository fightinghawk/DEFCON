package tpdds.interfaz;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tpdds.hibernate.HibernateSessionFactory;
import tpdds.interfaz.componentes.Busqueda;
import tpdds.interfaz.componentes.ObsBuscador;
import tpdds.interfaz.componentes.ObsPoi;
import tpdds.interfaz.componentes.ObsResultadoTotal;
import tpdds.pois.Poi;

public class ReporteTotal extends Escena implements Initializable {

	@FXML
	TableView<ObsPoi> tablaMostradaPoi;
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
	@FXML
	TableView<ObsResultadoTotal> resultadosTabla;
	@FXML
	TableColumn<ObsResultadoTotal, String> fechaCol;
	@FXML
	TableColumn<ObsResultadoTotal, String> userCol;
	@FXML
	TableColumn<ObsResultadoTotal, String> parametrosCol;
	@FXML
	TableColumn<ObsResultadoTotal, String> poisCol;
	@FXML
	TextField userBusc;
	@FXML
	TextField diaBusc;
	@FXML
	TextField mesBusc;
	@FXML
	TextField anioBusc;
	@FXML
	TextField diaBusc2;
	@FXML
	TextField mesBusc2;
	@FXML
	TextField anioBusc2;	
	
	@FXML
	public void buscar(MouseEvent evento){
		resultadosTabla.getItems().clear();
        ObservableList<ObsResultadoTotal> resultados = FXCollections.observableArrayList();
        ArrayList<Busqueda> busquedasRealizadas = (ArrayList<Busqueda>) this.elegirBusquedaAndDoIt();
        for (Busqueda busqueda : busquedasRealizadas) {
			resultados.add(new ObsResultadoTotal(busqueda.getFechaRealizada().toString(),busqueda.getUsuario(),busqueda.criteriosToShow(),busqueda.getCantResultados(),busqueda));
		}
		resultadosTabla.setItems(resultados);
	}
	
	
	private Collection<Busqueda> doQuery() {
		Session session = HibernateSessionFactory.getSession();
		Query pedido = session.createQuery("select p from Busqueda p");
		@SuppressWarnings("unchecked")
		List<Busqueda> resultados = pedido.list();
		session.close();
		return resultados;
	}
	
	private Collection<Busqueda> doQuery(String nombre){
		Session session = HibernateSessionFactory.getSession();
		Query pedido = session.createQuery("select p from Busqueda p where p.usuario = :nombre").setParameter("nombre", nombre);
		@SuppressWarnings("unchecked")
		List<Busqueda> resultados = pedido.list();
		session.close();
		return resultados;
	}
	
	private Collection<Busqueda> doQuery(String nombre, Date fecha, String comparacion){
		Session session = HibernateSessionFactory.getSession();
		Query pedido;
		if(comparacion.equals(">"))
			 pedido = session.createQuery("SELECT p FROM Busqueda p WHERE p.usuario = :nombre AND p.fechaRealizada > :fecha");
		else
			pedido = session.createQuery("SELECT p FROM Busqueda p WHERE p.usuario = :nombre AND p.fechaRealizada < :fecha");	
		pedido.setParameter("nombre", nombre);
		pedido.setParameter("fecha", fecha);
		@SuppressWarnings("unchecked")
		List<Busqueda> resultados = pedido.list();
		session.close();
		return resultados;
	}
	
	private Collection<Busqueda> doQuery(String nombre, Date fecha, Date fecha2){
		Session session = HibernateSessionFactory.getSession();
		Query pedido = session.createQuery("SELECT p FROM Busqueda p WHERE p.usuario = :nombre AND p.fechaRealizada > :fecha AND p.fechaRealizada < :fecha2");
		pedido.setParameter("nombre", nombre);
		pedido.setParameter("fecha", fecha);
		pedido.setParameter("fecha2", fecha2);
		@SuppressWarnings("unchecked")
		List<Busqueda> resultados = pedido.list();
		session.close();
		return resultados;
	}
	
	private Collection<Busqueda> elegirBusquedaAndDoIt(){
		if(userBusc.getText().isEmpty()){
			//Este if muestra todos los resultados de todas las fechas de todos los usuarios
			return this.doQuery();
		}
		else if(fechaCompletaYValida(diaBusc,mesBusc,anioBusc) && fechaCompletaYValida(diaBusc2, mesBusc2, anioBusc2)){
			Date fecha1 = this.crearFecha(Integer.parseInt(diaBusc.getText()),
					Integer.parseInt(mesBusc.getText()), Integer.parseInt(anioBusc.getText()));
			Date fecha2 = this.crearFecha(Integer.parseInt(diaBusc2.getText()),
					Integer.parseInt(mesBusc2.getText()), Integer.parseInt(anioBusc2.getText()));
			return this.doQuery(userBusc.getText(),fecha1, fecha2);
		}else if(fechaCompletaYValida(diaBusc, mesBusc, anioBusc)){
			Date fecha1 = this.crearFecha(Integer.parseInt(diaBusc.getText()),
					Integer.parseInt(mesBusc.getText()), Integer.parseInt(anioBusc.getText()));
			return this.doQuery(userBusc.getText(),fecha1,">");
		}else if(fechaCompletaYValida(diaBusc2, mesBusc2, anioBusc2)){
			Date fecha2 = this.crearFecha(Integer.parseInt(diaBusc2.getText()),
					Integer.parseInt(mesBusc2.getText()), Integer.parseInt(anioBusc2.getText()));
			return this.doQuery(userBusc.getText(),fecha2,"<");
		}
		else{
			return this.doQuery(userBusc.getText());
		}
	}
	
	private boolean fechaCompletaYValida(TextField diaBusc, TextField mesBusc , TextField anioBusc){
		try{
			if(diaBusc.getText().isEmpty() || !(Integer.parseInt(diaBusc.getText())<31)){
				return false;
			}
			if(mesBusc.getText().isEmpty() || !(Integer.parseInt(mesBusc.getText())<12)){
				return false;
			}
			if(anioBusc.getText().isEmpty() || !(anioBusc.getText().length() == 4)){
				return false;
			}
			//Pruebo convertirlo a int
			Integer.parseInt(anioBusc.getText());
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	private Date crearFecha(int dia, int mes, int anio){
		Calendar fechaMake = Calendar.getInstance();
		fechaMake.clear();
		fechaMake.set(Calendar.DAY_OF_MONTH, dia);
		fechaMake.set(Calendar.MONTH, mes-1);
		fechaMake.set(Calendar.YEAR, anio);
		return new Date(fechaMake.getTime().getTime());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		calle.setCellValueFactory(new PropertyValueFactory<>("calle"));
		altura.setCellValueFactory(new PropertyValueFactory<>("altura"));
		distancia.setCellValueFactory(new PropertyValueFactory<>("distancia"));
		fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		userCol.setCellValueFactory(new PropertyValueFactory<>("usuario"));
		parametrosCol.setCellValueFactory(new PropertyValueFactory<>("parametros"));
		poisCol.setCellValueFactory(new PropertyValueFactory<>("totales"));
		tablaMostradaPoi.setDisable(!false);
		tablaMostradaPoi.setVisible(!true);
	}
	
	@FXML
	private void tablaPrecionadaPoi(MouseEvent evento){
		if(evento.getClickCount()>0){
			ObservableList<ObsPoi> poisAMostrar = FXCollections.observableArrayList();
			Busqueda busqueda = resultadosTabla.getSelectionModel().getSelectedItem().getBuscado();
			if(busqueda == null){
				return;
			}
			for (Integer poiid : busqueda.getPois_encontrados()) {
				Poi temp = buscarPoi(poiid, Main.pois);
				poisAMostrar.add(new ObsPoi(temp.getNombre(), temp.getDireccion().getCallePrincipal(), temp.getDireccion().getAltura(), 0, temp.getIddb()));
				tablaMostradaPoi.setDisable(false);
				tablaMostradaPoi.setVisible(true);
				tablaMostradaPoi.setItems(poisAMostrar);
			}
		}	
	}
	
	@FXML
	public void sacarTabla(MouseEvent evento){
		tablaMostradaPoi.setDisable(!false);
		tablaMostradaPoi.setVisible(!true);
		tablaMostradaPoi.getItems().clear();
	}
	
	@FXML
	public void tablaPrecionadaPoiMostrar(MouseEvent evento){
		try{
			Poi poi = buscarPoi(tablaMostradaPoi.getSelectionModel().getSelectedItem().getId(), Main.pois);
			new MostrarPoi(poi).mostraPoiRender();
		}catch(Exception ex){
		}
	}
	
	
	private Poi buscarPoi(int id, Collection<Poi> pois){
		for (Poi poi : pois) {
			if(poi.getIddb()==id)
				return poi;
		}
		return null;
	}

}
