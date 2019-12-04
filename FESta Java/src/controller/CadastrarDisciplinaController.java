/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.scene.control.*;
import model.Departamento;
import model.Disciplina;
import model.PreRequisito;
import model.Read;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
    @FXML private Button btAtualizar;
    @FXML private Button btCadastrar;
    @FXML private Button btCancelar;
    @FXML private ComboBox<Departamento> comboBoxDepartamento;
    @FXML private ComboBox<Integer> comboBoxCreditos;
    @FXML private ListView<Disciplina> listViewPrerequisito;
    
    int maxCredito = 6;
    private List<Integer> listCreditos = new ArrayList<>();
    private List<Departamento> listDepartamentos = new ArrayList<>();
    private List<Disciplina> selectedPrerequisitos = new ArrayList<>();
    private List<Disciplina> listDisciplinas = new ArrayList<>();
    private ObservableList<Integer> obsCreditos;
    private ObservableList<Departamento> obsDepartamentos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	carregarCreditos();
    	carregarDepartametos();
    	carregarPrerequisitos();

        btAtualizar.setOnMouseClicked((MouseEvent e)->{
            carregarPrerequisitos();
        });
    	
        btCancelar.setOnMouseClicked((MouseEvent e)->{
            abrePrincipal();
        });
        
        btCadastrar.setOnMouseClicked((MouseEvent e)->{
            cadastraDisciplina();
        });
    }
    
    public void carregarCreditos() {
    	for(int i = 1 ;  i <= maxCredito ; i++) {
    		listCreditos.add(i);
    	}
    	obsCreditos = FXCollections.observableArrayList(listCreditos);
    	comboBoxCreditos.setItems(obsCreditos);
    }
    
    public void carregarDepartametos() {
	listDepartamentos = Read.getDepartamento();
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

    private boolean testaDados(){
        boolean erro = false;
        String alertmsg = "";

        if(!Read.Query("from Disciplina where codigoDisciplina = '" + txCodigoDisciplina.getText() + "'").isEmpty()) {
            alertmsg += "-Disciplina com codigoDisciplina j� existente\n";
            erro = true;
        }

        if(erro){
            Alert alert = new Alert(Alert.AlertType.ERROR, alertmsg);
            alert.setHeaderText("Dados inv�lidos!");
            alert.show();
        }

        return erro;
    }

        public void cadastraDisciplina(){

        if(testaDados()){
            return;
        }

    	// TODO: verifica se todos os  campos est�o selecionados
    	String nome = txNome.getText();
    	int creditos = comboBoxCreditos.getValue();
    	String codigoDisciplina = txCodigoDisciplina.getText();

    	Departamento departamento = comboBoxDepartamento.getSelectionModel().getSelectedItem();
        int departamentoId = departamento.getId();
        Disciplina d = new Disciplina(nome, creditos, departamentoId, codigoDisciplina);
        d.create();
        cadastraPreRequisito(d.getId());
    }

    public void cadastraPreRequisito(int disciplinaId){
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
