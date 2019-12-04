package model;

public class PreRequisitoView {

	private int disciplinaId,  preRequisitoId;
	private String disciplinaNome, preRequisitoNome;

	public PreRequisitoView() {	}

	public PreRequisitoView(int disciplinaId, int preRequisitoId, String disciplinaNome, String preRequisitoNome) {
		this.disciplinaId = disciplinaId;
		this.preRequisitoId = preRequisitoId;
		this.disciplinaNome = disciplinaNome;
		this.preRequisitoNome = preRequisitoNome;
	}


	public int getDisciplinaId() {
		return disciplinaId;
	}

	public int getPreRequisitoId() {
		return preRequisitoId;
	}

	public String getDiscplinaNome() { return disciplinaNome; }

	public String getPreRequisitoNome() { return  preRequisitoNome; }

}
