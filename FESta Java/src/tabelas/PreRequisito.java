package tabelas;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name="pre_requisito")
public class PreRequisito {

	@Column(name="disciplina_id")
	private int disciplinaId;
	
	@Column(name="prerequisito_id")
	private int prerequisitoId;
	
	public PreRequisito(int disciplinaId, int prerequisitoId) {
		super();
		this.disciplinaId = disciplinaId;
		this.prerequisitoId = prerequisitoId;
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
			System.out.println("Salvando o Pre Requisito...");
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

	public int getDisciplinaId() {
		return disciplinaId;
	}

	public void setDisciplinaId(int disciplinaId) {
		this.disciplinaId = disciplinaId;
	}

	public int getPrerequisitoId() {
		return prerequisitoId;
	}

	public void setPrerequisitoId(int prerequisitoId) {
		this.prerequisitoId = prerequisitoId;
	}
	
	@Override
	public String toString() {
		return "PreRequisito [disciplinaId=" + disciplinaId + ", prerequisitoId=" + prerequisitoId + "]";
	}
}
