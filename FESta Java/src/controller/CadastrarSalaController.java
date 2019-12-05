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
import view.CadastrarSala;
import view.Principal;

public class CadastrarSalaController implements Initializable {

    @FXML private Button btCadastrar;
    @FXML private Button btRemover;
    @FXML private Button btAlterar;
    @FXML private Button btCancelar;
    @FXML private Button btConfirmar;
    @FXML private TableView<Sala> tableView;
    @FXML private TableColumn<Sala, Integer> columnId;
    @FXML private TableColumn<Sala, String> columnCodigo;
    @FXML private TableColumn<Sala, Integer> columnCapacidade;
    @FXML private TableColumn<Sala, String> columnPredio;
    @FXML private TextField txCapacidade;
    @FXML private TextField txCodigoSala;
    @FXML private ComboBox<String> comboBoxPredio;
    @FXML private TextField txPesquisar;

    private ValidationSupport emptyValidator = new ValidationSupport();
    private ValidationSupport regexValidator = new ValidationSupport();

    private TextFieldFormatter tffCodSala = new TextFieldFormatter();

    private List<String> listPredios = new ArrayList<>(Arrays.asList("CCMN", "DCC", "NCE", "Letras", "CCS", "CT"));
    private ObservableList<String> obsListPredios;
    private List<Sala> listSala = new ArrayList<>();
    private ObservableList<Sala> obsListSala;
    private String acao = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicializarValidators();
        inicializarTextMasks();
        limpaCampos();
        desabilitaCampos();
        habilitaTableView();
        carregaPredios();
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
        emptyValidator.registerValidator(txCapacidade, Validator.createEmptyValidator(txCapacidade.getPromptText()));
        emptyValidator.registerValidator(txCodigoSala, Validator.createEmptyValidator(txCodigoSala.getPromptText()));
        emptyValidator.registerValidator(comboBoxPredio, Validator.createEmptyValidator(comboBoxPredio.getPromptText()));

