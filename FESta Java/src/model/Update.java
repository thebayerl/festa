package model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Update {

	
	public boolean Aluno(Integer id, String matricula, String nome, String dataNascimento, String dataIngresso, 
			String codigoCurso) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Aluno.class).addAnnotatedClass(Usuario.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Aluno obj = session.get(Aluno.class, id);
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				
				if(matricula != null) {
					obj.setMatricula(matricula);
				}
				if(codigoCurso != null) {
					obj.setcodigoCurso(codigoCurso);
				}
				if(dataIngresso != null) {
					obj.setDataIngresso(dataIngresso);
				}
				if(dataNascimento != null) {
					obj.setDataNascimento(dataNascimento);
				}
				if(nome != null) {
					obj.setNome(nome);
				}
				
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		
		return sucesso;
	}
	
	public boolean Cordenador(Integer usuarioId, String nome) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cordenador.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Cordenador obj = session.get(Cordenador.class, usuarioId);
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				if(nome != null) {
					obj.setNome(nome);
				}
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		
		return sucesso;
	}

	public boolean Curso(String codigoCurso, String nome) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Curso.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Curso obj = session.get(Curso.class, codigoCurso);
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				if(nome != null) {
					obj.setnome(nome);
				}
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		return sucesso;
	}

	public boolean Disciplina(Integer id, String nome, Integer creditos, String departamentoId) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Disciplina.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Disciplina obj = session.get(Disciplina.class, id);
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				System.out.println("\nsucesso: Disciplina com id = " + id + " não existente\n");
				sucesso = false;
			}
			
			if(sucesso) {
				if(departamentoId != null) {
					obj.setDepartamento(departamentoId);
				}
				if(nome != null) {
					obj.setNome(nome);
				}
				if(creditos != null) {
					obj.setCreditos(creditos);
				}
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		return sucesso;
	}

	public boolean DisciplinaCurso(String codigoCurso, Integer disciplinaId) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(DisciplinaCurso.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();

			List<DisciplinaCurso> obj = session.createQuery("from DisciplinaCurso where codigo_curso = " + codigoCurso + "disciplinaId = " + disciplinaId).getResultList();
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				//
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		return sucesso;
	}

	public boolean Historico(Integer alunoId, String codigoTurma, Double nota) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Historico.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();

			List<Historico> obj = session.createQuery("from Historico where alunoId = " + alunoId + "codigoTurma = " + codigoTurma).getResultList();
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				// deletando o objeto
				for(Historico o : obj) {
					if(nota != null) {
						//
					}
				}
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		return sucesso;
	}

	public boolean Matriculado(Integer alunoId, String codigoTurma) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Matriculado.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();

			List<Matriculado> obj = session.createQuery("from Matriculado where alunoId = " + alunoId + "codigoTurma = " + codigoTurma).getResultList();
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				// deletando o objeto
				for(Matriculado o : obj) {
					//
				}
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		return sucesso;
	}

	public boolean PreRequisito(Integer disciplinaId, Integer prerequisitoId) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(PreRequisito.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();

			List<PreRequisito> obj = session.createQuery("from PreRequisito where disciplinaId = " + disciplinaId + "prerequisitoId = " + prerequisitoId).getResultList();
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				// deletando o objeto
				for(PreRequisito o : obj) {
					//
				}
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		return sucesso;
	}

	public boolean Professor( Integer usuarioId, String nome, String matricula, String nivelFormacao, Integer codigoCurso) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Professor.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Professor obj = session.get(Professor.class, usuarioId);
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				if(nome != null) {
					obj.setNome(nome);
				}
				if(matricula != null) {
					obj.setMatricula(matricula);
				}
				if(nivelFormacao != null) {
					obj.setNivelFormacao(nivelFormacao);
				}
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		
		return sucesso;
	}

	public boolean ProfessorCapacidade(Integer professorId, Integer disciplinaId) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ProfessorCapacidade.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();

			List<ProfessorCapacidade> obj = session.createQuery("from ProfessorCapacidade where professorId = " + professorId + "disciplinaId = " + disciplinaId).getResultList();
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				// deletando o objeto
				for(ProfessorCapacidade o : obj) {
					//
				}
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		return sucesso;
	}

	public boolean Sala(String codigoSala, Integer capacidade, Integer predio) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Sala.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Sala obj = session.get(Sala.class, codigoSala);
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				if(capacidade != null) {
					obj.setCapacidade(capacidade);
				}
				if(predio != null) {
					obj.setPredio(predio);
				}
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		
		return sucesso;
	}

	public boolean Turma(String codigoTurma, Integer maxAlunos, String ano, String semestre, Integer professorId, Integer disciplinaId,
			Integer salaId) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Turma.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Turma obj = session.get(Turma.class, codigoTurma);
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				if(ano != null) {
					obj.setAno(ano);
				}
				if(maxAlunos != null) {
					obj.setMaxAlunos(maxAlunos);
				}
				if(semestre != null) {
					obj.setSemestre(semestre);
				}
				if(salaId != null) {
					obj.setSalaId(salaId);
				}
				if(professorId != null) {
					obj.setProfessorId(professorId);
				}
				if(disciplinaId != null) {
					obj.setDisciplinaId(disciplinaId);
				}
				
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		
		return sucesso;
	}

	public boolean Usuario(Integer id, String username, String senha, Integer rg, Integer cpf) {
		boolean sucesso = true;
		
		// create session factory
		SessionFactory factory =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Usuario.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Usuario obj = session.get(Usuario.class, id);
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				sucesso = false;
			}
			
			if(sucesso) {
				if(cpf != null) {
					obj.setCpf(cpf);
				}
				if(rg != null) {
					obj.setRg(rg);
				}
				if(senha != null) {
					obj.setSenha(senha);
				}
				if(username != null) {
					obj.setUsername(username);
				}
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			factory.close();
		}
		
		
		return sucesso;
	}
	
	
}
