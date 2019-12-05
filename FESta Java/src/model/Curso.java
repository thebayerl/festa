package model;
import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import static model.Read.factory;

@Entity
@Table(name="curso")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id; 
	
	@Column(name="codigo_curso")
	private String codigoCurso;
	
	@Column(name="nome")
	private String nome;

	@Column(name="departamento_id")
	private int departamentoId;

	public Curso() {
		
	}
	
	public Curso(String codigoCurso, String nome, int departamentoId) {
		super();
		this.codigoCurso = codigoCurso;
		this.nome = nome;
		this.departamentoId= departamentoId;
	}

	public void create() {
		// criando session
		Session session = factory.getCurrentSession();

		try {
			// iniciando a transação
			session.beginTransaction();

			// salvando o objeto
			System.out.println("Salvando o Curso...");
			session.save(this);

			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			session.close();
			session.close();
		}
	}
	
	public void delete() {
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			// deletando o objeto
			System.out.println("Deletando o Curso...");
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
		return this.id;
	}
	
	public String getcodigoCurso() {
		return codigoCurso;
	}

	public void setcodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	public String getnome() {
		return nome;
	}

	public void setnome(String nome) {
		this.nome = nome;
	}

	public int getDepartamentoId() {
		return this.departamentoId;
	}

	public void setdepartamentoId(int departamentoId) {
		this.departamentoId = departamentoId;
	}

	@Override
	public String toString() {
		return this.nome;
	}
	
}
