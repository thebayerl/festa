package model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static model.Read.factory;


@Entity
@Table(name="departamento")
public class Departamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="codigo_departamento")
	private String codigoDepartamento;
	
	@Column(name="nome")
	private String nome;
	
	public Departamento(){}

	public Departamento(String nome, String codigoDepartamento) {
		super();
		this.nome = nome;
		this.codigoDepartamento = codigoDepartamento;
	}

	public void create() {
		// criando session
		Session session = factory.getCurrentSession();

		try {
			// iniciando a transação
			session.beginTransaction();
			
			// salvando o objeto
			System.out.println("Salvando o Departamento...");
			session.save(this);

			// finalizando transação
			session.getTransaction().commit();

			System.out.println("Pronto!");

		} catch(Exception exc){
		}
		finally {
			session.close();
		}
	}

	public void delete() {
		//create session
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			// deletando o objeto
			System.out.println("Deletando o Departamento...");
			session.delete(this);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Pronto!");

		} catch(Exception exc){
		}
		finally {
			session.close();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCodigoDepartamento() {
		return codigoDepartamento;
	}

	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	@Override
	public String toString() {
		return this.nome;

	}
	

}
