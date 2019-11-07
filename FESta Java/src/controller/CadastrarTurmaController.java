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
    	//carregarProfessores();
    	
    }

    @FXML
    void SelecionarDisciplina() {
    	
    	disciplina = comboBoxDisciplina.getSelectionModel().getSelectedItem();
    	disciplinaId = String.valueOf(disciplina.getId());
    	nomeDisciplina = disciplina.getNome();
    	creditos = String.valueOf(disciplina.getCreditos());
    	departamento = disciplina.getDepartamento();
    	
    	//carregarCursos();
    	carregarProfessores();
    	
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

    @FXML
    void selecionarProfessor() {
    	
    	professor = comboBoxProfessor.getSelectionModel().getSelectedItem();
    	professorId = String.valueOf(professor.getUsuarioId());
    	nomeProfessor = professor.getNome();
    	String cursoId = String.valueOf(professor.getCursoId());
    	//String cursoIdX = String.valueOf(professor.getCursoId());
     	//if((cursoId != null && cursoId.compareTo(cursoIdX) != 0) || cursoId == null ) {
     		//System.out.println("cursoIdX: " + cursoIdX);
     		//cursoId = cursoIdX;
    		//carregarCursos();
    		
    	}
     	//carregarProfessorCapacidades();
     	//carregarDisciplinaCursos();
    	
    	
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	
    	carregarCursos();
    	carregarPredios();
    	//carregarSalas();
    	//carregarDisciplinas();
    	//carregarProfessores();
    	
    	
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
    	listProfessorCapacidades = Read.getProfessorCapacidade(professorId, disciplinaId);
    	listProfessores.clear();
    	for(ProfessorCapacidade elemento: listProfessorCapacidades){
    		   disciplinaId = elemento.getDisciplinaId();
    		   listDisciplinas.addAll(Read.getDisciplina(disciplinaId, nomeDisciplina, creditos, departamento));
    		}
    	obsDisciplinas.clear();
    	obsDisciplinas = FXCollections.observableArrayList(listDisciplinas);
    	comboBoxDisciplina.getItems().clear();
    	comboBoxDisciplina.setItems(obsDisciplinas);
    }
    
    
    public void carregarDisciplinaCursos() {
    	listDisciplinaCursos.clear();
    	listDisciplinaCursos = Read.getDisciplinaCurso(cursoId, disciplinaId);
    	listDisciplinas.clear();
    	for(DisciplinaCurso elemento: listDisciplinaCursos){
    		   disciplinaId = elemento.getDisciplinaId();
    		   listDisciplinas.addAll(Read.getDisciplina(disciplinaId, nomeDisciplina, creditos, departamento));
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
    	//System.out.println(cursoId + " " + codigoCurso + " " + nomeCurso );
    	listCursos = Read.getCurso(cursoId, codigoCurso, nomeCurso);
    	//for(Curso elemento: listCursos){
    	//	   System.out.println(elemento.getnome());
    	//}
    	if(obsCursos != null) {
    		obsCursos.clear();
    	}
    	obsCursos = FXCollections.observableArrayList(listCursos);
    	//comboBoxCurso.getItems().clear();
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
    
    public void carregarProfessores() {
    	listProfessores.clear();
    	listProfessores = Read.getProfessor(professorId, nomeProfessor, matricula, nivelFormacao,  cursoId);
    	if(obsProfessores != null) {
    		obsProfessores.clear();
    	}
        obsProfessores = FXCollections.observableArrayList(listProfessores);
        comboBoxProfessor.getItems().clear();
        comboBoxProfessor.setItems(obsProfessores);
        comboBoxProfessor.setDisable(false);
        //professor = (Professor) comboBoxProfessor.getValue();
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
        int professorId = professor.getUsuarioId();
        int disciplinaId = disciplina.getId();
        int salaId = sala.getId();
        //String codigoSala = txIdSala.getText();
        //String codigoTurma = txCodigoTurma.getText();
        
               
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
