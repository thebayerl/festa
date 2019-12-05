package model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Read {
	public static final SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	public static String aspas(String string) {
		return "'" + string + "'";
	}
	
	public static String add(String atributo, String valor) {
		
		
		if(valor != null && valor.compareTo("'null'") != 0) {
			return  atributo + " = " + valor + " AND ";
		}
		return "";
	}

	public static <T> List<T> Query(String query){
		List<T> listObj = null;
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			listObj = session.createQuery(query).getResultList();

		}finally {
			session.close();
		}
		return listObj;
	}
	
	public static List<Aluno> getAluno(String usuarioId, String matricula, String nome, String dataNascimento, String dataIngresso, 
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
		
		List<Aluno> resultado = null;

		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			session.close();
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

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	
		return resultado;
		
	}
	
	public static List<Curso> getCurso(String cursoId, String codigoCurso, String nome, String departamentoId) {
		
		codigoCurso = aspas(codigoCurso);
		nome = aspas(nome);
		
		String query = "from Curso where ";
		query += add("id",cursoId);
		query += add("codigoCurso",codigoCurso);
		query += add("nome",nome);
		query += add("departamentoId",departamentoId);
		query = query.substring(0, query.length() - 4);

		List<Curso> resultado = null;


		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	
		return resultado;
	}
	
	public static List<Curso> getCurso() {
		
		String query = "from Curso ";

		List<Curso> resultado = null;

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	
		return resultado;
	}
	
	public static List<Disciplina> getDisciplina(String id, String nome, String creditos, String departamentoId) {

		String query = "from Disciplina where ";
		
		if(id!= null ) {
		
			nome = aspas(nome);
			id = aspas(id);
			
			query += add("id",id);
			query += add("creditos",creditos);
			query += add("nome", nome);
			query += add("departamento_id", departamentoId);
			query = query.substring(0, query.length() - 4);
		} else {
			query = query.substring(0, query.length() - 6);
		}
		List<Disciplina> resultado = null;

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	
		return resultado;
	}
	
	public static List<Departamento> getDepartamento(Integer id) {
		
		String query = "from Departamento where id = " + id.toString();
		
		List<Departamento> resultado = null;

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	
		return resultado;
	}
			
	public static List<Professor> getProfessor() {
		
		String query = "from Professor ";
		
		List<Professor> resultado = null;

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	
		return resultado;
	}
	
	public static List<DisciplinaCurso> getDisciplinaCurso(String cursoId, String disciplinaId) {
		
//		cursoId = aspas(cursoId);
//		disciplinaId = aspas(disciplinaId);
		
		String query = "from DisciplinaCurso where ";
		query += add("disciplinaId",disciplinaId);
		query += add("cursoId",cursoId);
		query = query.substring(0, query.length() - 4);
		
		List<DisciplinaCurso> resultado = null;

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	
		return resultado;
	}
	
	public List<Historico> Historico( String nota, String alunoId, String turmaId) {
		
		//codigoTurma = aspas(codigoTurma);
		
		String query = "from Historico where ";
		query += add("nota",nota);
		query += add("alunoId",alunoId);
		query += add("turmaId",turmaId);
		query = query.substring(0, query.length() - 4);
			
		List<Historico> resultado = null;

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	
		return resultado;
	}
	
	public static List<Matriculado> getMatriculado(String alunoId, String turmaId) {
	
		String query = "from Matriculado where ";
		query += add("alunoId",alunoId);
		query += add("turmaId",turmaId);
		query = query.substring(0, query.length() - 4);
	
		List<Matriculado> resultado = null;

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	
		return resultado;
	}
	
	public List<PreRequisito> PreRequisito(String disciplinaId, String prerequisitoId) {

		String query = "from PreRequisito where ";
		query += add("disciplinaId",disciplinaId);
		query += add("prerequisitoId",prerequisitoId);
		query = query.substring(0, query.length() - 4);
		
		List<PreRequisito> resultado = null;

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	
		return resultado;
	
	}
	
	public static List<Professor> getProfessor(String usuarioId, String nome, String matricula, String nivelFormacao,  String cursoId) {
		
		nome = aspas(nome);
		matricula = aspas(matricula);
		nivelFormacao = aspas(nivelFormacao);
		
		String query = "from Professor where ";
		query += add("usuarioId",usuarioId);
		query += add("matricula",matricula);
		query += add("nome",nome);
		query += add("nivelFormacao",nivelFormacao);
		query += add("cursoId",cursoId);
		query = query.substring(0, query.length() - 4);
		
		List<Professor> resultado = null;

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	
		return resultado;
	}
	
	public static List<ProfessorCapacidade> getProfessorCapacidade(String professorId, String disciplinaId) {

		String query = "from ProfessorCapacidade where ";
		query += add("disciplinaId",disciplinaId);
		query += add("professorId",professorId);
		query = query.substring(0, query.length() - 4);
		
		List<ProfessorCapacidade> resultado = null;

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	
		return resultado;
		
	}
	
	public static List<Sala> getSala( String codigoSala, String capacidade, String predio) {
		
		String query = "from Sala where ";
		System.out.println("PREDIO: " + predio);
		
		if(codigoSala != null || capacidade != null || predio != null) {
			

			codigoSala = aspas(codigoSala);
			predio = aspas(predio);
			query += add("codigoSala",codigoSala);
			query += add("capacidade",capacidade);
			query += add("predio",predio);
			query = query.substring(0, query.length() - 4);
			
		} else {
			query = query.substring(0, query.length() - 6);
		}
		
		System.out.println(query);
		List<Sala> resultado = null;

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	
		return resultado;
		
	}
	
	// pegas as turmas de acordo com os par�metros
	public static List<Turma> getTurma(String turmaId, String codigoTurma, String maxAlunos, String ano, String semestre, String professorId, String disciplinaId,
									   String codigoSala) {
		
		codigoTurma = aspas(codigoTurma);
		ano = aspas(ano);
		semestre = aspas(semestre);
		codigoSala = aspas(codigoSala);
		disciplinaId = aspas(disciplinaId);
		
		
		String query = "from Turma where ";
		query += add("id",turmaId);
		query += add("codigoTurma",codigoTurma);
		query += add("maxAlunos",maxAlunos);
		query += add("ano",ano);
		query += add("semestre",semestre);
		query += add("professorId",professorId);
		query += add("disciplinaId",disciplinaId);
		query += add("codigoSala",codigoSala);
		query = query.substring(0, query.length() - 4);
		
		List<Turma> resultado = null;

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	
		return resultado;
		
	}
	
	// pega todas as tumas
	public static List<Turma> getTurma() {		
		return getTurma(null, null, null, null, null, null, null, null);
	}
	
	public static List<Usuario> getUsuario(String id, String username, String senha, String rg, String cpf) {
		
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

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	
		return resultado;
		
	}
	
	public static List<String> getDistinctPredio() {
		
		
		String query = "select distinct predio from Sala";
		
		List<String> resultado = null;

		Session session = factory.getCurrentSession();
		
		try {		
			session.beginTransaction();
			resultado = session.createQuery(query).getResultList();
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	
		return resultado;
		
	}

	
}
