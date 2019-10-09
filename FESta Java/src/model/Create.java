package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Create {
	
	public void Aluno(int usuarioId, String matricula, String nome, String dataNascimento, String dataIngresso, 
			String codigoCurso) {
		
		Aluno obj = new Aluno();
		boolean erro = false;
		obj.setUsuarioId(usuarioId);
		obj.setNome(nome);
		obj.setMatricula(matricula);
		obj.setDataNascimento(dataNascimento);
		obj.setDataIngresso(dataIngresso);
		obj.setcodigoCurso(codigoCurso);
		obj.create();
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Curso.class).addAnnotatedClass(Aluno.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			
			// testando a validade dos dados recebidos
			
			//if(session.get(Aluno.class, usuarioId) == null) {
			//	System.out.println("\nERRO: Aluno com Matricula = " + usuarioId + " já existente\n");
			//	erro = true;
			//}
			//
			//if(session.get(Curso.class, codigoCurso) == null) {
			//	System.out.println("\nERRO: Curso com codigoCurso = " + codigoCurso + " não encontrado\n");
			//	erro = true;
			//}
			
			if(!erro) {
			
				// salvando o objeto
				System.out.println("Salvando o Aluno...");
				session.save(obj);
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

	public void Cordenador(int usuarioId, String nome) {
		
		Cordenador obj = new Cordenador();
		obj.setUsuarioId(usuarioId);
		obj.setNome(nome);
		
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cordenador.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {	
			
			// iniciando a transação
			session.beginTransaction();
				
			// salvando o objeto
			System.out.println("Salvando o Cordenador...");
			session.save(obj);
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
	}

	public void Curso(String codigoCurso, String nome) {
		
		Curso obj = new Curso();
		boolean erro = false;
		obj.setnome(nome);
		obj.setcodigoCurso(codigoCurso);
		
		
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Curso.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {			
			// iniciando a transação
			session.beginTransaction();
			
			//if(session.get(Curso.class, codigoCurso) == null) {
			//	System.out.println("Curso com codigoCurso = " + codigoCurso + " já existente\n");
			//	erro = true;
			//}
			
			if(!erro) {
				
				// salvando o objeto
				System.out.println("Salvando o Curso...");
				session.save(obj);
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

	public void Disciplina(int id, String nome, int creditos, String departamentoId) {
		
		Disciplina obj = new Disciplina();
		boolean erro = false;
		obj.setId(id);
		obj.setNome(nome);
		obj.setCreditos(creditos);
		obj.setCreditos(creditos);
		obj.setDepartamento(departamentoId);
		
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Disciplina.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {			
			// iniciando a transação
			session.beginTransaction();
			
			// testando a validade dos dados recebidos
			
			//if(session.get(Disciplina.class, id) == null) {
			//	System.out.println("Disciplina com Id = " + id + " já existente\n");
			//	erro = true;
			//}
			
			//inserir check do departamento depois
			
			if(!erro) {
				
				// salvando o objeto
				System.out.println("Salvando a Disciplina...");
				session.save(obj);
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

	public void DisciplinaCurso(String codigoCurso, int disciplinaId) {
		
		DisciplinaCurso obj = new DisciplinaCurso();
		boolean erro = false;
		obj.setDisciplinaId(disciplinaId);
		obj.setCodigoCurso(codigoCurso);
		
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(DisciplinaCurso.class).addAnnotatedClass(Disciplina.class).addAnnotatedClass(Curso.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {		
			
			// iniciando a transação
			session.beginTransaction();
			
			// testando validade dos dados recebidos
			
			//if(session.get(Disciplina.class, disciplinaId) == null) {
			//	System.out.println("Disciplina com Id = " + disciplinaId + " já existente\n");
			//	erro = true;
			//}
			
			//if(session.get(Curso.class, codigoCurso) == null) {
			//	System.out.println("Curso com codigoCurso = " + codigoCurso + " já existente\n");
			//	erro = true;
			//}
			
			
			if(!erro) {
				
				// salvando o objeto
				System.out.println("Salvando a Diciplina no Curso...");
				session.save(obj);
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

	public void Historico(int alunoId, String codigoTurma, double nota) {
		
		Historico obj = new Historico();
		obj.setAlunoId(alunoId);
		obj.setNota(nota);
		obj.setcodigoTurma(codigoTurma);
		
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Aluno.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		//falta tratar os dados e trabalhar melhor na tabela de histórico
		
		try {			
			// iniciando a transação
			session.beginTransaction();
			
			// salvando o objeto
			System.out.println("Salvando o Histórico...");
			session.save(obj);
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
	}
		 
	public void Matriculado(int alunoId, String codigoTurma) {
		
		Matriculado obj = new Matriculado();
		boolean erro = false;
		obj.setAlunoId(alunoId);
		obj.setcodigoTurma(codigoTurma);
		
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Aluno.class).addAnnotatedClass(Turma.class).addAnnotatedClass(Matriculado.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		
		try {			
			// iniciando a transação
			session.beginTransaction();
			
			//tratando os dados de entrada
			
			//if(session.get(Turma.class, codigoTurma) == null) {
			//	System.out.println("Turma com codigoTurma = " + codigoTurma + " não existente\n");
			//	erro = true;
			//}
			//
			//if(session.get(Aluno.class, alunoId) == null) {
			//	System.out.println("Aluno com alunoId = " + alunoId + " não existente\n");
			//	erro = true;
			//}
			
			if(!erro) {
				
				// salvando o objeto
				System.out.println("Salvando o Matriculado...");
				session.save(obj);
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

	public void PreRequisito(int disciplinaId, int prerequisitoId) {
		
		PreRequisito obj = new PreRequisito();
		boolean erro = false;
		obj.setPrerequisitoId(prerequisitoId);
		obj.setDisciplinaId(disciplinaId);
		
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(PreRequisito.class).addAnnotatedClass(Disciplina.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {			
			// iniciando a transação
			session.beginTransaction();
			
			//if(session.get(Disciplina.class, disciplinaId) == null) {
			//	System.out.println("\nERRO: Disciplina com disciplinaId = " + disciplinaId + " já existente\n");
			//	erro = true;
			//}
			
			//if(session.get(Disciplina.class, disciplinaId) == null) {
			//	System.out.println("\nERRO: Disciplina com prerequisitoId = " + prerequisitoId + " já existente\n");
			//	erro = true;
			//}
			
			if(!erro) {
				
				// salvando o objeto
				System.out.println("Salvando o Pre Requisito...");
				session.save(obj);
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
	
	public void Professor( int usuarioId, String nome, String matricula, String nivelFormacao, int codigoCurso) {
		
		Professor obj = new Professor();
		boolean erro = false;
		obj.setUsuarioId(usuarioId);
		obj.setNome(nome);
		obj.setNivelFormacao(nivelFormacao);
		obj.setMatricula(matricula);
		obj.setcodigoCurso(codigoCurso);
		
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Professor.class).addAnnotatedClass(Cordenador.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {			
			// iniciando a transação
			session.beginTransaction();
			
			if(session.get(Cordenador.class, codigoCurso) == null) {
				System.out.println("\nERRO: Aluno com codigoCurso = " + codigoCurso + " já existente\n");
				erro = true;
			}
			
			if(!erro) {
				
				// salvando o objeto
				System.out.println("Salvando o Professor...");
				session.save(obj);
			};
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
	}
	
	public void ProfessorCapacidade(int professorId, int disciplinaId) {
		
		ProfessorCapacidade obj = new ProfessorCapacidade();
		boolean erro = false;
		obj.setProfessorId(professorId);
		obj.setDisciplinaId(disciplinaId);
		
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Aluno.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {
			// iniciando a transação
			session.beginTransaction();
			
			// Checando integridade dos dados
			//if(session.get(Professor.class, professorId) == null) {
			//	System.out.println("\nERRO: Professor não encontrado!");
			//	erro = true;
			//}
			//if(session.get(Disciplina.class, disciplinaId) == null) {
			//	System.out.println("\nERRO: Disciplina não encontrada!");
			//	erro = true;
			//}
			
			if(!erro) {

				// salvando o objeto
				System.out.println("Salvando o Professor Capacidade...");
				session.save(obj);
				
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
	
	public void Sala(String codigoSala, int capacidade, int predio) {
		
		Sala obj = new Sala();
		boolean erro = false;
		obj.setCodigoSala(codigoSala);
		obj.setCapacidade(capacidade);
		obj.setPredio(predio);
		
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Sala.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {			
			// iniciando a transação
			session.beginTransaction();
			
			//if(session.get(Sala.class, codigoSala) == null) {
			//	System.out.println("\nERRO: Sala com codigoSala = " + codigoSala + " já existente\n");
			//	erro = true;
			//}
			
			if(!erro) {
				
				// salvando o objeto
				System.out.println("Salvando a Sala...");
				session.save(obj);
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
	
	public void Turma(String codigoTurma, int maxAlunos, String ano, String semestre, int professorId, int disciplinaId,
			int salaId) {
		
		Turma obj = new Turma();
		boolean erro = false;
		obj.setSemestre(semestre);
		obj.setSalaId(salaId);
		obj.setProfessorId(professorId);
		obj.setMaxAlunos(maxAlunos);
		obj.setDisciplinaId(disciplinaId);
		obj.setCodigoTurma(codigoTurma);
		obj.setAno(ano);
		
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Turma.class).addAnnotatedClass(Professor.class).addAnnotatedClass(Disciplina.class).addAnnotatedClass(Sala.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {			
			// iniciando a transação
			session.beginTransaction();
			
			// testando a validade dos dados recebidos
			
			//if(session.get(Turma.class, codigoTurma) == null) {
			//	System.out.println("Turma com codigoTurma = " + codigoTurma + " já existente\n");
			//	erro = true;
			//}
			
			//if(session.get(Professor.class, professorId) == null) {
			//	System.out.println("Professor com professorId = " + professorId + " não existente\n");
			//	erro = true;
			//}
			
			//if(session.get(Disciplina.class, disciplinaId) == null) {
			//	System.out.println("Disciplina com disciplinaId = " + disciplinaId + " não existente\n");
			//	erro = true;
			//}
			
			//if(session.get(Sala.class, salaId) == null) {
			//	System.out.println("Sala com salaId = " + salaId + " não existente\n");
			//	erro = true;
			//}
			
			if(!erro) {
				
				// salvando o objeto
				System.out.println("Salvando a Turma...");
				session.save(obj);
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
	
	public void Usuario(int id, String username, String senha, int rg, int cpf) {
		
		Usuario obj = new Usuario();
		boolean erro = false;
		obj.setId(id);
		obj.setUsername(username);
		obj.setSenha(senha);
		obj.setRg(rg);
		obj.setCpf(cpf);
		
		// criando session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Usuario.class).buildSessionFactory();
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {			
			// iniciando a transação
			session.beginTransaction();
			
			// testando a validade dos dados recebidos
			
			//if(session.get(Usuario.class, id) == null) {
			//	System.out.println("Usuario com Id = " + id + " já existente\n");
			//	erro = true;
			//}
			
			if(!erro) {
				
				// salvando o objeto
				System.out.println("Salvando o Usuario...");
				session.save(obj);
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Commited!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
			
	}
}
