package parser.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import parser.entity.Account;
import parser.entity.Claim;
import parser.entity.FileAttachment;
import parser.entity.FileFormat;
import parser.entity.FileFormatParser;
import parser.entity.HeaderFieldParser;
import parser.entity.ItemLinePattern;
import parser.entity.ItemLinePosition;
import parser.entity.UploadedDataDetails;

public class HibernateUtils {
	private static SessionFactory sessionFactory;

	static {
		System.out.println("I am running");
	}
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();

				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/marshmellow");
				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "root");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.FORMAT_SQL, "true");
				

				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				//settings.put(Environment.HBM2DDL_AUTO, "create-drop");
				settings.put(Environment.HBM2DDL_AUTO, "update");

				configuration.setProperties(settings);
				addAnnotatedClass(configuration);
				

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

	private static void addAnnotatedClass(Configuration configuration) {
		configuration.addAnnotatedClass(Account.class);
		configuration.addAnnotatedClass(UploadedDataDetails.class);
		configuration.addAnnotatedClass(FileAttachment.class);
		configuration.addAnnotatedClass(FileFormat.class);
		configuration.addAnnotatedClass(FileFormatParser.class);
		configuration.addAnnotatedClass(HeaderFieldParser.class);
		configuration.addAnnotatedClass(ItemLinePattern.class);
		configuration.addAnnotatedClass(ItemLinePosition.class);
		configuration.addAnnotatedClass(Claim.class);
	}
}
