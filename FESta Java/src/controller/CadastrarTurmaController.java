/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Curso;
import model.Disciplina;
import model.DisciplinaCurso;
import model.Professor;
import model.ProfessorCapacidade;
import model.Read;
import model.Sala;
import model.Turma;

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
    
	private List<ProfessorCapacidade> listProfessorCapacidades = new ArrayList<>();
    
    private List<DisciplinaCurso> listDisciplinaCursos = new ArrayList<>();
    //private ObservableList<Disciplina> obsDisciplinaCursos;

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
	
	private Curso curso;
	private Disciplina disciplina;
	private Sala sala;
	private String predio;
	private Professor professor;
	
	String codigoCurso = null;
	String nomeCurso = null;
	
	String disciplinaId = null;
	String nomeDisciplina = null;
	String creditos = null;
	String departamento = null;
	
	String professorId = null; 
	String nomeProfessor = null;
	String matricula = null;
	String nivelFormacao = null;
	
	String codigoSala = null;
	String capacidade = null;
	String predioCB = null;
	
	String cursoId = null;
	
	
	
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
    	if(!comboBoxDisciplina.isDisable()) {
    		comboBoxDisciplina.setDisable(true);
    	}
    	if(!comboBoxProfessor.isDisable()) {
    		comboBoxProfessor.setDisable(true);
    	}
    	curso = comboBoxCurso.getSelectionModel().getSelectedItem();
    	codigoCurso = curso.getcodigoCurso();
    	nomeCurso = curso.getnome();
    	cursoId = String.valueOf(curso.getId());
    	//comboBoxDisciplina.set
    	carregarDisciplinaCursos();
    	
    }

    @FXML
    void SelecionarDisciplina() {
    	System.out.println("ENTREI NO SELECIONAR DISCIPLINA");
    	disciplina = comboBoxDisciplina.getSelectionModel().getSelectedItem();
    	System.out.println("ENTREI NO SELECIONAR DISCIPLINA2");
    	System.out.println("DISCIPLINA ID: "+String.valueOf(disciplina.getId()));
    	System.out.println("ENTREI NO SELECIONAR DISCIPLINA3");
    	disciplinaId = String.valueOf(disciplina.getId());
    	System.out.println("ENTREI NO SELECIONAR DISCIPLINA4");
    	nomeDisciplina = disciplina.getNome();
    	System.out.println("ENTREI NO SELECIONAR DISCIPLINA5");
    	creditos = String.valueOf(disciplina.getCreditos());
    	System.out.println("ENTREI NO SELECIONAR DISCIPLINA6");
    	departamento = disciplina.getDepartamento();
    	System.out.println("ENTREI NO SELECIONAR DISCIPLINA7");
    	carregarProfessorCapacidades();
    	System.out.println("ENTREI NO SELECIONAR DISCIPLINA8");
    	
    	
    }

    @FXML
    void SelecionarSala() {

    	sala = comboBoxSala.getSelectionModel().getSelectedItem();
    	codigoSala = sala.getCodigoSala();
    	capacidade = String.valueOf(sala.getCapacidade());
    	predio = sala.getPredio();
    	System.out.println("predioSelecionarSala: " + predio);
    	System.out.println("predioCBSelecionarSala: " + predioCB);
    	
    	//carregarPredios();
    }
    
    @FXML
    void selecionarPredio() {
    	
    	predioCB = comboBoxPredio.getSelectionModel().getSelectedItem();
    	carregarSalas();
    	
    	//carregarSalas();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	
    	carregarCursos();
    	carregarPredios();
    	
        btCancelar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            abrePrincipal();
        });
        
        btCadastrar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            cadastraTurma();
        });

        
    }
    
    public void carregarProfessorCapacidades() {
    	
    	comboBoxProfessor.getSelectionModel().clearSelection();
    	
    	listProfessorCapacidades.clear();
    	listProfessorCapacidades = Read.getProfessorCapacidade(null, disciplinaId);
    	listProfessores.clear();
    	for(ProfessorCapacidade elemento: listProfessorCapacidades){
    		   professorId = String.valueOf(elemento.getProfessorId());
    		   
    		   listProfessores.add(Read.getProfessor(professorId, null, null, null, cursoId).get(0));
    		}
    	if(obsProfessores != null) {
    		obsProfessores.clear();
    	}
    	obsProfessores = FXCollections.observableArrayList(listProfessores);
    	comboBoxProfessor.getItems().clear();
    	comboBoxProfessor.setDisable(false);
    	comboBoxProfessor.setItems(obsProfessores);
    }
    
    
    public void carregarDisciplinaCursos() {
    	
    	comboBoxDisciplina.getSelectionModel().clearSelection();
    	
    	listDisciplinaCursos.clear();
    	listDisciplinaCursos = Read.getDisciplinaCurso(cursoId, null);
    	listDisciplinas.clear();
    	for(DisciplinaCurso elemento: listDisciplinaCursos){
    		   disciplinaId = String.valueOf(elemento.getDisciplinaId());
    		   Disciplina d = Read.getDisciplina(disciplinaId, null, null, null).get(0);
    		   listDisciplinas.add(d);
    		}
    	
    	if(obsDisciplinas != null) {
    		obsDisciplinas.clear();
    	}

    	obsDisciplinas = FXCollections.observableArrayList(listDisciplinas);
    	comboBoxDisciplina.getItems().clear();
    	comboBoxDisciplina.setDisable(false);
    	comboBoxDisciplina.setItems(obsDisciplinas);
    	
    	
    }
    
    
    public void carregarCursos() {
    	listCursos.clear();
    	comboBoxCurso.getItems().clear();
    	listCursos = Read.getCurso(cursoId, codigoCurso, nomeCurso);
    	if(obsCursos != null) {
    		obsCursos.clear();
    	}
    	obsCursos = FXCollections.observableArrayList(listCursos);
    	comboBoxCurso.setItems(obsCursos);
    }
    
    public void carregarPredios() {
    	listPredios.clear();
    	listPredios = Read.getDistinctPredio();
    	if(obsPredios != null) {
    		obsPredios.clear();
    	}
    	obsPredios = FXCollections.observableArrayList(listPredios);
    	comboBoxPredio.getItems().clear();
    	comboBoxPredio.setItems(obsPredios);
    }
    
    public void carregarDisciplinas() {
    	listDisciplinas.clear();
    	listDisciplinas = Read.getDisciplina(disciplinaId, nomeDisciplina, creditos, departamento);
    	if(obsDisciplinas != null) {
    		obsDisciplinas.clear();
    	}
        obsDisciplinas = FXCollections.observableArrayList(listDisciplinas);
        comboBoxDisciplina.getItems().clear();
        comboBoxDisciplina.setItems(obsDisciplinas);
    }
    
    
    public void carregarSalas() {
    	if(!comboBoxSala.isDisable()) {
    		comboBoxSala.setDisable(true);
    	}
    	listSalas.clear();
    	System.out.println("predioCB:" + predioCB);
    	listSalas = Read.getSala( null, null, predioCB);
    	if(obsSalas != null) {
    		obsSalas.clear();
    	}
    	
    	for(Sala elemento: listSalas){
 		   String predioX = elemento.getPredio();
 		   System.out.println("PREDIOOOO: " + predioX);
 		}
    	
    	obsSalas = FXCollections.observableArrayList(listSalas);
    	comboBoxSala.getItems().clear();
    	comboBoxSala.setItems(obsSalas);
    	System.out.println();
    	comboBoxSala.setDisable(false);
    }
    
    public void cadastraTurma(){
    	
        RadioButton radio = (RadioButton) grupoSemestre.getSelectedToggle();
        int maxAlunos = Integer.parseInt(txMaxAluno.getText());
        String semestre = radio.getText();
        String ano = txAno.getText();
        int professorId = comboBoxProfessor.getSelectionModel().getSelectedItem().getUsuarioId();
        int disciplinaId = comboBoxDisciplina.getSelectionModel().getSelectedItem().getId();
        int salaId = comboBoxSala.getSelectionModel().getSelectedItem().getId();

        
               
        Turma t = new Turma(maxAlunos, ano, semestre, professorId, disciplinaId, salaId);
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
