package model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
		this.nome = nome;
		this.matricula = matricula;
		this.nivelFormacao = nivelFormacao;
		this.cursoId = cursoId;
	}

	public void create() {
		boolean erro = false;
		
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Professor.class).addAnnotatedClass(Cordenador.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {			
			// iniciando a transação
			session.beginTransaction();
			
			/*if(session.get(Cordenador.class, codigoCurso) == null) {
				System.out.println("\nERRO: Aluno com codigoCurso = " + codigoCurso + " já existente\n");
				erro = true;
			}*/
			
			if(!erro) {
				
				// salvando o objeto
				System.out.println("Salvando o Professor...");
				session.save(this);
			};
			
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
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Professor.class).addAnnotatedClass(Usuario.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
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
			factory.close();
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

	public int getcodigoCurso() {
		return cursoId;
	}

	public void setcodigoCurso(int codigoCurso) {
		this.cursoId = codigoCurso;
	}

	@Override
	public String toString() {
		return this.nome;
	}
	
}
