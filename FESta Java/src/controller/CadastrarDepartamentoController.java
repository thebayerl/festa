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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Departamento;
import model.Read;
import model.Departamento;
import model.Update;
import view.CadastrarDepartamento;

import java.net.URL;
import java.util.*;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class CadastrarDepartamentoController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML private Button btCadastrar;
    @FXML private Button btRemover;
    @FXML private Button btAlterar;
    @FXML private Button btCancelar;
    @FXML private Button btConfirmar;
    @FXML private TableView<Departamento> tableView;
    @FXML private TableColumn<Departamento, Integer> columnId;
    @FXML private TableColumn<Departamento, String> columnCodigo;
    @FXML private TableColumn<Departamento, String> columnNome;
    @FXML private TextField txNome;
    @FXML private TextField txCodigoDepartamento;
    @FXML private TextField txPesquisar;

    private List<Departamento> listDepartamento = new ArrayList<>();
    private ObservableList<Departamento> obsListDepartamento;
    private String acao = null;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        limpaCampos();
        desabilitaCampos();
        habilitaTableView();
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

    private void habilitaTableView() {
        txPesquisar.setDisable(false);
        tableView.setDisable(false);
    }

    private void limpaCampos() {
        txCodigoDepartamento.clear();
        txNome.clear();
        txPesquisar.clear();
    }

    private void desabilitaCampos() {

        txCodigoDepartamento.setDisable(true);
        txNome.setDisable(true);

        btCancelar.setDisable(true);
        btConfirmar.setDisable(true);
        btAlterar.setDisable(false);
        btRemover.setDisable(false);
        btCadastrar.setDisable(false);

    }

    private void habilitaTodosCampos() {
        txCodigoDepartamento.setDisable(false);
        txNome.setDisable(false);
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

        Departamento departamento = tableView.getSelectionModel().getSelectedItem();
        txCodigoDepartamento.setDisable(false);
        txNome.setDisable(false);
        btCadastrar.setDisable(true);
        btAlterar.setDisable(true);
        btRemover.setDisable(true);
        btConfirmar.setDisable(false);
        btCancelar.setDisable(false);
        txCodigoDepartamento.setText(departamento.getCodigoDepartamento());
        txNome.setText(String.valueOf(departamento.getNome()));

    }

    private void realizaAcao() {
        if (acao.equalsIgnoreCase("Alterar")) {
            altera();
        } else if (acao.equalsIgnoreCase("Cadastrar")) {
            cadastraDepartamento();
        }
    }

    private void inicializarTableColumns() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoDepartamento"));
    }

    private void carregarTableView() {
        listDepartamento.clear();

        listDepartamento = Read.Query("from Departamento");

        if (obsListDepartamento != null) {
            obsListDepartamento.clear();
        }
        obsListDepartamento = FXCollections.observableArrayList(listDepartamento);

        FilteredList<Departamento> filteredData = new FilteredList<>(obsListDepartamento, b -> true);

        txPesquisar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(objView -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (objView.getCodigoDepartamento().toLowerCase().indexOf(lowerCaseFilter) != -1 ||
                        objView.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1 )
                    return true;
                else
                    return false; // Does not match.
            });
        });

        SortedList<Departamento> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    private boolean testaDados() {
        boolean erro = false;
        String alertmsg = "";
        Departamento s = tableView.getSelectionModel().getSelectedItem();
        if (!Read.Query("from Departamento where codigoDepartamento = '" + txCodigoDepartamento.getText() + "'").isEmpty() && !s.getCodigoDepartamento().equals(txCodigoDepartamento.getText())) {
            alertmsg += "-Departamento com codigoDepartamento já existente\n";
            erro = true;
        }

        if (erro) {
            Alert alert = new Alert(Alert.AlertType.ERROR, alertmsg);
            alert.setHeaderText("Dados inválidos!");
            alert.show();
        }

        return erro;
    }
    
    public void cadastraDepartamento(){

        if(testaDados()){
            return;
        }

        String nome = txNome.getText();
        String codigoDepartamento = txCodigoDepartamento.getText();
        Departamento d = new Departamento(codigoDepartamento, nome);
        d.create();

        limpaCampos();
        desabilitaCampos();
        carregarTableView();
    }

    private void altera() {

        if (testaDados()) return;

        try {
            String codigoDepartamento = txCodigoDepartamento.getText();
            String nome = txNome.getText();

            Departamento d = tableView.getSelectionModel().getSelectedItem();

            Update.getDepartamento(d.getId(), codigoDepartamento, nome );

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remover");
        alert.setHeaderText("Tem certeza que deseja remover?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Departamento departamento = tableView.getSelectionModel().getSelectedItem();

            boolean erro = false;
            String msg ="";

            if(!Read.Query("from Curso where departamentoId =" + departamento.getId()).isEmpty()){
                msg += "Existem Cursos cadastrados nesse Departamento\n";
                erro=true;
            }
            if(!Read.Query("from Disciplina where departamentoId =" + departamento.getId()).isEmpty()){
                msg += "Existem Disciplinas cadastradas nesse Departamento\n";
                erro=true;
            }

            if(erro){
                Alert aler = new Alert(Alert.AlertType.ERROR, msg);
                aler.setHeaderText("Erro de dependencia!");
                aler.show();
                return;
            }

            Departamento s = (Departamento) Read.Query("from Departamento where id =" + departamento.getId()).get(0);
            s.delete();
            carregarTableView();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }
    
    public void fecha(){
        CadastrarDepartamento.getStage().close();
    }
    
    public void abrePrincipal(){
        //Principal p = new Principal();
        fecha();
//        try {
//            p.start(new Stage());
//        } catch (Exception ex) {
//            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
