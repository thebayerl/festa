package model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="senha")
	private String senha;
	
	@Column(name="rg")
	private int rg;
	
	@Column(name="cpf")
	private int cpf;


	public void create() {
		boolean erro = false;
		
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Usuario.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {			
			// iniciando a transa��o
			session.beginTransaction();
			
			// testando a validade dos dados recebidos
			
			if(session.get(Usuario.class, id) == null) {
				System.out.println("Usuario com Id = " + id + " ja existente\n");
				erro = true;
			}
			
			if(!erro) {
				
				// salvando o objeto
				System.out.println("Salvando o Usuario...");
				session.save(this);
			}
			
			// finalizando transa��o
			session.getTransaction().commit();
			
			System.out.println("Commited!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
	}
	
	public void delete() {
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Usuario.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// come�ando a transa��o
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// deletando o objeto
			System.out.println("Deletando o Usuario...");
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getRg() {
		return rg;
	}

	public void setRg(int rg) {
		this.rg = rg;
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", senha=" + senha + ", rg=" + rg + ", cpf=" + cpf
				+ "]";
	}

	
}
