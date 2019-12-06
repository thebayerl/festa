/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import view.CadastrarAluno;
import view.Principal;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VizualizarTurmaController implements Initializable {

    //@FXML private ListView<String> listViewTurmas;
	
	@FXML private TableView<TurmaView> tableViewTurma;
	@FXML private TableView<HistoricoView> tableViewAluno;
	@FXML private TableColumn<HistoricoView, Integer> columnNumAlunos;
	@FXML private TableColumn<HistoricoView, String> columnAluno;
	@FXML private TableColumn<HistoricoView, String> columnNota;
	@FXML private TableColumn<HistoricoView, String> columnFreq;
	
    @FXML private TableColumn<TurmaView, String> columnDisciplina;
    @FXML private TableColumn<TurmaView, String> columnSala;
    @FXML private TableColumn<TurmaView, String> columnDias;
    @FXML private TableColumn<TurmaView, String> columnHorario;
    @FXML private TextField txPesquisar;
    @FXML private Button btSelecionarTurma;
    @FXML private Button btLancarNota;
    @FXML private TextField txNota;
    @FXML private TextField txFreq;


    //private List<Turma> listTurmas = new ArrayList<>();
    //private List<String> listTurmasStr = new ArrayList<>();
    private ObservableList<TurmaView> obsTurmas;
    private ObservableList<HistoricoView> obsAlunos;
    
    TurmaView t = null;

    //private List<Professor> listProfessor = new ArrayList<>();
    //private ObservableList<Professor> obsProfessor;
	    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	
    	inicializarTableColumns();
    	
    	carregarTurmas();
    	
    	btLancarNota.setOnMouseClicked((MouseEvent e) -> {
    	    lancaNota();
    	});
    	
    	btSelecionarTurma.setOnMouseClicked((MouseEvent e) -> {
    	    selecionaTurma();
    	});
	
    }
    
    private void inicializarTableColumns(){
		columnNota.setCellValueFactory(new PropertyValueFactory<>("nota"));
		columnDisciplina.setCellValueFactory(new PropertyValueFactory<>("disciplinaNome"));
		columnAluno.setCellValueFactory(new PropertyValueFactory<>("nomeAluno"));
		columnSala.setCellValueFactory(new PropertyValueFactory<>("codigoSala"));
		columnNota.setCellValueFactory(new PropertyValueFactory<>("nota"));
		columnFreq.setCellValueFactory(new PropertyValueFactory<>("frequencia"));
		columnDias.setCellValueFactory(new PropertyValueFactory<>("dias"));
		columnHorario.setCellValueFactory(new PropertyValueFactory<>("horarios"));
		//columnMaxAlunos.setCellValueFactory(new PropertyValueFactory<>("maxAlunos"));
    
    }
    private void selecionaTurma() {
    	t = tableViewTurma.getSelectionModel().getSelectedItem();
    	carregarAlunos();
    }
    
    private void lancaNota(){
    	HistoricoView a = tableViewAluno.getSelectionModel().getSelectedItem();
    	if(a == null) {
    		return;
    	}
    	
    	String nota = txNota.getText();
    	String freq = txFreq.getText();
    	String situacao = null;
    	
    	if(Double.parseDouble(nota) > 5 && Integer.parseInt(freq) > 70) {
    		situacao = "Aprovado";
    	}else {
    		situacao = "Reprovado";
    	}
    	
    	Update.Historico(a.getAlunoId(), a.getTurmaId(), Double.parseDouble(nota), Integer.parseInt(freq), situacao);
    }
    

    private void carregarAlunos() {
    	 	
    	List<HistoricoView> listHistoricoView = Read.Query("select new model.HistoricoView(a.id, t.id, h.frequencia, d.creditos, h.nota, a.nome," +
                " t.codigoTurma, t.semestre, t.ano, h.resultado, d.nome, t.dias) " +
                "from Aluno as a, Disciplina as d, Turma as t, Historico as h " +
    			"where a.id = h.alunoId and d.id = t.disciplinaId and t.id = h.turmaId and t.id = "+ t.getId());
    	
    	obsAlunos = FXCollections.observableArrayList(listHistoricoView);
        tableViewAluno.setItems(obsAlunos);
    	
		/*
		 * FilteredList<HistoricoView> filteredData = new FilteredList<>(obsAlunos, b ->
		 * true);
		 * 
		 * txPesquisar.textProperty().addListener((observable, oldValue, newValue) -> {
		 * filteredData.setPredicate(objView -> { if (newValue == null ||
		 * newValue.isEmpty()) { return true; } String lowerCaseFilter =
		 * newValue.toLowerCase();
		 * 
		 * if (objView.getNomeDisciplina().toLowerCase().indexOf(lowerCaseFilter) != -1
		 * || objView.getDias().toLowerCase().indexOf(lowerCaseFilter) != -1 ||
		 * objView.getCodigoSala().toLowerCase().indexOf(lowerCaseFilter) != -1) return
		 * true; else return false; // Does not match. }); });
		 * 
		 * SortedList<HistoricoView> sortedData = new SortedList<>(filteredData);
		 * sortedData.comparatorProperty().bind(tableViewAluno.comparatorProperty());
		 * tableViewAluno.setItems(sortedData);
		 */
    }
    
    private void carregarTurmas() {

        Usuario user = LoggedUser.getInstance();
        String userId = String.valueOf(user.getId());
        //String role = user.getRole();
        //listMatriculados = Read.getMatriculado(userId, null);
        
        List<TurmaView> listTurmaView = Read.Query("select new model.TurmaView(t.id, t.professorId, t.disciplinaId, t.salaId, " +
				"t.maxAlunos, p.nome, d.nome, s.codigoSala, t.ano, t.semestre, t.dias, t.horarios, dept.id, d.creditos, d.codigoDisciplina) " +
				"from Departamento dept, Turma t, Professor p, Sala s, Disciplina d " +
				"where t.professorId = p.id and t.disciplinaId = d.id and t.salaId = s.id and d.departamentoId = dept.id and t.professorId = " + 101);
        

        obsTurmas = FXCollections.observableArrayList(listTurmaView);
        tableViewTurma.setItems(obsTurmas);
        
        FilteredList<TurmaView> filteredData = new FilteredList<>(obsTurmas, b -> true);

        txPesquisar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(objView -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (objView.getDisciplinaNome().toLowerCase().indexOf(lowerCaseFilter) != -1 ||
                        objView.getDias().toLowerCase().indexOf(lowerCaseFilter) != -1 ||
                        objView.getCodigoSala().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true;
                else
                    return false; // Does not match.
            });
        });

        SortedList<TurmaView> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableViewTurma.comparatorProperty());
        tableViewTurma.setItems(sortedData);
        
    }

    public void fecha(){
        CadastrarAluno.getStage().close();
    }
    
    public void abrePrincipal(){
        Principal p = new Principal();
        fecha();
        try {
            p.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
