package model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static model.Read.factory;


@Entity
@Table(name="aluno")
public class Aluno {
	
	@Id
	@Column(name="usuario_id")
	private int usuarioId;
	
	@Column(name="matricula")
	private String matricula;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="data_ingresso")
	private Date dataIngresso;
	
	@Column(name="curso_id")
	private int cursoId;
	
	public Aluno() {}
	
	public Aluno(int usuarioId, String nome, String dataIngresso, int cursoId) {
		super();
		this.usuarioId = usuarioId;
		this.matricula = "";
		this.nome = nome;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");				
		try {
			this.dataIngresso = format.parse ( dataIngresso );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		this.cursoId = cursoId;
	}

	public void create() {
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			// salvando o objeto
			System.out.println("Salvando o Aluno...");
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
			
			// deletando o(s) objeto(s)
			System.out.println("Deletando o Aluno e Usuario referente a ele...");
			session.delete(this);
			Usuario user = session.get(Usuario.class, usuarioId);
			session.delete(user);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			session.close();
		}
	}
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}



	public Date getDataIngresso() {
		return dataIngresso;
	}

	public void setDataIngresso(String dataIngresso) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");				
		try {			
			this.dataIngresso = format.parse ( dataIngresso );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getCursoId() {
		return cursoId;
	}

	public void setCursoId(int cursoId) {
		this.cursoId = cursoId;
	}

	@Override
	public String toString() {
		return nome;
	}
	
}
