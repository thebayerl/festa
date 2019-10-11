/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Aluno;

/**
 *
 * @author denin
 */
public class CadastrarAluno extends Application {
    
    private static Stage stage;
    private Aluno aluno = null;
    
    public CadastrarAluno () {    
    }
    
    public CadastrarAluno (Aluno aluno) {
    	this.aluno = aluno;
    }
    
    private int id_usuario;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        CadastrarAluno.stage = stage;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
    	
        Parent root = FXMLLoader.load(getClass().getResource("/view/CadastrarAluno.fxml"));//Carrega FXML
        
        Scene scene = new Scene(root);//Coloca o FXML em uma cena
        
        // String do titulo
        String titulo = "CadastrarAluno";
        
        if (aluno != null) {
        	populaUI(aluno, scene);
        	titulo = "Update Aluno";
        }
        
        stage.setTitle(titulo);
        stage.setScene(scene);
        
        stage.show();
        
        setStage(stage);        
    }

    private void populaUI(Aluno aluno, Scene scene) {
    	
    	// popula text field
    	TextField matriculaTextview = (TextField) scene.lookup("#txMatricula");
    	matriculaTextview.setText(aluno.getMatricula());    	
		
	}

	/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
