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
public class Principal extends Application {
    
    private static Stage stage;//Ã© uma janela1

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Principal.stage = stage;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
    	
    	//System.out.println("ENTREI NESSA BAGAÇA1");
        Parent root = FXMLLoader.load(getClass().getResource("/view/Principal.fxml"));//Carrega FXML
        //System.out.println("ENTREI NESSA BAGAÇA2");
        Scene scene = new Scene(root);//Coloca o FXML em uma cena
        //System.out.println("ENTREI NESSA BAGAÇA3");
        stage.setTitle("Principal");
        stage.setScene(scene);//Coloca a cena em uma janela
        //System.out.println("ENTREI NESSA BAGAÇA4");
        stage.show();//Abre a janela2
        //System.out.println("ENTREI NESSA BAGAÇA5");
        setStage(stage);
        //System.out.println("ENTREI NESSA BAGAÇA5");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
