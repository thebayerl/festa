/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.math.BigInteger;
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
import model.Create;
import view.CadastrarPreRequisitos;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class CadastrarPreRequisitosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TextField txIdDisciplina;
    @FXML private TextField txIdPrerequisito;
    @FXML private Button btCadastrar;
    @FXML private Button btCancelar;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        btCancelar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            abrePrincipal();
        });
        
        btCadastrar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            cadastraPreRequisito();
        });
    } 
    
    public void cadastraPreRequisito(){
        int disciplinaId = Integer.parseInt(txIdDisciplina.getText());
        int prerequisitoId = Integer.parseInt(txIdPrerequisito.getText());
        
        Create pr = new Create();
        System.out.println("Sai1");
        //pr.PreRequisito(disciplinaId, prerequisitoId);
        System.out.println("Sai2");
        abrePrincipal();
    }
    
    public void fecha(){
        CadastrarPreRequisitos.getStage().close();
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
