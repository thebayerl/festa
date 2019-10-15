package model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


@Entity
@Table(name="disciplina")
public class Disciplina {
	
	@Id
	@Column(name="codigo_disciplina")
	private String codigoDisciplina;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="creditos")
	private int creditos;
	
	@Column(name="departamento_codigoDisciplina")
	private String departamentocodigoDisciplina;

	public Disciplina() {
		
	}
	
	public Disciplina(String codigoDisciplina, String nome, int creditos, String departamentocodigoDisciplina) {
		super();
		this.codigoDisciplina = codigoDisciplina;
		this.nome = nome;
		this.creditos = creditos;
		this.departamentocodigoDisciplina = departamentocodigoDisciplina;
	}

	public void create() {
		boolean erro = false;
		
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Disciplina.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {			
			// iniciando a transação
			session.beginTransaction();
			
			// testando a valcodigoDisciplinaade dos dados recebcodigoDisciplinaos
			
			if(session.get(Disciplina.class, codigoDisciplina) == null) {
				System.out.println("Disciplina com codigoDisciplina = " + codigoDisciplina + " já existente\n");
				erro = true;
			}
			
			//inserir check do departamento depois
			
			if(!erro) {
				
				// salvando o objeto
				System.out.println("Salvando a Disciplina...");
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
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Disciplina.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// deletando o objeto
			System.out.println("Deletando a Disciplina...");
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
	
	public String getcodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setcodigoDisciplina(String codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public String getDepartamento() {
		return departamentocodigoDisciplina;
	}

	public void setDepartamento(String departamentocodigoDisciplina) {
		this.departamentocodigoDisciplina = departamentocodigoDisciplina;
	}

	@Override
	public String toString() {
		return "Disciplina [codigoDisciplina=" + codigoDisciplina + ", nome=" + nome + ", creditos=" + creditos + ", departamentocodigoDisciplina =" + departamentocodigoDisciplina
				+ "]";
	}

}
