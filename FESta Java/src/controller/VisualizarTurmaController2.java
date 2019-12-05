package controller;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import model.*;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.ValidationDecoration;
import view.CadastrarAluno;
import javafx.scene.shape.Rectangle;

import javax.persistence.Column;
import java.net.URL;
import java.util.ResourceBundle;

public class VisualizarTurmaController2 implements Initializable {

    @FXML private Label lbD00;
    @FXML private Label lbC00;
    @FXML private Label lbS00;
    @FXML private Label lbD42;
    @FXML private Label lbC42;
    @FXML private Label lbS42;
    @FXML private Label lbC01;
    @FXML private Label lbS01;
    @FXML private Label lbD02;
    @FXML private Label lbC02;
    @FXML private Label lbS02;
    @FXML private Label lbD01;
    @FXML private Label lbC10;
    @FXML private Label lbS10;
    @FXML private Label lbD10;
    @FXML private Label lbD12;
    @FXML private Label lbC12;
    @FXML private Label lbS12;
    @FXML private Label lbD03;
    @FXML private Label lbC03;
    @FXML private Label lbS03;
    @FXML private Label lbD13;
    @FXML private Label lbC13;
    @FXML private Label lbS13;
    @FXML private Label lbD04;
    @FXML private Label lbC04;
    @FXML private Label lbS04;
    @FXML private Label lbD14;
    @FXML private Label lbC14;
    @FXML private Label lbS14;
    @FXML private Label lbD20;
    @FXML private Label lbC20;
    @FXML private Label lbS20;
    @FXML private Label lbD21;
    @FXML private Label lbC21;
    @FXML private Label lbS21;
    @FXML private Label lbD22;
    @FXML private Label lbC22;
    @FXML private Label lbS22;
    @FXML private Label lbD23;
    @FXML private Label lbC23;
    @FXML private Label lbS23;
    @FXML private Label lbD24;
    @FXML private Label lbC24;
    @FXML private Label lbS24;
    @FXML private Label lbD30;
    @FXML private Label lbC30;
    @FXML private Label lbS30;
    @FXML private Label lbD31;
    @FXML private Label lbC31;
    @FXML private Label lbS31;
    @FXML private Label lbD32;
    @FXML private Label lbC32;
    @FXML private Label lbS32;
    @FXML private Label lbD33;
    @FXML private Label lbC33;
    @FXML private Label lbS33;
    @FXML private Label lbD34;
    @FXML private Label lbC34;
    @FXML private Label lbS34;
    @FXML private Label lbD40;
    @FXML private Label lbC40;
    @FXML private Label lbS40;
    @FXML private Label lbD41;
    @FXML private Label lbC41;
    @FXML private Label lbS41;
    @FXML private Label lbD43;
    @FXML private Label lbC43;
    @FXML private Label lbS43;
    @FXML private Label lbD44;
    @FXML private Label lbC44;
    @FXML private Label lbS44;
    @FXML private Label lbD11;
    @FXML private Label lbC11;
    @FXML private Label lbS11;

    @FXML private Button btMinhasTurmas;
    @FXML private Button btCancelar;
    @FXML private Button btConfirmar;
    @FXML private Button btRemover;
    @FXML private Button btInscrever;
    @FXML private TextField txPesquisar;
    @FXML private TableView<TurmaView> tableView;
    @FXML private TableColumn<TurmaView, String> columnDisciplina;
    @FXML private TableColumn<TurmaView, String> columnCodigo;
    @FXML private TableColumn<TurmaView, String> columnSala;
    @FXML private TableColumn<TurmaView, Integer> columnCreditos;
    @FXML private TableColumn<TurmaView, String> columnProfessor;
    @FXML private TableColumn<TurmaView, String> columnHora;
    @FXML private TableColumn<TurmaView, String> columnDias;
    @FXML private ComboBox<Disciplina> comboBoxDisciplina;
    @FXML private Rectangle rec01;
    @FXML private Rectangle rec20;
    @FXML private Rectangle rec21;
    @FXML private Rectangle rec30;
    @FXML private Rectangle rec31;
    @FXML private Rectangle rec40;
    @FXML private Rectangle rec41;
    @FXML private Rectangle rec22;
    @FXML private Rectangle rec32;
    @FXML private Rectangle rec42;
    @FXML private Rectangle rec23;
    @FXML private Rectangle rec33;
    @FXML private Rectangle rec43;
    @FXML private Rectangle rec24;
    @FXML private Rectangle rec34;
    @FXML private Rectangle rec44;
    @FXML private Rectangle rec02;
    @FXML private Rectangle rec10;
    @FXML private Rectangle rec12;
    @FXML private Rectangle rec03;
    @FXML private Rectangle rec13;
    @FXML private Rectangle rec04;
    @FXML private Rectangle rec14;
    @FXML private Rectangle rec00;
    @FXML private Rectangle rec11;

