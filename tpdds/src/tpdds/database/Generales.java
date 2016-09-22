package tpdds.database;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import HIBERNATE.HibernateSessionFactory;
import tpdds.dispositivo.Dispositivo;
import tpdds.factory.POIFactory;
import tpdds.interfaz.ObsResultadoFecha;
import tpdds.pois.Bancos;
import tpdds.pois.CGP;
import tpdds.pois.DiaPoi;
import tpdds.pois.LocalesComerciales;
import tpdds.pois.ParadaColectivo;
import tpdds.pois.Poi;
import tpdds.pois.keyWords;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Location;


public class Generales{

	public static Connection conexion;
	
	public static void initDatabase() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		conexion = DriverManager.getConnection("jdbc:mysql://mysql3.gear.host:3306/dds2016", "dds2016", "dds2016.");
	}	
	
	public static  ArrayList<Poi> cargarPois(){
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		String sql = "SELECT * FROM POIS";
		SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Poi.class);
        List<Poi> POIS = query.list();
        ArrayList<Poi> poisRev = new  ArrayList<Poi>();
        for(Poi poi: POIS)
        {
			switch (poi.getTipo().toLowerCase()) {
			case "cgp":
				CGP nuevocgp =new CGP(poi.getIddb(),poi.getNombre(),poi.getTipo(), poi.getDireccion(), poi.getGeoloc(),poi.getPalabrasClaves());
				poisRev.add(nuevocgp);
				break;
			case "colectivo":
				ParadaColectivo nuevaparada = new ParadaColectivo(poi.getIddb(),poi.getNombre(),poi.getTipo(), poi.getDireccion(), poi.getGeoloc(),poi.getPalabrasClaves());
				poisRev.add(nuevaparada);
				break;
			case "bancos":
				Bancos nuevobancos = new Bancos(poi.getIddb(),poi.getNombre(),poi.getTipo(), poi.getDireccion(), poi.getGeoloc(),poi.getPalabrasClaves());
				poisRev.add(nuevobancos);
				break;
			case "comercios":
				LocalesComerciales nuevolocal = new LocalesComerciales(poi.getIddb(),poi.getNombre(),poi.getTipo(), poi.getDireccion(), poi.getGeoloc(),poi.getPalabrasClaves());
				poisRev.add(nuevolocal);
				break;
			}
        }
        session.getTransaction().commit();
        session.close();
		return poisRev;
	}
	
	public static void borrarPoi(Poi poi){
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		session.delete(poi);
		session.delete(poi.getDireccion());
		session.delete(poi.getGeoloc());
        session.getTransaction().commit();
        session.close();
	}
	
	public static void registrarBusqueda(Dispositivo tablero,String busqueda,Integer resultados,double time) throws SQLException, ClassNotFoundException{
		//Fecha Actual Java
	    Calendar actual = new GregorianCalendar();

		PreparedStatement search = conexion.prepareStatement("INSERT INTO busquedas (id_terminal,dia,mes,anio,frase,resultados,time)"
		+ " values (?,?,?,?,?,?,?)");
		
		search.setInt(1, tablero.getId());
		search.setInt(2, actual.get(Calendar.DATE));
		search.setInt(3, actual.get(Calendar.MONTH)+1);
		search.setInt(4, actual.get(Calendar.YEAR));
		search.setString(5, busqueda);
		search.setInt(6, resultados);
		search.setDouble(7, time);
		search.executeUpdate();
		
	}
	
	public static ResultSet obtenerReporteFecha(Integer dia,Integer mes,Integer anio) throws SQLException, ClassNotFoundException{
		ResultSet listaReporteFecha;
		PreparedStatement searchr;
		if(anio != 0 && mes != 0 && dia !=0)
		{
		 searchr = conexion.prepareStatement("SELECT dia,mes,anio, sum(resultados) AS Totales FROM busquedas WHERE (dia=? AND mes=? AND anio=?) GROUP BY dia,mes,anio");
		searchr.setInt(1, dia);
		searchr.setInt(2, mes);
		searchr.setInt(3, anio);
		}
		else
		{
			if(anio != 0 && mes != 0 && dia ==0)
			{
			 searchr = conexion.prepareStatement("SELECT dia,mes,anio, sum(resultados) AS Totales FROM busquedas WHERE (mes=? AND anio=?) GROUP BY dia,mes,anio ORDER BY dia ASC");
			searchr.setInt(1, mes);
			searchr.setInt(2, anio);
			}
			else
			{
				if(anio != 0)
				{
				 searchr = conexion.prepareStatement("SELECT dia,mes,anio, sum(resultados) AS Totales FROM busquedas WHERE (anio=?) GROUP BY dia,mes,anio ORDER BY dia ASC, mes ASC, anio ASC");
				searchr.setInt(1, anio);
				}
				else
				{
					searchr = conexion.prepareStatement("SELECT dia,mes,anio, sum(resultados) AS Totales FROM busquedas GROUP BY dia,mes,anio ORDER BY dia ASC, mes ASC, anio ASC");
				}
				}
			}

		listaReporteFecha = searchr.executeQuery();

		return listaReporteFecha;
	}
	
	public static ResultSet obtenerReporteBusquedayTerminal(String buscada,Integer terminal) throws SQLException, ClassNotFoundException{
		ResultSet listaReporte;
		PreparedStatement searchrf;
	    Calendar actual = new GregorianCalendar();
		searchrf = conexion.prepareStatement("SELECT sum(resultados) AS Totales FROM busquedas  where (frase = ? AND id_terminal=? AND anio = ?) GROUP BY frase,id_terminal,mes,anio ORDER BY mes ASC");
		searchrf.setString(1, buscada);
		searchrf.setInt(2, terminal);
		searchrf.setInt(3, actual.get(Calendar.YEAR));
		listaReporte = searchrf.executeQuery();
		return listaReporte;
	}
	
	public static void registrarTerminal(Dispositivo tablero) throws SQLException, ClassNotFoundException{
		
	    Calendar actual = new GregorianCalendar();

		PreparedStatement search = conexion.prepareStatement("INSERT INTO terminales (nombre)"
		+ " values (?)");
		
		search.setString(1, tablero.getNombre());

		search.executeUpdate();
		
	}
	
	public static ResultSet obtenerReporteTerminales() throws SQLException, ClassNotFoundException{
		PreparedStatement searcht;
		ResultSet listaReporteTerminal;
		searcht = conexion.prepareStatement("SELECT nombre, sum(resultados) AS Totales FROM busquedas, terminales WHERE terminales.id = busquedas.id_terminal  GROUP BY busquedas.id_terminal ORDER BY terminales.id ASC");
		listaReporteTerminal = searcht.executeQuery();
		return listaReporteTerminal;
	}
}
