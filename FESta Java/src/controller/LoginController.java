/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafxfesta.Login;
import javafxfesta.Principal;

/**
 *
 * @author denin
 */
public class LoginController implements Initializable {
    
    @FXML private Label label;
    @FXML private TextField txEmail;
    @FXML private PasswordField txSenha;
    @FXML private Button btSair;
    @FXML private Button btEntrar;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btEntrar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Entrei");
            logar();
        });
        
        btSair.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            fecha();
        });
        
    }    
    public void fecha(){
        Login.getStage().close();
    }
    
    public void logar(){
        if(txEmail.getText().equals("Adm") && txSenha.getText().equals("1234")){
            Principal p = new Principal();
            fecha();
            try {
                p.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Erro");
            alert.setTitle("Erro");
            alert.setContentText("Usuario ou Senha Inválido(s)");
            alert.show();

        }
    }
}
