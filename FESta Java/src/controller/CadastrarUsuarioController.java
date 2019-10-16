/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Aluno;
import model.ComboBoxTipo;
import model.Create;
import model.Professor;
import model.Usuario;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.CadastrarAluno;
import view.CadastrarProfessor;
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
    
    
    
    private List<ComboBoxTipo> tipos = new ArrayList<>();
    private ObservableList<ComboBoxTipo> obsTipos;
    private Aluno a;
    private Usuario u;
    private Professor p;
    private int usuarioId;
    
     
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	
    	carregarTipos();
    	
    	
        btCancelar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
            abrePrincipal();
        });
        
        btCadastrar.setOnMouseClicked((MouseEvent e)->{
            //System.out.println("Sai");
        	cadastraUsuario();
        	ComboBoxTipo t2 = comboBoxUsers.getSelectionModel().getSelectedItem();
        	if(t2.getNome().equals("Professor")) {
        		cadastraProfessor();
        	}else {	
        		cadastraAluno();
        	}
        	abrePrincipal();
            
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
    
    public void carregarTipos() {
		ComboBoxTipo t1 = new ComboBoxTipo(1, "Professor");
		ComboBoxTipo t2 = new ComboBoxTipo(2, "Aluno");
		
		tipos.add(t1);
		tipos.add(t2);
		
		obsTipos = FXCollections.observableArrayList(tipos);
		
		comboBoxUsers.setItems(obsTipos);
	}
    
    
    public void cadastraUsuario(){
        String username = txUserName.getText();
        String senha = psSenha.getText();
        String confirmacao = psSenhaConf.getText();
        String cpf = txCPF.getText();
        String rg = txRG.getText();
        Random rand = new Random();
        int id = rand.nextInt(10000);
        //final String sql = "SELECT max( u.id ) FROM Usuario u";
        //Integer lastId = (Integer) HibernateUtil.getSession().createQuery( sql ).uniqueResult();
        
        //usuarioId = lastId+1;
        usuarioId = id;
        if(senha.equals(confirmacao)){
        	//Create u = new Create();
            //u.Usuario(id, username, senha, rg, cpf); 
        	u = new Usuario(username, senha, rg, cpf);
        	u.create();
        }else{
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
    	String codigoCurso = txCodigoCursoAluno.getText();
    	
    	//final String sql = "SELECT max( a.id ) FROM Aluno a";
    	//int lastId = (Integer) HibernateUtil.getSession().createQuery( sql ).uniqueResult();
    	        
    	//int usuarioId = lastId;
    	
        a = new Aluno(usuarioId, matricula, nome, dataNascimento, dataIngresso, codigoCurso);
        a.create();
        abrePrincipal();
    }
    
    public void cadastraProfessor(){
    	
    	String nome = txNomeProfessor.getText();
    	String matricula = txMatriculaProfessor.getText();
    	String nivelFormacao = txFormacao.getText();
    	int codigoCurso = Integer.parseInt(txCodigoCursoProfessor.getText());
    	//final String sql = "SELECT max( p.id ) FROM Professor p";
    	//Integer lastId = (Integer) HibernateUtil.getSession().createQuery( sql ).uniqueResult();
    	        
    	//int usuarioId = (int) lastId;
    	System.out.println("usuario id: " + usuarioId);
    	p = new Professor(usuarioId, nome, matricula, nivelFormacao, codigoCurso);
        p.create();
    	abrePrincipal();
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
