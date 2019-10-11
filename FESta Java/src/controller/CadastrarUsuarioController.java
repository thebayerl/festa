/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Create;
import model.Usuario;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.CadastrarAluno;
import view.CadastrarProfessor;
import view.CadastrarUsuario;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class CadastrarUsuarioController implements Initializable {
    
    
    @FXML private TextField txNome;
    @FXML private TextField txRG;
    @FXML private PasswordField psSenha;
    @FXML private PasswordField psSenhaConf;
    @FXML private TextField txCPF;
    @FXML private Button btCancelar;
    @FXML private Button btAluno;
    @FXML private Button btProfessor;
    
    
    
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
        
        btAluno.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            cadastraAluno();
        });
        
        btProfessor.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            cadastraProfessor();
        });
    }   
    
    public void cadastraUsuario(){
        String username = txNome.getText();
        String senha = psSenha.getText();
        String confirmacao = psSenhaConf.getText();
        int cpf = Integer.parseInt(txCPF.getText());
        int rg = Integer.parseInt(txRG.getText());
        //Random rand = new Random();
        //int id = rand.nextInt(100);
        final String sql = "SELECT max( u.id ) FROM Usuario u";
        Integer lastId = (Integer) HibernateUtil.getSession().createQuery( sql ).uniqueResult();
        
        int id = lastId+1;
        if(senha.equals(confirmacao)){
        	Create u = new Create();
            u.Usuario(id, username, senha, rg, cpf);                       
        }else{
            Alert al = new Alert(AlertType.ERROR);
            al.setHeaderText("As senhas não coincidem");
            al.show();
        }
    }
    
    
    public void fecha(){
        CadastrarUsuario.getStage().close();
    }
    
    public void cadastraAluno(){
    	cadastraUsuario();
        CadastrarAluno ca = new CadastrarAluno();
        fecha();
        try {
            ca.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadastrarAlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cadastraProfessor(){
    	cadastraUsuario();
    	CadastrarProfessor cp = new CadastrarProfessor();
        fecha();
        try {
            cp.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadastrarProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
