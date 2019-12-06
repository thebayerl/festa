package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.InscricaoTurmas;
import view.VisualizarTurma;

public class TelaProfessorController {
	 
	    @FXML private Button btMinhasTurmas;
	    @FXML private Button btInfoConta;
	    
	    @FXML
	    void initialize(URL url, ResourceBundle rb) {
	      
	    	this.btMinhasTurmas.setOnMouseClicked((MouseEvent e)->{
				this.exibeTelaTurmas();
			});
	    	
	    }	
	    
	    
	    private void exibeTelaTurmas() {

			VisualizarTurma telaTurmas = new VisualizarTurma();

			try {
				telaTurmas.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    
	    

}
