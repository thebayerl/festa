package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import model.*;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import view.CadastrarProfessor;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AtualizaProfessorController implements Initializable {


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
        habilitaCamposAlteracao();
        desabilitaCampos();

		btCancelar.setOnMouseClicked((MouseEvent e) -> {
            habilitaCamposAlteracao();
            desabilitaCampos();
            btConfirmar.setDisable(true);
            btCancelar.setDisable(true);
            btAlterar.setDisable(false);
		});

		btConfirmar.setOnMouseClicked((MouseEvent e) -> {
			alteraProfessor();
            habilitaCamposAlteracao();
            desabilitaCampos();
            btConfirmar.setDisable(true);
            btCancelar.setDisable(true);
            btAlterar.setDisable(false);
		});

		btAlterar.setOnMouseClicked((MouseEvent e) -> {
			habilitaCampos();
			btConfirmar.setDisable(false);
			btCancelar.setDisable(false);
		});
	}

    private void desabilitaCampos(){
	    Boolean o = true;
	    txCPF.setDisable(o);
	    txEmail.setDisable(o);
        txNome.setDisable(o);
        txRG.setDisable(o);
        txTelCelular.setDisable(o);
        txTelResidencial.setDisable(o);
        txUserName.setDisable(o);
        psSenha.setDisable(o);
        psSenhaConf.setDisable(o);
        dtNascimento.setDisable(o);
    }

    private void habilitaCampos(){
        Boolean o = false;
        txCPF.setDisable(o);
        txEmail.setDisable(o);
        txNome.setDisable(o);
        txRG.setDisable(o);
        txTelCelular.setDisable(o);
        txTelResidencial.setDisable(o);
        txUserName.setDisable(o);
        psSenha.setDisable(o);
        psSenhaConf.setDisable(o);
        dtNascimento.setDisable(o);
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

	private void habilitaCamposAlteracao() {

		Professor professor = (Professor) Read.Query("from Professor where usuarioId = " + userId).get(0);
		Usuario u = (Usuario) Read.Query("from Usuario where id = " + userId).get(0);

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


		txUserName.setText(u.getUsername());
		psSenha.setText(u.getSenha());
		psSenhaConf.setText(u.getSenha());
		txNome.setText(professor.getNome());
		txRG.setText(u.getRg());
		txCPF.setText(u.getCpf());
		txTelResidencial.setText(u.getTelResidencial());
		txTelCelular.setText(u.getTelCelular());
		txEmail.setText(u.getEmail());

        LocalDate dataNascimento = stringToLocalDate(u.getDataNascimento());
        dtNascimento.setValue(dataNascimento);

	}

	private LocalDate stringToLocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(date, formatter);
	}

	private boolean testaDadosAlterar() {
		boolean erro = false;
		String alertmsg = "";

        Professor professor = (Professor) Read.Query("from Professor where usuarioId = " + userId).get(0);
        Usuario u = (Usuario) Read.Query("from Usuario where id = " + userId).get(0);

		if (!Read.Query("from Usuario where username = '" + txUserName.getText() + "'").isEmpty() && !u.getUsername().equals(txUserName.getText()) ) {
			alertmsg += "-Usuario com username já existente\n";
			erro = true;
		}

		if (!Read.Query("from Usuario where rg = '" + txRG.getText() + "'").isEmpty() && !u.getRg().equals(txRG.getText()) ) {
			alertmsg += "-Usuario com rg já existente\n";
			erro = true;
		}

		if (!Read.Query("from Usuario where cpf = '" + txCPF.getText() + "'").isEmpty() && !u.getCpf().equals(txCPF.getText())) {
			alertmsg += "-Usuario com cpf já existente\n";
			erro = true;
		}

		if (!Read.Query("from Usuario where email = '" + txEmail.getText() + "'").isEmpty() && !u.getEmail().equals(txEmail.getText())) {
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

	private void alteraProfessor() {
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

			Update.Professor(userId, nome, null, null,null);
			Update.Usuario(userId, username, senha, rg, cpf, email, telCelular, telResidencial, dataNascimento.toString());

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Professor alterado com sucesso!");
			alert.show();

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
		CadastrarProfessor.getStage().close();
	}
}
