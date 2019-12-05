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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import view.CadastrarCurso;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class CadastrarCursoController implements Initializable {
    

    @FXML private Button btCadastrar;
    @FXML private Button btRemover;
    @FXML private Button btAlterar;
    @FXML private Button btCancelar;
    @FXML private Button btConfirmar;
    @FXML private TableView<CursoView> tableView;
    @FXML private TableColumn<CursoView, Integer> columnId;
    @FXML private TableColumn<CursoView, String> columnCodigo;
    @FXML private TableColumn<CursoView, String > columnNome;
    @FXML private TableColumn<CursoView, String> columnDepartamento;
    @FXML private TextField txNome;
    @FXML private TextField txCodigoCurso;
    @FXML private TextField txPesquisar;
    @FXML private ComboBox<Departamento> comboBoxDepartamento;

    private ValidationSupport emptyValidator = new ValidationSupport();
    private ValidationSupport regexValidator = new ValidationSupport();

    private TextFieldFormatter tffCodCurso = new TextFieldFormatter();

    private List<Departamento> listDepartamentos = new ArrayList<>();
    private ObservableList<Departamento> obsDepartamentos;
    private List<CursoView> listCursoView = new ArrayList<>();
    private ObservableList<CursoView> obsListCursoView;
    private String acao = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicializarTextMasks();
        inicializarValidators();
        desabilitaCampos();
        carregarDepartametos();
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
            txPesquisar.setDisable(true);
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

    private void inicializarValidators(){
        //Campos obrigatórios
        emptyValidator.registerValidator(txNome, Validator.createEmptyValidator(txNome.getPromptText()));
        emptyValidator.registerValidator(txCodigoCurso, Validator.createEmptyValidator(txCodigoCurso.getPromptText()));
        emptyValidator.registerValidator(comboBoxDepartamento, Validator.createEmptyValidator(comboBoxDepartamento.getPromptText()));

        regexValidator.registerValidator(txNome, Validator.createRegexValidator(txNome.getPromptText(), "[a-zA-Z]{0,50}", Severity.ERROR));
        regexValidator.registerValidator(txCodigoCurso, Validator.createRegexValidator(txCodigoCurso.getPromptText(), "\\S{5}", Severity.ERROR));
    }

    private void inicializarTextMasks() {
        tffCodCurso.setMask("UUU##");
    }

    private void habilitaTableView() {
        txPesquisar.setDisable(false);
        tableView.setDisable(false);
    }

    private void limpaCampos() {
        txNome.clear();
        txCodigoCurso.clear();
        txPesquisar.clear();
        comboBoxDepartamento.setValue(null);
    }

    private void desabilitaCampos() {

        txNome.setDisable(true);
        txCodigoCurso.setDisable(true);
        comboBoxDepartamento.setDisable(true);

        btCancelar.setDisable(true);
        btConfirmar.setDisable(true);
        btAlterar.setDisable(false);
        btRemover.setDisable(false);
        btCadastrar.setDisable(false);

    }

    private void habilitaTodosCampos() {
        txNome.setDisable(false);
        txCodigoCurso.setDisable(false);
        comboBoxDepartamento.setDisable(false);
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

    private void habilitaCamposAlteracao() {

        CursoView curso = tableView.getSelectionModel().getSelectedItem();
        txNome.setDisable(false);
        txCodigoCurso.setDisable(false);
        comboBoxDepartamento.setDisable(false);
        btCadastrar.setDisable(true);
        btAlterar.setDisable(true);
        btRemover.setDisable(true);
        btConfirmar.setDisable(false);
        btCancelar.setDisable(false);
        txNome.setText(curso.getNome());
        txCodigoCurso.setText(curso.getCodigoCurso());

        Departamento d = (Departamento) Read.Query("from Departamento where id = " + curso.getDepartamentoId()).get(0);
        comboBoxDepartamento.setValue(d);

        }

    private void realizaAcao() {
        if (acao.equalsIgnoreCase("Alterar")) {
            altera();
        } else if (acao.equalsIgnoreCase("Cadastrar")) {
            cadastraCurso();
        }
    }

    public void carregarDepartametos() {
        listDepartamentos = Read.Query("from Departamento");
        obsDepartamentos = FXCollections.observableArrayList(listDepartamentos);
        comboBoxDepartamento.setItems(obsDepartamentos);
    }

    private void inicializarTableColumns() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoCurso"));
        columnDepartamento.setCellValueFactory(new PropertyValueFactory<>("departamentoNome"));
    }

    private void carregarTableView() {
        listCursoView.clear();

        listCursoView = Read.Query("select new model.CursoView(c.id, c.codigoCurso, c.nome,  d.id , d.codigoDepartamento) " +
                "from Departamento as d, Curso as c " +
                "where d.id = c.departamentoId");

        if (obsListCursoView != null) {
            obsListCursoView.clear();
        }
        obsListCursoView = FXCollections.observableArrayList(listCursoView);

        FilteredList<CursoView> filteredData = new FilteredList<>(obsListCursoView, b -> true);

        txPesquisar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(objView -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (objView.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1 ||
                        objView.getCodigoCurso().toLowerCase().indexOf(lowerCaseFilter) != -1 )
                    return true;
                else
                    return false; // Does not match.
            });
        });

        SortedList<CursoView> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    private boolean testaDados() {
        boolean erro = false;
        String alertmsg = "";
        CursoView c = tableView.getSelectionModel().getSelectedItem();
        if (!Read.Query("from Curso where codigoCurso = '" + txCodigoCurso.getText() + "'").isEmpty() && !c.getCodigoCurso().equals(txCodigoCurso.getText())) {
            alertmsg += "-Curso com codigoCurso já existente\n";
            erro = true;
        }

        if (erro) {
            Alert alert = new Alert(Alert.AlertType.ERROR, alertmsg);
            alert.setHeaderText("Dados inválidos!");
            alert.show();
        }

        return erro;
    }

    private Boolean errorsDialog(){
        List<ValidationMessage> emptyFieldsError = new ArrayList<>(emptyValidator.getValidationResult().getErrors());
        List<ValidationMessage> regexFieldsError = new ArrayList<>(regexValidator.getValidationResult().getErrors());
        List<ValidationMessage> regexFieldsErrorCopy = new ArrayList<>(regexValidator.getValidationResult().getErrors());

        String emptyFieldsMessage = "Campos obrigatórios vazios:";
        String regexFieldsMessage = "Campos não preenchidos corretamente:";
        String dialogMessage = "";
        boolean emptyFieldsErrorBool = false, regexFieldsErrorBool = false;

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

        if(emptyFieldsErrorBool || regexFieldsErrorBool){
            dialogMessage = emptyFieldsMessage + regexFieldsMessage;

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    dialogMessage,
                    ButtonType.OK);
            alert.setTitle("Erro");
            alert.setHeaderText("Não foi possível efetuar o cadastro");
            alert.show();
            return true;
        }
        return false;
    }

    public void cadastraCurso(){

        if(errorsDialog()){ return;}
        if(testaDados()){ return;}

        try{
            String nome = txNome.getText();
            String codigoCurso = txCodigoCurso.getText();
            Departamento d = comboBoxDepartamento.getSelectionModel().getSelectedItem() ;
            Curso c = new Curso(codigoCurso, nome, d.getId());
            c.create();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Curso cadastrado com sucesso!");
            alert.show();

            limpaCampos();
            desabilitaCampos();
            carregarTableView();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    e.getMessage(),
                    ButtonType.OK);
            alert.show();
        }
    }

    private void altera() {

        if (testaDados()) return;

        try {
            String codigoCurso = txCodigoCurso.getText();
            String nome = txNome.getText();
            Departamento dep = comboBoxDepartamento.getSelectionModel().getSelectedItem();

            CursoView c = tableView.getSelectionModel().getSelectedItem();

            Update.Curso(c.getId(),codigoCurso ,nome,dep.getId());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Disciplina alterada com sucesso!");
            alert.show();

            limpaCampos();
            desabilitaCampos();
            habilitaTableView();
            carregarTableView();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    e.getMessage(),
                    ButtonType.OK);
            alert.show();
        }
    }

    private void remover() {
        boolean erro = false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remover");
        alert.setHeaderText("Tem certeza que deseja remover?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            CursoView curso = tableView.getSelectionModel().getSelectedItem();
            String msg = "";
            if(!Read.Query("from Aluno where cursoId =" + curso.getId()).isEmpty()){
                msg += "Existem Alunos cadastrados nesse Curso\n";
                erro=true;
            }
            if(!Read.Query("from Professor where cursoId =" + curso.getId()).isEmpty()){
                msg += "Existem Professores cadastrados nesse Curso\n";
                erro=true;
            }

            if(erro){
                Alert aler = new Alert(Alert.AlertType.ERROR, msg);
                aler.setHeaderText("Erro de dependencia!");
                aler.show();
                return;
            }

            Curso c = (Curso) Read.Query("from Curso where id =" + curso.getId()).get(0);
            c.delete();
            carregarTableView();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    @FXML
    private void txCodCursoKeyReleased() {
        tffCodCurso.setTf(txCodigoCurso);
        tffCodCurso.formatter();
    }
}
