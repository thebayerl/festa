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

/**
 *
 * @author denin
 */
public class Login extends Application {
    
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Login.stage = stage;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
    	 
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
