package com.fes.entityClasses;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="matriculado")
public class Matriculado {
	
	@Column(name="aluno_id")
	private int alunoId;
	
	@Column(name="turma_id")
	private int turmaId;

	public Matriculado(int alunoId, int turmaId) {
		super();
		this.alunoId = alunoId;
		this.turmaId = turmaId;
	}

	public int getAlunoId() {
		return alunoId;
	}

	public void setAlunoId(int alunoId) {
		this.alunoId = alunoId;
	}

	public int getTurmaId() {
		return turmaId;
	}

	public void setTurmaId(int turmaId) {
		this.turmaId = turmaId;
	}

	@Override
	public String toString() {
		return "Matriculado [alunoId=" + alunoId + ", turmaId=" + turmaId + "]";
	}
	
}
