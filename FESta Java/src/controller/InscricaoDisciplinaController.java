package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import model.Turma;

public class InscricaoDisciplinaController {
	
	 @FXML
	    private ComboBox<?> selecTurmaComboBox;

	    @FXML
	    private Button removeTurmaComboBox;

	    @FXML
	    private Button adicionaTurmaComboBox;

	    @FXML
	    private Button DetalhesTurmaComboBox;

	    @FXML
	    private ListView<Turma> turmasSelecionadasListView;

	    @FXML
	    private Button cancelarBt;

	    @FXML
	    private Button concluirBt;
	    
	    @FXML
	    void initialize() {	       
	    	// popula a combobox de turmas
	    	
	    }
	    
	    private void populaComboTurmas() {
	    	
	    }

}
