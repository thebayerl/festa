package tabelas;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Read {
	
	public String add(String atributo, String valor) {
		
		if(valor != null) {
			return  atributo + " = " + valor + " AND ";
		}
		return "";
	}
	
	public List<Aluno> Aluno(int usuarioId, String matricula, String nome, String dataNascimento, String dataIngresso, 
			String codigoCurso) {
		
		String query = "select * from Aluno where ";
		query += add("usuarioId",Integer.toString(usuarioId));
		query += add("matricula",matricula);
		query += add("nome",nome);
		query += add("dataNascimento",dataNascimento);
		query += add("dataIngresso",dataIngresso);
		query += add("codigoCurso",codigoCurso);
		query = query.substring(0, query.length() - 4);
		
		List<Aluno> resultado = null;
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Aluno.class).buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	
		return resultado;
	}
	
	public List<Cordenador> Cordenador(String nome, int usuarioId) {
		
		String query = "select * from Cordenador where ";
		query += add("usuarioId",Integer.toString(usuarioId));
		query += add("nome",nome);
		query = query.substring(0, query.length() - 4);
		
		List<Cordenador> resultado = null;
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cordenador.class).buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	
		return resultado;
		
	}
	
	public List<Curso> Curso(String codigoCurso, String nome) {
		
		String query = "select * from Curso where ";
		query += add("codigoCurso",codigoCurso);
		query += add("nome",nome);
		query = query.substring(0, query.length() - 4);

		List<Curso> resultado = null;
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Curso.class).buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	
		return resultado;
	}
	
	public List<Disciplina> Disciplina(int id, String nome, int creditos, String departamento) {

		String query = "select * from Disciplina where ";
		query += add("id",Integer.toString(id));
		query += add("creditos",Integer.toString(creditos));
		query += add("nome", nome);
		query += add("departamento", departamento);
		query = query.substring(0, query.length() - 4);
		
		List<Disciplina> resultado = null;
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Disciplina.class).buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	
		return resultado;
	}
	
	public List<DisciplinaCurso> DisciplinaCurso(String codigoCurso, int disciplinaId) {
		
		String query = "select * from Aluno where ";
		query += add("disciplinaId",Integer.toString(disciplinaId));
		query += add("codigoCurso",codigoCurso);
		query = query.substring(0, query.length() - 4);
		
		List<DisciplinaCurso> resultado = null;
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(DisciplinaCurso.class).buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	
		return resultado;
	}
	
	public List<Historico> Historico(int id, double nota, int alunoId, String codigoTurma) {
		
		String query = "select * from Aluno where ";
		query += add("id",Integer.toString(id));
		query += add("nota",Double.toString(nota));
		query += add("alunoId",Integer.toString(alunoId));
		query += add("codigoTurma",codigoTurma);
		query = query.substring(0, query.length() - 4);
			
		List<Historico> resultado = null;
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Historico.class).buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	
		return resultado;
	}
	
	public List<Matriculado> Matriculado(int alunoId, String codigoTurma) {
	
		String query = "select * from Aluno where ";
		query += add("alunoId",Integer.toString(alunoId));
		query += add("codigoTurma",codigoTurma);
		query = query.substring(0, query.length() - 4);
	
		List<Matriculado> resultado = null;
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Matriculado.class).buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	
		return resultado;
	}
	
	public List<PreRequisito> PreRequisito(int disciplinaId, int prerequisitoId) {
		
		String query = "select * from Aluno where ";
		query += add("disciplinaId",Integer.toString(disciplinaId));
		query += add("prerequisitoId",Integer.toString(prerequisitoId));
		query = query.substring(0, query.length() - 4);
		
		List<PreRequisito> resultado = null;
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(PreRequisito.class).buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	
		return resultado;
	
	}
	
	public List<Professor> Professor(int usuarioId, String nome, String matricula, String nivelFormacao,  String codigoCurso) {
		
		String query = "select * from Aluno where ";
		query += add("usuarioId",Integer.toString(usuarioId));
		query += add("matricula",matricula);
		query += add("nome",nome);
		query += add("nivelFormacao",nivelFormacao);
		query += add("codigoCurso",codigoCurso);
		query = query.substring(0, query.length() - 4);
		
		List<Professor> resultado = null;
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Professor.class).buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	
		return resultado;
	}
	
	public List<ProfessorCapacidade> ProfessorCapacidade(int professorId, int disciplinaId) {
		
		String query = "select * from Aluno where ";
		query += add("disciplinaId",Integer.toString(disciplinaId));
		query += add("professorId",Integer.toString(professorId));
		query = query.substring(0, query.length() - 4);
		
		List<ProfessorCapacidade> resultado = null;
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ProfessorCapacidade.class).buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	
		return resultado;
		
	}
	
	public List<Sala> Sala( String codigoSala, int capacidade, String predio) {
		
		String query = "select * from Aluno where ";
		query += add("codigoCurso",codigoSala);
		query += add("capacidade",Integer.toString(capacidade));
		query += add("predio",predio);
		query = query.substring(0, query.length() - 4);
		
		List<Sala> resultado = null;
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Sala.class).buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	
		return resultado;
		
	}
	
	public List<Turma> Turma(String codigoTurma, int maxAlunos, String ano, String semestre, int professorId, int disciplinaId,
			String codigoSala) {
		
		String query = "select * from Aluno where ";
		query += add("codigoTurma",codigoTurma);
		query += add("maxAlunos",Integer.toString(maxAlunos));
		query += add("ano",ano);
		query += add("semestre",semestre);
		query += add("professorId",Integer.toString(professorId));
		query += add("disciplinaId",Integer.toString(disciplinaId));
		query += add("codigoSala",codigoSala);
		query = query.substring(0, query.length() - 4);
		
		List<Turma> resultado = null;
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Turma.class).buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	
		return resultado;
		
	}
	
	public List<Usuario> Usuario(int id, String username, String senha, int rg, int cpf) {
		
		String query = "select * from Aluno where ";
		query += add("disciplinaId",Integer.toString(id));
		query += add("username",username);
		query += add("senha",senha);
		query += add("rg",Integer.toString(rg));
		query += add("cpf",Integer.toString(cpf));
		query = query.substring(0, query.length() - 4);
		
		List<Usuario> resultado = null;
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Usuario.class).buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	
		return resultado;
		
	}

	
}
