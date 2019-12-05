package model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static model.Read.factory;

@Entity
@Table(name="disciplina_curso")
public class DisciplinaCurso implements Serializable {
	
	@Id
	@Column(name="curso_id")
	private int cursoId;
	
	@Id
	@Column(name="disciplina_id")
	private int disciplinaId;
	
	public void create() {
		// criando session
		Session session = factory.getCurrentSession();
		
		try {		
			
			// iniciando a transação
			session.beginTransaction();
			System.out.println("Salvando a Diciplina no Curso...");
			session.save(this);
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			session.close();
		}
	}
	
	public void delete() {
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
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
			session.close();
		}
	}

	public int getCursoId() {
		return cursoId;
	}

	public void setCodigoCurso(int cursoId) {
		this.cursoId = cursoId;
	}

	public int getDisciplinaId() {
		return disciplinaId;
	}

	public void setDisciplinaId(int disciplinaId) {
		this.disciplinaId = disciplinaId;
	}

	@Override
	public String toString() {
		return "DisciplinaCurso [codigoCurso=" + cursoId + ", disciplinaId=" + disciplinaId + "]";
	}

	
}
