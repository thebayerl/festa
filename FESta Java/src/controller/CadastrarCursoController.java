/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Curso;
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
import view.CadastrarCurso;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class CadastrarCursoController implements Initializable {
    
    
    @FXML private TextField txNome;
    @FXML private Button btCadastrar;
    @FXML private Button btCancelar;

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
            //System.out.println("Sai");
            cadastraCurso();
        });
    }

    public void cadastraCurso(){
        String nome = txNome.getText();
        //Curso c = new Curso(nome);        
        
    }
    public void fecha(){
        CadastrarCurso.getStage().close();
    }
    
    public void abrePrincipal(){
        Principal p = new Principal();
        fecha();
        try {
            p.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadastrarDisciplinaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}