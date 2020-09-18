package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static Session session;
	private static SessionFactory sf;

	static {
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		cfg.setProperty("hibernate.connection.url", System.getenv("db_url"));
		cfg.setProperty("hibernate.connection.password", System.getenv("postgres_pw"));
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
}
