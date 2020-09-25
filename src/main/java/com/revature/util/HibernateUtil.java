package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static Session session;
	private static SessionFactory sf;
	private static Configuration cfg;
	
	
	// check this if you can't connect to your DB or if HibernateUtil isn't working
	static {
		cfg = new Configuration().configure("hibernate.cfg.xml");
//		System.out.println("--------------------------------------------------------------------");
//		System.out.println("project2_schema: " + System.getenv("project2_schema"));
//		System.out.println("db_url: " + System.getenv("db_url"));
//		System.out.println("un: " + System.getenv("postgres_username"));
//		System.out.println("pass: " + System.getenv("postgres_pw"));
//		System.out.println("Config Object: " + cfg);
//		System.out.println("--------------------------------------------------------------------");
		cfg.setProperty("hibernate.connection.url", System.getenv("db_url"));
		cfg.setProperty("hibernate.connection.password", System.getenv("postgres_pw"));
		cfg.setProperty("hibernate.connection.username", System.getenv("postgres_username"));
		cfg.setProperty("hibernate.default_schema", System.getenv("project2_schema"));

		try {
			sf = cfg.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private HibernateUtil() {
		super();
	}

	public static Session getSession() {
		if (session == null || !session.isOpen()) {
			session = sf.openSession();
		}

		return session;
	}

	public static void closeSession() {
		session.close();
	}
	
	public static void reconfigureSchema(String s) {
		cfg.setProperty("hibernate.default_schema", s);
		try {
			sf = cfg.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
