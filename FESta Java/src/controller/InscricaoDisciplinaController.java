package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import model.Aluno;
import model.Curso;
import model.Disciplina;
import model.DisciplinaCurso;
import model.LoggedUser;
import model.Matriculado;
import model.Read;
import model.Turma;
import view.InscricaoTurma;

public class InscricaoDisciplinaController {
	
	private int cursoId;
	Disciplina disciplina;
	int userId;
	
	private List<DisciplinaCurso> listDisciplinaCursos = new ArrayList<>();
	private ObservableList<Disciplina> obsDisciplina;
	
	private List<Disciplina> listDisciplinas = new ArrayList<>();
	
	private List<Turma> turmasNaoSelecionadas;
	private ObservableList<Turma> obsTurmas;	
	
	@FXML private ComboBox<Turma> selecTurmaComboBox;
    @FXML private Button removeTurmaButton;
    @FXML private Button adicionaTurmaButton;
    @FXML private Button DetalhesTurmaButton;
    @FXML private ListView<Turma> turmasSelecionadasListView;
    @FXML private Button cancelarBt;
    @FXML private Button concluirBt;
    @FXML private Button btDisciplinasCurso;
    @FXML private Button btTodasDisciplinas;
    @FXML private ComboBox<Disciplina> comboBoxDisciplina;
   
    
    @FXML
    void SelecionarDisciplina() {
    	
    	disciplina = comboBoxDisciplina.getSelectionModel().getSelectedItem();
    	
    	populaComboTurmas();

    	
    }
    
    
    @FXML
    void onSelecaoTurma() {
    	
    	boolean disable = false; 
    	if (this.selecTurmaComboBox.getSelectionModel().getSelectedIndex() == 0)
    		disable = true;
    	
    	this.adicionaTurmaButton.setDisable(disable);
    }
    
    @FXML
    void adicionaTurmaNaLista() {
    	
    	// pega a instancia da turma selecionada
    	Turma crrtTurma = this.selecTurmaComboBox.getSelectionModel().getSelectedItem();
    	// remove da combobox e seta a primeira posição
    	this.selecTurmaComboBox.getItems().remove(crrtTurma);
    	this.selecTurmaComboBox.getSelectionModel().clearAndSelect(0);
    	// adiciona na lista
    	this.turmasSelecionadasListView.getItems().add(crrtTurma);
    }

    @FXML
    void removeTurmaDaList() {
    	// pega a instancia da turma selecionada
    	Turma crrtTurma = this.turmasSelecionadasListView.getSelectionModel().getSelectedItem();
    	// remove da combobox
    	this.selecTurmaComboBox.getItems().add(crrtTurma);
    	// adiciona na lista
    	this.turmasSelecionadasListView.getItems().remove(crrtTurma);    	
    	this.turmasSelecionadasListView.getSelectionModel().clearSelection();
    	// desabilita botão de remover
    	this.removeTurmaButton.setDisable(true);
    }

    @FXML
    void cancelaInscricoes() {
    	InscricaoTurma.getStage().close();
    }

    @FXML
    void concluiInscricoes() {
    	    	    	
    	// impede o usuario de alterar a lista
    	this.turmasSelecionadasListView.setDisable(true);
    	// pega o id do usuario logado
    	
    	
    	
    	
    	// salva a matricula do aluno
    	for (Turma turma : this.turmasSelecionadasListView.getItems()) {
    		Matriculado novaMatricula = new Matriculado(userId, turma.getId());    		
    		novaMatricula.create();
    	}
    	
    	//InscricaoTurma.getStage().close();
    }
    
    @FXML
    void initialize() {	       
    	// popula a combobox de turmas
    	//this.populaComboTurmas();    	
    	// verifica se houveram mudanças na seleção da listview
    	
    	btDisciplinasCurso.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
    		System.out.println("APERTEI O BOTÃO DMC");
    		carregarDisciplinasMeuCurso();
    		
        });
    	
//    	btTodasDisciplinas.setOnMouseClicked((MouseEvent e)->{
//            //System.out.println("Sai");
//    		System.out.println("APERTEI O BOTÃO TD");
//    		carregarTodasDisciplinas();
//    		
//        });j
    	
    	btDisciplinasCurso.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
    		System.out.println("APERTEI O BOTÃO DMC");
    		carregarDisciplinasMeuCurso();
    		
        });
    	
    	this.turmasSelecionadasListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Turma>() {           
			@Override
			public void changed(ObservableValue<? extends Turma> observable, Turma oldValue, Turma newValue) {
				// habilita botao de remover
				removeTurmaButton.setDisable(false);								
			}
		});
    }
    
    private void carregarDisciplinas(String cursoIdString){
    	if(obsDisciplina != null) {
			obsDisciplina.clear();
		}
		listDisciplinas.clear();
		listDisciplinaCursos.clear();
		Disciplina d;
		Curso c;
		
		//System.out.println("cursoID: "+  cursoIdString);
		
		System.out.println(listDisciplinaCursos);
		listDisciplinaCursos = Read.getDisciplinaCurso(cursoIdString, null);
		String disciplinaId;
		String cursoIdX;
		for(DisciplinaCurso elemento: listDisciplinaCursos){
			disciplinaId = String.valueOf(elemento.getDisciplinaId());
			System.out.println("DISCIPLINAID: "+disciplinaId);
	
			d = Read.getDisciplina(disciplinaId, null, null, null).get(0);
			cursoIdX = String.valueOf(elemento.getCursoId());
			c = Read.getCurso(cursoIdX, null, null, null).get(0);
			
			
			listDisciplinas.add(d);
		}
		System.out.println(listDisciplinaCursos);
		
		
		obsDisciplina = FXCollections.observableArrayList(listDisciplinas);
		comboBoxDisciplina.getItems().clear();
		comboBoxDisciplina.setDisable(false);
		comboBoxDisciplina.setItems(obsDisciplina);
    }
    
 ///   private void carregarTodasDisciplinas() {
 //   	
 //   	carregarDisciplinas(null);
 //  }

    
    private void carregarDisciplinasMeuCurso(){
    	System.out.println("ENTREI DISCIPLINASCURSO");
    	
    	userId = LoggedUser.getInstance().getId();  
    	String userIdString = String.valueOf(userId);
    	
    	Aluno aluno = Read.getAluno(userIdString, null, null, null, null, null).get(0);
    	cursoId = aluno.getCursoId();
    	
    	System.out.println("CURSOID É: "+ cursoId);
    	
    	String cursoIdString = String.valueOf(cursoId);
    	
    	carregarDisciplinas(cursoIdString);
    	
    	
    }
    
    // popula a combobox com as turmas que o aluno pode se inscrever
    private void populaComboTurmas() {
    	
    	selecTurmaComboBox.setDisable(false);
    	
    	String disciplinaId = String.valueOf(disciplina.getId());
    	// lê as turmas
		this.turmasNaoSelecionadas = Read.getTurma(null, null, null, null, null, null, disciplinaId, null);
		this.turmasNaoSelecionadas.add(0, new Turma());
		this.obsTurmas = FXCollections.observableArrayList(this.turmasNaoSelecionadas);
		// popula a combobox
		this.selecTurmaComboBox.getItems().clear();			
		this.selecTurmaComboBox.setItems(this.obsTurmas);
    }

}
