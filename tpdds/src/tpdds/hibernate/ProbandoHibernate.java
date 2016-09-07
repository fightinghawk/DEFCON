package tpdds.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class ProbandoHibernate {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session sesion = factory.openSession();
		Algo celulares = new Algo(75, "Celulares");
		Precio valor_celulares = new Precio(45332.56f);
		Precio valor_celulares_2 = new Precio(6245554.456f);
		valor_celulares.setIdPrecio(celulares);
		valor_celulares_2.setIdPrecio(celulares);
		List<Precio> precioCelulares = new ArrayList();
		precioCelulares.add(valor_celulares);precioCelulares.add(valor_celulares_2);
		celulares.setPrecio(precioCelulares);
		sesion.beginTransaction();
		for (Precio precio : precioCelulares) {
			sesion.save(precio);
		}
	//sesion.save(precioCelulares);
		sesion.save(celulares);
		sesion.getTransaction().commit();
		sesion.close();

	}
}

