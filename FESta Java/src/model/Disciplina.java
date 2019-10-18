package model;
import java.math.BigInteger;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


@Entity
@Table(name="disciplina")
public class Disciplina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="codigo_disciplina")
	private String codigoDisciplina;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="creditos")
	private int creditos;
	
	@Column(name="departamento")
	private String departamento;

	public Disciplina() {}
	
	public Disciplina(String codigoDisciplina, String nome, int creditos, String departamento) {
		super();
		this.codigoDisciplina = codigoDisciplina;
		this.nome = nome;
		this.creditos = creditos;
		this.departamento = departamento;
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
			/*if(session.get(Disciplina.class, codigoDisciplina) == null) {
				System.out.println("Disciplina com codigoDisciplina = " + codigoDisciplina + " j� existente\n");
				erro = true;
				session.
			}*/
			
			//inserir check do departamento depois
			
			if(!erro) {
				
				// salvando o objeto
				System.out.println("Salvando a Disciplina...");
				session.save(this);
			}
			
			// finalizando transa��o
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
			System.out.println(exc);
		}
		finally {
			session.close();
			factory.close();
		}
	}
	
	public void delete() {
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Disciplina.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// come�ando a transa��o
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
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Disciplina [codigoDisciplina=" + codigoDisciplina + ", nome=" + nome + ", creditos=" + creditos + ", departamento =" + departamento
				+ "]";
	}

}
