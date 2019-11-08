/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Aluno;
import model.Curso;
import model.Professor;
import model.Read;
import model.Usuario;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.CadastrarProfessor;
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
    
   
    @FXML private TextField txUserName;
    @FXML private PasswordField psSenha;
    @FXML private PasswordField psSenhaConf;
    @FXML private TextField txNome;
    @FXML private TextField txFormacao;
    @FXML private TextField txRG;
    @FXML private TextField txCPF;
    @FXML private ComboBox<Curso> comboBoxCurso;
    @FXML private Button btVoltar;
    @FXML private Button btEnviar;
    
    
    
    private List<Curso> listCursos = new ArrayList<>();
    private ObservableList<Curso> obsCursos;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	
    	carregarCursos();
    	
    	btVoltar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            abrePrincipal();
        });
        
    	btEnviar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
        	cadastraProfessor();
        });
        
    }
    
    public void carregarCursos() {
    	listCursos.clear();
    	comboBoxCurso.getItems().clear();
    	//System.out.println(cursoId + " " + codigoCurso + " " + nomeCurso );
    	listCursos = Read.getCurso(null, null, null);
    	//for(Curso elemento: listCursos){
    	//	   System.out.println(elemento.getnome());
    	//}
    	if(obsCursos != null) {
    		obsCursos.clear();
    	}
    	obsCursos = FXCollections.observableArrayList(listCursos);
    	//comboBoxCurso.getItems().clear();
    	comboBoxCurso.setItems(obsCursos);
    }
    
    public void cadastraProfessor(){
    	
    	String username = txUserName.getText();
    	String senha = psSenha.getText();
    	String senhaConf = psSenhaConf.getText();
    	
    	String rg = txRG.getText();
    	String cpf= txCPF.getText();
    	
    	String nome = txNome.getText();
    	
    	String nivelFormacao = txFormacao.getText();
    	
    	String role = "docente";
    	//int cursoId = Integer.parseInt(txCodigoCurso.getText());
    	Curso curso = comboBoxCurso.getSelectionModel().getSelectedItem();
    	int cursoId = curso.getId();
    	
    	
    	if(senha.compareTo(senhaConf) == 0) {
    		
    		Usuario u = new Usuario(username, senha, rg, cpf, role);
        	u.create();
    		int usuarioId = u.getId();
    		
       
    		Professor p = new Professor(usuarioId, nome, nivelFormacao, cursoId);
    		p.create();
    	}else {

            Alert al = new Alert(AlertType.ERROR);
            al.setHeaderText("As senhas não coincidem");
            al.show();
    	}
    	
    	
    	
        
        //abrePrincipal();
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
