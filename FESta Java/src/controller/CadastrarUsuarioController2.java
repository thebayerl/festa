/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Aluno;
import model.ComboBoxTipo;
import model.Professor;
import model.Usuario;
import view.CadastrarUsuario;
import view.CadastrarUsuario2;
import view.Principal;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class CadastrarUsuarioController2 implements Initializable {

    @FXML private JFXTextField tfUsername;
    @FXML private JFXTextField tfEmail;
    @FXML private JFXTextField tfSenha;
    @FXML private JFXTextField tfConfimarSenha;
    @FXML private JFXTextField tfNome;
    @FXML private JFXTextField tfMatricula;
    @FXML private JFXTextField tfFormacao;
    @FXML private JFXTextField tfRg;
    @FXML private JFXTextField tfCpf;

    private Aluno a;
    private Usuario u;
    private Professor p;
    private int usuarioId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }
    
    public void cadastraProfessor(){
    	
    	String nome = tfNome.getText();
    	String matricula = tfMatricula.getText();
    	String formacao = tfFormacao.getText();
    	//int codigoCurso = Integer.parseInt(txCodigoCursoProfessor.getText());

    	p = new Professor(usuarioId, nome, matricula, formacao, 1);
        p.create();
    	abrePrincipal();
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

    public void fecha(){
        CadastrarUsuario2.getStage().close();
    }
}