    @FXML void selecionarDisciplina() {
       this.disciplinaId = comboBoxDisciplina.getSelectionModel().getSelectedItem().getId();
       carregarTableView();
    }

    @FXML void select00(MouseEvent event) {
        int a = 0,b=0;
        trataSelect(a,b);
        rec00.setFill(Color.color(0.7,0.7,0.7));
    }

    @FXML void select01(MouseEvent event) {
        int a = 0,b=0;
        trataSelect(a,b);
        rec01.setFill(Color.color(0.7,0.7,0.7));
    }

    @FXML void select02(MouseEvent event) {
        int a = 0,b=2;
        trataSelect(a,b);
        rec02.setFill(Color.color(0.7,0.7,0.7));
    }

    @FXML void select03(MouseEvent event) {
        int a = 0,b=3;
        trataSelect(a,b);
        rec03.setFill(Color.color(0.7,0.7,0.7));
    }

    @FXML void select04(MouseEvent event) {
        int a = 0,b=4;
        trataSelect(a,b);
        rec04.setFill(Color.color(0.7,0.7,0.7));
    }

    @FXML void select10(MouseEvent event) {
        int a = 1,b=0;
        trataSelect(a,b);
        rec10.setFill(Color.color(0.7,0.7,0.7));
    }

    @FXML void select12(MouseEvent event) {
        int a = 1,b=2;
        trataSelect(a,b);
        rec12.setFill(Color.color(0.7,0.7,0.7));
    }

    @FXML void select13(MouseEvent event) {
        int a = 1,b=3;
        trataSelect(a,b);
        rec13.setFill(Color.color(0.7,0.7,0.7));
    }

    @FXML void select14(MouseEvent event) {
        int a = 1,b=4;
        trataSelect(a,b);
        rec14.setFill(Color.color(0.7,0.7,0.7));
    }

    @FXML void select20(MouseEvent event) {
        int a = 2,b=0;
        trataSelect(a,b);
        rec20.setFill(Color.color(0.7,0.7,0.7));
    }

    @FXML void select21(MouseEvent event) {
        int a =2, b=1;
        trataSelect(a,b);
        rec00.setFill(Color.color(0.7,0.7,0.7));
    }

    @FXML void select22(MouseEvent event) {

    }

    @FXML void select23(MouseEvent event) {

    }

    @FXML void select24(MouseEvent event) {

    }

    @FXML void select30(MouseEvent event) {

    }

    @FXML void select31(MouseEvent event) {

    }

    @FXML void select32(MouseEvent event) {

    }

    @FXML void select33(MouseEvent event) {

    }

    @FXML void select34(MouseEvent event) {

    }

    @FXML void select40(MouseEvent event) {

    }

    @FXML void select41(MouseEvent event) {

    }

    @FXML void select42(MouseEvent event) {

    }

    @FXML void select43(MouseEvent event) {

    }

    @FXML void select44(MouseEvent event) {

    }

    public void trataSelect(int a, int b){
        if(this.a==a && this.b==b) {
            limpaR();
            this.a = -1;
            this.b = -1;
            return;
        }
        limpaR();
        this.a=a;
        this.b=b;
    }

