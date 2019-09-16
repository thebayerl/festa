package tabelas;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name="historico")
public class Historico {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="nota")
	private double nota;
	
	@Column(name="aluno_id")
	private int alunoId;
	
	@Column(name="turma_id")
	private int turmaId;

	public Historico(int id, double nota, int alunoId, int turmaId) {
		super();
		this.id = id;
		this.nota = nota;
		this.alunoId = alunoId;
		this.turmaId = turmaId;
	}

	public void create() {
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Aluno.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {			
			// iniciando a transação
			session.beginTransaction();
			
			// salvando o objeto
			System.out.println("Salvando o Histórico...");
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public int getAlunoId() {
		return alunoId;
	}

	public void setAlunoId(int alunoId) {
		this.alunoId = alunoId;
	}

	public int getTurmaId() {
		return turmaId;
	}

	public void setTurmaId(int turmaId) {
		this.turmaId = turmaId;
	}

	@Override
	public String toString() {
		return "Historico [id=" + id + ", nota=" + nota + ", alunoId=" + alunoId + ", turmaId=" + turmaId + "]";
	}
	
}
