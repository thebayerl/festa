package model;
import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name="sala")
public class Sala {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="codigo_sala")
	private String codigoSala;
	
	@Column(name="capacidade")
	private int capacidade;
	
	@Column(name="predio")
	private String predio;
	

	public Sala() {
		
	}
	
	public Sala(String codigoSala, int capacidade, String predio) {
		super();
		this.codigoSala = codigoSala;
		this.capacidade = capacidade;
		this.predio = predio;
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
			
			/*if(session.get(Sala.class, codigoSala) == null) {
				System.out.println("\nERRO: Sala com codigoSala = " + codigoSala + " já existente\n");
				erro = true;
			}*/
			
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
	
	public void delete() {
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Sala.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// deletando o objeto
			System.out.println("Deletando a Sala...");
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

	public String getPredio() {
		return predio;
	}

	public void setPredio(String predio) {
		this.predio = predio;
	}

	@Override
	public String toString() {
		return this.codigoSala;
	}
	
}
