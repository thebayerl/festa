package tabelas;
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
	
	@Column(name="cordenador_id")
	private int cordenadorId;

	public Professor(String nome, String matricula, String nivelFormacao, int usuarioId, int cordenadorId) {
		super();
		this.nome = nome;
		this.matricula = matricula;
		this.nivelFormacao = nivelFormacao;
		this.usuarioId = usuarioId;
		this.cordenadorId = cordenadorId;
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
			
			if(session.get(Cordenador.class, cordenadorId) == null) {
				System.out.println("\nERRO: Aluno com cordenadorId = " + cordenadorId + " já existente\n");
				erro = true;
			}
			
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

	public int getCordenadorId() {
		return cordenadorId;
	}

	public void setCordenadorId(int cordenadorId) {
		this.cordenadorId = cordenadorId;
	}

	@Override
	public String toString() {
		return "Professor [usuarioId=" + usuarioId + ", nome=" + nome + ", matricula=" + matricula + ", nivelFormacao="
				+ nivelFormacao + ", cordenadorId=" + cordenadorId + "]";
	}
	
}
