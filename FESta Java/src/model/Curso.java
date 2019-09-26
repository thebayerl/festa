package tabelas;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name="curso")
public class Curso {
	
	@Id
	@Column(name="codigo_curso")
	private int codigoCurso;
	
	@Column(name="nome")
	private String nome;

	public Curso(int codigoCurso, String nome) {
		this.codigoCurso = codigoCurso;
		this.nome = nome;
	}
	
	public void create() {
		boolean erro = false;
		
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Curso.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {			
			// iniciando a transação
			session.beginTransaction();
			
			if(session.get(Curso.class, codigoCurso) == null) {
				System.out.println("Curso com codigoCurso = " + codigoCurso + " já existente\n");
				erro = true;
			}
			
			if(!erro) {
				
				// salvando o objeto
				System.out.println("Salvando o Curso...");
				session.save(this);
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
	}
	
	public int getcodigoCurso() {
		return codigoCurso;
	}

	public void setcodigoCurso(int codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	public String getnome() {
		return nome;
	}

	public void setnome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Curso [codigoCurso=" + codigoCurso + ", nome=" + nome + "]";
	}
	
}
