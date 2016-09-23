package tpdds.hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

	public static SessionFactory sessionFactory = null;
	public static Session session = null;
	
	public static Session getSession()
	{
		if (sessionFactory == null || !session.isOpen())
		{

			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			session = sessionFactory.openSession();
		}
		
		return session;
	}

}
