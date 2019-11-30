/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Aluno;
import model.Curso;
import model.Read;
import model.Usuario;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import view.CadastrarAluno;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class CadastrarAlunoController implements Initializable {

	public class UsuarioAluno{
		private Integer id, cursoId;
		private String  nome, cursoNome, email, telCel, telRes, cpf, rg, dataIngresso, dataNascimento;

		public UsuarioAluno(Integer id, Integer cursoId, String nome, String cursoNome, String email, String telCel,
							String telRes, String cpf, String rg, String dataIngresso, String dataNascimento) {
			this.id = id;
			this.cursoId = cursoId;
			this.nome = nome;
			this.cursoNome = cursoNome;
			this.email = email;
			this.telCel = telCel;
			this.telRes = telRes;
			this.cpf = cpf;
			this.rg = rg;
			this.dataIngresso = dataIngresso;
			this.dataNascimento = dataNascimento;
		}

		public Integer getId() {
			return id;
		}

		public Integer getCursoId() {
			return cursoId;
		}

		public String getNome() {
			return nome;
		}

		public String getCursoNome() {
			return cursoNome;
		}

		public String getEmail() {
			return email;
		}

		public String getTelCel() {
			return telCel;
		}

		public String getTelRes() {
			return telRes;
		}

		public String getCpf() {
			return cpf;
		}

		public String getRg() {
			return rg;
		}

		public String getDataIngresso() {
			return dataIngresso;
		}

		public String getDataNascimento() {
			return dataNascimento;
		}
	}

	@FXML private TextField txUserName;
	@FXML private PasswordField psSenha;
	@FXML private PasswordField psSenhaConf;
	@FXML private TextField txNome;
	@FXML private TextField txRG;
	@FXML private TextField txCPF;
	@FXML private TextField txTelResidencial;
	@FXML private TextField txTelCelular;
	@FXML private TextField txEmail;
	@FXML private DatePicker dtNascimento;
	@FXML private DatePicker dtIngresso;
	@FXML private ComboBox<Curso> comboBoxCurso;
	@FXML private Button btCadastrar;
	@FXML private Button btAlterar;
	@FXML private Button btRemover;
	@FXML private TableView<UsuarioAluno> tableAlunos;
	@FXML private TableColumn<UsuarioAluno, Integer> columnId;
	@FXML private TableColumn<UsuarioAluno, String> columnNome;
	@FXML private TableColumn<UsuarioAluno, String> columnCurso;
	@FXML private TableColumn<UsuarioAluno, String> columnEmail;
	@FXML private TableColumn<UsuarioAluno, String> columnTelCel;
	@FXML private TableColumn<UsuarioAluno, String> columnCpf;

	private TextFieldFormatter tffCpf = new TextFieldFormatter();
	private	TextFieldFormatter tffRg = new TextFieldFormatter();
	private	TextFieldFormatter tffTelRes = new TextFieldFormatter();
	private	TextFieldFormatter tffTelCel = new TextFieldFormatter();

	private List<Curso> listCursos = new ArrayList<>();
	private ObservableList<Curso> obsCursos;
	private List<UsuarioAluno> listUsuariosAluno = new ArrayList<>();
	private ObservableList<UsuarioAluno> obsListUsuariosAluno;

	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Usuario.class).buildSessionFactory();
	    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarCursos();
		carregarTableAlunos();

		tffCpf.setMask("###.###.###-##");
		tffCpf.setCaracteresValidos("0123456789");
		tffRg.setMask("##.###.###-#");
		tffRg.setCaracteresValidos("0123456789");
		tffTelRes.setMask("(##) ####-####");
		tffTelRes.setCaracteresValidos("0123456789");
		tffTelCel.setMask("(##) #####-####");
		tffTelCel.setCaracteresValidos("0123456789");

        btCadastrar.setOnMouseClicked((MouseEvent e)->{
        	cadastraAluno();
        });
    }
    
    public void carregarCursos() {
    	listCursos.clear();
    	comboBoxCurso.getItems().clear();

		Session session = factory.getCurrentSession();
		session.beginTransaction();
		listCursos = session.createQuery("from Curso").getResultList();
		session.close();

    	if(obsCursos != null) {
    		obsCursos.clear();
    	}
    	obsCursos = FXCollections.observableArrayList(listCursos);
    	comboBoxCurso.setItems(obsCursos);
    }

    public void carregarTableAlunos(){
    	columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnCurso.setCellValueFactory(new PropertyValueFactory<>("cursoNome"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		columnTelCel.setCellValueFactory(new PropertyValueFactory<>("telCel"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<Object> objs = session.createQuery("select u.id, c.id, a.nome, c.nome, u.email, u.telCelular, u.telResidencial, u.cpf, u.rg, a.dataIngresso, u.dataNascimento from Usuario u, Aluno a, Curso c where u.id = a.id and a.cursoId = c.id").getResultList();
		session.close();

		for (Object obj : objs) {
			Object[] o = (Object[]) obj;
			UsuarioAluno usuarioAluno = new UsuarioAluno(Integer.valueOf(String.valueOf(o[0])),
														Integer.valueOf(String.valueOf(o[1])),
														String.valueOf(o[2]),
														String.valueOf(o[3]),
														String.valueOf(o[4]),
														String.valueOf(o[5]),
														String.valueOf(o[6]),
														String.valueOf(o[7]),
														String.valueOf(o[8]),
														String.valueOf(o[9]),
														String.valueOf(o[10]));
			listUsuariosAluno.add(usuarioAluno);
		}
    	obsListUsuariosAluno = FXCollections.observableArrayList(listUsuariosAluno);
    	tableAlunos.setItems(obsListUsuariosAluno);
	}
    
    public void cadastraAluno(){
    	Alert alert =	new Alert(AlertType.NONE,
						"Tem certeza que deseja cadastrar?",
						ButtonType.OK,
						ButtonType.CANCEL);
		alert.setTitle("Date format warning");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			String username = txUserName.getText();
			String senha = psSenha.getText();
			String senhaConf = psSenhaConf.getText();

			String rg = txRG.getText();
			String cpf= txCPF.getText();
			String telResidencial = txTelResidencial.getText();
			String telCelular = txTelCelular.getText();
			String email = txEmail.getText();
			String nome = txNome.getText();
			String dataNascimento = dtNascimento.getValue().toString();
			System.out.println(dataNascimento);
			String dataIngresso = dtIngresso.getValue().toString();

			String role = "discente";
			Curso curso = comboBoxCurso.getSelectionModel().getSelectedItem();
			int cursoId = curso.getId();

			if(senha.compareTo(senhaConf) == 0) {

				Usuario u = new Usuario(username, senha, rg, cpf, dataNascimento, telResidencial, telCelular, email, role);
				u.create();
				int usuarioId = u.getId();

				System.out.println("usuarioId: "+ usuarioId);

				Aluno a = new Aluno(usuarioId, nome, dataIngresso, cursoId);
				a.create();
			}else {

				Alert al = new Alert(AlertType.ERROR);
				al.setHeaderText("As senhas não coincidem");
				al.show();
			}
		}
    }

    @FXML
	private void txCpfKeyReleased(){
		tffCpf.setTf(txCPF);
		tffCpf.formatter();
	}

	@FXML
	private void txRgKeyReleased(){
		tffRg.setTf(txRG);
		tffRg.formatter();
	}

	@FXML
	private void txTelResReleased(){
		tffTelRes.setTf(txTelResidencial);
		tffTelRes.formatter();
	}

	@FXML
	private void txTelCelReleased(){
		tffTelCel.setTf(txTelCelular);
		tffTelCel.formatter();
	}
    
    public void fecha(){
        CadastrarAluno.getStage().close();
    }
    
    public void voltaTela(){


    	fecha();
//        Principal p = new Principal(user);
//        fecha();
//        try {
//            p.start(new Stage());
//        } catch (Exception ex) {
//            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    

    
}
