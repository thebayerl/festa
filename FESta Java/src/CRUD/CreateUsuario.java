package CRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import tabelas.Usuario;

public class CreateUsuario {
	
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Usuario.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// create a Usuario object
			System.out.println("Creating new Usuario object...");
			Usuario tempUsuario = new Usuario( 1, "Daniel", "Cachorro");
			
			// start a transaction
			session.beginTransaction();
			
			// save the Usuario object
			System.out.println("Saving the Usuario...");
			session.save(tempUsuario);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
		
		
	}

}
