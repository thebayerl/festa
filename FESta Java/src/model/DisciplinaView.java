package model;

public class DisciplinaView {

	private int id, departamentoId;
	private String codigoDisciplina;
	private String nome, departamentoNome;
	private int creditos;

	public DisciplinaView() {}

	public DisciplinaView(String nome, int creditos,int id, int departamentoId, String codigoDisciplina, String departamentoNome) {
		super();
		this.id= id;
		this.codigoDisciplina = codigoDisciplina;
		this.nome = nome;
		this.creditos = creditos;
		this.departamentoId = departamentoId;
		this.departamentoNome = departamentoNome;
	}


	public int getId() { return this.id; }
	
	public String getcodigoDisciplina() {
		return this.codigoDisciplina;
	}

	public String getNome() {
		return this.nome;
	}

	public int getCreditos() {
		return this.creditos;
	}

	public int getDepartamentoId() {
		return this.departamentoId;
	}

	public String getDepartamentoNome() { return this.departamentoNome; }

}
