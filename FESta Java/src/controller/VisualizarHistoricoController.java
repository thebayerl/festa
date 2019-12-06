package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.HistoricoView;
import model.Historico;
import model.HistoricoView;
import model.Read;

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
    @FXML private RadioButton rb1;
    @FXML private RadioButton rb2;
    @FXML private RadioButton rbAprovado;
    @FXML private RadioButton rbReprovado;
    @FXML private Button btBuscar;
    private List<HistoricoView> listHistoricoView = new ArrayList<>();
    private ObservableList<HistoricoView> obsListHistoricoView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inicializarTableColumns();
        carregarTableView();

        btBuscar.setOnMouseClicked((MouseEvent e) -> {
            busca();
        });
    }

    private void inicializarTableColumns() {
        columnTurma.setCellValueFactory(new PropertyValueFactory<>("codigoTurma"));
        columnDisciplina.setCellValueFactory(new PropertyValueFactory<>("disciplinaNome"));
        columnCreditos.setCellValueFactory(new PropertyValueFactory<>("creditos"));
        columnNota.setCellValueFactory(new PropertyValueFactory<>("note"));
        columnSemestre.setCellValueFactory(new PropertyValueFactory<>("semestre"));
        columnAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        columnSituacao.setCellValueFactory(new PropertyValueFactory<>("resultado"));
    }

    private void carregarTableView(){
        obsListHistoricoView.clear();

        listHistoricoView = Read.Query("select new model.HistoricoView(a.id, t.id, t.frequencia, d.creditos, h.nota, a.nome," +
                                    " t.codigoTurma, t.semestre, t.ano, h.resultado, d.nome) " +
                "from Aluno as a, Disciplina as d, Turma as t, Historico as h " +
                "where a.id = h.alunoId and d.id = t.disciplinaId and t.id = h.turmaId");

        if (listHistoricoView != null) {
            listHistoricoView.clear();
        }
        obsListHistoricoView = FXCollections.observableArrayList(listHistoricoView);

        FilteredList<HistoricoView> filteredData = new FilteredList<>(obsListHistoricoView, b -> true);

        txAno.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(objView -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (objView.getAno().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true;
                else
                    return false; // Does not match.
            });
        });
        txDisciplina.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(objView -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (objView.getNomeDisciplina().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true;
                else
                    return false; // Does not match.
            });
        });

        SortedList<HistoricoView> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);

    }

    public void busca(){}

}
