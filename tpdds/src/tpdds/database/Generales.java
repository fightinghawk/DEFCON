package tpdds.database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tpdds.Usuarios.User;
import tpdds.hibernate.HibernateSessionFactory;
import tpdds.interfaz.componentes.Historial;
import tpdds.interfaz.componentes.reporteFecha;
import tpdds.interfaz.componentes.reporteTerminal;
import tpdds.pois.Poi;


public class Generales{
	
	public static  ArrayList<Poi> cargarPois(){
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		String sql = "SELECT * FROM POIS";
		SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Poi.class);
        ArrayList<Poi> poisRev = new  ArrayList<Poi>(query.list());
        session.getTransaction().commit();
        session.close();
		return poisRev;
	}
	
	public static  ArrayList<User> cargarUsuarios(){
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		String sql = "SELECT * FROM USUARIOS";
		SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(User.class);
        ArrayList<User> userRev = new  ArrayList<User>(query.list());
        session.getTransaction().commit();
        session.close();
		return userRev;
	}
	
	public static  ObservableList<String> cargarTiposUsuarios(){
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		Query pedido = session.createQuery("Select p.tipo FROM TipoUsuario p");
		@SuppressWarnings("unchecked")
		ObservableList<String> tiposUsuario = FXCollections.observableArrayList();
		List<String> hola = pedido.list();
		for (String string : hola) {
			tiposUsuario.add(string);
		}
        session.close();
		return tiposUsuario;
	}
	
	public static void modificarUsuario(ArrayList<User> users){
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		for (User user : users) {
			session.saveOrUpdate(user);
		}
        session.getTransaction().commit();
        session.close();
	}
	
	public static  ArrayList<String> obtenerTerminales(){
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		String sql = "SELECT nombre FROM terminales";
		SQLQuery query = session.createSQLQuery(sql);
		query.addScalar("nombre", StringType.INSTANCE);
        ArrayList<String> terminales = new  ArrayList<String>(query.list());
        session.getTransaction().commit();
        session.close();
		return terminales;
	}
	
	public static void agregarPoi(Poi poi){
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		session.save(poi);
        session.getTransaction().commit();
        session.close();
	}
	
	public static void modificarPoi(Poi poi){
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		session.saveOrUpdate(poi);
        session.getTransaction().commit();
        session.close();
	}
	
	public static void borrarPoi(Poi poi){
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		session.delete(poi);
        session.getTransaction().commit();
        session.close();
	}
	
	public static ArrayList<Historial> obtenerHistorial(String usuario){
		ArrayList<Historial> result = new ArrayList<Historial>();
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		String sql = "SELECT b.fecha,b.usuarios_user_id,c.cri_tipo,c.cri_contenido,b.resultados from busqueda b, criteriosdebusqueda c WHERE b.usuarios_user_id=:usuario AND c.busquedaid=b.id";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("usuario", usuario);
		query.addScalar("fecha", StringType.INSTANCE);
		query.addScalar("usuarios_user_id", StringType.INSTANCE);
		query.addScalar("cri_tipo", StringType.INSTANCE);
		query.addScalar("cri_contenido", StringType.INSTANCE);
		query.addScalar("resultados", IntegerType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(Historial.class));

		result = new ArrayList<Historial>(query.list());
        session.getTransaction().commit();
        session.close();
        
        return result;
	}
	
	public static ArrayList<reporteFecha> obtenerReporteFecha(Integer dia,Integer mes,Integer anio) {
		
		ArrayList<reporteFecha> result = new ArrayList<reporteFecha>();
            

		if(anio != 0 && mes != 0 && dia !=0)
		{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			String sql = "SELECT fecha, sum(resultados) AS totales FROM busqueda WHERE DAY(fecha) = :dia AND MONTH(fecha) = :mes AND YEAR(fecha) = :anio   GROUP BY fecha";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("dia", dia);
			query.setParameter("mes", mes);
			query.setParameter("anio", anio);
			query.addScalar("fecha", StringType.INSTANCE);
			query.addScalar("totales", BigDecimalType.INSTANCE);
			query.setResultTransformer(Transformers.aliasToBean(reporteFecha.class));
			result = new ArrayList<reporteFecha>(query.list());
	        session.getTransaction().commit();
	        session.close();
		}
		else
		{
			if(anio != 0 && mes != 0 && dia ==0)
			{
				Session session = HibernateSessionFactory.getSession();
				session.beginTransaction();
				String sql = "SELECT DAY(fecha) AS fecha, sum(resultados) AS totales FROM busqueda WHERE MONTH(fecha) = :mes AND YEAR(fecha) = :anio   GROUP BY DAY(fecha) ORDER BY DAY(fecha) ASC";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("mes", mes);
				query.setParameter("anio", anio);
				query.addScalar("fecha", StringType.INSTANCE);
				query.addScalar("totales", BigDecimalType.INSTANCE);
				query.setResultTransformer(Transformers.aliasToBean(reporteFecha.class));
				result = new ArrayList<reporteFecha>(query.list());
		        session.getTransaction().commit();
		        session.close();
			}
			else
			{
				if(anio != 0)
				{
					Session session = HibernateSessionFactory.getSession();
					session.beginTransaction();
					String sql = "SELECT MONTH(fecha) AS fecha, sum(resultados) AS totales FROM busqueda WHERE YEAR(fecha) = :anio GROUP BY MONTH(fecha) ORDER BY MONTH(fecha) ASC";
					SQLQuery query = session.createSQLQuery(sql);
					query.setParameter("anio", anio);
					query.addScalar("fecha", StringType.INSTANCE);
					query.addScalar("totales", BigDecimalType.INSTANCE);
					query.setResultTransformer(Transformers.aliasToBean(reporteFecha.class));
					result = new ArrayList<reporteFecha>(query.list());
			        session.getTransaction().commit();
			        session.close();
				}
				else
				{
					Session session = HibernateSessionFactory.getSession();
					session.beginTransaction();
					String sql = "SELECT YEAR(fecha) AS fecha, sum(resultados) AS totales FROM busqueda GROUP BY YEAR(fecha) ORDER BY YEAR(fecha) ASC";
					SQLQuery query = session.createSQLQuery(sql);
					query.addScalar("fecha", StringType.INSTANCE);
					query.addScalar("totales", BigDecimalType.INSTANCE);
					query.setResultTransformer(Transformers.aliasToBean(reporteFecha.class));
					result = new ArrayList<reporteFecha>(query.list());
			        session.getTransaction().commit();
			        session.close();
				}
				}
			}
		return result;
	}
	
	public static ArrayList<reporteTerminal> obtenerReporteBusquedayTerminal(String buscada,String terminal) {
		ArrayList<reporteTerminal> result = new ArrayList<reporteTerminal>();
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		String sql = "SELECT sum(b.resultados) AS resultados FROM busqueda b,terminales t,criteriosdebusqueda c WHERE t.nombre = :terminal_nombre AND b.terminales_id = t.id AND c.busquedaid = b.id AND c.cri_contenido = :busqueda group by month(b.fecha) order by  month(b.fecha) asc";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("terminal_nombre", terminal);
		query.setParameter("busqueda", buscada);
		query.addScalar("resultados", BigDecimalType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(reporteTerminal.class));
		ArrayList<reporteTerminal> resultados = new ArrayList<reporteTerminal>(query.list());
		result = new ArrayList<reporteTerminal>(query.list());
        session.getTransaction().commit();
        session.close();
	    return resultados;
	}

}