package model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name="historico")
public class ProfessorCapacidade {
	
	@Column(name="professor_id")
	private int professorId;
	
	@Column(name="disciplina_id")
	private int disciplinaId;
	
	public void create() {
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Aluno.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {
			boolean erro = false;
			// iniciando a transação
			session.beginTransaction();
			
			// Checando integridade dos dados
			if(session.get(Professor.class, professorId) == null) {
				System.out.println("\nERRO: Professor não encontrado!");
				erro = true;
			}
			if(session.get(Disciplina.class, disciplinaId) == null) {
				System.out.println("\nERRO: Disciplina não encontrada!");
				erro = true;
			}
			
			if(!erro) {

				// salvando o objeto
				System.out.println("Salvando o Professor Capacidade...");
				session.save(this);
				
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
	}
	
	public void delete() {
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ProfessorCapacidade.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// deletando o objeto
			System.out.println("Deletando o ProfessorCapacidade...");
			session.delete(this);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
	}

	public int getProfessorId() {
		return professorId;
	}

	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}

	public int getDisciplinaId() {
		return disciplinaId;
	}

	public void setDisciplinaId(int disciplinaId) {
		this.disciplinaId = disciplinaId;
	}

	@Override
	public String toString() {
		return "ProfessorCapacidade [professorId=" + professorId + ", disciplinaId=" + disciplinaId + "]";
	}

}
