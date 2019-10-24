/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Usuario;

/**
 *
 * @author denin
 */
public class Principal extends Application {
	
	private Usuario loggedUser;
	
	public Principal (Usuario user) {		
		super();
		// atribui usuario logado
		this.loggedUser = user;
	}
	
	public Principal() {
		
	}
    
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Principal.stage = stage;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
    	
        Parent root = FXMLLoader.load(getClass().getResource("/view/Principal.fxml")); //Carrega FXML
        Scene scene;
        
        System.out.println("role: " + this.loggedUser.getRole());
                
        if (this.loggedUser.getRole().compareTo("docente") == 0) {
        	// inicia tela de professores
        	scene = new Scene(new TelaProfessores());
        } else if (this.loggedUser.getRole().compareTo("discente") == 0) {
        	// inicia tela de alunos        	
        	scene = new Scene(new TelaAluno());
        } else {
        	// inicia tela padrão de erro
        	System.out.println("role desconhecida");
        	scene = new Scene(root); //Coloca o FXML em uma cena
        }        
        
        stage.setTitle("Principal");
        stage.setScene(scene); //Coloca a cena em uma janela
        
        stage.show();
        
        setStage(stage);        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
