package com.fes.entityClasses;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pre_requisito")
public class PreRequisito {

	@Column(name="disciplina_id")
	private int disciplinaId;
	
	@Column(name="prerequisito_id")
	private int prerequisitoId;

	public int getDisciplinaId() {
		return disciplinaId;
	}

	public void setDisciplinaId(int disciplinaId) {
		this.disciplinaId = disciplinaId;
	}

	public int getPrerequisitoId() {
		return prerequisitoId;
	}

	public void setPrerequisitoId(int prerequisitoId) {
		this.prerequisitoId = prerequisitoId;
	}
	
	@Override
	public String toString() {
		return "PreRequisito [disciplinaId=" + disciplinaId + ", prerequisitoId=" + prerequisitoId + "]";
	}
}
