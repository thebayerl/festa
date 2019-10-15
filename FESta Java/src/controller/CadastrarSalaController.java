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
import view.CadastrarSala;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class CadastrarSalaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TextField txCapacidade;
    @FXML private Button btCadastrar;
    @FXML private Button btCancelar;
    @FXML private TextField txCodigoSala;
    @FXML private TextField txPredio;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btCancelar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            abrePrincipal();
        });
        
        btCadastrar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            cadastraSala();
        });
        
    }
    
    public void cadastraSala(){
        int capacidade = Integer.parseInt(txCapacidade.getText());
        int predio = Integer.parseInt(txPredio.getText());
        String codigoSala = txCodigoSala.getText();
        //Create s = new Create();
        //s.Sala(codigoSala, capacidade, predio);
        Sala s = new Sala(codigoSala, capacidade, predio);
        s.create();
        abrePrincipal();
        //Sala s = new Sala(capacidade);
        
    }
    
    public void fecha(){
        CadastrarSala.getStage().close();
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
