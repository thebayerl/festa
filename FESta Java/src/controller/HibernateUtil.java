package controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Usuario;

/*
 * Classe para criar uma sessão de acesso ao banco de dados 
 */
public class HibernateUtil {
	
	private static Configuration configuration;
	 private static SessionFactory sessionFactory;
	 
	 static {
	  configuration = new Configuration().configure("hibernate.cfg.xml");
	  sessionFactory = configuration.buildSessionFactory();
	 }

	 public static Session getSession() {
	  Session session = null;
	  if (sessionFactory != null) {
	   session = sessionFactory.openSession();
	  }
	  return session;
	 }

}
