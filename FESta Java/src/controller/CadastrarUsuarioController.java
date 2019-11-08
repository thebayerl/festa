/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Aluno;
import model.ComboBoxTipo;
import model.Curso;
import model.Professor;
import model.Read;
import model.Usuario;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.CadastrarUsuario;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class CadastrarUsuarioController implements Initializable {
    
    
	@FXML  private TextField txUserName;
    @FXML  private TextField txRG;
    @FXML  private PasswordField psSenha;
    @FXML  private TextField txCPF;
    @FXML  private Button btCancelar;
    @FXML  private Button btCadastrar;
    @FXML  private PasswordField psSenhaConf;
    @FXML  private TextField txNomeProfessor;
    @FXML  private TextField txFormacao;
    @FXML  private TextField txMatriculaProfessor;
    @FXML  private TextField txCodigoCursoProfessor;
    @FXML  private TextField txMatriculaAluno;
    @FXML  private TextField txNomeAluno;
    @FXML  private TextField txNascimento;
    @FXML  private TextField txCodigoCursoAluno;
    @FXML  private TextField txIngresso;
    @FXML  private AnchorPane paneProfessor;
    @FXML  private AnchorPane paneAluno;
    @FXML  private ComboBox<ComboBoxTipo> comboBoxUsers;
    @FXML private ComboBox<Curso> comboBoxCurso;
    
    private List<Curso> listCursos = new ArrayList<>();
    private ObservableList<Curso> obsCursos;
    
    private List<ComboBoxTipo> tipos = new ArrayList<>();
    private ObservableList<ComboBoxTipo> obsTipos;
    private Aluno a;
    private Usuario u;
    private Professor p;
    private int usuarioId;
    
    // TODO: adicionar role secretaria
    private String[] rolesAvailable = {"docente","discente"};
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	carregarTipos();
    	carregarCursos();
    	    	
        btCancelar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            abrePrincipal();
        });
        
        btCadastrar.setOnMouseClicked((MouseEvent e)->{

        	cadastraUsuario();
        	ComboBoxTipo t2 = comboBoxUsers.getSelectionModel().getSelectedItem();
        	
        	if(t2.getNome().equals("Professor")) {
        		cadastraProfessor();
        	} else {	
        		cadastraAluno();
        	}
        	//abrePrincipal();
            
        });
        
        comboBoxUsers.getSelectionModel().select(0);
    }
    
    @FXML
    void selecionarTipo() {
    	
    	ComboBoxTipo t = comboBoxUsers.getSelectionModel().getSelectedItem();
    	
    	if(t.getNome().equals("Professor")) {
    		paneProfessor.setVisible(true);
    		paneAluno.setVisible(false);
    		
    	}else {	
    		paneProfessor.setVisible(false);
    		paneAluno.setVisible(true);
    	}
    	
    }
    
    // carrega os valores possíveis de usuário na combobox
    public void carregarTipos() {
    	
		ComboBoxTipo t1 = new ComboBoxTipo(1, "Professor");
		ComboBoxTipo t2 = new ComboBoxTipo(2, "Aluno");
		ComboBoxTipo t3 = new ComboBoxTipo(3, "Secretario");
		
		tipos.add(t1);
		tipos.add(t2);
		tipos.add(t3);
		
		obsTipos = FXCollections.observableArrayList(tipos);
		
		comboBoxUsers.setItems(obsTipos);
	}
    
    
    public void carregarCursos() {
    	listCursos.clear();
    	comboBoxCurso.getItems().clear();
    	//System.out.println(cursoId + " " + codigoCurso + " " + nomeCurso );
    	listCursos = Read.getCurso(null, null, null);
    	//for(Curso elemento: listCursos){
    	//	   System.out.println(elemento.getnome());
    	//}
    	if(obsCursos != null) {
    		obsCursos.clear();
    	}
    	obsCursos = FXCollections.observableArrayList(listCursos);
    	//comboBoxCurso.getItems().clear();
    	comboBoxCurso.setItems(obsCursos);
    }
    
    // método para cadastrar usuário
    public void cadastraUsuario(){
        
    	// pega os dasdos do usuário
    	String username = txUserName.getText();
        String senha = psSenha.getText();
        String confirmacao = psSenhaConf.getText();
        String cpf = txCPF.getText();
        String rg = txRG.getText();
        
        int userType = this.comboBoxUsers.getSelectionModel().getSelectedIndex();
        String role = this.rolesAvailable[userType];

        if(senha.equals(confirmacao)){
        	//Create u = new Create();; 
        	u = new Usuario(username, senha, rg, cpf, role);
        	u.create();
        	usuarioId = u.getId();

        } else {

            Alert al = new Alert(AlertType.ERROR);
            al.setHeaderText("As senhas não coincidem");
            al.show();
        }
    }
    
    
    public void fecha(){
        CadastrarUsuario.getStage().close();
    }
    
    public void cadastraAluno(){
    	
    	String matricula = txMatriculaAluno.getText();
    	String dataNascimento = txNascimento.getText();
    	String nome = txNomeAluno.getText();
    	String dataIngresso = txIngresso.getText();
    	Curso curso = comboBoxCurso.getSelectionModel().getSelectedItem();
    	int cursoId = curso.getId();
    	//int cursoId = Integer.parseInt(txCodigoCursoAluno.getText());    	
    	
        a = new Aluno(usuarioId, nome, dataNascimento, dataIngresso, cursoId);
        a.create();
        System.out.println("cadastreiiiiiiiiiiiiiiii");
        //abrePrincipal();
    }
    
    public void cadastraProfessor(){
    	
    	String nome = txNomeProfessor.getText();
    	String matricula = txMatriculaProfessor.getText();
    	String nivelFormacao = txFormacao.getText();
    	Curso curso = comboBoxCurso.getSelectionModel().getSelectedItem();
    	int cursoId = curso.getId();

    	System.out.println("usuario id: " + usuarioId);
    	p = new Professor(usuarioId, nome, nivelFormacao, cursoId);
        p.create();
        System.out.println("cadastreiiiiiiiiiiiiiiii");
    	//abrePrincipal();
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
