package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.*;

public class TelaSecretarioController {
	
	@FXML private Button btCadastrarProfessor;
    @FXML private Button btCadastrarAluno;
    @FXML private Button btCadastrarAdmin;
    @FXML private Button btCadastrarDisciplina;
    @FXML private Button btCadastrarTurma;
    @FXML private Button btCadastrarCurso;
    @FXML private Button btCadastrarDepartamento;
    @FXML private Button btCadastrarSala;

    
    @FXML
    void initialize() {
        
    	this.btCadastrarDepartamento.setOnMouseClicked((MouseEvent e)->{    		
            this.initializeNewScreen(new CadastrarDepartamento(), "Cadastrar Departamento");
         });

        this.btCadastrarSala.setOnMouseClicked((MouseEvent e)->{
            this.initializeNewScreen(new CadastrarSala(), "Cadastrar Sala");
        });

        this.btCadastrarAdmin.setOnMouseClicked((MouseEvent e)->{
            this.initializeNewScreen(new CadastrarAdmin(), "Cadastrar Administrador");
        });
    	
    	this.btCadastrarCurso.setOnMouseClicked((MouseEvent e)->{    		
            this.initializeNewScreen(new CadastrarCurso(), "Cadastrar Curso");
         });
    	
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
