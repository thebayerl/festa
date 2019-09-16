package tabelas;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="turma")
public class Turma {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="codigo_turma")
	private String codigoTurma;
	
	@Column(name="max_alunos")
	private int maxAlunos;
	
	@Column(name="ano")
	private String ano;
	
	@Column(name="semestre")
	private String semestre;
	
	@Column(name="professor_id")
	private int professorId;
	
	@Column(name="disciplina_id")
	private int disciplinaId;
	
	@Column(name="sala_id")
	private int salaId;

	public Turma(String codigoTurma, int maxAlunos, String ano, String semestre, int professorId, int disciplinaId,
			int salaId) {
		super();
		this.codigoTurma = codigoTurma;
		this.maxAlunos = maxAlunos;
		this.ano = ano;
		this.semestre = semestre;
		this.professorId = professorId;
		this.disciplinaId = disciplinaId;
		this.salaId = salaId;
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

	public int getMaxAlunos() {
		return maxAlunos;
	}

	public void setMaxAlunos(int maxAlunos) {
		this.maxAlunos = maxAlunos;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
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

	public int getSalaId() {
		return salaId;
	}

	public void setSalaId(int salaId) {
		this.salaId = salaId;
	}

	@Override
	public String toString() {
		return "Turma [id=" + id + ", codigoTurma=" + codigoTurma + ", maxAlunos=" + maxAlunos + ", ano=" + ano
				+ ", semestre=" + semestre + ", professorId=" + professorId + ", disciplinaId=" + disciplinaId
				+ ", salaId=" + salaId + "]";
	}
	
}
