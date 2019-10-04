/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SalaDao;
import Model.Sala;
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
import javafxfesta.CadastrarSala;
import javafxfesta.Principal;

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
        Sala s = new Sala(capacidade);
        SalaDao dao = new SalaDao();
        
        //Dao
        //
        //
        //
        //
        //
        //Dao
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
            Logger.getLogger(CadastrarDisciplinaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
