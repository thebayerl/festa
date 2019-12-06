/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.*;
import org.hibernate.Session;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.Login;
import view.Principal;
import model.LoggedUser;
import model.Usuario;

/**
 *
 * @author denin
 */
public class LoginController implements Initializable {
    
    @FXML private Label erroLoginLabel;
    @FXML private TextField txEmail;
    @FXML private PasswordField txSenha;
    @FXML private Button btSair;
    @FXML private Button btEntrar;
    
    private Session session;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    	btEntrar.setOnMouseClicked((MouseEvent e)->{        	
            // faz o login
            logar();
        });
    	
    	this.txSenha.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    logar();
                }
            }
        });
    	
    	this.txEmail.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    logar();
                }
            }
        });
  
        btSair.setOnMouseClicked((MouseEvent e)->{
        	// fecha a janela
            fecha();
        });
        
        // pega a session do hibernate
        session = HibernateUtil.getSession();
    } 
    
    public void fecha(){
        Login.getStage().close();
    }
    
    public void logar(){	
    	
    	String username = txEmail.getText();
    	String password = txSenha.getText();
    	    	
    	// cria caso default de usuario caso nao exista no banco
    	Usuario user = null;
    	
    	if (session != null) {
    		try {
    			
    			// tenta buscar um usuario no banco pelo username
				@SuppressWarnings("unchecked")
				List<Usuario> results = session.createQuery("from Usuario where username = :name").setParameter("name", username).list();
				 
				System.out.println("session is not null");
    			// verifica se o usuario existe
    			if (!results.isEmpty()) {
    				
    				System.out.println("results not empty");
    				// pega o usuario com username unico - a unicidade do username deve ser garantida na inseridos
    				user = results.get(0);    				    				
    			
	    			if (password.equals(user.getSenha())) {
	    				
	    				LoggedUser.setLoggedUser(user);
	    				Principal p = new Principal(user);
	    	            fecha();
	    	            
	    	            p.start(new Stage());
	    	            
	    			} else {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Erro");
                        alert.setHeaderText("Senha incorreta");
                        alert.show();
	    			}
    			} else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText("Usu�rio n�o existe");
                    alert.show();
    			}
    		} catch (Exception exception) {
    			exception.printStackTrace();   			
		   }
    	}     	    	
    }
}
