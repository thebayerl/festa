package model;
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
	@Column(name="usuario_id")
	private int usuarioId;
	
	@Column(name="matricula")
	private String matricula;
	
	@Column(name="nome")
	private String nome;

	@Column(name="data_nascimento")
	private String dataNascimento; 
	
	@Column(name="data_ingresso")
	private String dataIngresso;
	
	@Column(name="codigo_curso")
	private String codigoCurso;

	
	
	public void create() {
		boolean erro = false;
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Curso.class).addAnnotatedClass(Aluno.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// come�ando a transa��o
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			
			// testando a validade dos dados recebidos
			
			if(session.get(Aluno.class, usuarioId) == null) {
				System.out.println("\nERRO: Aluno com Matricula = " + usuarioId + " j� existente\n");
				erro = true;
			}
			
			if(session.get(Curso.class, codigoCurso) == null) {
				System.out.println("\nERRO: Curso com codigoCurso = " + codigoCurso + " n�o encontrado\n");
				erro = true;
			}
			
			if(!erro) {
			
				// salvando o objeto
				System.out.println("Salvando o Aluno...");
				session.save(this);
			}
			
			// finalizando transa��o
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
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Aluno.class).addAnnotatedClass(Usuario.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// come�ando a transa��o
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// deletando o(s) objeto(s)
			System.out.println("Deletando o Aluno e Usuario referente a ele...");
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

	public String getcodigoCurso() {
		return codigoCurso;
	}

	public void setcodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	@Override
	public String toString() {
		return "Aluno [matricula=" + matricula + ", nome=" + nome + ", dataNascimento=" + dataNascimento
				+ ", dataIngresso=" + dataIngresso + ", usuarioId=" + usuarioId + ", codigoCurso=" + codigoCurso + "]";
	}
	
}
