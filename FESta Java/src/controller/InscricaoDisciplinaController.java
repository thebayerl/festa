package controller;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import model.LoggedUser;
import model.Read;
import model.Turma;
import view.InscricaoTurma;

public class InscricaoDisciplinaController {
	
	private List<Turma> turmasNaoSelecionadas;
	private ObservableList<Turma> obsTurmas;	
	
	@FXML
    private ComboBox<Turma> selecTurmaComboBox;

    @FXML
    private Button removeTurmaButton;

    @FXML
    private Button adicionaTurmaButton;

    @FXML
    private Button DetalhesTurmaButton;

    @FXML
    private ListView<Turma> turmasSelecionadasListView;

    @FXML
    private Button cancelarBt;

    @FXML
    private Button concluirBt;
    
    @FXML
    void onSelecaoTurma() {
    	
    	boolean disable = false; 
    	if (this.selecTurmaComboBox.getSelectionModel().getSelectedIndex() == 0)
    		disable = true;
    	
    	this.adicionaTurmaButton.setDisable(disable);
    }
    
    @FXML
    void adicionaTurmaNaLista() {
    	
    	// pega a instancia da turma selecionada
    	Turma crrtTurma = this.selecTurmaComboBox.getSelectionModel().getSelectedItem();
    	// remove da combobox e seta a primeira posição
    	this.selecTurmaComboBox.getItems().remove(crrtTurma);
    	this.selecTurmaComboBox.getSelectionModel().clearAndSelect(0);
    	// adiciona na lista
    	this.turmasSelecionadasListView.getItems().add(crrtTurma);
    }

    @FXML
    void removeTurmaDaList() {
    	// pega a instancia da turma selecionada
    	Turma crrtTurma = this.turmasSelecionadasListView.getSelectionModel().getSelectedItem();
    	// remove da combobox
    	this.selecTurmaComboBox.getItems().add(crrtTurma);
    	// adiciona na lista
    	this.turmasSelecionadasListView.getItems().remove(crrtTurma);    	
    	this.turmasSelecionadasListView.getSelectionModel().clearSelection();
    	// desabilita botão de remover
    	this.removeTurmaButton.setDisable(true);
    }

    @FXML
    void cancelaInscricoes() {
    	InscricaoTurma.getStage().close();
    }

    @FXML
    void concluiInscricoes() {
    	
    	LoggedUser user = LoggedUser.getInstance();
    	
    	System.out.print("user " + user.getUsername());
    }
    
    @FXML
    void initialize() {	       
    	// popula a combobox de turmas
    	this.populaComboTurmas();    	
    	// verifica se houveram mudanças na seleção da listview
    	this.turmasSelecionadasListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Turma>() {           
			@Override
			public void changed(ObservableValue<? extends Turma> observable, Turma oldValue, Turma newValue) {
				// habilita botao de remover
				removeTurmaButton.setDisable(false);								
			}
		});
    }
    
    // popula a combobox com as turmas que o aluno pode se inscrever
    private void populaComboTurmas() {
    	
    	// lê as turmas
		this.turmasNaoSelecionadas = Read.getTurma();
		this.turmasNaoSelecionadas.add(0, new Turma());
		this.obsTurmas = FXCollections.observableArrayList(this.turmasNaoSelecionadas);
		// popula a combobox
		this.selecTurmaComboBox.getItems().clear();			
		this.selecTurmaComboBox.setItems(this.obsTurmas);
    }

}
