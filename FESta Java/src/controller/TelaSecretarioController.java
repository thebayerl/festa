package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.CadastrarAluno;
import view.CadastrarDisciplina;
import view.CadastrarProfessor;
import view.CadastrarTurma;
import view.VisualizarTurma;

public class TelaSecretarioController {
	
	@FXML private Button btCadastrarProfessor;
    @FXML private Button btCadastrarAluno;
    @FXML private Button btCadastrarDisciplina;
    @FXML private Button btCadastrarTurma;
    @FXML private Button btVisualizarTurma;
    
    
    @FXML
    void initialize() {
        
    	this.btCadastrarAluno.setOnMouseClicked((MouseEvent e)->{    		
           this.initializeNewScreen(new CadastrarAluno(), "Cadastrar Aluno");
        });
    	
    	this.btCadastrarProfessor.setOnMouseClicked((MouseEvent e)->{
            this.initializeNewScreen(new CadastrarProfessor(), "Cadastrar Professor");
        });
    	
    	this.btCadastrarDisciplina.setOnMouseClicked((MouseEvent e)->{
    		this.initializeNewScreen(new CadastrarDisciplina(), "Cadastrar Disciplina");
        });
    	
    	this.btCadastrarTurma.setOnMouseClicked((MouseEvent e)->{
    		this.initializeNewScreen(new CadastrarTurma(), "Cadastrar Turma");
        });
    	
    	this.btVisualizarTurma.setOnMouseClicked((MouseEvent e)->{
    		this.initializeNewScreen(new VisualizarTurma(), "Visualizar Turma");
        });
    	    	
    }
  
    // inicializa a tela passada como parâmetro
    private void initializeNewScreen(Application screen, String tituloTela) {
    	try {
			screen.start(new Stage());
		} catch (Exception e) {
			System.out.println("Erro ao iniciar tela " + tituloTela + " " + e.getMessage() );
		}
    }

}
