/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*

package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.*;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import view.CadastrarAluno;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AtualizaAlunoController implements Initializable {


	@FXML private TextField txUserName;
	@FXML private PasswordField psSenha;
	@FXML private PasswordField psSenhaConf;
	@FXML private TextField txNome;
	@FXML private TextField txRG;
	@FXML private TextField txCPF;
	@FXML private TextField txTelResidencial;
	@FXML private TextField txTelCelular;
	@FXML private TextField txEmail;
	@FXML private TextField txPesquisar;
	@FXML private DatePicker dtNascimento;
	@FXML private Button btAlterar;
	@FXML private Button btConfirmar;
	@FXML private Button btCancelar;

	private TextFieldFormatter tffCpf = new TextFieldFormatter();
	private TextFieldFormatter tffRg = new TextFieldFormatter();
	private TextFieldFormatter tffTelRes = new TextFieldFormatter();
	private TextFieldFormatter tffTelCel = new TextFieldFormatter();
	private ValidationSupport emptyValidator = new ValidationSupport();
	private ValidationSupport regexValidator = new ValidationSupport();
	int userId = LoggedUser.getInstance().getId();
	private String acao = null;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		inicializarTextMasks();

		inicializarEmptyValidator();
		inicializarRegexValidator();
		carregarTableView();

		btCancelar.setOnMouseClicked((MouseEvent e) -> {

		});

		btConfirmar.setOnMouseClicked((MouseEvent e) -> {
			realizaAcao();
		});

		btAlterar.setOnMouseClicked((MouseEvent e) -> {
			acao = "Alterar";
		});
	}



	private void inicializarTextMasks() {
		tffCpf.setMask("###.###.###-##");
		tffCpf.setCaracteresValidos("0123456789");
		tffRg.setMask("##.###.###-#");
		tffRg.setCaracteresValidos("0123456789");
		tffTelRes.setMask("(##)####-####");
		tffTelRes.setCaracteresValidos("0123456789");
		tffTelCel.setMask("(##)#####-####");
		tffTelCel.setCaracteresValidos("0123456789");
	}

	private void inicializarEmptyValidator() {
		//Campos obrigat�rios
		emptyValidator.registerValidator(txUserName, Validator.createEmptyValidator(txUserName.getPromptText()));
		emptyValidator.registerValidator(psSenha, Validator.createEmptyValidator(psSenha.getPromptText()));
		emptyValidator.registerValidator(psSenhaConf, Validator.createEmptyValidator(psSenhaConf.getPromptText()));
		emptyValidator.registerValidator(txEmail, Validator.createEmptyValidator(txEmail.getPromptText()));
		emptyValidator.registerValidator(txNome, Validator.createEmptyValidator(txNome.getPromptText()));
		emptyValidator.registerValidator(txRG, Validator.createEmptyValidator(txRG.getPromptText()));
		emptyValidator.registerValidator(txCPF, Validator.createEmptyValidator(txCPF.getPromptText()));
		emptyValidator.registerValidator(txTelCelular, Validator.createEmptyValidator(txTelCelular.getPromptText()));
		emptyValidator.registerValidator(dtNascimento, Validator.createEmptyValidator(dtNascimento.getPromptText()));
		}

	private void inicializarRegexValidator() {
		regexValidator.registerValidator(txRG, Validator.createRegexValidator(txRG.getPromptText(), "\\S{12}", Severity.ERROR));
		regexValidator.registerValidator(txCPF, Validator.createRegexValidator(txCPF.getPromptText(), "\\S{14}", Severity.ERROR));
		regexValidator.registerValidator(txTelCelular, Validator.createRegexValidator(txTelCelular.getPromptText(), "\\S{14}", Severity.ERROR));
		regexValidator.registerValidator(txTelResidencial, Validator.createRegexValidator(txTelResidencial.getPromptText(), "\\S{0,13}", Severity.ERROR));
	}

	private Boolean errorsDialog() {
		List<ValidationMessage> emptyFieldsError = new ArrayList<>(emptyValidator.getValidationResult().getErrors());
		List<ValidationMessage> regexFieldsError = new ArrayList<>(regexValidator.getValidationResult().getErrors());
		List<ValidationMessage> regexFieldsErrorCopy = new ArrayList<>(regexValidator.getValidationResult().getErrors());

		String emptyFieldsMessage = "Campos obrigat�rios vazios:";
		String regexFieldsMessage = "Campos n�o preenchidos corretamente:";
		String passFieldsMessage = "Senhas n�o coincidem\n\n";
		String dialogMessage = "";
		boolean emptyFieldsErrorBool = false, passFieldsErrorBool = false, regexFieldsErrorBool = false;

		for (ValidationMessage o : regexFieldsErrorCopy) {
			if (emptyFieldsError.contains(o)) regexFieldsError.remove(o);
		}

		if (!emptyFieldsError.isEmpty()) {
			emptyFieldsErrorBool = true;
			for (ValidationMessage erro : emptyFieldsError) emptyFieldsMessage += "\n  - " + erro.getText();
			emptyFieldsMessage += "\n\n";
		} else emptyFieldsMessage = "";

		if (!regexFieldsError.isEmpty()) {
			regexFieldsErrorBool = true;
			for (ValidationMessage erro : regexFieldsError) regexFieldsMessage += "\n  - " + erro.getText();
		} else regexFieldsMessage = "";

		if (!psSenhaConf.getText().equals(psSenha.getText())) {
			passFieldsErrorBool = true;
		} else passFieldsMessage = "";

		if (emptyFieldsErrorBool || passFieldsErrorBool || regexFieldsErrorBool) {
			dialogMessage = passFieldsMessage + emptyFieldsMessage + regexFieldsMessage;

			Alert alert = new Alert(AlertType.ERROR,
					dialogMessage,
					ButtonType.OK);
			alert.setTitle("Erro");
			alert.setHeaderText("N�o foi poss�vel efetuar o cadastro");
			alert.show();
			return true;
		}
		return false;
	}

	private void realizaAcao() {
		if (acao.equalsIgnoreCase("Alterar")) {
			alteraAluno();
		} else if (acao.equalsIgnoreCase("Cadastrar")) {
			cadastraAluno();
		}
	}

	private void habilitaCamposAlteracao() {

		Aluno aluno = Read.Query("from Aluno where id = " + userId).get(0);
		Usuario u = Read.getUsuario(aluno.getId().toString(), null, null, null, null).get(0);

		txNome.setDisable(false);
		txEmail.setDisable(false);
		txUserName.setDisable(false);
		txRG.setDisable(false);
		txCPF.setDisable(false);
		txTelResidencial.setDisable(false);
		txTelCelular.setDisable(false);
		txEmail.setDisable(false);
		dtNascimento.setDisable(false);
		psSenha.setDisable(false);
		psSenhaConf.setDisable(false);

		btAlterar.setDisable(true);
		btConfirmar.setDisable(false);
		btCancelar.setDisable(false);

		txUserName.setText(u.getUsername());
		psSenha.setText(u.getSenha());
		psSenhaConf.setText(u.getSenha());
		txNome.setText(aluno.getNome());
		txRG.setText(aluno.getRg());
		txCPF.setText(aluno.getCpf());
		txTelResidencial.setText(aluno.getTelRes());
		txTelCelular.setText(aluno.getTelCel());
		txEmail.setText(aluno.getEmail());

		Curso c = Read.getCurso(aluno.getCursoId().toString(), null, null, null).get(0);

		comboBoxCurso.setValue(c);

		LocalDate dataNascimento = stringToLocalDate(aluno.getDataNascimento());
		dtNascimento.setValue(dataNascimento);

		LocalDate dataIngresso = stringToLocalDate(aluno.getDataIngresso());
		dtIngresso.setValue(dataIngresso);
	}

	private LocalDate stringToLocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(date, formatter);
	}

	private void carregarTableView() {
		listAlunoView.clear();

		listAlunoView = Read.Query("select new model.AlunoView(u.id, c.id, a.nome, c.nome, u.email, u.telCelular, " +
				"u.telResidencial, u.cpf, u.rg, a.dataIngresso, u.dataNascimento, u.username) " +
				"from Usuario as u, Aluno as a, Curso as c " +
				"where u.id = a.id and a.cursoId = c.id");

		if (obsListAlunoView != null) {
			obsListAlunoView.clear();
		}
		obsListAlunoView = FXCollections.observableArrayList(listAlunoView);

		FilteredList<AlunoView> filteredData = new FilteredList<>(obsListAlunoView, b -> true);

		txPesquisar.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(objView -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (objView.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1 ||
					objView.getCpf().toLowerCase().indexOf(lowerCaseFilter) != -1 ||
					objView.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1)
					return true;
				else
					return false; // Does not match.
			});
		});

		SortedList<AlunoView> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		tableView.setItems(sortedData);
	}

	private boolean testaDadosAlterar() {
		boolean erro = false;
		String alertmsg = "";
		AlunoView aluno = tableView.getSelectionModel().getSelectedItem();

		if (!Read.Query("from Usuario where username = '" + txUserName.getText() + "'").isEmpty() && !aluno.getUsername().equals(txUserName.getText()) ) {
			alertmsg += "-Usuario com username j� existente\n";
			erro = true;
		}

		if (!Read.Query("from Usuario where rg = '" + txRG.getText() + "'").isEmpty() && !aluno.getRg().equals(txRG.getText()) ) {
			alertmsg += "-Usuario com rg j� existente\n";
			erro = true;
		}

		if (!Read.Query("from Usuario where cpf = '" + txCPF.getText() + "'").isEmpty() && !aluno.getCpf().equals(txCPF.getText())) {
			alertmsg += "-Usuario com cpf j� existente\n";
			erro = true;
		}

		if (!Read.Query("from Usuario where email = '" + txEmail.getText() + "'").isEmpty() && !aluno.getEmail().equals(txEmail.getText())) {
			alertmsg += "-Usuario com email j� existente\n";
			erro = true;
		}

		if (erro) {
			Alert alert = new Alert(AlertType.ERROR, alertmsg);
			alert.setHeaderText("Dados inv�lidos!");
			alert.show();
		}

		return erro;
	}

	private boolean testaDadosCadastrar() {
		boolean erro = false;
		String alertmsg = "";

		if (!Read.Query("from Usuario where username = '" + txUserName.getText() + "'").isEmpty()) {
			alertmsg += "-Usuario com username j� existente\n";
			erro = true;
		}

		if (!Read.Query("from Usuario where rg = '" + txRG.getText() + "'").isEmpty()) {
			alertmsg += "-Usuario com rg j� existente\n";
			erro = true;
		}

		if (!Read.Query("from Usuario where cpf = '" + txCPF.getText() + "'").isEmpty()) {
			alertmsg += "-Usuario com cpf j� existente\n";
			erro = true;
		}

		if (!Read.Query("from Usuario where email = '" + txEmail.getText() + "'").isEmpty()) {
			alertmsg += "-Usuario com email j� existente\n";
			erro = true;
		}

		if (erro) {
			Alert alert = new Alert(AlertType.ERROR, alertmsg);
			alert.setHeaderText("Dados inv�lidos!");
			alert.show();
		}

		return erro;
	}

	private void alteraAluno() {
		if(errorsDialog()) return;
		if(testaDadosAlterar()) return;

		try {
			String username = txUserName.getText();
			String senha = psSenha.getText();
			String rg = txRG.getText();
			String cpf = txCPF.getText();
			String telResidencial = txTelResidencial.getText();
			String telCelular = txTelCelular.getText();
			String email = txEmail.getText();
			String nome = txNome.getText();
			LocalDate dataNascimento = dtNascimento.getValue();
			LocalDate dataIngresso = dtIngresso.getValue();
			Curso curso = comboBoxCurso.getSelectionModel().getSelectedItem();

			int cursoId = curso.getId();

			AlunoView a = tableView.getSelectionModel().getSelectedItem();

			Update.Aluno(a.getId(), null, nome, dataIngresso.toString(), cursoId);
			Update.Usuario(a.getId(), username, senha, rg, cpf, email, telCelular, telResidencial, dataNascimento.toString());

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Aluno alterado com sucesso!");
			alert.show();

			limpaCampos();
			desabilitaCampos();
			habilitaTableView();
			carregarTableView();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR,
					e.getMessage(),
					ButtonType.OK);
			alert.show();
		}
	}

	private void cadastraAluno() {
		if(errorsDialog()) return;
		if(testaDadosCadastrar()) return;

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

			Aluno a = new Aluno(usuarioId, nome, dataIngresso, cursoId);
			a.create();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Aluno cadastrado com sucesso!");
			alert.show();

			limpaCampos();
			desabilitaCampos();
			carregarTableView();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR,
					e.getMessage(),
					ButtonType.OK);
			alert.show();
		}
	}

	@FXML
	private void txCpfKeyReleased() {
		tffCpf.setTf(txCPF);
		tffCpf.formatter();
	}

	@FXML
	private void txRgKeyReleased() {
		tffRg.setTf(txRG);
		tffRg.formatter();
	}

	@FXML
	private void txTelResReleased() {
		tffTelRes.setTf(txTelResidencial);
		tffTelRes.formatter();
	}

	@FXML
	private void txTelCelReleased() {
		tffTelCel.setTf(txTelCelular);
		tffTelCel.formatter();
	}

	public void fecha() {
		CadastrarAluno.getStage().close();
	}
}
*/
