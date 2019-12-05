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
@Table(name="matriculado")
public class Matriculado implements Serializable {

	private static final long serialVersionUID = 1L;

	public Matriculado(){}

	public Matriculado (int idAluno, int idTurma) {
		this.alunoId = idAluno;
		this.turmaId = idTurma;
	}

	@Id
	@Column(name="aluno_id")
	private int alunoId;
	
	@Id
	@Column(name="turma_id")
	private int turmaId;

	
	public void create() {
		// criando session
		Session session = factory.getCurrentSession();
		
		
		try {
			// iniciando a transação
			session.beginTransaction();
			// salvando o objeto
			System.out.println("Salvando o Matriculado...");
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
			System.out.println("Deletando o Matriculado...");
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

	public int getAlunoId() {
		return alunoId;
	}

	public void setAlunoId(int alunoId) {
		this.alunoId = alunoId;
	}

	public int getturmaId() {
		return turmaId;
	}

	public void setturmaId(int turmaId) {
		this.turmaId = turmaId;
	}

	@Override
	public String toString() {
		return "Matriculado [alunoId=" + alunoId + ", turmaId=" + turmaId + "]";
	}
	
}
