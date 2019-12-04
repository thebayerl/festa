/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import view.CadastrarTurma;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class CadastrarTurmaController implements Initializable {
    @FXML private TextField txMaxAluno;
    @FXML private TextField txAno;
    @FXML private ToggleGroup grupoSemestre;
    @FXML private ComboBox<Disciplina> comboBoxDisciplina;
    @FXML private ComboBox<Professor> comboBoxProfessor;
    @FXML private ComboBox<String> comboBoxPredio;
    @FXML private ComboBox<Sala> comboBoxSala;
    @FXML private ComboBox<Departamento> comboBoxDept;
	@FXML private Button btCadastrar;
	@FXML private Button btAlterar;
	@FXML private Button btRemover;
	@FXML private Button btConfirmar;
	@FXML private Button btCancelar;
	@FXML private RadioButton radioBt1;
	@FXML private RadioButton radioBt2;
	@FXML private TableView<TurmaView> tableView;
	@FXML private TableColumn<TurmaView, Integer> columnId;
	@FXML private TableColumn<TurmaView, String> columnDisciplina;
	@FXML private TableColumn<TurmaView, String> columnProfessor;
	@FXML private TableColumn<TurmaView, String> columnSala;
	@FXML private TableColumn<TurmaView, String> columnAno;
	@FXML private TableColumn<TurmaView, String> columnSemestre;
	@FXML private TableColumn<TurmaView, Integer> columnMaxAlunos;

	private List<ProfessorCapacidade> listProfessorCapacidades = new ArrayList<>();
	private List<DisciplinaCurso> listDisciplinaCursos = new ArrayList<>();
	private List<Departamento> listDepartamento = new ArrayList<>();
	private List<Sala> listSalas = new ArrayList<>();
	private List<String> listPredios = new ArrayList<>();
	private List<Disciplina> listDisciplinas = new ArrayList<>();
	private List<Professor> listProfessores = new ArrayList<>();
	private List<TurmaView> listTurmaView = new ArrayList<>();

	private ObservableList<Departamento> obsDepartamento;
	private ObservableList<Sala> obsSalas;
	private ObservableList<String> obsPredios;
	private ObservableList<Disciplina> obsDisciplinas;
	private ObservableList<Professor> obsProfessores;
	private ObservableList<TurmaView> obsListTurmaView;

	private ValidationSupport emptyValidator = new ValidationSupport();
	private ValidationSupport regexValidator = new ValidationSupport();

	String disciplinaId = null;
	String professorId = null;
	String predioCB = null;
	String cursoId = null;
	
	String acao = null;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		inicializarValidators();
		inicializarTextFieldLimitations();
		inicializarTableColumns();
		carregarTableView();
		carregarDepartamentos();
		carregarPredios();

		
		btCancelar.setOnMouseClicked((MouseEvent e) -> {
			limpaCampos();
			desabilitaCampos();
			habilitaTableView();
		});
		
		btConfirmar.setOnMouseClicked((MouseEvent e) -> {
			realizaAcao();
		});
		
		btCadastrar.setOnMouseClicked((MouseEvent e)->{
			acao = "Cadastrar";
			habilitaTodosCampos();
		});
		
		btRemover.setOnMouseClicked((MouseEvent e)->{
			if (!tableView.getSelectionModel().isEmpty()) {
				remover();
			}
		});
		
		btAlterar.setOnMouseClicked((MouseEvent e)->{
			acao = "Alterar";
			if (!tableView.getSelectionModel().isEmpty()) {
				habilitaCamposAlteracao();
				
			}
		});
	}

	private void inicializarValidators(){
		//Campos obrigatórios
		emptyValidator.registerValidator(comboBoxDept, Validator.createEmptyValidator(comboBoxDept.getPromptText()));
		emptyValidator.registerValidator(txMaxAluno, Validator.createEmptyValidator(txMaxAluno.getPromptText()));
		emptyValidator.registerValidator(txAno, Validator.createEmptyValidator(txAno.getPromptText()));
		emptyValidator.registerValidator(comboBoxDisciplina, Validator.createEmptyValidator(comboBoxDisciplina.getPromptText()));
		emptyValidator.registerValidator(comboBoxProfessor, Validator.createEmptyValidator(comboBoxProfessor.getPromptText()));
		emptyValidator.registerValidator(comboBoxPredio, Validator.createEmptyValidator(comboBoxPredio.getPromptText()));
		emptyValidator.registerValidator(comboBoxSala, Validator.createEmptyValidator(comboBoxSala.getPromptText()));
		emptyValidator.registerValidator(comboBoxDept, Validator.createEmptyValidator(comboBoxDept.getPromptText()));

		regexValidator.registerValidator(txAno, Validator.createRegexValidator(txAno.getPromptText(), "[0-9]{4}", Severity.ERROR));
	}

	private void inicializarTextFieldLimitations(){
		/*txAno.setIntegerField();
		txAno.setMaxLength(4);
		txMaxAluno.setIntegerField();
		txMaxAluno.setMaxLength(3);*/
	}

	private void inicializarTableColumns(){
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnDisciplina.setCellValueFactory(new PropertyValueFactory<>("disciplinaNome"));
		columnProfessor.setCellValueFactory(new PropertyValueFactory<>("professorNome"));
		columnSala.setCellValueFactory(new PropertyValueFactory<>("codigoSala"));
		columnAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
		columnSemestre.setCellValueFactory(new PropertyValueFactory<>("semestre"));
		columnMaxAlunos.setCellValueFactory(new PropertyValueFactory<>("maxAlunos"));
	}
	
	private void habilitaTableView() {
		//txPesquisar.setDisable(false);
		tableView.setDisable(false);
	}
	
	private void habilitaTodosCampos() {
		
		comboBoxDept.setDisable(false);
		comboBoxPredio.setDisable(false);
		radioBt1.setDisable(false);
		radioBt2.setDisable(false);
		txMaxAluno.setDisable(false);
	    txAno.setDisable(false);

		btCancelar.setDisable(false);
		btConfirmar.setDisable(false);
		
		btAlterar.setDisable(true);
		btRemover.setDisable(true);
		btCadastrar.setDisable(true);
		
		
	}
	
	
	private void limpaCampos() {
		
		comboBoxDept.setValue(null);
		comboBoxDisciplina.setValue(null);
		comboBoxProfessor.setValue(null);
		comboBoxPredio.setValue(null);
		comboBoxSala.setValue(null);
		
		
		txMaxAluno.clear();
		txAno.clear();
		
	}
	
	private void desabilitaCampos() {

		comboBoxDept.setDisable(true);
		comboBoxDisciplina.setDisable(true);
		comboBoxProfessor.setDisable(true);
		comboBoxPredio.setDisable(true);
		comboBoxSala.setDisable(true);
		txMaxAluno.setDisable(true);
		txAno.setDisable(true);
		
		radioBt1.setDisable(true);
		radioBt2.setDisable(true);

		btCancelar.setDisable(true);
		btConfirmar.setDisable(true);

		btAlterar.setDisable(false);
		btRemover.setDisable(false);
		btCadastrar.setDisable(false);

	}
	
	private void realizaAcao() {
		if (acao.equalsIgnoreCase("Alterar")) {
			alteraTurma();
		} else if (acao.equalsIgnoreCase("Cadastrar")) {
			cadastraTurma();
		}
	}
	
	
	private void alteraTurma() {
		if (errorsDialog()) return;
		if (testaDados()) return;

		try {

			TurmaView t = tableView.getSelectionModel().getSelectedItem();
			
			String maxAlunos = txMaxAluno.getText();
			String ano = txAno.getText();

			RadioButton radio = (RadioButton) grupoSemestre.getSelectedToggle();
			
			String semestre = radio.getText();
			Update.Turma(t.getId(),Integer.parseInt(maxAlunos) , ano, semestre, t.getProfessorId(), t.getDisciplinaId(),
					t.getSalaId());

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Turma alterado com sucesso!");
			alert.show();
			
			limpaCampos();
			carregarTableView();
			desabilitaCampos();

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR,
					e.getMessage(),
					ButtonType.OK);
			alert.show();
		}
		carregarTableView();
		
		
	}
	
	private void habilitaCamposAlteracao() {
		
		TurmaView turma = tableView.getSelectionModel().getSelectedItem();
		Departamento dept = (Departamento) Read.Query("from Departamento where id = " + turma.getDepartamentoId().toString()).get(0);
		Professor p = Read.getProfessor(turma.getProfessorId().toString(), null, null, null, null).get(0);
		Disciplina d = Read.getDisciplina(turma.getDisciplinaId().toString(), null, null, null).get(0);
		Sala s = Read.getSala(turma.getCodigoSala(), null, null).get(0);

		comboBoxDept.setDisable(false);
		comboBoxDisciplina.setDisable(false);
		comboBoxProfessor.setDisable(false);
		comboBoxPredio.setDisable(false);
		comboBoxSala.setDisable(false);
		txMaxAluno.setDisable(false);
		txAno.setDisable(false);
		radioBt1.setDisable(false);
		radioBt2.setDisable(false);
		
		btCadastrar.setDisable(true);
		btAlterar.setDisable(true);
		btRemover.setDisable(true);
		
		btConfirmar.setDisable(false);
		btCancelar.setDisable(false);
		
		
		comboBoxDept.setValue(dept);
		comboBoxDisciplina.setValue(d);
		comboBoxProfessor.setValue(p);
		comboBoxPredio.setValue(s.getPredio());
		comboBoxSala.setValue(s);
		txMaxAluno.setText(turma.getMaxAlunos().toString());
		txAno.setText(turma.getAno());

		if(turma.getSemestre().equalsIgnoreCase("1º")) {
			radioBt1.setSelected(true);
		}else {
			radioBt2.setSelected(true);
		}
	}
	
	private void remover() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Remover");
		alert.setHeaderText("Tem certeza que deseja remover?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			TurmaView t = tableView.getSelectionModel().getSelectedItem();
			
			Turma turma = Read.getTurma(t.getId().toString(), null, null, null, null, null, null,
					null).get(0);
			turma.delete();
			carregarTableView();
		} else {
			// ... user chose CANCEL or closed the dialog
		}
	}

	private void carregarTableView(){
		listTurmaView.clear();

		listTurmaView = Read.Query("select new model.TurmaView(t.id, t.professorId, t.disciplinaId, t.salaId, " +
									"t.maxAlunos, p.nome, d.nome, s.codigoSala, t.ano, t.semestre, d.id) " +
									"from Departamento d, Turma t, Professor p, Sala s, Disciplina d " +
									"where t.professorId = p.id and t.disciplinaId = d.id and t.salaId = s.id and d.departamentoId = d.id");

		if(obsListTurmaView != null) {
			obsListTurmaView.clear();
		}
		obsListTurmaView = FXCollections.observableArrayList(listTurmaView);
		tableView.setItems(obsListTurmaView);
	}
    
    public void carregarProfessorCapacidades() {
    	comboBoxProfessor.getSelectionModel().clearSelection();
    	
    	listProfessorCapacidades.clear();
    	listProfessorCapacidades = Read.getProfessorCapacidade(null, disciplinaId);
    	listProfessores.clear();
    	for(ProfessorCapacidade elemento: listProfessorCapacidades){
    		   professorId = String.valueOf(elemento.getProfessorId());
    		   System.out.println(professorId);
    		   listProfessores.add(Read.getProfessor(professorId, null, null, null, null).get(0));
    		}
    	if(obsProfessores != null) {
    		obsProfessores.clear();
    	}
    	
    	comboBoxProfessor.setValue(null);
    	
    	obsProfessores = FXCollections.observableArrayList(listProfessores);
    	comboBoxProfessor.getItems().clear();
    	comboBoxProfessor.setDisable(false);
    	comboBoxProfessor.setItems(obsProfessores);
    }
    
    
    public void carregarDisciplinaCursos() {
    	
    	comboBoxDisciplina.getSelectionModel().clearSelection();
    	
    	listDisciplinaCursos.clear();
    	listDisciplinaCursos = Read.getDisciplinaCurso(cursoId, null);
    	listDisciplinas.clear();
    	for(DisciplinaCurso elemento: listDisciplinaCursos){
    		   disciplinaId = String.valueOf(elemento.getDisciplinaId());
    		   Disciplina d = Read.getDisciplina(disciplinaId, null, null, null).get(0);
    		   listDisciplinas.add(d);
    		}
    	
    	if(obsDisciplinas != null) {
    		obsDisciplinas.clear();
    	}
    	
    	comboBoxDisciplina.setValue(null);

    	obsDisciplinas = FXCollections.observableArrayList(listDisciplinas);
    	comboBoxDisciplina.getItems().clear();
    	comboBoxDisciplina.setDisable(false);
    	comboBoxDisciplina.setItems(obsDisciplinas);
    }
    
    
    public void carregarDepartamentos() {
    	listDepartamento.clear();
    	comboBoxDept.getItems().clear();
    	//listDepartamento = Read.getCurso(cursoId, null, null, null);
    	if(obsDepartamento != null) {
    		obsDepartamento.clear();
    	}
    	obsDepartamento = FXCollections.observableArrayList(listDepartamento);
    	comboBoxDept.setItems(obsDepartamento);
    }
    
    public void carregarPredios() {
    	listPredios.clear();
    	listPredios = Read.getDistinctPredio();
    	if(obsPredios != null) {
    		obsPredios.clear();
    	}
    	obsPredios = FXCollections.observableArrayList(listPredios);
    	comboBoxPredio.getItems().clear();
    	comboBoxPredio.setItems(obsPredios);
    }
    
    public void carregarSalas() {
    	if(!comboBoxSala.isDisable()) {
    		comboBoxSala.setDisable(true);
    	}
    	listSalas.clear();
    	listSalas = Read.getSala( null, null, predioCB);
    	if(obsSalas != null) {
    		obsSalas.clear();
    	}
    	
    	for(Sala elemento: listSalas){
 		   String predioX = elemento.getPredio();
 		   System.out.println("PREDIOOOO: " + predioX);
 		}
    	
    	comboBoxSala.setValue(null);
    	
    	obsSalas = FXCollections.observableArrayList(listSalas);
    	comboBoxSala.getItems().clear();
    	comboBoxSala.setItems(obsSalas);
    	System.out.println();
    	comboBoxSala.setDisable(false);
    }

	private Boolean errorsDialog(){
		List<ValidationMessage> emptyFieldsError = new ArrayList<>(emptyValidator.getValidationResult().getErrors());
		List<ValidationMessage> regexFieldsError = new ArrayList<>(regexValidator.getValidationResult().getErrors());
		List<ValidationMessage> regexFieldsErrorCopy = new ArrayList<>(regexValidator.getValidationResult().getErrors());

		String emptyFieldsMessage = "Campos obrigatórios vazios:";
		String regexFieldsMessage = "Campos não preenchidos corretamente:";
		String dialogMessage = "";
		boolean emptyFieldsErrorBool = false, regexFieldsErrorBool = false;

		for(ValidationMessage o : regexFieldsErrorCopy){
			if(emptyFieldsError.contains(o)) regexFieldsError.remove(o);
		}

		if(!emptyFieldsError.isEmpty()) {
			emptyFieldsErrorBool = true;
			for(ValidationMessage erro : emptyFieldsError)	emptyFieldsMessage += "\n  - " + erro.getText();
			emptyFieldsMessage += "\n\n";
		}
		else emptyFieldsMessage = "";

		if(!regexFieldsError.isEmpty()) {
			regexFieldsErrorBool = true;
			for(ValidationMessage erro : regexFieldsError)	regexFieldsMessage += "\n  - " + erro.getText();
		}
		else regexFieldsMessage = "";

		if(emptyFieldsErrorBool || regexFieldsErrorBool){
			dialogMessage = emptyFieldsMessage + regexFieldsMessage;

			Alert alert = new Alert(Alert.AlertType.ERROR,
					dialogMessage,
					ButtonType.OK);
			alert.setTitle("Erro");
			alert.setHeaderText("Não foi possível efetuar o cadastro");
			alert.show();
			return true;
		}
		return false;
	}


	private boolean testaDados(){
		boolean erro = false;
		String alertmsg = "";
		List<Sala> sala = Read.Query("from Sala where codigoSala = '" + comboBoxSala.getSelectionModel().getSelectedItem() + "'");

		int tam = sala.get(0).getCapacidade();

		if(Integer.parseInt(txMaxAluno.getText()) > tam) {
			alertmsg += "-A sala suporta no máximo " + tam + " alunos\n";
			erro = true;
		}

		if(erro){
			Alert alert = new Alert(Alert.AlertType.ERROR, alertmsg);
			alert.setHeaderText("Dados inválidos!");
			alert.show();
		}

		return erro;
	}

    public void cadastraTurma(){

    	if(errorsDialog()) {
    		return;
    	}
    	
		if(testaDados()){
			return;
		}

    	try {
			RadioButton radio = (RadioButton) grupoSemestre.getSelectedToggle();
			int maxAlunos = Integer.parseInt(txMaxAluno.getText());
			String semestre = radio.getText();
			String ano = txAno.getText();
			int professorId = comboBoxProfessor.getSelectionModel().getSelectedItem().getUsuarioId();
			int disciplinaId = comboBoxDisciplina.getSelectionModel().getSelectedItem().getId();
			int salaId = comboBoxSala.getSelectionModel().getSelectedItem().getId();

			Turma t = new Turma(maxAlunos, ano, semestre, professorId, disciplinaId, salaId);
			t.create();

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText("Turma cadastrada com sucesso!");
			alert.show();

			limpaCampos();
			carregarTableView();
			desabilitaCampos();
		} catch (Exception e){
			Alert alert = new Alert(Alert.AlertType.ERROR,
					e.getMessage(),
					ButtonType.OK);
			alert.show();
		}
    }
    
    
    public void fecha(){
        CadastrarTurma.getStage().close();
    }
    
    public void abrePrincipal(){
        //Principal p = new Principal();
        fecha();
//        try {
//			p.start(new Stage());
//		} catch (Exception ex) {
//			Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
//		}
    }

	@FXML
	void SelecionarDepartamento() {
		if(!comboBoxDisciplina.isDisable()) {
			comboBoxDisciplina.setDisable(true);
		}
		if(!comboBoxProfessor.isDisable()) {
			comboBoxProfessor.setDisable(true);
		}
		
		Departamento c = comboBoxDept.getSelectionModel().getSelectedItem();
		
		if(c != null) {
			cursoId = String.valueOf(c.getId());
			System.out.println(cursoId);
			carregarDisciplinaCursos();
		}
		
	}

	@FXML
	void SelecionarDisciplina() {
		Disciplina disciplina = comboBoxDisciplina.getSelectionModel().getSelectedItem();
		if(disciplina != null){
			disciplinaId = String.valueOf(disciplina.getId());
			carregarProfessorCapacidades();
		}
	}

	@FXML
	void selecionarPredio() {
		predioCB = comboBoxPredio.getSelectionModel().getSelectedItem();
		carregarSalas();
	}
}
