/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import model.*;

import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import view.CadastrarAluno;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class CadastrarAlunoController implements Initializable {

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
	@FXML private FlowPane flowPaneForm;
	@FXML private Button btCadastrar;
	@FXML private Button btAlterar;
	@FXML private Button btRemover;
	@FXML private TableView<UsuarioAluno> tableAlunos;
	@FXML private TableColumn<UsuarioAluno, Integer> columnId;
	@FXML private TableColumn<UsuarioAluno, String> columnNome;
	@FXML private TableColumn<UsuarioAluno, String> columnCurso;
	@FXML private TableColumn<UsuarioAluno, String> columnEmail;
	@FXML private TableColumn<UsuarioAluno, String> columnTelCel;
	@FXML private TableColumn<UsuarioAluno, String> columnTelRes;
	@FXML private TableColumn<UsuarioAluno, String> columnCpf;
	@FXML private TableColumn<UsuarioAluno, String> columnRg;
	@FXML private TableColumn<UsuarioAluno, Date> columnDataIngresso;
	@FXML private TableColumn<UsuarioAluno, Date> columnDataNascimento;

	private TextFieldFormatter tffCpf = new TextFieldFormatter();
	private	TextFieldFormatter tffRg = new TextFieldFormatter();
	private	TextFieldFormatter tffTelRes = new TextFieldFormatter();
	private	TextFieldFormatter tffTelCel = new TextFieldFormatter();

	private ValidationSupport emptyValidator = new ValidationSupport();
	private ValidationSupport regexValidator = new ValidationSupport();

	private List<Curso> listCursos = new ArrayList<>();
	private ObservableList<Curso> obsCursos;
	private List<UsuarioAluno> listUsuariosAluno = new ArrayList<>();
	private ObservableList<UsuarioAluno> obsListUsuariosAluno;

	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Usuario.class).buildSessionFactory();
	    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
		inicializarTextMasks();
		inicializarEmptyValidator();
		inicializarRegexValidator();
		inicializarTableColumns();
        carregarCursos();
		carregarTableAlunos();

        btCadastrar.setOnMouseClicked((MouseEvent e)->{
			errorsDialog();
        	cadastraAluno();
        });
    }

	private void inicializarTableColumns(){
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnCurso.setCellValueFactory(new PropertyValueFactory<>("cursoNome"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		columnTelCel.setCellValueFactory(new PropertyValueFactory<>("telCel"));
		columnTelRes.setCellValueFactory(new PropertyValueFactory<>("telRes"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		columnRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
		columnDataIngresso.setCellValueFactory(new PropertyValueFactory<>("dataIngresso"));
		columnDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
	}

	private void inicializarTextMasks(){
		tffCpf.setMask("###.###.###-##");
		tffCpf.setCaracteresValidos("0123456789");
		tffRg.setMask("##.###.###-#");
		tffRg.setCaracteresValidos("0123456789");
		tffTelRes.setMask("(##)####-####");
		tffTelRes.setCaracteresValidos("0123456789");
		tffTelCel.setMask("(##)#####-####");
		tffTelCel.setCaracteresValidos("0123456789");
	}

	private void inicializarEmptyValidator(){
		//Campos obrigatórios
		emptyValidator.registerValidator(txUserName, Validator.createEmptyValidator(txUserName.getPromptText()));
		emptyValidator.registerValidator(psSenha, Validator.createEmptyValidator(psSenha.getPromptText()));
		emptyValidator.registerValidator(psSenhaConf, Validator.createEmptyValidator(psSenhaConf.getPromptText()));
		emptyValidator.registerValidator(txEmail, Validator.createEmptyValidator(txEmail.getPromptText()));
		emptyValidator.registerValidator(txNome, Validator.createEmptyValidator(txNome.getPromptText()));
		emptyValidator.registerValidator(txRG, Validator.createEmptyValidator(txRG.getPromptText()));
		emptyValidator.registerValidator(txCPF, Validator.createEmptyValidator(txCPF.getPromptText()));
		emptyValidator.registerValidator(txTelCelular, Validator.createEmptyValidator(txTelCelular.getPromptText()));
		emptyValidator.registerValidator(dtNascimento, Validator.createEmptyValidator(dtNascimento.getPromptText()));
		emptyValidator.registerValidator(dtIngresso, Validator.createEmptyValidator(dtIngresso.getPromptText()));
		emptyValidator.registerValidator(comboBoxCurso, Validator.createEmptyValidator(comboBoxCurso.getPromptText()));
	}

	private void inicializarRegexValidator(){
    	regexValidator.registerValidator(txRG, Validator.createRegexValidator(txRG.getPromptText(), "\\S{12}", Severity.ERROR));
    	regexValidator.registerValidator(txCPF, Validator.createRegexValidator(txCPF.getPromptText(), "\\S{14}", Severity.ERROR));
    	regexValidator.registerValidator(txTelCelular, Validator.createRegexValidator(txTelCelular.getPromptText(), "\\S{14}", Severity.ERROR));
    	regexValidator.registerValidator(txTelResidencial, Validator.createRegexValidator(txTelResidencial.getPromptText(), "\\S{13}", Severity.ERROR));
	}

	private void errorsDialog(){
    	Collection<ValidationMessage> emptyFieldsError = emptyValidator.getValidationResult().getErrors();
    	Collection<ValidationMessage> regexFieldsError = regexValidator.getValidationResult().getErrors();
		String emptyFieldsMessage = "Campos obrigatórios vazios:";
		String regexFieldsMessage = "Campos não preenchidos corretamente:";
		String passFieldsMessage = "Senhas não coincidem\n\n";
		String dialogMessage = "";
		boolean emptyFieldsErrorBool = false, passFieldsErrorBool = false, regexFieldsErrorBool = false;

    	if(!emptyFieldsError.isEmpty()) {
    		emptyFieldsErrorBool = true;
			for(ValidationMessage erro : emptyFieldsError)	emptyFieldsMessage += "\n  - " + erro.getText();
			emptyFieldsMessage += "\n\n";
		}
    	else emptyFieldsMessage = "";

		if(!regexFieldsError.isEmpty()) {
			regexFieldsErrorBool = true;
			for(ValidationMessage erro : regexFieldsError)	regexFieldsMessage += "\n  - " + erro.getText();
		}
		else regexFieldsMessage = "";

    	if(!psSenhaConf.getText().equals(psSenha.getText())){
    		passFieldsErrorBool = true;
		}
    	else passFieldsMessage = "";

    	if(emptyFieldsErrorBool || passFieldsErrorBool || regexFieldsErrorBool){
			dialogMessage = passFieldsMessage + emptyFieldsMessage + regexFieldsMessage;

			Alert alert = new Alert(AlertType.ERROR,
					dialogMessage,
					ButtonType.OK);
			alert.setTitle("Não foi possível efetuar o cadastro");
			alert.show();
		}
    }

	private void carregarCursos() {
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

	private void carregarTableAlunos(){
		listUsuariosAluno.clear();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		listUsuariosAluno = session.createQuery("select new model.UsuarioAluno(u.id, c.id, a.nome, c.nome, u.email, " +
													"u.telCelular, u.telResidencial, u.cpf, u.rg, a.dataIngresso, u.dataNascimento) " +
													"from Usuario as u, Aluno as a, Curso as c " +
													"where u.id = a.id and a.cursoId = c.id").getResultList();
		session.close();

		if(obsListUsuariosAluno != null) {
			obsListUsuariosAluno.clear();
		}
    	obsListUsuariosAluno = FXCollections.observableArrayList(listUsuariosAluno);
    	tableAlunos.setItems(obsListUsuariosAluno);
	}

	private void cadastraAluno(){
    	try {
			String username = txUserName.getText();
			String senha = psSenha.getText();
			String rg = txRG.getText();
			String cpf = txCPF.getText();
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

			Usuario u = new Usuario(username, senha, rg, cpf, dataNascimento, telResidencial, telCelular, email, role);
			u.create();
			int usuarioId = u.getId();

			System.out.println("usuarioId: " + usuarioId);

			Aluno a = new Aluno(usuarioId, nome, dataIngresso, cursoId);
			a.create();

			Alert alert = new Alert(AlertType.CONFIRMATION,
					"Aluno cadastrado com sucesso!",
					ButtonType.OK);
			alert.show();

			carregarTableAlunos();
		} catch (Exception e){
			Alert alert = new Alert(AlertType.ERROR,
					e.getMessage(),
					ButtonType.OK);
			alert.show();
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
