/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Create;
import model.Departamento;
import model.Disciplina;
import model.Professor;
import model.Read;
import model.Turma;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.CadastrarTurma;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class CadastrarTurmaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TextField txMaxAluno;
    @FXML private Button btCadastrar;
    @FXML private Button btCancelar;
    @FXML private TextField txAno;
    @FXML private ToggleGroup grupoSemestre;
    @FXML private RadioButton radioBt1;
    @FXML private RadioButton radioBt2;
    @FXML private ComboBox<Disciplina> comboBoxDisciplina;
    @FXML private ComboBox<Professor> comboBoxProfessor;
    @FXML private ComboBox<?> comboBoxPredio;
    @FXML private ComboBox<?> comboBoxSala;
    
    
    
    
    
    
    
    private List<Disciplina> listDisciplinas = new ArrayList<>();
    private ObservableList<Disciplina> obsDisciplinas;
    
    private List<Professor> listProfessores = new ArrayList<>();
    private ObservableList<Professor> obsProfessores;
    
    private Disciplina disciplina;
    private Professor professor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	
    	carregarDisciplinas();
    	carregarProfessores();
    	
        btCancelar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            abrePrincipal();
        });
        
        btCadastrar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            cadastraTurma();
        });
        
    
        
    } 
    
    public void carregarDisciplinas() {
    	listDisciplinas = Read.getDisciplina();
        obsDisciplinas = FXCollections.observableArrayList(listDisciplinas);
        comboBoxDisciplina.setItems(obsDisciplinas);
    }
    
    public void carregarProfessores() {
    	listProfessores = Read.getProfessor();
        obsProfessores = FXCollections.observableArrayList(listProfessores);
        comboBoxProfessor.setItems(obsProfessores);
        //professor = (Professor) comboBoxProfessor.getValue();
    }
    
    public void cadastraTurma(){
        
    	professor = (Professor) comboBoxProfessor.getValue();
    	disciplina = (Disciplina) comboBoxDisciplina.getValue();
    	
        RadioButton radio = (RadioButton) grupoSemestre.getSelectedToggle();
        int maxAlunos = Integer.parseInt(txMaxAluno.getText());
        String semestre = radio.getText();
        String ano = txAno.getText();
        int professorId = professor.getUsuarioId();
        String disciplinaId = disciplina.getcodigoDisciplina();
        //String codigoSala = txIdSala.getText();
        //String codigoTurma = txCodigoTurma.getText();
        
               
        //Turma t = new Turma(codigoTurma, maxAlunos, ano, semestre, professorId, disciplinaId, codigoSala);
        //t.create();
        //abrePrincipal();
            
        
        
    }
    
    
    public void fecha(){
        CadastrarTurma.getStage().close();
    }
    
    public void abrePrincipal(){
        Principal p = new Principal();
        fecha();
        try {
            p.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
