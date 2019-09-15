package com.fes.entityClasses;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sala")
public class Sala {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="codigo_sala")
	private String codigoSala;
	
	@Column(name="capacidade")
	private int capacidade;

	public Sala(int id, String codigoSala, int capacidade) {
		super();
		this.id = id;
		this.codigoSala = codigoSala;
		this.capacidade = capacidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigoSala() {
		return codigoSala;
	}

	public void setCodigoSala(String codigoSala) {
		this.codigoSala = codigoSala;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	@Override
	public String toString() {
		return "Sala [id=" + id + ", codigoSala=" + codigoSala + ", capacidade=" + capacidade + "]";
	}
	
}
