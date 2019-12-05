/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CadastrarAdminController implements Initializable {

	@FXML private TextField txUserName;
	@FXML private PasswordField psSenha;
	@FXML private PasswordField psSenhaConf;
	@FXML private TextField txRG;
	@FXML private TextField txCPF;
	@FXML private TextField txTelResidencial;
	@FXML private TextField txTelCelular;
	@FXML private TextField txEmail;
	@FXML private TextField txPesquisar;
	@FXML private DatePicker dtNascimento;
	@FXML private Button btCadastrar;
	@FXML private Button btAlterar;
	@FXML private Button btRemover;
	@FXML private Button btConfirmar;
	@FXML private Button btCancelar;
	@FXML private TableView<Usuario> tableView;
	@FXML private TableColumn<Usuario, Integer> columnId;
	@FXML private TableColumn<Usuario, String> columnEmail;
	@FXML private TableColumn<Usuario, String> columnUsername;
	@FXML private TableColumn<Usuario, String> columnTelCel;
	@FXML private TableColumn<Usuario, String> columnTelRes;
	@FXML private TableColumn<Usuario, String> columnCpf;
	@FXML private TableColumn<Usuario, String> columnRg;
	@FXML private TableColumn<Usuario, String> columnDataNascimento;

	private TextFieldFormatter tffCpf = new TextFieldFormatter();
	private TextFieldFormatter tffRg = new TextFieldFormatter();
	private TextFieldFormatter tffTelRes = new TextFieldFormatter();
	private TextFieldFormatter tffTelCel = new TextFieldFormatter();

	private ValidationSupport emptyValidator = new ValidationSupport();
	private ValidationSupport regexValidator = new ValidationSupport();

	private List<Usuario> listUsuario = new ArrayList<>();
	private ObservableList<Usuario> obsListUsuario;
	private String acao = null;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		inicializarTextMasks();
		inicializarEmptyValidator();
		inicializarRegexValidator();
		inicializarTableColumns();
		carregarTableView();

		btCancelar.setOnMouseClicked((MouseEvent e) -> {
			limpaCampos();
			desabilitaCampos();
			habilitaTableView();
		});

		btConfirmar.setOnMouseClicked((MouseEvent e) -> {
			realizaAcao();
		});

		btCadastrar.setOnMouseClicked((MouseEvent e) -> {
			acao = "Cadastrar";
			limpaCampos();
			habilitaTodosCampos();
		});

		btRemover.setOnMouseClicked((MouseEvent e) -> {
			if (!tableView.getSelectionModel().isEmpty()) {
				remover();
			}
		});

		btAlterar.setOnMouseClicked((MouseEvent e) -> {
			acao = "Alterar";
			if (!tableView.getSelectionModel().isEmpty()) {
				habilitaCamposAlteracao();
				desabilitaTableView();
			}
		});
	}

	private void inicializarTableColumns() {
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		columnTelCel.setCellValueFactory(new PropertyValueFactory<>("telCelular"));
		columnTelRes.setCellValueFactory(new PropertyValueFactory<>("telResidencial"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		columnRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
		columnDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
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
		//Campos obrigatórios
		emptyValidator.registerValidator(txUserName, Validator.createEmptyValidator(txUserName.getPromptText()));
		emptyValidator.registerValidator(psSenha, Validator.createEmptyValidator(psSenha.getPromptText()));
		emptyValidator.registerValidator(psSenhaConf, Validator.createEmptyValidator(psSenhaConf.getPromptText()));
		emptyValidator.registerValidator(txEmail, Validator.createEmptyValidator(txEmail.getPromptText()));
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

		String emptyFieldsMessage = "Campos obrigatórios vazios:";
		String regexFieldsMessage = "Campos não preenchidos corretamente:";
		String passFieldsMessage = "Senhas não coincidem\n\n";
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
			alert.setHeaderText("Não foi possível efetuar o cadastro");
			alert.show();
			return true;
		}
		return false;
	}

	private void habilitaTodosCampos() {
		psSenha.setDisable(false);
		psSenhaConf.setDisable(false);
		txEmail.setDisable(false);
		txUserName.setDisable(false);
		txRG.setDisable(false);
		txCPF.setDisable(false);
		txTelResidencial.setDisable(false);
		txTelCelular.setDisable(false);
		txEmail.setDisable(false);
		dtNascimento.setDisable(false);

		btCancelar.setDisable(false);
		btConfirmar.setDisable(false);
		btAlterar.setDisable(true);
		btRemover.setDisable(true);
		btCadastrar.setDisable(true);
	}

	private void desabilitaTableView() {
		txPesquisar.setDisable(true);
		tableView.setDisable(true);
	}

	private void habilitaTableView() {
		txPesquisar.setDisable(false);
		tableView.setDisable(false);
	}

	private void limpaCampos() {
		psSenha.clear();
		psSenhaConf.clear();
		txEmail.clear();
		txUserName.clear();
		txRG.clear();
		txCPF.clear();
		txTelResidencial.clear();
		txTelCelular.clear();
		dtNascimento.setValue(null);
	}

	private void desabilitaCampos() {
		psSenha.setDisable(true);
		psSenhaConf.setDisable(true);
		txEmail.setDisable(true);
		txUserName.setDisable(true);
		txRG.setDisable(true);
		txCPF.setDisable(true);
		txTelResidencial.setDisable(true);
		txTelCelular.setDisable(true);
		dtNascimento.setDisable(true);

		btCancelar.setDisable(true);
		btConfirmar.setDisable(true);

		btAlterar.setDisable(false);
		btRemover.setDisable(false);
		btCadastrar.setDisable(false);

	}

	private void realizaAcao() {
		if (acao.equalsIgnoreCase("Alterar")) {
			alterausuario();
		} else if (acao.equalsIgnoreCase("Cadastrar")) {
			cadastrausuario();
		}
	}

	private void remover() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Remover");
		alert.setHeaderText("Tem certeza que deseja remover?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				Usuario usuario = tableView.getSelectionModel().getSelectedItem();
				Usuario u = Read.getUsuario(String.valueOf(usuario.getId()), null, null, null, null).get(0);
				u.delete();

				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Remover");
				alert2.setHeaderText("Removido com sucesso");
				alert2.show();

				carregarTableView();
			} catch (Exception e) {
				Alert alert2 = new Alert(AlertType.ERROR,
						e.getMessage(),
						ButtonType.OK);
				alert2.show();
			}
		}
	}

	private void habilitaCamposAlteracao() {

		Usuario usuario = tableView.getSelectionModel().getSelectedItem();
		Usuario u = Read.getUsuario(String.valueOf(usuario.getId()), null, null, null, null).get(0);

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

		btCadastrar.setDisable(true);
		btAlterar.setDisable(true);
		btRemover.setDisable(true);

		btConfirmar.setDisable(false);
		btCancelar.setDisable(false);

		txUserName.setText(u.getUsername());
		psSenha.setText(u.getSenha());
		psSenhaConf.setText(u.getSenha());
		txRG.setText(usuario.getRg());
		txCPF.setText(usuario.getCpf());
		txTelResidencial.setText(usuario.getTelResidencial());
		txTelCelular.setText(usuario.getTelCelular());
		txEmail.setText(usuario.getEmail());

		LocalDate dataNascimento = stringToLocalDate(usuario.getDataNascimento());
		dtNascimento.setValue(dataNascimento);
	}

	private LocalDate stringToLocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(date, formatter);
	}

	private void carregarTableView() {
		listUsuario.clear();

		listUsuario = Read.Query("from Usuario where role = 'admin'");
		System.out.println(listUsuario);
		if (obsListUsuario != null) {
			obsListUsuario.clear();
		}
		obsListUsuario = FXCollections.observableArrayList(listUsuario);

		FilteredList<Usuario> filteredData = new FilteredList<>(obsListUsuario, b -> true);

		txPesquisar.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(objView -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (objView.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1 ||
					objView.getCpf().toLowerCase().indexOf(lowerCaseFilter) != -1 ||
					objView.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1)
					return true;
				else
					return false; // Does not match.
			});
		});

		SortedList<Usuario> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		tableView.setItems(sortedData);
	}

	private boolean testaDadosAlterar() {
		boolean erro = false;
		String alertmsg = "";
		Usuario usuario = tableView.getSelectionModel().getSelectedItem();

		if (Read.Query("from Usuario where username = '" + txUserName.getText() + "'").isEmpty() && !usuario.getUsername().equals(txUserName.getText()) ) {
			alertmsg += "-Usuario com username já existente\n";
			erro = true;
		}

		if (!Read.Query("from Usuario where rg = '" + txRG.getText() + "'").isEmpty() && !usuario.getRg().equals(txRG.getText()) ) {
			alertmsg += "-Usuario com rg já existente\n";
			erro = true;
		}

		if (!Read.Query("from Usuario where cpf = '" + txCPF.getText() + "'").isEmpty() && !usuario.getCpf().equals(txCPF.getText())) {
			alertmsg += "-Usuario com cpf já existente\n";
			erro = true;
		}

		if (!Read.Query("from Usuario where email = '" + txEmail.getText() + "'").isEmpty() && !usuario.getEmail().equals(txEmail.getText())) {
			alertmsg += "-Usuario com email já existente\n";
			erro = true;
		}

		if (erro) {
			Alert alert = new Alert(AlertType.ERROR, alertmsg);
			alert.setHeaderText("Dados inválidos!");
			alert.show();
		}

		return erro;
	}

	private boolean testaDadosCadastrar() {
		boolean erro = false;
		String alertmsg = "";

		if (!Read.Query("from Usuario where username = '" + txUserName.getText() + "'").isEmpty()) {
			alertmsg += "-Usuário com username já existente\n";
			erro = true;
		}

		if (!Read.Query("from Usuario where rg = '" + txRG.getText() + "'").isEmpty()) {
			alertmsg += "-Usuário com rg já existente\n";
			erro = true;
		}

		if (!Read.Query("from Usuario where cpf = '" + txCPF.getText() + "'").isEmpty()) {
			alertmsg += "-Usuário com cpf já existente\n";
			erro = true;
		}

		if (!Read.Query("from Usuario where email = '" + txEmail.getText() + "'").isEmpty()) {
			alertmsg += "-Usuário com email já existente\n";
			erro = true;
		}

		if (erro) {
			Alert alert = new Alert(AlertType.ERROR, alertmsg);
			alert.setHeaderText("Dados inválidos!");
			alert.show();
		}

		return erro;
	}

	private void alterausuario() {
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
			LocalDate dataNascimento = dtNascimento.getValue();

			Usuario a = tableView.getSelectionModel().getSelectedItem();

			Update.Usuario(a.getId(), username, senha, rg, cpf, email, telCelular, telResidencial, dataNascimento.toString());

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Usuário alterado com sucesso!");
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

	private void cadastrausuario() {
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
			String dataNascimento = dtNascimento.getValue().toString();
			System.out.println(dataNascimento);
			String role = "admin";

			Usuario u = new Usuario(username, senha, rg, cpf, dataNascimento, telResidencial, telCelular, email, role);
			u.create();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Usuário cadastrado com sucesso!");
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
}
