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
import javafx.stage.Stage;

/**
 *
 * @author denin
 */
public class CadastrarAluno extends Application {
    
    private static Stage stage;//é uma janela1
    
    private int id_usuario;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        CadastrarAluno.stage = stage;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
    	
    	//System.out.println("ENTREI NESSA BAGA�A1");
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML/CadastrarAluno.fxml"));//Carrega FXML
        //System.out.println("ENTREI NESSA BAGA�A2");
        Scene scene = new Scene(root);//Coloca o FXML em uma cena
        //System.out.println("ENTREI NESSA BAGA�A3");
        stage.setTitle("CadastrarAluno");
        stage.setScene(scene);//Coloca a cena em uma janela
        //System.out.println("ENTREI NESSA BAGA�A4");
        stage.show();//Abre a janela2
        //System.out.println("ENTREI NESSA BAGA�A5");
        setStage(stage);
        //System.out.println("ENTREI NESSA BAGA�A5");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
//    public CadastrarAluno(int id_usuario) {
//    	this.id_usuario = id_usuario;
//    }
    
}
