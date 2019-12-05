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
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.hibernate.Session;
import view.CadastrarDisciplina;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class CadastrarDisciplinaController implements Initializable {
    
	@FXML private TextField txNome;
    @FXML private TextField txCodigoDisciplina;
    @FXML private TextField txPesquisar;
    @FXML private Button btCadastrar;
    @FXML private Button btCancelar;
    @FXML private ComboBox<Departamento> comboBoxDepartamento;
    @FXML private ComboBox<Integer> comboBoxCreditos;
    @FXML private ListView<Disciplina> listViewPrerequisito;
    @FXML private Button btAlterar;
    @FXML private Button btRemover;
    @FXML private Button btConfirmar;
    @FXML private TableView<DisciplinaView> tableView;
    @FXML private TableColumn<DisciplinaView, Integer> columnId;
    @FXML private TableColumn<DisciplinaView, String> columnCodigo;
    @FXML private TableColumn<DisciplinaView, String> columnNome;
    @FXML private TableColumn<DisciplinaView, Integer> columnCreditos;
    @FXML private TableColumn<DisciplinaView, String> columnDepartamento;
    
    int maxCredito = 6;
    private List<Integer> listCreditos = new ArrayList<>();
    private List<Departamento> listDepartamentos = new ArrayList<>();
    private List<Disciplina> selectedPrerequisitos = new ArrayList<>();
    private List<Disciplina> listDisciplinas = new ArrayList<>();
    private ObservableList<Integer> obsCreditos;
    private ObservableList<Departamento> obsDepartamentos;
    private List<DisciplinaView> listDisciplinaView = new ArrayList<>();
    private ObservableList<DisciplinaView> obsListDisciplinaView;

    private String acao = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        limpaCampos();
        desabilitaCampos();
        habilitaTableView();
    	carregarCreditos();
    	carregarDepartametos();
    	carregarPrerequisitos();
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

    private void habilitaTableView() {
        txPesquisar.setDisable(false);
        tableView.setDisable(false);
    }

    private void limpaCampos() {
        txNome.clear();
        txCodigoDisciplina.clear();
        txPesquisar.clear();
        comboBoxCreditos.setValue(null);
        comboBoxDepartamento.setValue(null);
    }

    private void desabilitaCampos() {

        txNome.setDisable(true);
        txCodigoDisciplina.setDisable(true);
        listViewPrerequisito.setDisable(true);
        comboBoxCreditos.setDisable(true);
        comboBoxDepartamento.setDisable(true);

        btCancelar.setDisable(true);
        btConfirmar.setDisable(true);
        btAlterar.setDisable(false);
        btRemover.setDisable(false);
        btCadastrar.setDisable(false);

    }

    private void habilitaTodosCampos() {
        txNome.setDisable(false);
        txCodigoDisciplina.setDisable(false);
        comboBoxCreditos.setDisable(false);
        comboBoxDepartamento.setDisable(false);
        listViewPrerequisito.setDisable(false);
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

        DisciplinaView disciplina = tableView.getSelectionModel().getSelectedItem();
        txNome.setDisable(false);
        txCodigoDisciplina.setDisable(false);
        listViewPrerequisito.setDisable(false);
        comboBoxCreditos.setDisable(false);
        comboBoxDepartamento.setDisable(false);
        btCadastrar.setDisable(true);
        btAlterar.setDisable(true);
        btRemover.setDisable(true);
        btConfirmar.setDisable(false);
        btCancelar.setDisable(false);
        txNome.setText(disciplina.getNome());
        txCodigoDisciplina.setText(disciplina.getCodigoDisciplina());


        Departamento d = (Departamento) Read.Query("from Departamento where id = " + disciplina.getDepartamentoId()).get(0);
        comboBoxDepartamento.setValue(d);
        comboBoxCreditos.setValue(disciplina.getCreditos());

        List<PreRequisito> preReqexistentes = Read.Query("from PreRequisito where disciplinaId = " + disciplina.getId());
        listViewPrerequisito.getSelectionModel().clearSelection();
        for(PreRequisito p : preReqexistentes){
            for (Disciplina g : listViewPrerequisito.getItems()){
                if(g.getId() == p.getPrerequisitoId())
                    listViewPrerequisito.getSelectionModel().select(g);
            }
        }
    }

    private void realizaAcao() {
        if (acao.equalsIgnoreCase("Alterar")) {
           altera();
        } else if (acao.equalsIgnoreCase("Cadastrar")) {
            cadastraDisciplina();
        }
    }

    public void carregarCreditos() {
    	for(int i = 1 ;  i <= maxCredito ; i++) {
    		listCreditos.add(i);
    	}
    	obsCreditos = FXCollections.observableArrayList(listCreditos);
    	comboBoxCreditos.setItems(obsCreditos);
    }
    
    public void carregarDepartametos() {
	listDepartamentos = Read.Query("from Departamento");
    obsDepartamentos = FXCollections.observableArrayList(listDepartamentos);
    comboBoxDepartamento.setItems(obsDepartamentos);
    }

    public void carregarPrerequisitos() {
        if(listViewPrerequisito != null){
            listViewPrerequisito.getSelectionModel().clearSelection();
            listViewPrerequisito.getItems().clear();
        }
        listViewPrerequisito.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listDisciplinas = Read.getDisciplina(null, null, null, null);
        Comparator<Disciplina> comparator = Comparator.comparing(Disciplina::getNome);
        ObservableList obsDisciplinas = FXCollections.observableArrayList(listDisciplinas);
        FXCollections.sort(obsDisciplinas, comparator);
        listViewPrerequisito.setItems(obsDisciplinas);

    }

    private void inicializarTableColumns() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoDisciplina"));
        columnCreditos.setCellValueFactory(new PropertyValueFactory<>("creditos"));
        columnDepartamento.setCellValueFactory(new PropertyValueFactory<>("departamentoNome"));
    }

    private void carregarTableView() {
        listDisciplinaView.clear();

        listDisciplinaView = Read.Query("select new model.DisciplinaView(dis.nome, dis.creditos ,dis.id, dep.id, dis.codigoDisciplina ,dep.codigoDepartamento) " +
                "from Departamento as dep, Disciplina as dis " +
                "where dep.id = dis.departamentoId");

        if (obsListDisciplinaView != null) {
            obsListDisciplinaView.clear();
        }
        obsListDisciplinaView = FXCollections.observableArrayList(listDisciplinaView);

        FilteredList<DisciplinaView> filteredData = new FilteredList<>(obsListDisciplinaView, b -> true);

        txPesquisar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(objView -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (objView.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1 ||
                        objView.getCodigoDisciplina().toLowerCase().indexOf(lowerCaseFilter) != -1 ||
                        objView.getDepartamentoNome().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true;
                else
                    return false; // Does not match.
            });
        });

        SortedList<DisciplinaView> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    private boolean testaDados(){
        boolean erro = false;
        String alertmsg = "";

        DisciplinaView d = tableView.getSelectionModel().getSelectedItem();

        if(!Read.Query("from Disciplina where codigoDisciplina = '" + txCodigoDisciplina.getText() + "'").isEmpty() && !d.getCodigoDisciplina().equals(txCodigoDisciplina.getText())) {
            alertmsg += "-Disciplina com codigoDisciplina já existente\n";
            erro = true;
        }

        if(erro){
            Alert alert = new Alert(Alert.AlertType.ERROR, alertmsg);
            alert.setHeaderText("Dados inválidos!");
            alert.show();
        }

        return erro;
    }

    private void remover() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remover");
        alert.setHeaderText("Tem certeza que deseja remover?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            DisciplinaView disciplina = tableView.getSelectionModel().getSelectedItem();

            if(!Read.Query("from Turma where disciplinaId =" + disciplina.getId()).isEmpty()) {
                Alert aler = new Alert(Alert.AlertType.ERROR, "Existem Turmas cadastradas nessa Disciplina\n");
                aler.setHeaderText("Erro de dependencia!");
                aler.show();
                return;
            }

            Disciplina d = (Disciplina) Read.Query("from Disciplina where id =" + disciplina.getId()).get(0);
            d.delete();
            carregarTableView();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    private void altera() {

        if (testaDados()) return;

        try {
            String codigoDisciplina = txCodigoDisciplina.getText();
            String nome = txNome.getText();
            Departamento dep = comboBoxDepartamento.getSelectionModel().getSelectedItem();
            int creditos = comboBoxCreditos.getSelectionModel().getSelectedItem();

            DisciplinaView a = tableView.getSelectionModel().getSelectedItem();

            Update.Disciplina(a.getId(), codigoDisciplina, nome, creditos, dep.getId());
            cadastraPreRequisito(a.getId());

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

    public void cadastraDisciplina(){

        if(testaDados()){
            return;
        }

    	// TODO: verifica se todos os  campos estão selecionados
    	String nome = txNome.getText();
    	int creditos = comboBoxCreditos.getValue();
    	String codigoDisciplina = txCodigoDisciplina.getText();

    	Departamento departamento = comboBoxDepartamento.getSelectionModel().getSelectedItem();
        int departamentoId = departamento.getId();
        Disciplina d = new Disciplina(nome, creditos, departamentoId, codigoDisciplina);
        d.create();
        cadastraPreRequisito(d.getId());

        limpaCampos();
        desabilitaCampos();
        carregarTableView();
    }

    public void cadastraPreRequisito(int disciplinaId){
        Session session = Read.factory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete PreRequisito where disciplinaId= " + disciplinaId).executeUpdate();
        session.getTransaction().commit();
        session.close();
        selectedPrerequisitos = listViewPrerequisito.getSelectionModel().getSelectedItems();
        if(selectedPrerequisitos != null){
            for(Disciplina d : selectedPrerequisitos){
                System.out.println(d.getNome());
                PreRequisito preRequisito = new PreRequisito(disciplinaId, d.getId());
                preRequisito.create();
            }
        }
    }
    
    public void fecha(){
        CadastrarDisciplina.getStage().close();
    }
    
    public void abrePrincipal() {
        //Principal p = new Principal();
        fecha();
        //try {
        //    p.start(new Stage());
        //} catch (Exception ex) {
        //    Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }
}
