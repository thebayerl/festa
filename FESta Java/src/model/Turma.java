package tabelas;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name="turma")
public class Turma {
	
	@Id
	@Column(name="codigo_turma")
	private String codigoTurma;
	
	@Column(name="max_alunos")
	private int maxAlunos;
	
	@Column(name="ano")
	private String ano;
	
	@Column(name="semestre")
	private String semestre;
	
	@Column(name="professor_id")
	private int professorId;
	
	@Column(name="disciplina_id")
	private int disciplinaId;
	
	@Column(name="sala_id")
	private int salaId;

	public Turma(String codigoTurma, int maxAlunos, String ano, String semestre, int professorId, int disciplinaId,
			int salaId) {
		super();
		this.codigoTurma = codigoTurma;
		this.maxAlunos = maxAlunos;
		this.ano = ano;
		this.semestre = semestre;
		this.professorId = professorId;
		this.disciplinaId = disciplinaId;
		this.salaId = salaId;
	}
	
	public void create() {
		boolean erro = false;
		
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Turma.class).addAnnotatedClass(Professor.class).addAnnotatedClass(Disciplina.class).addAnnotatedClass(Sala.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {			
			// iniciando a transação
			session.beginTransaction();
			
			// testando a validade dos dados recebidos
			
			if(session.get(Turma.class, codigoTurma) == null) {
				System.out.println("Turma com codigoTurma = " + codigoTurma + " já existente\n");
				erro = true;
			}
			
			if(session.get(Professor.class, professorId) == null) {
				System.out.println("Professor com professorId = " + professorId + " não existente\n");
				erro = true;
			}
			
			if(session.get(Disciplina.class, disciplinaId) == null) {
				System.out.println("Disciplina com disciplinaId = " + disciplinaId + " não existente\n");
				erro = true;
			}
			
			if(session.get(Sala.class, salaId) == null) {
				System.out.println("Sala com salaId = " + salaId + " não existente\n");
				erro = true;
			}
			
			if(!erro) {
				
				// salvando o objeto
				System.out.println("Salvando a Turma...");
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
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Turma.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// deletando o objeto
			System.out.println("Deletando a Turma...");
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

	public String getCodigoTurma() {
		return codigoTurma;
	}

	public void setCodigoTurma(String codigoTurma) {
		this.codigoTurma = codigoTurma;
	}

	public int getMaxAlunos() {
		return maxAlunos;
	}

	public void setMaxAlunos(int maxAlunos) {
		this.maxAlunos = maxAlunos;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public int getProfessorId() {
		return professorId;
	}

	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}

	public int getDisciplinaId() {
		return disciplinaId;
	}

	public void setDisciplinaId(int disciplinaId) {
		this.disciplinaId = disciplinaId;
	}

	public int getSalaId() {
		return salaId;
	}

	public void setSalaId(int salaId) {
		this.salaId = salaId;
	}

	@Override
	public String toString() {
		return "Turma [codigoTurma=" + codigoTurma + ", maxAlunos=" + maxAlunos + ", ano=" + ano
				+ ", semestre=" + semestre + ", professorId=" + professorId + ", disciplinaId=" + disciplinaId
				+ ", salaId=" + salaId + "]";
	}
	
}
