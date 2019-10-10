/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.CadastrarCurso;
import view.CadastrarDisciplina;
import view.CadastrarPreRequisitos;
import view.CadastrarSala;
import view.CadastrarTurma;
import view.CadastrarUsuario;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class PrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private Button btCadastrarCurso;
    @FXML private Button btCadastrarDisciplina;
    @FXML private Button btCadastrarPreRequisitos;
    @FXML private Button btCadastrarUsuario;
    @FXML private Button btCadastrarSala;
    @FXML private Button btCadastrarTurma;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btCadastrarCurso.setOnMouseClicked((MouseEvent e)->{
            abreCadastroCurso();          
        });
        
        btCadastrarDisciplina.setOnMouseClicked((MouseEvent e)->{
            abreCadastroDiscplina();
        });
        
        btCadastrarPreRequisitos.setOnMouseClicked((MouseEvent e)->{
            abreCadastroPreRequisitos();
        });
        
        btCadastrarUsuario.setOnMouseClicked((MouseEvent e)->{
            abreCadastroUsuario();              });        
        btCadastrarSala.setOnMouseClicked((MouseEvent e)->{
            abreCadastroSala(); 
        });
        
        btCadastrarTurma.setOnMouseClicked((MouseEvent e)->{
            abreCadastroTurma();
            
        });
         
    }
    public void fecha(){
        Principal.getStage().close();
    }
    
    public void abreCadastroUsuario(){
        CadastrarUsuario u = new CadastrarUsuario();
            fecha();
            try {
                u.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void abreCadastroSala(){
            CadastrarSala s = new CadastrarSala();
            fecha();
            try {
                s.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void abreCadastroTurma(){
        CadastrarTurma t = new CadastrarTurma();
            fecha();
            try {
                t.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void abreCadastroDiscplina(){
        CadastrarDisciplina d = new CadastrarDisciplina();
            fecha();
            try {
                d.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void abreCadastroCurso(){
        CadastrarCurso c = new CadastrarCurso();
            fecha();
            try {
                c.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void abreCadastroPreRequisitos(){
        CadastrarPreRequisitos pr = new CadastrarPreRequisitos();
            fecha();
            try {
                pr.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
