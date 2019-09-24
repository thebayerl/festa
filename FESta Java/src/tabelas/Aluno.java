package tabelas;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


@Entity
@Table(name="aluno")
public class Aluno {
	
	@Id
	@Column(name="matricula")
	private String matricula;
	
	@Column(name="nome")
	private String nome;

	@Column(name="data_nascimento")
	private String dataNascimento; 
	
	@Column(name="data_ingresso")
	private String dataIngresso;
	
	@Column(name="usuario_id")
	private int usuarioId;
	
	@Column(name="curso_id")
	private int cursoId;

	
	public Aluno(String matricula, String nome, String dataNascimento, String dataIngresso, int usuarioId,
			int cursoId) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.dataIngresso = dataIngresso;
		this.usuarioId = usuarioId;
		this.cursoId = cursoId;
	}
	
	public void create() {
		boolean erro = false;
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Usuario.class).addAnnotatedClass(Curso.class).addAnnotatedClass(Aluno.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// start a transaction
			session.beginTransaction();
			
			
			// testando a validade dos dados recebidos
			
			if(session.get(Aluno.class, matricula) == null) {
				System.out.println("Aluno com Matricula = " + matricula + " já existente\n");
				erro = true;
			}
			
			if(session.createQuery("from Aluno where usuarioId=" + usuarioId ) != null) {
				System.out.println("Aluno com UsuarioId = " + usuarioId + " já existente\n");
				erro = true;
			}
			
			if(session.get(Usuario.class, usuarioId) == null) {
				System.out.println("Usuario com UsuarioId = " + usuarioId + "não encontrado\n");
				erro = true;
			}
			
			if(session.get(Curso.class, cursoId) == null) {
				System.out.println("Curso com CursoId = " + cursoId + " não encontrado\n");
				erro = true;
			}
			
			if(!erro) {
			
				// save the object
				session.save(this);
			}
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Commited!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getDataIngresso() {
		return dataIngresso;
	}

	public void setDataIngresso(String dataIngresso) {
		this.dataIngresso = dataIngresso;
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

	public void setCursoId(int cursoId) {
		this.cursoId = cursoId;
	}

	@Override
	public String toString() {
		return "Aluno [matricula=" + matricula + ", nome=" + nome + ", dataNascimento=" + dataNascimento
				+ ", dataIngresso=" + dataIngresso + ", usuarioId=" + usuarioId + ", cursoId=" + cursoId + "]";
	}
	
}
