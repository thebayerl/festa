/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Usuario;
import java.net.URL;
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
    @FXML private Button btCadastrar;
    @FXML private Button btCancelar;
    
    
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
        
        btCadastrar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            cadastraUsuario();
        });
    }   
    
    public void cadastraUsuario(){
        String nome = txNome.getText();
        String senha = psSenha.getText();
        String confirmacao = psSenhaConf.getText();
        int cpf = Integer.parseInt(txCPF.getText());
        int rg = Integer.parseInt(txRG.getText());
    
        
        if(senha.equals(confirmacao)){
//            Usuario u = new Usuario(nome, senha, rg, cpf);            
//            if(dao.add(u)){
//                Alert al = new Alert(AlertType.CONFIRMATION);
//                al.setHeaderText("Usuario cadastrado");
//                abrePrincipal();
//                al.show();
//            }else{
//                Alert al = new Alert(AlertType.ERROR);
//                al.setHeaderText("Usuario não cadastrado");
//                al.show();
//            }
//        }else{
//            Alert al = new Alert(AlertType.ERROR);
//            al.setHeaderText("As senhas não coincidem");
//            al.show();
        }
    }
    
    
    public void fecha(){
        CadastrarUsuario.getStage().close();
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
