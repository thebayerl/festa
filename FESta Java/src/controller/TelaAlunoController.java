package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.AtualizaAluno;
import view.InscricaoTurmas;
import view.VisualizarHistorico;

public class TelaAlunoController {
	 
	    @FXML  private Button btInscricaoTurma;
	    @FXML  private Button btHistorico;    
	    @FXML  private VBox layoutPrincipalTelaAluno;	    
	    @FXML  private Button btInfo;


	    @FXML
	    void initialize() {
	      
	    	this.layoutPrincipalTelaAluno.setCache(false);
	    	
	    	this.btInscricaoTurma.setOnMouseClicked((MouseEvent e)->{    		
	            this.exibeTelaInscricao();
	        });

			this.btHistorico.setOnMouseClicked((MouseEvent e)->{
				this.exibeHistorico();
			});
			
			this.btInfo.setOnMouseClicked((MouseEvent e)->{
				this.exibeInformacoes();
			});
			
	    }
	    
	    private void exibeInformacoes(){
	    	
	    	AtualizaAluno telaAtualizaAluno = new AtualizaAluno();
	    	
	    	try {
				telaAtualizaAluno.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	    }
	    
	    
	    private void exibeTelaInscricao() {	    		    
	    	
	    	InscricaoTurmas telaInscricaoTurmas = new InscricaoTurmas();
	    	
	    	try {
				telaInscricaoTurmas.start(new Stage());
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
