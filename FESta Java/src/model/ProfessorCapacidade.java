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

	public ProfessorCapacidade(int professorId, int disciplinaId) {
		super();
		this.professorId = professorId;
		this.disciplinaId = disciplinaId;
	}
	
	public void create() {
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Aluno.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {			
			// iniciando a transação
			session.beginTransaction();
			
			// salvando o objeto
			System.out.println("Salvando o Professor Capacidade...");
			session.save(this);
			
			// finalizando transação
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
