package tabelas;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cordenador")
public class Cordenador {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="usuario_id")
	private int usuarioId;

	public Cordenador(String nome, int usuarioId) {
		this.nome = nome;
		this.usuarioId = usuarioId;
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

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	@Override
	public String toString() {
		return "Cordenador [id=" + id + ", nome=" + nome + ", usuarioId=" + usuarioId + "]";
	}
	
}
