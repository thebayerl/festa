package com.fes.entityClasses;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="historico")
public class ProfessorCapacidade {
	
	@Column(name="professor_id")
	private int professorId;
	
	@Column(name="disciplina_id")
	private int disciplinaId;

	public ProfessorCapacidade(int professorId, int disciplinaId) {
		super();
		this.professorId = professorId;
		this.disciplinaId = disciplinaId;
	}

	public int getProfessorId() {
		return professorId;
	}

	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}

	public int getDisciplinaId() {
		return disciplinaId;
	}

	public void setDisciplinaId(int disciplinaId) {
		this.disciplinaId = disciplinaId;
	}

	@Override
	public String toString() {
		return "ProfessorCapacidade [professorId=" + professorId + ", disciplinaId=" + disciplinaId + "]";
	}

}
