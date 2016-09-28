package tpdds.interfaz;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tpdds.database.Generales;
import tpdds.hibernate.HibernateSessionFactory;
import tpdds.interfaz.componentes.Busqueda;
import tpdds.interfaz.componentes.Historial;
import tpdds.interfaz.componentes.ObsBuscador;
import tpdds.interfaz.componentes.ObsResultadoTotal;
import tpdds.interfaz.componentes.reporteFecha;
import tpdds.pois.Poi;

public class ReporteTotal implements Initializable {

	
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
	
	Stage nuevaStage;
	FXMLLoader loader;
	AnchorPane rootLayout;
	
	public void ReportePorTerminalRender(){
		try{
			nuevaStage = new Stage();
			nuevaStage.initModality(Modality.WINDOW_MODAL);
			nuevaStage.initOwner(Main.primaryStage);
			nuevaStage.setResizable(false);
			nuevaStage.setTitle("Reporte por TERMINALES");
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("reporteTotal.fxml"));
			loader.setController(this);
			rootLayout = loader.load();
			Scene scene = new Scene(rootLayout);
			nuevaStage.setScene(scene);
			nuevaStage.show();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@FXML
	public void buscar(MouseEvent evento){
		resultadosTabla.getItems().clear();
        ObservableList<ObsResultadoTotal> resultados = FXCollections.observableArrayList();
        //ArrayList<Historial> historiales = Generales.obtenerHistorial(userBusc.getText());
		ArrayList<Busqueda> busquedasRealizadas = (ArrayList<Busqueda>) this.elegirBusquedaAndDoIt();
        for (Busqueda busqueda : busquedasRealizadas) {
			resultados.add(new ObsResultadoTotal(busqueda.getFechaRealizada().toString(),busqueda.getUsuario(),busqueda.criteriosToShow(),busqueda.getCantResultados()));
		}
		resultadosTabla.setItems(resultados);
	}
	
	
	private Collection<Busqueda> doQuery() {
		Session session = HibernateSessionFactory.getSession();
		Query pedido = session.createQuery("select p from Busqueda p");
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
	
	private Collection<Busqueda> doQuery(String nombre, Date fecha){
		Session session = HibernateSessionFactory.getSession();
		Query pedido = session.createQuery("SELECT p FROM Busqueda p WHERE p.usuario = :nombre AND p.fechaRealizada = :fecha");
		pedido.setParameter("nombre", nombre);
		pedido.setParameter("fecha", fecha);
		@SuppressWarnings("unchecked")
		List<Busqueda> resultados = pedido.list();
		session.close();
		return resultados;
	}
	
	private Collection<Busqueda> elegirBusquedaAndDoIt(){
		if(userBusc.getText().isEmpty()){
			return this.doQuery();
		}
		else if(fechaCompletaYValida()){
			return this.doQuery(userBusc.getText(), this.crearFecha(Integer.parseInt(diaBusc.getText()),
					Integer.parseInt(mesBusc.getText()), Integer.parseInt(anioBusc.getText())));
		}else{
			return this.doQuery(userBusc.getText());
		}
	}
	
	private boolean fechaCompletaYValida(){
		try{
			if(diaBusc.getText().isEmpty() || !(Integer.parseInt(diaBusc.getText())<31)){
				return false;
			}
			if(mesBusc.getText().isEmpty() || !(Integer.parseInt(mesBusc.getText())<12)){
				return false;
			}
			if(anioBusc.getText().isEmpty() || !(anioBusc.getText().length() == 4)){
				//Pruebo convertirlo a int
				Integer.parseInt(anioBusc.getText());
				return false;
			}
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
		fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		userCol.setCellValueFactory(new PropertyValueFactory<>("usuario"));
		parametrosCol.setCellValueFactory(new PropertyValueFactory<>("parametros"));
		poisCol.setCellValueFactory(new PropertyValueFactory<>("totales"));
	}

}
