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

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    @FXML private ListView<Disciplina> listViewPrerequisito;
    @FXML private Button btCadastrar;
    @FXML private Button btCancelar;

    private List<Disciplina> listDisciplinas = new ArrayList<>();
    private List<Disciplina> selectedPrerequisitos = new ArrayList<>();
    private ObservableList<Disciplina> obsDisciplinas;

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
        listViewPrerequisito.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listViewPrerequisito.setItems(obsDisciplinas);
    }
    
    public void cadastraPreRequisito(){
        int disciplinaId = comboDisciplina.getSelectionModel().getSelectedItem().getId();
        selectedPrerequisitos = listViewPrerequisito.getSelectionModel().getSelectedItems();

        for(Disciplina d : selectedPrerequisitos){
            PreRequisito preRequisito = new PreRequisito(disciplinaId, d.getId());
            preRequisito.create();
        }
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
