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
import model.Aluno;

/**
 *
 * @author denin
 */
public class CadastrarAluno extends Application {
    
    private static Stage stage;    

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        CadastrarAluno.stage = stage;
    }
    
    public void cadastrarAluno() {
    	
    }
    
    public void cadastrarAluno(Aluno aluno) {
    	//aluno.getUsuarioId();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
    	
        Parent root = FXMLLoader.load(getClass().getResource("/view/CadastrarAluno.fxml"));//Carrega FXML
        
        Scene scene = new Scene(root);//Coloca o FXML em uma cena
        
        stage.setTitle("CadastrarAluno");
        stage.setScene(scene);//Coloca a cena em uma janela
        
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
