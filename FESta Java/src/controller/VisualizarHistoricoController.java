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
import model.*;
import model.HistoricoView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VisualizarHistoricoController implements Initializable {

    @FXML private TableView<HistoricoView> tableView;
    @FXML private TableColumn<HistoricoView, String> columnTurma;
    @FXML private TableColumn<HistoricoView, Integer> columnCreditos;
    @FXML private TableColumn<HistoricoView, String> columnDisciplina;
    @FXML private TableColumn<HistoricoView, Double> columnNota;
    @FXML private TableColumn<HistoricoView, String> columnSemestre;
    @FXML private TableColumn<HistoricoView, Integer> columnAno;
    @FXML private TableColumn<HistoricoView, String> columnSituacao;
    @FXML private TextField txDisciplina;
    @FXML private TextField txAno;
    @FXML private ToggleGroup semestre;
    @FXML private ToggleGroup situacao;
    @FXML private RadioButton rb1;
    @FXML private RadioButton rb2;
    @FXML private RadioButton rbAprovado;
    @FXML private RadioButton rbReprovado;
    @FXML private Button btBuscar;
    @FXML private Button btLimpar;
    private List<HistoricoView> listHistoricoView = new ArrayList<>();
    private ObservableList<HistoricoView> obsListHistoricoView;
    private int userId = LoggedUser.getInstance().getId();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTableColumns();
        carregarTableView();


        btBuscar.setOnMouseClicked((MouseEvent e) -> {
            busca();
        });

        btLimpar.setOnMouseClicked((MouseEvent e) -> {
            txDisciplina.clear();
            txAno.clear();
            rb1.setSelected(false);
            rb2.setSelected(false);
            rbAprovado.setSelected(false);
            rbReprovado.setSelected(false);
            carregarTableView();
        });
    }

    private void inicializarTableColumns() {
        columnTurma.setCellValueFactory(new PropertyValueFactory<>("codigoTurma"));
        columnDisciplina.setCellValueFactory(new PropertyValueFactory<>("nomeDisciplina"));
        columnCreditos.setCellValueFactory(new PropertyValueFactory<>("creditos"));
        columnNota.setCellValueFactory(new PropertyValueFactory<>("note"));
        columnSemestre.setCellValueFactory(new PropertyValueFactory<>("semestre"));
        columnAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        columnSituacao.setCellValueFactory(new PropertyValueFactory<>("resultado"));
    }

    private void carregarTableView(){
        listHistoricoView.clear();
        listHistoricoView = Read.Query("select new model.HistoricoView(a.id, t.id, h.frequencia, d.creditos, h.nota, a.nome," +
                                    " t.codigoTurma, t.semestre, t.ano, h.resultado, d.nome) " +
                "from Aluno as a, Disciplina as d, Turma as t, Historico as h " +
                "where a.id = h.alunoId and d.id = t.disciplinaId and t.id = h.turmaId and a.id =" + userId);
        obsListHistoricoView = FXCollections.observableArrayList(listHistoricoView);
        tableView.setItems(obsListHistoricoView);
    }

    public void busca() {
        String busca = "";
        if(!txDisciplina.getText().isEmpty())
            busca += "and d.nome like '%" + txDisciplina.getText() + "%'";
        if(!txAno.getText().isEmpty())
            busca += "and t.ano = " + txAno.getText() ;
        if(rb1.isSelected() || rb2.isSelected()) {
            RadioButton sem = (RadioButton) semestre.getSelectedToggle();
                busca += "and t.semestre = '" + sem.getText() + "'";
        }
        if(rbAprovado.isSelected() || rbReprovado.isSelected()) {
            RadioButton sit = (RadioButton) situacao.getSelectedToggle();
                busca += "and h.resultado = '" + sit.getText() + "'";
        }

        listHistoricoView.clear();
        listHistoricoView = Read.Query("select new model.HistoricoView(a.id, t.id, h.frequencia, d.creditos, h.nota, a.nome," +
                " t.codigoTurma, t.semestre, t.ano, h.resultado, d.nome) " +
                "from Aluno as a, Disciplina as d, Turma as t, Historico as h " +
                "where a.id = h.alunoId and d.id = t.disciplinaId and t.id = h.turmaId and a.id =" + userId + busca);
        obsListHistoricoView = FXCollections.observableArrayList(listHistoricoView);
        tableView.setItems(obsListHistoricoView);
    }

}
