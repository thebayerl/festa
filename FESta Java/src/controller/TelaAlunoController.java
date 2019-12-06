package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.InscricaoTurma;
import view.VisualizarHistorico;

public class TelaAlunoController {
	 
	    @FXML
	    private Button btInscricaoTurma;

	    @FXML
	    private Button btHistorico;
	    
	    @FXML
	    private VBox layoutPrincipalTelaAluno;

	    @FXML
	    void initialize() {
	      
	    	this.layoutPrincipalTelaAluno.setCache(false);
	    	
	    	this.btInscricaoTurma.setOnMouseClicked((MouseEvent e)->{    		
	            this.exibeTelaInscricao();
	        });

			this.btHistorico.setOnMouseClicked((MouseEvent e)->{
				this.exibeHistorico();
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

	private void exibeHistorico() {

		VisualizarHistorico historicoVisu = new VisualizarHistorico();

		try {
			historicoVisu.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
