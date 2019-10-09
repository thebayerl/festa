package model;

public class Test {

	public static void main(String[] args) {
		
		//Usuario novoUsuario = new Usuario(2,"meu", "piru",2,5);
		//novoUsuario.create();
		//Curso novoCurso = new Curso(2,"piru");
		//novoCurso.create();
		Aluno novoAluno = new Aluno(2, "pau" ,"meu" , "1-1-1", "1-1-1", "2");
		novoAluno.create();
		System.out.println(novoAluno);
		//Professor novoProfessor = new Professor(2, "a", "b", "c", "2");
		//novoProfessor.create();
	}

}
