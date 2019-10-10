package model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Delete {
	
	public boolean Aluno(int id) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Aluno.class).addAnnotatedClass(Usuario.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Aluno obj = session.get(Aluno.class, id);
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				// deletando o objeto
				session.delete(obj);
				Usuario user = session.get(Usuario.class, id);
				session.delete(user);
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		
		return sucesso;
	}
	
	public boolean Cordenador(int id) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cordenador.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Cordenador obj = session.get(Cordenador.class, id);
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				// deletando o objeto
				System.out.println("Deletando o Cordenador...");
				session.delete(obj);
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		
		return sucesso;
	}

	public boolean Curso(String id) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Curso.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Curso obj = session.get(Curso.class, id);
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				// deletando o objeto
				System.out.println("Deletando o Curso...");
				session.delete(obj);
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		return sucesso;
	}

	public boolean Disciplina(int id) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Disciplina.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Disciplina obj = session.get(Disciplina.class, id);
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				System.out.println("\nsucesso: Disciplina com id = " + id + " não existente\n");
				sucesso = false;
			}
			
			if(sucesso) {
				// deletando o objeto
				System.out.println("Deletando a Disciplina...");
				session.delete(obj);
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		return sucesso;
	}

	public boolean DisciplinaCurso(String id, int id2) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(DisciplinaCurso.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();

			List<DisciplinaCurso> obj = session.createQuery("select * from DisciplinaCurso where codigo_curso = " + id + "disciplinaId = " + id2).getResultList();
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				// deletando o objeto
				for(DisciplinaCurso o : obj) {
					session.delete(o);
				}
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		return sucesso;
	}

	public boolean Historico(int id, String id2) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Historico.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();

			List<Historico> obj = session.createQuery("select * from Historico where alunoId = " + id + "turmaId = " + id2).getResultList();
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				// deletando o objeto
				for(Historico o : obj) {
					session.delete(o);
				}
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		return sucesso;
	}

	public boolean Matriculado(int id, String id2) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Matriculado.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();

			List<Matriculado> obj = session.createQuery("select * from Matriculado where alunoId = " + id + "turmaId = " + id2).getResultList();
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				// deletando o objeto
				for(Matriculado o : obj) {
					session.delete(o);
				}
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		return sucesso;
	}

	public boolean PreRequisito(int id, int id2) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(PreRequisito.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();

			List<PreRequisito> obj = session.createQuery("select * from PreRequisito where disciplinaId = " + id + "prerequisitoId = " + id2).getResultList();
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				// deletando o objeto
				for(PreRequisito o : obj) {
					session.delete(o);
				}
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		return sucesso;
	}

	public boolean Professor(int id) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Professor.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Professor obj = session.get(Professor.class, id);
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				// deletando o objeto
				session.delete(obj);
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		
		return sucesso;
	}

	public boolean ProfessorCapacidade(int id, int id2) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ProfessorCapacidade.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();

			List<ProfessorCapacidade> obj = session.createQuery("select * from ProfessorCapacidade where professorId = " + id + "disciplinaId = " + id2).getResultList();
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				// deletando o objeto
				for(ProfessorCapacidade o : obj) {
					session.delete(o);
				}
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		return sucesso;
	}

	public boolean Sala(String id) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Sala.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Sala obj = session.get(Sala.class, id);
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				// deletando o objeto
				session.delete(obj);
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		
		return sucesso;
	}

	public boolean Turma(String id) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Turma.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Turma obj = session.get(Turma.class, id);
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				// deletando o objeto
				session.delete(obj);
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		
		return sucesso;
	}

	public boolean Usuario(int id) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Usuario.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Usuario obj = session.get(Usuario.class, id);
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				// deletando o objeto
				session.delete(obj);
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		
		return sucesso;
	}

	
}
