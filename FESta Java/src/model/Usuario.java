package model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static model.Read.factory;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="senha")
	private String senha;
	
	@Column(name="rg")
	private String rg;
	
	@Column(name="cpf")
	private String cpf;

	@Column(name="data_nascimento")
	private Date dataNascimento;

	@Column(name="tel_residencial")
	private String telResidencial;

	@Column(name="tel_celular")
	private String telCelular;

	@Column(name="email")
	private String email;

	@Column(name="role_user")
	private String role;


	public Usuario(){}

	public Usuario(String username, String senha, String rg, String cpf, String dataNascimento, String telResidencial,
				   String telCelular, String email, String role) {
		this.username = username;
		this.senha = senha;
		this.rg = rg;
		this.cpf = cpf;
		this.dataNascimento = stringToDate(dataNascimento);
		this.telResidencial = telResidencial;
		this.telCelular = telCelular;
		this.email = email;
		this.role = role;
	}

	private Date stringToDate(String data){
		Date formatData = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			formatData = format.parse ( data );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return formatData;
	}

	public void create() {
		
		// criando session
		Session session = factory.getCurrentSession();
		
		try {			
			// iniciando a transação
			session.beginTransaction();
			// salvando o objeto
			System.out.println("Salvando o Usuario...");
			session.save(this);
			
			// finalizando transação
			session.getTransaction().commit();
			
			System.out.println("Commited!");
			
		} catch(Exception exc){
		}
		finally {
			session.close();
		}
	}
	
	public void delete() {
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// começando a transação
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// deletando o objeto
			System.out.println("Deletando o Usuario...");
			session.delete(this);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Pronto!");
			
		} catch(Exception exc){
		}
		finally {
			session.close();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.dataNascimento = format.parse ( dataNascimento );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		// TODO: adicionar verificação no set
		this.role = role;
	}

	public String getTelResidencial() {
		return telResidencial;
	}

	public void setTelResidencial(String telResidencial) {
		this.telResidencial = telResidencial;
	}

	public String getTelCelular() {
		return telCelular;
	}

	public void setTelCelular(String telCelular) {
		this.telCelular = telCelular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", senha=" + senha + ", rg=" + rg + ", cpf=" + cpf + "]";
	}

}