    public void remover(int a, int b){
        int c = 0;
        if(D[a][b].equals(""))
            return;

        Disciplina d = (Disciplina) Read.Query("from Disciplina where codigoDisciplina = '" + C[a][b] + "'").get(0);
        Sala s = (Sala) Read.Query("from Sala where codigoSala = '" + S[a][b] + "'").get(0);
        Turma t = (Turma) Read.Query("from Turma where disciplinaId = " + d.getId() + " and salaId =" + s.getId()).get(0);
        Matriculado m = (Matriculado) Read.Query("from Matriculado where turmaId = " + t.getId() + "and alunoId =" + "2").get(0);
        m.delete();
        if(b == 1 || b == 3)
            c=3;
        if(b==0)
            c=1;
        if(b==4)
            c=2;
        if(b==2)
            c=2;
        removeTurma(a,  c);
        geraDCS();
    }

    int a,b;
    int disciplinaId = 0;
    private List<Disciplina> listDisciplinas = new ArrayList<>();
    private ObservableList<Disciplina> obsDisciplinas;
    private List<TurmaView> listTurmaView = new ArrayList<>();
    private ObservableList<TurmaView> obsListTurmaView;

    String[][] D = new String[5][5];
    String[][] C = new String[5][5];
    String[][] S = new String[5][5];
    Boolean[][] R = new Boolean[5][5];


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        limpaGrid();
        geraDCS();
        carregarDisciplinas();
        inicializarTableColumns();
        carregarTableView();

