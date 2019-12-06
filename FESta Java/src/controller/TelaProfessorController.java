package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.AtualizaProfessor;
import view.VisualizarTurma;

public class TelaProfessorController {
	 
	    @FXML private Button btMinhasTurmas;
	    @FXML private Button btInfoConta;
	    
	    @FXML
	    void initialize() {
	      
	    	this.btMinhasTurmas.setOnMouseClicked((MouseEvent e)->{
				this.exibeTelaTurmas();
			});
	    	
	    	this.btInfoConta.setOnMouseClicked((MouseEvent e)->{
	    		exibeTelaInfo();
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
	    
	    private void exibeTelaInfo() {

			AtualizaProfessor telaInfo = new AtualizaProfessor();

			try {
				telaInfo.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    
	    

}
