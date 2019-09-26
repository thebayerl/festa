package tabelas;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name="sala")
public class Sala {
	
	@Id
	@Column(name="codigo_sala")
	private String codigoSala;
	
	@Column(name="capacidade")
	private int capacidade;

	public Sala(int id, String codigoSala, int capacidade) {
		super();
		this.codigoSala = codigoSala;
		this.capacidade = capacidade;
	}
	
	public void create() {
		boolean erro = false;
		
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Sala.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {			
			// iniciando a transação
			session.beginTransaction();
			
			if(session.get(Sala.class, codigoSala) == null) {
				System.out.println("\nERRO: Sala com codigoSala = " + codigoSala + " já existente\n");
				erro = true;
			}
			
			if(!erro) {
				
				// salvando o objeto
				System.out.println("Salvando a Sala...");
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


	public String getCodigoSala() {
		return codigoSala;
	}

	public void setCodigoSala(String codigoSala) {
		this.codigoSala = codigoSala;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	@Override
	public String toString() {
		return "Sala [ codigoSala=" + codigoSala + ", capacidade=" + capacidade + "]";
	}
	
}
