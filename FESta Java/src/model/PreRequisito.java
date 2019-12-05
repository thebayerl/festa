package model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;

import static model.Read.factory;

@Entity
@Table(name="pre_requisito")
public class PreRequisito implements Serializable {
	@Id
	@Column(name="disciplina_id")
	private int disciplinaId;

	@Id
	@Column(name="prerequisito_id")
	private int prerequisitoId;

	public PreRequisito() {	}

	public PreRequisito(int disciplinaId, int prerequisitoId) {
		this.disciplinaId = disciplinaId;
		this.prerequisitoId = prerequisitoId;
	}

	public void create() {
		// criando session
		Session session = factory.getCurrentSession();
		
		try {			
			// iniciando a transação
			session.beginTransaction();
			// salvando o objeto
			System.out.println("Salvando o Pre Requisito...");
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
			System.out.println("Deletando o PreRequisito...");
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

	public int getDisciplinaId() {
		return disciplinaId;
	}

	public void setDisciplinaId(int disciplinaId) {
		this.disciplinaId = disciplinaId;
	}

	public int getPrerequisitoId() {
		return prerequisitoId;
	}

	public void setPrerequisitoId(int prerequisitoId) {
		this.prerequisitoId = prerequisitoId;
	}

	@Override
	public String toString() {
		return "PreRequisito [disciplinaId=" + disciplinaId + ", prerequisitoId=" + prerequisitoId + "]";
	}
}