        regexValidator.registerValidator(txCapacidade, Validator.createRegexValidator(txCapacidade.getPromptText(), "[0-9]{1,3}", Severity.ERROR));
        regexValidator.registerValidator(txCodigoSala, Validator.createRegexValidator(txCodigoSala.getPromptText(), "\\S{5}", Severity.ERROR));
    }

    private void inicializarTextMasks() {
        tffCodSala.setMask("U####");
    }

    private void habilitaTableView() {
        txPesquisar.setDisable(false);
        tableView.setDisable(false);
    }

    private void limpaCampos() {
        txCodigoSala.clear();
        txCapacidade.clear();
        txPesquisar.clear();
        comboBoxPredio.setValue(null);
    }

    private void desabilitaCampos() {

        txCapacidade.setDisable(true);
        txCodigoSala.setDisable(true);
        comboBoxPredio.setDisable(true);

        btCancelar.setDisable(true);
        btConfirmar.setDisable(true);
        btAlterar.setDisable(false);
        btRemover.setDisable(false);
        btCadastrar.setDisable(false);

    }

    private void habilitaTodosCampos() {
        txCodigoSala.setDisable(false);
        txCapacidade.setDisable(false);
        comboBoxPredio.setDisable(false);
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

        Sala sala = tableView.getSelectionModel().getSelectedItem();
        txCapacidade.setDisable(false);
        txCodigoSala.setDisable(false);
        comboBoxPredio.setDisable(false);
        btCadastrar.setDisable(true);
        btAlterar.setDisable(true);
        btRemover.setDisable(true);
        btConfirmar.setDisable(false);
        btCancelar.setDisable(false);
        txCodigoSala.setText(sala.getCodigoSala());
        txCapacidade.setText(String.valueOf(sala.getCapacidade()));
        comboBoxPredio.setValue(sala.getPredio());

    }

    private void realizaAcao() {
        if (acao.equalsIgnoreCase("Alterar")) {
            altera();
        } else if (acao.equalsIgnoreCase("Cadastrar")) {
            cadastraSala();
        }
    }

    private void inicializarTableColumns() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnCapacidade.setCellValueFactory(new PropertyValueFactory<>("capacidade"));
        columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoSala"));
        columnPredio.setCellValueFactory(new PropertyValueFactory<>("predio"));
    }

    public void carregaPredios() {
        obsListPredios = FXCollections.observableArrayList(listPredios);
        comboBoxPredio.setItems(obsListPredios);
    }

    private void carregarTableView() {
        listSala.clear();

        listSala = Read.Query("from Sala");

        if (obsListSala != null) {
            obsListSala.clear();
        }
        obsListSala = FXCollections.observableArrayList(listSala);

        FilteredList<Sala> filteredData = new FilteredList<>(obsListSala, b -> true);

        txPesquisar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(objView -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (objView.getCodigoSala().toLowerCase().indexOf(lowerCaseFilter) != -1 ||
                        objView.getPredio().toLowerCase().indexOf(lowerCaseFilter) != -1 )
                    return true;
                else
                    return false; // Does not match.
            });
        });

        SortedList<Sala> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    private boolean testaDadosAlterar() {
        boolean erro = false;
        String alertmsg = "";
        Sala s = tableView.getSelectionModel().getSelectedItem();
        String predio = comboBoxPredio.getSelectionModel().getSelectedItem();

        if (!Read.Query("from Sala where codigoSala = '" + txCodigoSala.getText() + "'" + " and predio = '" + predio + "'").isEmpty() && !s.getCodigoSala().equals(txCodigoSala.getText())) {
            alertmsg += "Sala com código já existente no prédio " + predio;
            erro = true;
        }

        if (erro) {
            Alert alert = new Alert(Alert.AlertType.ERROR, alertmsg);
            alert.setHeaderText("Dados inválidos!");
            alert.show();
        }

        return erro;
    }

    private boolean testaDadosCadastrar() {
        boolean erro = false;
        String alertmsg = "";
        Sala s = tableView.getSelectionModel().getSelectedItem();
        String predio = comboBoxPredio.getSelectionModel().getSelectedItem();

        if (!Read.Query("from Sala where codigoSala = '" + txCodigoSala.getText() + "'" + " and predio = '" + predio + "'").isEmpty()) {
            alertmsg += "Sala com código já existente no prédio " + predio;
            erro = true;
        }

        if (erro) {
            Alert alert = new Alert(Alert.AlertType.ERROR, alertmsg);
            alert.setHeaderText("Dados inválidos!");
            alert.show();
        }

        return erro;
    }
    
    public void cadastraSala(){

        if(errorsDialog()){ return;}
        if(testaDados()){ return;}

        try{
            int capacidade = Integer.parseInt(txCapacidade.getText());
            String predio = comboBoxPredio.getValue();
            String codigoSala = txCodigoSala.getText();
            Sala s = new Sala(codigoSala, capacidade, predio);
            s.create();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Sala cadastrada com sucesso!");
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

        if(errorsDialog()){ return;}
        if(testaDados()){ return;}

        try {
            String codigoSala = txCodigoSala.getText();
            int capacidade = Integer.parseInt(txCapacidade.getText());
            String predio = comboBoxPredio.getSelectionModel().getSelectedItem();

            Sala s = tableView.getSelectionModel().getSelectedItem();

            Update.Sala(s.getId(), codigoSala,capacidade, predio);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Sala alterada com sucesso!");
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remover");
        alert.setHeaderText("Tem certeza que deseja remover?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Sala sala = tableView.getSelectionModel().getSelectedItem();

            if(!Read.Query("from Turma where salaId =" + sala.getId()).isEmpty()){
                Alert aler = new Alert(Alert.AlertType.ERROR, "Existem Turmas cadastradas nessa Sala\n");
                aler.setHeaderText("Erro de dependencia!");
                aler.show();
                return;
            }

            try {
                Sala s = (Sala) Read.Query("from Sala where id =" + sala.getId()).get(0);
                s.delete();

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Remover");
                alert2.setHeaderText("Removido com sucesso");
                alert2.show();

                carregarTableView();
            } catch (Exception e) {
                Alert alert2 = new Alert(Alert.AlertType.ERROR,
                        e.getMessage(),
                        ButtonType.OK);
                alert2.show();
            }
        }
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

    @FXML
    private void txCodSalaKeyReleased() {
        tffCodSala.setTf(txCodigoSala);
        tffCodSala.formatter();
    }
}
