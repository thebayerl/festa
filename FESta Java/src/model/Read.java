package model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Read {
	
	public String aspas(String string) {
		return "'" + string + "'";
	}
	
	public String add(String atributo, String valor) {
		
		if(valor != null || valor == "-1") {
			return  atributo + " = " + valor + " AND ";
		}
		return "";
	}
	
	public List<Aluno> Aluno(String usuarioId, String matricula, String nome, String dataNascimento, String dataIngresso, 
			String codigoCurso) {
		
		matricula = aspas(matricula);
		nome = aspas(nome);
		dataNascimento = aspas(dataNascimento);
		dataIngresso = aspas(dataIngresso);
		codigoCurso = aspas(codigoCurso);
		
		String query = "from Aluno where ";
		query += add("usuarioId",usuarioId);
		query += add("matricula",matricula);
		query += add("nome",nome);
		query += add("dataNascimento",dataNascimento);
		query += add("dataIngresso",dataIngresso);
		query += add("codigoCurso",codigoCurso);
		query = query.substring(0, query.length() - 4);
		System.out.println(query);
		
		List resultado = null;
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
	
	public List<Cordenador> Cordenador(String usuarioId, String nome) {
		
		nome = aspas(nome);
		
		String query = "from Cordenador where ";
		query += add("usuarioId",usuarioId);
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
		
		codigoCurso = aspas(codigoCurso);
		nome = aspas(nome);
		
		String query = "from Curso where ";
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
	
	public List<Disciplina> Disciplina(String id, String nome, String creditos, String departamento) {

		nome = aspas(nome);
		id = aspas(id);
		departamento = aspas(departamento);
		
		String query = "from Disciplina where ";
		query += add("id",id);
		query += add("creditos",creditos);
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
	
	public List<DisciplinaCurso> DisciplinaCurso(String codigoCurso, String disciplinaId) {
		
		codigoCurso = aspas(codigoCurso);
		disciplinaId = aspas(disciplinaId);
		
		String query = "from DisciplinaCurso where ";
		query += add("disciplinaId",disciplinaId);
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
	
	public List<Historico> Historico( String nota, String alunoId, String codigoTurma) {
		
		codigoTurma = aspas(codigoTurma);
		
		String query = "from Historico where ";
		query += add("nota",nota);
		query += add("alunoId",alunoId);
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
	
	public List<Matriculado> Matriculado(String alunoId, String codigoTurma) {
	
		String query = "from Matriculado where ";
		query += add("alunoId",alunoId);
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
	
	public List<PreRequisito> PreRequisito(String disciplinaId, String prerequisitoId) {
		
		disciplinaId = aspas(disciplinaId);
		disciplinaId = aspas(disciplinaId);
		
		String query = "from PreRequisito where ";
		query += add("disciplinaId",disciplinaId);
		query += add("prerequisitoId",prerequisitoId);
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
	
	public List<Professor> Professor(String usuarioId, String nome, String matricula, String nivelFormacao,  String codigoCurso) {
		
		nome = aspas(nome);
		matricula = aspas(matricula);
		nivelFormacao = aspas(nivelFormacao);
		codigoCurso = aspas(codigoCurso);
		
		String query = "from Professor where ";
		query += add("usuarioId",usuarioId);
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
	
	public List<ProfessorCapacidade> ProfessorCapacidade(String professorId, String disciplinaId) {
		
		disciplinaId = aspas(disciplinaId);
		String query = "from ProfessorCapacidade where ";
		query += add("disciplinaId",disciplinaId);
		query += add("professorId",professorId);
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
	
	public List<Sala> Sala( String codigoSala, String capacidade, String predio) {
		
		codigoSala = aspas(codigoSala);
		predio = aspas(predio);
		
		String query = "from Sala where ";
		query += add("codigoCurso",codigoSala);
		query += add("capacidade",capacidade);
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
	
	public List<Turma> Turma(String codigoTurma, String maxAlunos, String ano, String semestre, String professorId, String disciplinaId,
			String codigoSala) {
		
		codigoTurma = aspas(codigoTurma);
		ano = aspas(ano);
		semestre = aspas(semestre);
		codigoSala = aspas(codigoSala);
		disciplinaId = aspas(disciplinaId);
		
		
		String query = "from Turma where ";
		query += add("codigoTurma",codigoTurma);
		query += add("maxAlunos",maxAlunos);
		query += add("ano",ano);
		query += add("semestre",semestre);
		query += add("professorId",professorId);
		query += add("disciplinaId",disciplinaId);
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
	
	public List<Usuario> Usuario(String id, String username, String senha, String rg, String cpf) {
		
		username = aspas(username);
		senha = aspas(senha);
		
		String query = "from Usuario where ";
		query += add("id",id);
		query += add("username",username);
		query += add("senha",senha);
		query += add("rg",rg);
		query += add("cpf",cpf);
		query = query.substring(0, query.length() - 4);
		System.out.println(query);
		
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