        btInscrever.setOnMouseClicked((MouseEvent e) -> {
            cadastraMatriculado();
        });

    }

    private void inicializarTableColumns(){
        columnDisciplina.setCellValueFactory(new PropertyValueFactory<>("disciplinaNome"));
        columnProfessor.setCellValueFactory(new PropertyValueFactory<>("professorNome"));
        columnSala.setCellValueFactory(new PropertyValueFactory<>("codigoSala"));
        columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoDisciplina"));
        //columnCreditos.setCellValueFactory(new PropertyValueFactory<>("semestre"));
        //columnMaxAlunos.setCellValueFactory(new PropertyValueFactory<>("maxAlunos"));
        columnProfessor.setCellValueFactory(new PropertyValueFactory<>("professorNome"));
    }

    private void habilitaTableView() {
        txPesquisar.setDisable(false);
        tableView.setDisable(false);
    }

    private void desabilitaTableView() {
        txPesquisar.setDisable(true);
        tableView.setDisable(true);
    }

    private void carregarTableView(){
        listTurmaView.clear();
        if (this.disciplinaId == 0) {
            listTurmaView = Read.Query("select new model.TurmaView(t.id, t.professorId, t.disciplinaId, t.salaId, " +
                    "t.maxAlunos, p.nome, d.nome, s.codigoSala, t.ano, t.semestre, dept.id) " +
                    "from Departamento dept, Turma t, Professor p, Sala s, Disciplina d " +
                    "where t.professorId = p.id and t.disciplinaId = d.id and t.salaId = s.id and d.departamentoId = dept.id");
        }else {
            listTurmaView = Read.Query("select new model.TurmaView(t.id, t.professorId, t.disciplinaId, t.salaId, " +
                    "t.maxAlunos, p.nome, d.nome, s.codigoSala, t.ano, t.semestre, dept.id) " +
                    "from Departamento dept, Turma t, Professor p, Sala s, Disciplina d " +
                    "where t.professorId = p.id and t.disciplinaId = d.id and t.salaId = s.id and d.departamentoId = dept.id and t.disciplinaId = " + this.disciplinaId);
        }
        if(obsListTurmaView != null) {
            obsListTurmaView.clear();
        }
        obsListTurmaView = FXCollections.observableArrayList(listTurmaView);

        FilteredList<TurmaView> filteredData = new FilteredList<>(obsListTurmaView, b -> true);

        txPesquisar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(objView -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (objView.getDisciplinaNome().toLowerCase().indexOf(lowerCaseFilter) != -1 ||
                        objView.getProfessorNome().toLowerCase().indexOf(lowerCaseFilter) != -1 ||
                        objView.getCodigoSala().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true;
                else
                    return false; // Does not match.
            });
        });

        SortedList<TurmaView> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    private boolean testaDados(){
        return false;
    }

    private void cadastraMatriculado(){
        if(testaDados()){
            return;
        }

        try {

            TurmaView t= tableView.getSelectionModel().getSelectedItem();

            Matriculado m = new Matriculado(2,t.getId());
            m.create();
            addTurma(t, 0 , 3);
            geraDCS();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Inscrição concluida com sucesso seu filho da puta!");
            alert.show();

            //limpaCampos();
            carregarTableView();
            //desabilitaCampos();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    e.getMessage(),
                    ButtonType.OK);
            alert.show();
        }
    }

    private void remover() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Remover");
        alert.setHeaderText("Tem certeza que deseja remover?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {

                TurmaView t = tableView.getSelectionModel().getSelectedItem();
                Matriculado m = Read.getMatriculado("2", String.valueOf(t.getId())).get(0);
                m.delete();

                Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setTitle("Remover");
                alert2.setHeaderText("Removido com sucesso");
                alert2.show();

                carregarTableView();
            } catch (Exception e) {
                Alert alert2 = new Alert(AlertType.ERROR,
                        e.getMessage(),
                        ButtonType.OK);
                alert2.show();
            }
        }
    }

    public void geraDCS(){
        geraD();
        geraC();
        geraS();
    }

    public void geraD(){
        lbD00.setText(D[0][0]);
        lbD01.setText(D[0][1]);
        lbD02.setText(D[0][2]);
        lbD03.setText(D[0][3]);
        lbD04.setText(D[0][4]);
        lbD10.setText(D[1][0]);
        lbD11.setText(D[1][1]);
        lbD12.setText(D[1][2]);
        lbD13.setText(D[1][3]);
        lbD14.setText(D[1][4]);
        lbD20.setText(D[2][0]);
        lbD21.setText(D[2][1]);
        lbD22.setText(D[2][2]);
        lbD23.setText(D[2][3]);
        lbD24.setText(D[2][4]);
        lbD30.setText(D[3][0]);
        lbD31.setText(D[3][1]);
        lbD32.setText(D[3][2]);
        lbD33.setText(D[3][3]);
        lbD34.setText(D[3][4]);
        lbD40.setText(D[4][0]);
        lbD41.setText(D[4][1]);
        lbD42.setText(D[4][2]);
        lbD43.setText(D[4][3]);
        lbD44.setText(D[4][4]);
    }

    public void geraC(){
        lbC00.setText(C[0][0]);
        lbC01.setText(C[0][1]);
        lbC02.setText(C[0][2]);
        lbC03.setText(C[0][3]);
        lbC04.setText(C[0][4]);
        lbC10.setText(C[1][0]);
        lbC11.setText(C[1][1]);
        lbC12.setText(C[1][2]);
        lbC13.setText(C[1][3]);
        lbC14.setText(C[1][4]);
        lbC20.setText(C[2][0]);
        lbC21.setText(C[2][1]);
        lbC22.setText(C[2][2]);
        lbC23.setText(C[2][3]);
        lbC24.setText(C[2][4]);
        lbC30.setText(C[3][0]);
        lbC31.setText(C[3][1]);
        lbC32.setText(C[3][2]);
        lbC33.setText(C[3][3]);
        lbC34.setText(C[3][4]);
        lbC40.setText(C[4][0]);
        lbC41.setText(C[4][1]);
        lbC42.setText(C[4][2]);
        lbC43.setText(C[4][3]);
        lbC44.setText(C[4][4]);
    }

    public void geraS(){
        lbS00.setText(S[0][0]);
        lbS01.setText(S[0][1]);
        lbS02.setText(S[0][2]);
        lbS03.setText(S[0][3]);
        lbS04.setText(S[0][4]);
        lbS10.setText(S[1][0]);
        lbS11.setText(S[1][1]);
        lbS12.setText(S[1][2]);
        lbS13.setText(S[1][3]);
        lbS14.setText(S[1][4]);
        lbS20.setText(S[2][0]);
        lbS21.setText(S[2][1]);
        lbS22.setText(S[2][2]);
        lbS23.setText(S[2][3]);
        lbS24.setText(S[2][4]);
        lbS30.setText(S[3][0]);
        lbS31.setText(S[3][1]);
        lbS32.setText(S[3][2]);
        lbS33.setText(S[3][3]);
        lbS34.setText(S[3][4]);
        lbS40.setText(S[4][0]);
        lbS41.setText(S[4][1]);
        lbS42.setText(S[4][2]);
        lbS43.setText(S[4][3]);
        lbS44.setText(S[4][4]);
    }

    public void limpaR(){
        rec00.setFill(Color.WHITE);
        rec01.setFill(Color.WHITE);
        rec02.setFill(Color.WHITE);
        rec03.setFill(Color.WHITE);
        rec04.setFill(Color.WHITE);
        rec40.setFill(Color.WHITE);
        rec41.setFill(Color.WHITE);
        rec42.setFill(Color.WHITE);
        rec43.setFill(Color.WHITE);
        rec44.setFill(Color.WHITE);
        rec20.setFill(Color.WHITE);
        rec21.setFill(Color.WHITE);
        rec22.setFill(Color.WHITE);
        rec23.setFill(Color.WHITE);
        rec24.setFill(Color.WHITE);
        rec30.setFill(Color.WHITE);
        rec31.setFill(Color.WHITE);
        rec32.setFill(Color.WHITE);
        rec33.setFill(Color.WHITE);
        rec34.setFill(Color.WHITE);
        rec10.setFill(Color.WHITE);
        rec11.setFill(Color.WHITE);
        rec12.setFill(Color.WHITE);
        rec13.setFill(Color.WHITE);
        rec14.setFill(Color.WHITE);

    }

    public void limpaGrid(){
        for(int j=0 ; j<5 ; j++ ){
            for(int i=0 ; i<5 ; i++ ){
               D[i][j] = "" ;
            }
        }
        for(int j=0 ; j<5 ; j++ ){
            for(int i=0 ; i<5 ; i++ ){
                C[i][j] = "" ;
            }
        }
        for(int j=0 ; j<5 ; j++ ){
            for(int i=0 ; i<5 ; i++ ){
                S[i][j] = "" ;
            }
        }
    }

    public void carregarDisciplinas() {
        //comboBoxDisciplina.getSelectionModel().clearSelection();
        //listDisciplinas.clear();
        listDisciplinas = Read.Query("from Disciplina ");

        if(obsDisciplinas != null) {
            obsDisciplinas.clear();
        }

        //comboBoxDisciplina.setValue(null);
        obsDisciplinas = FXCollections.observableArrayList(listDisciplinas);
        //comboBoxDisciplina.getItems().clear();
        //comboBoxDisciplina.setDisable(false);
        comboBoxDisciplina.setItems(obsDisciplinas);
    }

    public void addTurma(TurmaView t, int hora, int dia){

        Disciplina d = (Disciplina) Read.Query("from Disciplina where id = " + t.getDisciplinaId()).get(0);
        Sala s = (Sala) Read.Query("from Sala where id = " + t.getSalaId()).get(0);
        List<String> a = new ArrayList<String>();
        if(dia == 1) {
            D[hora][0] = d.getNome();
            C[hora][0] = d.getcodigoDisciplina();
            S[hora][0] = s.getCodigoSala();
            D[hora][2] = d.getNome();
            C[hora][2] = d.getcodigoDisciplina();
            S[hora][2] = s.getCodigoSala();
        }
        if(dia == 2) {
            D[hora][2] = d.getNome();
            C[hora][2] = d.getcodigoDisciplina();
            S[hora][2] = s.getCodigoSala();
            D[hora][4] = d.getNome();
            C[hora][4] = d.getcodigoDisciplina();
            S[hora][4] = s.getCodigoSala();
        }
        if(dia == 3) {
            D[hora][1] = d.getNome();
            C[hora][1] = d.getcodigoDisciplina();
            S[hora][1] = s.getCodigoSala();
            D[hora][3] = d.getNome();
            C[hora][3] = d.getcodigoDisciplina();
            S[hora][3] = s.getCodigoSala();
        }
    }

    public void removeTurma( int hora, int dia){
        if(dia == 1 || dia == 2 ) {
            D[hora][0] = "";
            C[hora][0] = "";
            S[hora][0] = "";
            D[hora][2] = "";
            C[hora][2] = "";
            S[hora][2] = "";
            D[hora][2] = "";
            C[hora][2] = "";
            S[hora][2] = "";
            D[hora][4] = "";
            C[hora][4] = "";
            S[hora][4] = "";
        }
        if(dia == 3) {
            D[hora][1] = "";
            C[hora][1] = "";
            S[hora][1] = "";
            D[hora][3] = "";
            C[hora][3] = "";
            S[hora][3] = "";
        }
    }
}
