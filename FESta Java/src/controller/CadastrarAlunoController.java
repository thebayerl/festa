/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Create;
import model.Disciplina;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.CadastrarAluno;
import view.CadastrarDisciplina;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class CadastrarAlunoController implements Initializable {
    
	 	@FXML private TextField txNome;
	    @FXML private Button btCadastrar;
	    @FXML private Button btCancelar;
	    @FXML private TextField txNascimento;
	    @FXML private TextField txMatricula;
	    @FXML private TextField txCodigoCurso;
	    @FXML private TextField txIngresso;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        btCancelar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            abrePrincipal();
        });
        
        btCadastrar.setOnMouseClicked((MouseEvent e)->{
            System.out.println("Apertei");
        	cadastraAluno();
        });
    }
    
    public void cadastraAluno(){
    	System.out.println("ENTREI no cadastra aluno cac");
        
    	String matricula = txMatricula.getText();
    	String nome = txNome.getText();
    	String dataNascimento = txNascimento.getText();
    	String dataIngresso = txIngresso.getText();
    	String codigoCurso = txCodigoCurso.getText();
    	final String sql = "SELECT max( u.id ) FROM Usuario u";
        Integer usuarioId = (Integer) HibernateUtil.getSession().createQuery( sql ).uniqueResult();
    	
        Create a = new Create();
        a.Aluno(usuarioId, matricula, nome, dataNascimento, dataIngresso, codigoCurso);
        abrePrincipal();
        
        
     //   Disciplina d = new Disciplina(nome, creditos, departamento);
     
    }
    
    public void fecha(){
        CadastrarAluno.getStage().close();
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
