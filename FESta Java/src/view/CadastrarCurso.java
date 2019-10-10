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
public class CadastrarCurso extends Application {
    
    private static Stage stage;//é uma janela1

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        CadastrarCurso.stage = stage;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/CadastrarCurso.fxml"));//Carrega FXML

        Scene scene = new Scene(root);//Coloca o FXML em uma cena
        stage.setTitle("CadastrarCurso");
        stage.setScene(scene);//Coloca a cena em uma janela
        stage.show();//Abre a janela2
        setStage(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
