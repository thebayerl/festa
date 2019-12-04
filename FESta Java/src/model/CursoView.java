package model;

public class CursoView {

	private int id, departamentoId;
	private String codigoCurso;
	private String nome, departamentoNome;


	public CursoView(int id, String codigoCurso, String nome, int departamentoId, String departamentoNome) {
		super();
		this.id = id;
		this.codigoCurso = codigoCurso;
		this.nome = nome;
		this.departamentoId = departamentoId;
		this.departamentoNome = departamentoNome;
	}


	public int getId() {
		return this.id;
	}

	public String getCodigoCurso() {
		return codigoCurso;
	}

	public String getNome() {
		return this.nome;
	}

	public int getDepartamentoId() {
		return this.departamentoId;
	}

	public String getDepartamentoNome() { return this.departamentoNome; }

}
