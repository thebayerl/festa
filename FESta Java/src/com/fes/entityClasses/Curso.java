package com.fes.entityClasses;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="curso")
public class Curso {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="codigo_turma")
	private String codigoTurma;

	public Curso(int id, String codigoTurma) {
		this.id = id;
		this.codigoTurma = codigoTurma;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigoTurma() {
		return codigoTurma;
	}

	public void setCodigoTurma(String codigoTurma) {
		this.codigoTurma = codigoTurma;
	}
	
	@Override
	public String toString() {
		return "Curso [id=" + id + ", codigoTurma=" + codigoTurma + "]";
	}
	
}
