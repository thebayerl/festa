package model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static model.Read.factory;

@Entity
@Table(name="professor")
public class Professor {
	
	@Id
	@Column(name="usuario_id")
	private int usuarioId;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="matricula")
	private String matricula;
	
	@Column(name="nivel_formacao")
	private String nivelFormacao;
	
	@Column(name="curso_id")
	private int cursoId;

	public Professor(){}
	
	public Professor(int usuarioId, String nome, String matricula, String nivelFormacao, int cursoId) {
		super();
		this.usuarioId = usuarioId;
		this.matricula = matricula;
		this.nome = nome;
		this.nivelFormacao = nivelFormacao;
		this.cursoId = cursoId;
	}

	public void create() {
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {			
			// iniciando a transação
			session.beginTransaction();
			// salvando o objeto
			System.out.println("Salvando o Professor...");
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
			// começando a transação
			session.beginTransaction();
			
			// deletando o(s) objeto(s)
			System.out.println("Deletando o Professor e Usuario referente a ele...");
			session.delete(this);
			Usuario user = session.get(Usuario.class, usuarioId);
			session.delete(user);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			session.close();
		}
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNivelFormacao() {
		return nivelFormacao;
	}

	public void setNivelFormacao(String nivelFormacao) {
		this.nivelFormacao = nivelFormacao;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getCursoId() {
		return cursoId;
	}

	public void setcursoId(int cursoId) {
		this.cursoId = cursoId;
	}

	@Override
	public String toString() {
		return this.nome;
	}
	
}
