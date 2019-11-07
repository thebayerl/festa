package model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name="historico")
public class Historico implements Serializable {
	
	@Column(name="nota")
	private double nota;
	
	@Id
	@Column(name="aluno_id")
	private int alunoId;
	
	@Id
	@Column(name="turma_id")
	private int turmaId;


	public void create() {
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Aluno.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		//falta tratar os dados e trabalhar melhor na tabela de histórico
		
		try {			
			// iniciando a transação
			session.beginTransaction();
			
			// salvando o objeto
			System.out.println("Salvando o Histórico...");
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
	
	public void delete() {
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Historico.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// deletando o objeto
			System.out.println("Deletando o Historico...");
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

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
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
		return "Historico [nota=" + nota + ", alunoId=" + alunoId + ", turmaId=" + turmaId + "]";
	}
	
}
