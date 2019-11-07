/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;
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
    
    @FXML private ComboBox<Disciplina> comboDisciplina;
    @FXML private ComboBox<Disciplina> comboPrerequisito;
    @FXML private Button btCadastrar;
    @FXML private Button btCancelar;

    private List<Disciplina> listDisciplinas = new ArrayList<>();
    private ObservableList<Disciplina> obsDisciplinas;

    int disciplinaId;
    int prerequisitoId;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarDisciplina();

        btCancelar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            abrePrincipal();
        });
        
        btCadastrar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            cadastraPreRequisito();
        });
    }

    public void carregarDisciplina() {
        listDisciplinas = Read.getDisciplina(null, null, null, null);
        obsDisciplinas = FXCollections.observableArrayList(listDisciplinas);
        comboDisciplina.setItems(obsDisciplinas);
        comboPrerequisito.setItems(obsDisciplinas);
    }
    
    public void cadastraPreRequisito(){
        Disciplina disciplina = comboDisciplina.getSelectionModel().getSelectedItem();
        disciplinaId = disciplina.getId();
        Disciplina disciplinaPrerequisito = comboPrerequisito.getSelectionModel().getSelectedItem();
        prerequisitoId = disciplinaPrerequisito.getId();

        PreRequisito preRequisito = new PreRequisito(disciplinaId, prerequisitoId);
        preRequisito.create();
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
