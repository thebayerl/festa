package model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name="disciplina_curso")
public class DisciplinaCurso {
	
	@Column(name="codigo_curso")
	private String codigoCurso;
	
	@Column(name="disciplina_id")
	private int disciplinaId;
	
	public void create() {
		boolean erro = false;
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(DisciplinaCurso.class).addAnnotatedClass(Disciplina.class).addAnnotatedClass(Curso.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {		
			
			// iniciando a transação
			session.beginTransaction();
			
			// testando validade dos dados recebidos
			
			if(session.get(Disciplina.class, disciplinaId) == null) {
				System.out.println("Disciplina com Id = " + disciplinaId + " já existente\n");
				erro = true;
			}
			
			if(session.get(Curso.class, codigoCurso) == null) {
				System.out.println("Curso com codigoCurso = " + codigoCurso + " já existente\n");
				erro = true;
			}
			
			
			if(!erro) {
				
				// salvando o objeto
				System.out.println("Salvando a Diciplina no Curso...");
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
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(DisciplinaCurso.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// deletando o objeto
			System.out.println("Deletando a DisciplinaCurso...");
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

	public String getCodigoCurso() {
		return codigoCurso;
	}

	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	public int getDisciplinaId() {
		return disciplinaId;
	}

	public void setDisciplinaId(int disciplinaId) {
		this.disciplinaId = disciplinaId;
	}

	@Override
	public String toString() {
		return "DisciplinaCurso [codigoCurso=" + codigoCurso + ", disciplinaId=" + disciplinaId + "]";
	}

	
}
