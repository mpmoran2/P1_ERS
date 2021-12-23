package util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import persistant.Employees;
import persistant.Reimb;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			try {
				Configuration config = new Configuration();				
				Properties settings = new Properties();
				
				settings.put(Environment.DRIVER, "org.postgresql.Driver");
				settings.put(Environment.URL, "jdbc:postgresql://ersrev.cragov3k6p6c.us-west-1.rds.amazonaws.com/ers");
				settings.put(Environment.USER, "ers_user");				
				settings.put(Environment.PASS, "panic");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				settings.put(Environment.HBM2DDL_AUTO, "update");
				
				config.setProperties(settings);
				config.addAnnotatedClass(Employees.class);
				config.addAnnotatedClass(Reimb.class);
				
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
				System.out.println("Hibernate Registry Created");
				sessionFactory = config.buildSessionFactory(serviceRegistry);				
				System.out.println("got here");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	};
	
	
}
