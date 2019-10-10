/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;

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
import view.Login;
import view.Principal;
import model.Usuario;

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
        
    	String username = txEmail.getText();
    	String password = txSenha.getText();
    	
    	// pega a sessão do hibernate
    	Session session = HibernateUtil.getSession();
    	// cria caso default de usuario caso não exista no banco
    	Usuario user = null;
    	
    	if (session != null) {
    		try {
    			user = (Usuario) session.get(Usuario.class, username);
    			if (password.equals(user.getSenha())) {
    				System.out.println("User: " + user.toString()); 
    				Principal p = new Principal();
    	            fecha();
    	            
    	            p.start(new Stage());
    			} else {
    				Alert alert = new Alert(AlertType.ERROR);
    	            alert.setHeaderText("Erro");
    	            alert.setTitle("Erro");
    	            alert.setContentText("Usuario ou Senha Invalido(s)");
    	            alert.show();
    			}
    		} catch (Exception exception) {
    			System.out.println("Exception occred while reading user data: " + exception.getMessage());    			
		   }
    	} 
    	
    	
    }
}
