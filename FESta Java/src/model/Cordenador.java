package tabelas;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name="cordenador")
public class Cordenador {
	
	@Id
	@Column(name="usuario_id")
	private int usuarioId;
	
	@Column(name="nome")
	private String nome;
	


	public Cordenador(String nome, int usuarioId) {
		this.usuarioId = usuarioId;
		this.nome = nome;
	}
	
	public void create() {
		
		
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cordenador.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {	
			
			// iniciando a transação
			session.beginTransaction();
				
			// salvando o objeto
			System.out.println("Salvando o Cordenador...");
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	@Override
	public String toString() {
		return "Cordenador [usuarioId=" + usuarioId + ", nome=" + nome + "]";
	}
	
}
