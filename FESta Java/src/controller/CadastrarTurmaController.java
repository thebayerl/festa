/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.ComboBoxTipo;
import model.Create;
import model.Curso;
import model.Departamento;
import model.Disciplina;
import model.Professor;
import model.Read;
import model.Sala;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
    
	private Curso curso;
	private Disciplina disciplina;
	private Sala sala;
	private String predio;
	private Professor professor;
	
	
    @FXML private TextField txMaxAluno;
    @FXML private Button btCadastrar;
    @FXML private Button btCancelar;
    @FXML private TextField txAno;
    @FXML private ToggleGroup grupoSemestre;
    @FXML private RadioButton radioBt1;
    @FXML private RadioButton radioBt2;
    @FXML private ComboBox<Disciplina> comboBoxDisciplina;
    @FXML private ComboBox<Professor> comboBoxProfessor;
    @FXML private ComboBox<String> comboBoxPredio;
    @FXML private ComboBox<Sala> comboBoxSala;
    @FXML private ComboBox<Curso> comboBoxCurso;
    
    
    @FXML
    void SelecionarCurso() {
    	
    	curso = comboBoxCurso.getSelectionModel().getSelectedItem();
    	
    }

    @FXML
    void SelecionarDisciplina() {
    	
    	disciplina = comboBoxDisciplina.getSelectionModel().getSelectedItem();
    	
    	
    }

    @FXML
    void SelecionarSala() {
    	
    	sala = comboBoxSala.getSelectionModel().getSelectedItem();

    }
    
    @FXML
    void selecionarPredio() {
    	
    	predio = comboBoxPredio.getSelectionModel().getSelectedItem();
    }

    @FXML
    void selecionarProfessor() {
    	
    	professor = comboBoxProfessor.getSelectionModel().getSelectedItem();;
    }

    private List<Curso> listCursos = new ArrayList<>();
    private ObservableList<Curso> obsCursos;
    
    private List<Sala> listSalas = new ArrayList<>();
    private ObservableList<Sala> obsSalas;
    
    private List<String> listPredios = new ArrayList<>();
    private ObservableList<String> obsPredios;
    
    private List<Disciplina> listDisciplinas = new ArrayList<>();
    private ObservableList<Disciplina> obsDisciplinas;
    
    private List<Professor> listProfessores = new ArrayList<>();
    private ObservableList<Professor> obsProfessores;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	
    	carregarCursos();
    	carregarPredios();
    	carregarDisciplinas();
    	carregarProfessores();
    	//carregarSalas();
    	
        btCancelar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            abrePrincipal();
        });
        
        btCadastrar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            cadastraTurma();
        });
        
    
        
    } 
    
    public void carregarCursos() {
    	listCursos = Read.getCurso(null, null);
    	obsCursos = FXCollections.observableArrayList(listCursos);
    	comboBoxCurso.setItems(obsCursos);
    }
    
    public void carregarPredios() {
    	listPredios = Read.getDistinctPredio();
    	obsPredios = FXCollections.observableArrayList(listPredios);
    	comboBoxPredio.setItems(obsPredios);
    }
    
    public void carregarDisciplinas() {
    	
    	listDisciplinas = Read.getDisciplina(null, null, null, null);
        obsDisciplinas = FXCollections.observableArrayList(listDisciplinas);
        comboBoxDisciplina.setItems(obsDisciplinas);
    }
    
    public void carregarProfessores() {
    	
    	listProfessores = Read.getProfessor(null, null, null, null, null);
        obsProfessores = FXCollections.observableArrayList(listProfessores);
        comboBoxProfessor.setItems(obsProfessores);
        //professor = (Professor) comboBoxProfessor.getValue();
    }
    
    public void carregarSalas() {
    	
    	//String disponibilidade = "true";
    	
    	listSalas = Read.getSala(null, null, null, null);
    	obsSalas = FXCollections.observableArrayList(listSalas);
    	comboBoxSala.setItems(obsSalas);
    }
    
    public void cadastraTurma(){
    	
        RadioButton radio = (RadioButton) grupoSemestre.getSelectedToggle();
        int maxAlunos = Integer.parseInt(txMaxAluno.getText());
        String semestre = radio.getText();
        String ano = txAno.getText();
        int professorId = professor.getUsuarioId();
        String disciplinaId = disciplina.getcodigoDisciplina();
        String codigoSala = sala.getCodigoSala();
        //String codigoSala = txIdSala.getText();
        //String codigoTurma = txCodigoTurma.getText();
        
               
        Turma t = new Turma(maxAlunos, ano, semestre, professorId, disciplinaId, codigoSala);
        t.create();
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
