package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.CadastrarAluno;
import view.InscricaoTurma;
import view.VisualizarTurma;

public class TelaAlunoController {
	 
	    @FXML
	    private Button btInscricaoTurma;

	    @FXML
	    private Button btVerMinhasTurmas;
	    
	    @FXML
	    private VBox layoutPrincipalTelaAluno;

	    @FXML
	    void initialize() {
	      
	    	this.layoutPrincipalTelaAluno.setCache(false);
	    	
	    	this.btInscricaoTurma.setOnMouseClicked((MouseEvent e)->{    		
	            this.exibeTelaInscricao();
	        });

			this.btVerMinhasTurmas.setOnMouseClicked((MouseEvent e)->{
				this.exibeTelaTurmas();
			});
	    }
	    
	    private void exibeTelaInscricao() {	    		    
	    	
	    	InscricaoTurma telaInscricaoTurma = new InscricaoTurma();
	    	
	    	try {
				telaInscricaoTurma.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
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
