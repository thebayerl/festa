/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Create;
import model.Sala;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.CadastrarProfessor;
import view.CadastrarSala;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class CadastrarProfessorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
	@FXML private TextField txNome;
    @FXML private Button btCadastrar;
    @FXML private Button btCancelar;
    @FXML private TextField txFormacao;
    @FXML private TextField txMatricula;
    @FXML private TextField txCodigoCurso;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btCancelar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            abrePrincipal();
        });
        
        btCadastrar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
        	cadastraProfessor();
        });
        
    }
    
    public void cadastraProfessor(){
    	
    	String nome = txNome.getText();
    	String matricula = txMatricula.getText();
    	String nivelFormacao = txFormacao.getText();
    	int codigoCurso = Integer.parseInt(txCodigoCurso.getText());

    	final String sql = "SELECT max( u.id ) FROM Usuario u";
        Integer usuarioId = (Integer) HibernateUtil.getSession().createQuery( sql ).uniqueResult();
    	
        Create p = new Create();
        p.Professor(usuarioId, nome, matricula, nivelFormacao, codigoCurso);
        abrePrincipal();
        //Sala s = new Sala(capacidade);
        
    }
    
    public void fecha(){
        CadastrarProfessor.getStage().close();
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
