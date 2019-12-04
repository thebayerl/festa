package model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static model.Read.factory;

public class Update {
	public static boolean Aluno(Integer id, String matricula, String nome, String dataIngresso,
			Integer cursoId) {
		boolean sucesso = true;
		
		// create session factory
		Session session = factory.getCurrentSession();
		
		try {
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
				if(cursoId != null) {
					obj.setCursoId(cursoId);
				}
				if(dataIngresso != null) {
					obj.setDataIngresso(dataIngresso);
				}
				if(nome != null) {
					obj.setNome(nome);
				}
				
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		}
		finally {
			session.close();
		}
		
		
		return sucesso;
	}
	
	public boolean Cordenador(Integer usuarioId, String nome) {
		boolean sucesso = true;

		Session session = factory.getCurrentSession();
		
		try {
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
			
		}
		finally {
			session.close();
		}
		
		
		return sucesso;
	}

	public boolean Curso(String codigoCurso, String nome, Integer departamentoId) {
		boolean sucesso = true;

		Session session = factory.getCurrentSession();
		
		try {
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
				if(departamentoId != null) {
					obj.setdepartamentoId(departamentoId);
				}
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		}
		finally {
			session.close();
		}
		
		return sucesso;
	}

	public static boolean Disciplina(Integer id, String codigoDisciplina , String nome, Integer creditos, Integer departamentoId) {
		boolean sucesso = true;

		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			Disciplina obj = session.get(Disciplina.class, id);
			
			// testando a validade dos dados recebidos
			if(obj == null) {
				System.out.println("\nsucesso: Disciplina com id = " + id + " não existente\n");
				sucesso = false;
			}
			
			if(sucesso) {
				if(departamentoId != null) {
					obj.setDepartamentoId(departamentoId);
				}
				if(nome != null) {
					obj.setNome(nome);
				}
				if(creditos != null) {
					obj.setCreditos(creditos);
				}
				if(codigoDisciplina != null){
					obj.setcodigoDisciplina(codigoDisciplina);
				}
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		}
		finally {
			session.close();
		}
		
		return sucesso;
	}

	public boolean DisciplinaCurso(String codigoCurso, String disciplinaId) {
		boolean sucesso = true;

		Session session = factory.getCurrentSession();
		
		try {
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
			session.close();
		}
		
		return sucesso;
	}

	public boolean Historico(Integer alunoId, String codigoTurma, Double nota) {
		boolean sucesso = true;

		Session session = factory.getCurrentSession();
		
		try {
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
			
		}
		finally {
			session.close();
		}
		
		return sucesso;
	}

	public boolean Matriculado(Integer alunoId, Integer turmaId) {
		boolean sucesso = true;

		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();

			List<Matriculado> obj = session.createQuery("from Matriculado where alunoId = " + alunoId + "codigoTurma = " + turmaId).getResultList();
			
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
			
		}
		finally {
			session.close();
		}
		
		return sucesso;
	}

	public boolean PreRequisito(String disciplinaId, String prerequisitoId) {
		boolean sucesso = true;

		Session session = factory.getCurrentSession();
		
		try {
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
			
		}
		finally {
			session.close();
		}
		
		return sucesso;
	}

	public static boolean Professor(Integer usuarioId, String nome, String nivelFormacao, Integer cursoId) {
		boolean sucesso = true;

		Session session = factory.getCurrentSession();
		
		try {
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
				if(nivelFormacao != null) {
					obj.setNivelFormacao(nivelFormacao);
				}
				if(cursoId != null) {
					obj.setcursoId(cursoId);
				}
			}
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		}
		finally {
			session.close();
		}
		
		
		return sucesso;
	}

	public boolean ProfessorCapacidade(Integer professorId, String disciplinaId) {
		boolean sucesso = true;

		Session session = factory.getCurrentSession();
		
		try {
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
			
		}
		finally {
			session.close();
		}
		
		return sucesso;
	}

	public boolean Sala(String codigoSala, Integer capacidade, String predio) {
		boolean sucesso = true;

		Session session = factory.getCurrentSession();
		
		try {
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
			session.close();
		}
		
		
		return sucesso;
	}

	public static boolean Turma(Integer turmaId, Integer maxAlunos, String ano, String semestre, Integer professorId, Integer disciplinaId,
			Integer salaId) {
		boolean sucesso = true;

		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			Turma obj = session.get(Turma.class, turmaId);
			
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
			
		}
		finally {session.close();}

		return sucesso;
	}

	public static boolean Usuario(Integer id, String username, String senha, String rg, String cpf,
			                      String email, String telCel, String telRes, String dataNascimento) {
		boolean sucesso = true;

		Session session = factory.getCurrentSession();;
		
		try {
			session.beginTransaction();

			Usuario obj = session.get(Usuario.class, id);

			// testando a validade dos dados recebidos
			if (obj == null) {
				sucesso = false;
			}

			if (sucesso) {
				if (cpf != null) {
					obj.setCpf(cpf);
				}
				if (rg != null) {
					obj.setRg(rg);
				}
				if (senha != null) {
					obj.setSenha(senha);
				}
				if (telCel != null) {
					obj.setTelCelular(telCel);
				}
				if (telRes != null) {
					obj.setTelResidencial(telRes);
				}
				if (email != null) {
					obj.setEmail(email);
				}
				if (username != null) {
					obj.setUsername(username);
				}
				if (dataNascimento != null) {
					obj.setDataNascimento(dataNascimento);
				}

			}

			// finalizando transação
			session.getTransaction().commit();

			System.out.println("Pronto!");
		}
		finally {
			session.close();
		}
		
		return sucesso;
	}
	
	
}
