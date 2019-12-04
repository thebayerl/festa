/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import view.CadastrarAluno;
import view.CadastrarProfessor;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class CadastrarProfessorController implements Initializable {

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
    @FXML private ComboBox<String> comboBoxFormacao;
    @FXML private ComboBox<Curso> comboBoxCurso;
    @FXML private Button btCadastrar;
    @FXML private Button btAlterar;
    @FXML private Button btRemover;
    @FXML private Button btConfirmar;
    @FXML private Button btCancelar;
    @FXML private TableView<ProfessorView> tableView;
    @FXML private TableColumn<ProfessorView, Integer> columnId;
    @FXML private TableColumn<ProfessorView, String> columnNome;
    @FXML private TableColumn<ProfessorView, String> columnCurso;
    @FXML private TableColumn<ProfessorView, String> columnEmail;
    @FXML private TableColumn<ProfessorView, String> columnTelCel;
    @FXML private TableColumn<ProfessorView, String> columnTelRes;
    @FXML private TableColumn<ProfessorView, String> columnCpf;
    @FXML private TableColumn<ProfessorView, String> columnRg;
    @FXML private TableColumn<ProfessorView, String> columnFormacao;
    @FXML private TableColumn<ProfessorView, Date> columnDataNascimento;
    @FXML private ListView<Disciplina> listViewCapacidades;

    private TextFieldFormatter tffCpf = new TextFieldFormatter();
    private	TextFieldFormatter tffRg = new TextFieldFormatter();
    private	TextFieldFormatter tffTelRes = new TextFieldFormatter();
    private	TextFieldFormatter tffTelCel = new TextFieldFormatter();

    private ValidationSupport emptyValidator = new ValidationSupport();
    private ValidationSupport regexValidator = new ValidationSupport();

    private List<String> listFormacao = Arrays.asList("Graduação", "Mestrado", "Doutorado", "Pós-doutorado");
    private List<Curso> listCursos = new ArrayList<>();
    private List<ProfessorView> listProfessorView = new ArrayList<>();
    private List<Disciplina> listCapacidades = new ArrayList<>();

    private ObservableList<Curso> obsCursos;
    private ObservableList<ProfessorView> obsListProfessorView;
    private ObservableList<String> obsListFormacao;
    private ObservableList<Disciplina> obsListCapacidades;

    private String acao = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicializarTextMasks();
        inicializarTextFieldLimitations();
        inicializarEmptyValidator();
        inicializarRegexValidator();
        inicializarTableColumns();
        carregarCursos();
        carregarFormacao();
        carregarCapacidades();
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
            desabilitaTableView();
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
            }
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
        columnFormacao.setCellValueFactory(new PropertyValueFactory<>("formacao"));
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

    private void inicializarTextFieldLimitations(){
		/*txUserName.setStandardField();
		txUserName.setMaxLength(20);
		txEmail.setMaxLength(40);
		txNome.setCharsOnlyFieldwSpace();
		txNome.setMaxLength(100);*/
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
        emptyValidator.registerValidator(comboBoxFormacao, Validator.createEmptyValidator(comboBoxFormacao.getPromptText()));
        emptyValidator.registerValidator(comboBoxCurso, Validator.createEmptyValidator(comboBoxCurso.getPromptText()));
    }

    private void inicializarRegexValidator(){
        regexValidator.registerValidator(txRG, Validator.createRegexValidator(txRG.getPromptText(), "\\S{12}", Severity.ERROR));
        regexValidator.registerValidator(txCPF, Validator.createRegexValidator(txCPF.getPromptText(), "\\S{14}", Severity.ERROR));
        regexValidator.registerValidator(txTelCelular, Validator.createRegexValidator(txTelCelular.getPromptText(), "\\S{14}", Severity.ERROR));
        regexValidator.registerValidator(txTelResidencial, Validator.createRegexValidator(txTelResidencial.getPromptText(), "\\S{0,13}", Severity.ERROR));
    }

    private Boolean errorsDialog(){
        List<ValidationMessage> emptyFieldsError = new ArrayList<>(emptyValidator.getValidationResult().getErrors());
        List<ValidationMessage> regexFieldsError = new ArrayList<>(regexValidator.getValidationResult().getErrors());
        List<ValidationMessage> regexFieldsErrorCopy = new ArrayList<>(regexValidator.getValidationResult().getErrors());

        String emptyFieldsMessage = "Campos obrigatórios vazios:";
        String regexFieldsMessage = "Campos não preenchidos corretamente:";
        String passFieldsMessage = "Senhas não coincidem\n\n";
        String dialogMessage = "";
        boolean emptyFieldsErrorBool = false, passFieldsErrorBool = false, regexFieldsErrorBool = false;

        for(ValidationMessage o : regexFieldsErrorCopy){
            if(emptyFieldsError.contains(o)) regexFieldsError.remove(o);
        }

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
            alert.setTitle("Erro");
            alert.setHeaderText("Não foi possível efetuar o cadastro");
            alert.show();
            return true;
        }
        return false;
    }

    private void habilitaTodosCampos() {
        txNome.setDisable(false);
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
        comboBoxCurso.setDisable(false);

        btCancelar.setDisable(false);
        btConfirmar.setDisable(false);
        btAlterar.setDisable(true);
        btRemover.setDisable(true);
        btCadastrar.setDisable(true);
    }

    private void desabilitaTableView() {
        tableView.setDisable(true);
    }

    private void habilitaTableView() {
        tableView.setDisable(false);
    }

    private void limpaCampos() {
        txNome.clear();
        psSenha.clear();
        psSenhaConf.clear();
        txEmail.clear();
        txUserName.clear();
        txRG.clear();
        txCPF.clear();
        txTelResidencial.clear();
        txTelCelular.clear();
        dtNascimento.setValue(null);
        comboBoxCurso.setValue(null);
        comboBoxFormacao.setValue(null);
    }

    private void desabilitaCampos() {

        txNome.setDisable(true);
        psSenha.setDisable(true);
        psSenhaConf.setDisable(true);
        txEmail.setDisable(true);
        txUserName.setDisable(true);
        txRG.setDisable(true);
        txCPF.setDisable(true);
        txTelResidencial.setDisable(true);
        txTelCelular.setDisable(true);
        dtNascimento.setDisable(true);
        comboBoxCurso.setDisable(true);
        comboBoxFormacao.setDisable(true);

        btCancelar.setDisable(true);
        btConfirmar.setDisable(true);

        btAlterar.setDisable(false);
        btRemover.setDisable(false);
        btCadastrar.setDisable(false);

    }

    private void realizaAcao() {
        if (acao.equalsIgnoreCase("Alterar")) {
            alterar();
        } else if (acao.equalsIgnoreCase("Cadastrar")) {
            cadastrar();
        }
    }

    private void habilitaCamposAlteracao() {
        ProfessorView professor = tableView.getSelectionModel().getSelectedItem();
        Usuario u = Read.getUsuario(professor.getId().toString(), null, null, null, null).get(0);

        txNome.setDisable(false);
        txEmail.setDisable(false);
        txUserName.setDisable(false);
        txRG.setDisable(false);
        txCPF.setDisable(false);
        txTelResidencial.setDisable(false);
        txTelCelular.setDisable(false);
        txEmail.setDisable(false);
        dtNascimento.setDisable(false);
        comboBoxCurso.setDisable(false);
        comboBoxFormacao.setDisable(false);

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
        txNome.setText(professor.getNome());
        txRG.setText(professor.getRg());
        txCPF.setText(professor.getCpf());
        txTelResidencial.setText(professor.getTelRes());
        txTelCelular.setText(professor.getTelCel());
        txEmail.setText(professor.getEmail());

        Curso c = Read.getCurso(professor.getCursoId().toString(), null, null, null).get(0);

        comboBoxCurso.setValue(c);
        comboBoxFormacao.setValue(tableView.getSelectionModel().getSelectedItem().getFormacao());

        List<ProfessorCapacidade> capacidadesExistentes = Read.Query("from ProfessorCapacidade c where c.professorId = " + professor.getId().toString());
        listViewCapacidades.getSelectionModel().clearSelection();
        for(ProfessorCapacidade p : capacidadesExistentes){
            for (Disciplina d : listViewCapacidades.getItems()){
                if(d.getId() == p.getDisciplinaId())
                    listViewCapacidades.getSelectionModel().select(d);
            }
        }

        LocalDate dataNascimento = stringToLocalDate(professor.getDataNascimento());
        dtNascimento.setValue(dataNascimento);
    }

    private LocalDate stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }

    private void carregarCursos() {
        listCursos.clear();
        comboBoxCurso.getItems().clear();

        listCursos = Read.Query("from Curso");

        if(obsCursos != null) {
            obsCursos.clear();
        }
        obsCursos = FXCollections.observableArrayList(listCursos);
        comboBoxCurso.setItems(obsCursos);
    }

    private void carregarFormacao(){
        obsListFormacao = FXCollections.observableArrayList(listFormacao);
        comboBoxFormacao.setItems(obsListFormacao);
    }

    public void carregarCapacidades() {
        if(listViewCapacidades != null){
            listViewCapacidades.getSelectionModel().clearSelection();
            listViewCapacidades.getItems().clear();
        }
        listViewCapacidades.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listCapacidades = Read.getDisciplina(null, null, null, null);
        Comparator<Disciplina> comparator = Comparator.comparing(Disciplina::getNome);
        obsListCapacidades = FXCollections.observableArrayList(listCapacidades);
        FXCollections.sort(obsListCapacidades, comparator);
        listViewCapacidades.setItems(obsListCapacidades);
    }

    private void carregarTableView(){
        listProfessorView.clear();

        listProfessorView = Read.Query("select new model.ProfessorView(u.id, c.id, p.nome, c.nome, u.email, u.telCelular, " +
                "u.telResidencial, u.cpf, u.rg, p.nivelFormacao, u.dataNascimento) " +
                "from Usuario as u, Professor as p, Curso as c " +
                "where u.id = p.id and p.cursoId = c.id");

        if(obsListProfessorView != null) {
            obsListProfessorView.clear();
        }
        obsListProfessorView = FXCollections.observableArrayList(listProfessorView);

        FilteredList<ProfessorView> filteredData = new FilteredList<>(obsListProfessorView, b -> true);

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

        SortedList<ProfessorView> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    private boolean testaDados(){
        boolean erro = false;
        String alertmsg = "";

        if(!Read.Query("from Usuario where username = '" + txUserName.getText() + "'").isEmpty()) {
            alertmsg += "-Usuario com username já existente\n";
            erro = true;
        }

        if(!Read.Query("from Usuario where rg = '" + txRG.getText() + "'").isEmpty()) {
            alertmsg += "-Usuario com rg já existente\n";
            erro = true;
        }

        if(!Read.Query("from Usuario where cpf = '" + txCPF.getText() + "'").isEmpty()) {
            alertmsg +="-Usuario com cpf já existente\n";
            erro = true;
        }

        if(!Read.Query("from Usuario where email = '" + txEmail.getText() + "'").isEmpty()) {
            alertmsg +="-Usuario com email já existente\n";
            erro = true;
        }

        if(erro){
            Alert alert = new Alert(AlertType.ERROR, alertmsg);
            alert.setHeaderText("Dados inválidos!");
            alert.show();
        }

        return erro;
    }

    private void remover() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Remover");
        alert.setHeaderText("Tem certeza que deseja remover?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            ProfessorView professorView = tableView.getSelectionModel().getSelectedItem();
            Usuario u = Read.getUsuario(professorView.getId().toString(), null, null, null, null).get(0);
            u.delete();
            carregarTableView();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    private void alterar() {
        if (errorsDialog()) return;
        if (testaDados()) return;

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
            String formacao = comboBoxFormacao.getSelectionModel().getSelectedItem();
            Curso curso = comboBoxCurso.getSelectionModel().getSelectedItem();
            int cursoId = curso.getId();

            ProfessorView p = tableView.getSelectionModel().getSelectedItem();

            Professor professor = Read.getProfessor(p.getId().toString(), null, null, null, null).get(0);
            Usuario usuario = Read.getUsuario(p.getId().toString(), null, null, null, null).get(0);

            Update.Professor(professor.getUsuarioId(), nome, formacao, cursoId);
            Update.Usuario(usuario.getId(), username, senha, rg, cpf, email, telCelular, telResidencial, dataNascimento);

            Read.Query("delete from ProfessorCapacidade where professorId = " + p.getId());
            cadastrarCapacidades(p.getId());

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Professor alterado com sucesso!");
            alert.show();

            limpaCampos();
            desabilitaCampos();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR,
                    e.getMessage(),
                    ButtonType.OK);
            alert.show();
        }
        carregarTableView();
    }

    private void cadastrar(){

        if(testaDados()){
            return;
        }

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
            String formacao = comboBoxFormacao.getSelectionModel().getSelectedItem();
            Curso curso = comboBoxCurso.getSelectionModel().getSelectedItem();
            String role = "docente";
            int cursoId = curso.getId();

            Usuario u = new Usuario(username, senha, rg, cpf, dataNascimento, telResidencial, telCelular, email, role);
            u.create();
            int usuarioId = u.getId();

            Professor p = new Professor(usuarioId, nome, formacao, cursoId);
            p.create();
            cadastrarCapacidades(usuarioId);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Professor cadastrado com sucesso!");
            alert.show();

            carregarTableView();
        } catch (Exception e){
            Alert alert = new Alert(AlertType.ERROR,
                    e.getMessage(),
                    ButtonType.OK);
            alert.show();
        }
    }

    public void cadastrarCapacidades(int professorId){
        ObservableList<Disciplina> selectedCapacidades = listViewCapacidades.getSelectionModel().getSelectedItems();
        if(selectedCapacidades != null){
            for(Disciplina d : selectedCapacidades){
                ProfessorCapacidade pCapacidade = new ProfessorCapacidade(professorId, d.getId());
                pCapacidade.create();
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

    public void voltaTela() {


        fecha();
    }
}
