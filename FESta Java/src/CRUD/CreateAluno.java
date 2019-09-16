package CRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import tabelas.Aluno;

public class CreateAluno {
	
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Aluno.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// create a Aluno object
			System.out.println("Creating new Aluno object...");
			Aluno tempAluno = new Aluno( 1,"117234580", "Daniel", "1999-01-06", "2017-06-01" , 1, 1);
			
			// start a transaction
			session.beginTransaction();
			
			// save the Aluno object
			System.out.println("Saving the Aluno...");
			session.save(tempAluno);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
		
		
	}

}
