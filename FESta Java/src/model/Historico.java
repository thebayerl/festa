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

	@Column(name="frequencia")
	private int frequencia;

	@Column(name="resultado")
	private String resultado;

	public Historico(int alunoId, int turmaId, double nota, int frequencia, String resultado) {
		super();
		this.alunoId = alunoId;
		this.turmaId = turmaId;
		this.nota = nota;
		this.frequencia = frequencia;
		this.resultado = resultado;
	}

	public Historico() {}

	public void create() {
		
		// criando session
		Session session = factory.getCurrentSession();
		
		//falta tratar os dados e trabalhar melhor na tabela de hist�rico
		
		try {			
			// iniciando a transa��o
			session.beginTransaction();
			
			// salvando o objeto
			System.out.println("Salvando o Hist�rico...");
			session.save(this);
			
			// finalizando transa��o
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
			System.out.println("Deletando o Historico...");
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

	public int getTurmaId() {
		return turmaId;
	}

	public void setTurmaId(int turmaId) {
		this.turmaId = turmaId;
	}

	public int getFrequencia() {
		return frequencia;
	}

	public String getResultado() {
		return resultado;
	}

	public void setFrequencia(int frequencia) {
		this.frequencia = frequencia;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "Historico [nota=" + nota + ", alunoId=" + alunoId + ", turmaId=" + turmaId + "]";
	}
	
}
