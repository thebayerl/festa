/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Create;
import model.Turma;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.CadastrarTurma;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class CadastrarTurmaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TextField txMaxAluno;
    @FXML private TextField txIdDisciplina;
    @FXML private TextField txIdProfessor;
    @FXML private TextField txIdSala;
    @FXML private Button btCadastrar;
    @FXML private Button btCancelar;
    @FXML private TextField txAno;
    @FXML private TextField txCodigoTurma;
    @FXML private ToggleGroup grupoSemestre;
    @FXML private RadioButton radioBt1;
    @FXML private RadioButton radioBt2;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btCancelar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            abrePrincipal();
        });
        
        btCadastrar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            cadastraTurma();
        });
        
    
        
    } 
    public void cadastraTurma(){
        
        RadioButton radio = (RadioButton) grupoSemestre.getSelectedToggle();
        int maxAlunos = Integer.parseInt(txMaxAluno.getText());
        String semestre = radio.getText();
        String ano = txAno.getText();
        int professorId = Integer.parseInt(txIdProfessor.getText());
        int disciplinaId = Integer.parseInt(txIdDisciplina.getText());
        String codigoSala = txIdSala.getText();
        String codigoTurma = txCodigoTurma.getText();
        
        Create t = new Create();
        t.Turma(codigoTurma, maxAlunos, ano, semestre, professorId, disciplinaId, codigoSala);
        abrePrincipal();
        //Turma t = new Turma(max_alunos, semestre, ano, professor_id, disciplina_id, sala_id);       
        
        
    }
    
    
    public void fecha(){
        CadastrarTurma.getStage().close();
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
