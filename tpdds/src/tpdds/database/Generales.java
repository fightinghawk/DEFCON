package tpdds.database;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import tpdds.factory.POIFactory;
import tpdds.pois.Bancos;
import tpdds.pois.CGP;
import tpdds.pois.DiaPoi;
import tpdds.pois.LocalesComerciales;
import tpdds.pois.ParadaColectivo;
import tpdds.pois.Poi;
import tpdds.ubicacion.Direccion;
import tpdds.ubicacion.Location;


public class Generales{

	public static Connection conexion;
	
	public static void initDatabase() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		conexion = DriverManager.getConnection("jdbc:mysql://sql5.freemysqlhosting.net:3306/sql5124816", "sql5124816", "JEMggS6Mzz");
	}	
	
	public static ArrayList<Poi> cargarPois() throws SQLException, ClassNotFoundException {
		String pedido = new String("SELECT pois.id,pois.nombre,pois.tipo,direcciones.principal,direcciones.izquierda,"
						+ " direcciones.derecha,direcciones.barrio,direcciones.altura,geoLoc.latitud,"
						+ "	geoLoc.longitud,pois.strtipo, direcciones.id , geoLoc.id"
						+ " FROM pois LEFT JOIN (direcciones,geoLoc)"
						+ " ON (pois.direccion = direcciones.id AND pois.geopos = geoLoc.id)");
		PreparedStatement pedidoSQL = conexion.prepareStatement(pedido);
		ResultSet resultadosPoi = pedidoSQL.executeQuery();
		ArrayList<Poi> pois = new ArrayList<>();
		while(resultadosPoi.next()){
			//Datos poi
			int id = resultadosPoi.getInt(1);
			String nombre = resultadosPoi.getString(2);
			int tipo = resultadosPoi.getInt(3);
			//Datos direccion
			String calle = resultadosPoi.getString(4);
			String izquierda = resultadosPoi.getString(5);
			String derecha = resultadosPoi.getString(6);
			String barrio = resultadosPoi.getString(7);
			String strtipo = resultadosPoi.getString(11);
			int altura = resultadosPoi.getInt(8);
			int iddirec = resultadosPoi.getInt(12);
			Direccion direc = new Direccion(calle, altura, izquierda, derecha, barrio);
			direc.setIddb(iddirec);
			//Datos geolocalizacion
			float latitud = resultadosPoi.getFloat(9);
			float longitud = resultadosPoi.getFloat(10);
			int idgeo = resultadosPoi.getInt(13);
			Location loc = new Location(latitud, longitud);
			loc.setIddb(idgeo);
			//Key Words
			String pedidoKeyWords = "SELECT keywords.clave FROM keywords WHERE (poid = ?)";
			PreparedStatement pedidoKeyWordsSQL = conexion.prepareStatement(pedidoKeyWords);
			pedidoKeyWordsSQL.setInt(1, id);
			ResultSet keyWords = pedidoKeyWordsSQL.executeQuery();
			HashSet<String> palabrasclaves = new HashSet<>();
			while(keyWords.next()){
				palabrasclaves.add(keyWords.getString(1).toLowerCase());
			}
			//Dias
			String pedidoDias = "SELECT dias.horainicio,dias.horafin,dias.minutoinicio,dias.minutofin,dias.dia "
					+ "FROM dias WHERE (poid = ?)";
			PreparedStatement pedidoDiasSQL = conexion.prepareStatement(pedidoDias);
			pedidoDiasSQL.setInt(1, id);
			ResultSet dias = pedidoDiasSQL.executeQuery();
			ArrayList<DiaPoi> diasAbieros = new ArrayList<>();
			while(dias.next()){
				diasAbieros.add(new DiaPoi(dias.getInt(1), dias.getInt(2), dias.getInt(3), dias.getInt(4), dias.getInt(5)));
			}
			switch (tipo) {
			case 1:
				pois.add(new Bancos(nombre,direc,loc,palabrasclaves,diasAbieros,id));
				break;
			case 2:
				pois.add(new CGP(nombre,direc,loc,palabrasclaves,diasAbieros,id));
				break;
			case 3:
				pois.add(new ParadaColectivo(nombre,direc,loc,palabrasclaves,diasAbieros,id));
				break;
			case 4:
				pois.add(new LocalesComerciales(nombre,strtipo,direc,loc,palabrasclaves,diasAbieros,id));
				break;
			}
		}
		return pois;
	}
	
	public static void almacenarPOI(Poi poiA) throws SQLException, ClassNotFoundException{
		PreparedStatement insertarDireccion = conexion.prepareStatement("INSERT INTO direcciones (principal,izquierda,derecha,barrio,altura) "
				+ " VALUES (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
		PreparedStatement insertarGeoLoc = conexion.prepareStatement("INSERT INTO geoLoc (latitud,longitud) VALUES (?,?)",Statement.RETURN_GENERATED_KEYS);
		PreparedStatement insertarPOI = conexion.prepareStatement("INSERT INTO pois (nombre,tipo,direccion,geopos,strtipo) VALUES (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
		PreparedStatement insertarKeyWord = conexion.prepareStatement("INSERT INTO keywords (poid,clave) VALUES (?,?)");
		PreparedStatement insertarDia = conexion.prepareStatement("INSERT INTO dias (poid,horainicio,horafin,minutoinicio,minutofin,dia) VALUES(?,?,?,?,?,?)");

		ResultSet temp;
		//Insertar direccion
		insertarDireccion.setString(1, poiA.getDireccion().getCallePrincipal());
		insertarDireccion.setString(2, poiA.getDireccion().getCalleLateralIzq());
		insertarDireccion.setString(3, poiA.getDireccion().getCalleLateralDer());
		insertarDireccion.setString(4, poiA.getDireccion().getBarrio());
		insertarDireccion.setInt(5,  poiA.getDireccion().getAltura());
		insertarDireccion.executeUpdate();
		temp = insertarDireccion.getGeneratedKeys();
		temp.next();
		int idDirec= temp.getInt(1);//Obtengo clave autogenerada
		//InsertoGeoLoc
		insertarGeoLoc.setDouble(1, poiA.getGeoloc().getLatitud());
		insertarGeoLoc.setDouble(2, poiA.getGeoloc().getLongitud());
		insertarGeoLoc.executeUpdate(); 
		temp = insertarGeoLoc.getGeneratedKeys();
		temp.next();
		int idGeoLoc = temp.getInt(1);//Obtengo clave autogenerada
		//InsertoPoi
		insertarPOI.setString(1, poiA.getNombre());
		insertarPOI.setInt(2, poiA.getIdTipo());
		insertarPOI.setInt(3, idDirec);
		insertarPOI.setInt(4, idGeoLoc);
		insertarPOI.setString(5, poiA.getTipo());
		insertarPOI.executeUpdate();
		temp = insertarPOI.getGeneratedKeys();
		temp.next();
		int idPoi = temp.getInt(1);
		//inserto key words
		ArrayList<String> palabras = new ArrayList<>(poiA.getPalabrasClaves());
		for (String key : palabras) {
			insertarKeyWord.setInt(1, idPoi);
			insertarKeyWord.setString(2, key);
			insertarKeyWord.executeUpdate();
		}
		//inserto dias 
		ArrayList<DiaPoi> dias = poiA.getDiasDisp();
		for (DiaPoi diaPoi : dias) {
			insertarDia.setInt(1, idPoi);
			insertarDia.setInt(2, diaPoi.getHoraApertura());
			insertarDia.setInt(3, diaPoi.getHoraClose());
			insertarDia.setInt(4, diaPoi.getMinApertura());
			insertarDia.setInt(5, diaPoi.getMinClose());
			insertarDia.setInt(6, diaPoi.getDia());
			insertarDia.executeUpdate();
		}
	}

	public static void modificarDireccion(Direccion direc) throws SQLException, ClassNotFoundException{
		//principal,izquierda,derecha,barrio,altura
		PreparedStatement modif = conexion.prepareStatement("UPDATE direcciones"
				+ " SET principal = ? , izquierda = ? , derecha = ? , barrio = ? , altura = ?"
				+ " WHERE id = ?");
		modif.setString(1, direc.getCallePrincipal());
		modif.setString(2, direc.getCalleLateralIzq());
		modif.setString(3, direc.getCalleLateralDer());
		modif.setString(4, direc.getBarrio());
		modif.setInt(5, direc.getAltura());
		modif.setInt(6, direc.getIddb());
		modif.executeUpdate();	
	}
	
	public static void modificarGeoLoc(Location geo) throws SQLException, ClassNotFoundException{
		PreparedStatement modif = conexion.prepareStatement("UPDATE geoLoc"
				+ " SET latitud = ? , longitud = ?"
				+ " WHERE id = ?");
		modif.setDouble(1, geo.getLatitud());
		modif.setDouble(2, geo.getLongitud());
		modif.setInt(3, geo.getIddb());
		modif.executeUpdate();
	}
	
	public static void modificarPOI(Poi poi)throws SQLException, ClassNotFoundException{
		PreparedStatement modif = conexion.prepareStatement("UPDATE pois"
				+ " SET nombre = ?"
				+ " WHERE id = ?");
		modif.setString(1, poi.getNombre());
		modif.setInt(2, poi.getIddb());
		modif.executeUpdate();
		Generales.modificarDireccion(poi.getDireccion());
		Generales.modificarGeoLoc(poi.getGeoloc());
	}
	public static void borrarPoi(Poi poi) throws SQLException, ClassNotFoundException {
		PreparedStatement borrado = conexion.prepareStatement("DELETE FROM pois WHERE id = ?");
		borrado.setInt(1, poi.getIddb());
		borrado.executeUpdate();
	}
}
