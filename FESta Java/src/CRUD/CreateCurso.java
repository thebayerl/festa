package CRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import tabelas.Curso;

public class CreateCurso {
	
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Curso.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// create a Curso object
			System.out.println("Creating new Curso object...");
			Curso tempCurso = new Curso( 1, "MAB");
			
			// start a transaction
			session.beginTransaction();
			
			// save the Curso object
			System.out.println("Saving the Curso...");
			session.save(tempCurso);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
		
		
	}

}
