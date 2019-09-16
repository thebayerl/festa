package tabelas;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="professor")
public class Professor {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="matricula")
	private String matricula;
	
	@Column(name="nivel_formacao")
	private String nivelFormacao;
	
	@Column(name="usuario_id")
	private int usuarioId;
	
	@Column(name="cordenador_id")
	private int cordenadorId;

	public Professor(String nome, String matricula, String nivelFormacao, int usuarioId, int cordenadorId) {
		super();
		this.nome = nome;
		this.matricula = matricula;
		this.nivelFormacao = nivelFormacao;
		this.usuarioId = usuarioId;
		this.cordenadorId = cordenadorId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNivelFormacao() {
		return nivelFormacao;
	}

	public void setNivelFormacao(String nivelFormacao) {
		this.nivelFormacao = nivelFormacao;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getCordenadorId() {
		return cordenadorId;
	}

	public void setCordenadorId(int cordenadorId) {
		this.cordenadorId = cordenadorId;
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", nome=" + nome + ", matricula=" + matricula + ", nivelFormacao="
				+ nivelFormacao + ", usuarioId=" + usuarioId + ", cordenadorId=" + cordenadorId + "]";
	}
	
}
