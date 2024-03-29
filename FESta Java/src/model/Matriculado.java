package tabelas;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name="matriculado")
public class Matriculado {
	
	@Column(name="aluno_id")
	private int alunoId;
	
	@Column(name="turma_id")
	private int turmaId;

	public Matriculado(int alunoId, int turmaId) {
		super();
		this.alunoId = alunoId;
		this.turmaId = turmaId;
	}
	
	public void create() {
		boolean erro = false;
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Aluno.class).addAnnotatedClass(Turma.class).addAnnotatedClass(Matriculado.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		
		try {			
			// iniciando a transa��o
			session.beginTransaction();
			
			//tratando os dados de entrada
			
			if(session.get(Turma.class, turmaId) == null) {
				System.out.println("Turma com turmaId = " + turmaId + " n�o existente\n");
				erro = true;
			}
			
			if(session.get(Aluno.class, alunoId) == null) {
				System.out.println("Aluno com alunoId = " + alunoId + " n�o existente\n");
				erro = true;
			}
			
			if(!erro) {
				
				// salvando o objeto
				System.out.println("Salvando o Matriculado...");
				session.save(this);
			}
			
			// finalizando transa��o
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
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
		return "Matriculado [alunoId=" + alunoId + ", turmaId=" + turmaId + "]";
	}
	
}
