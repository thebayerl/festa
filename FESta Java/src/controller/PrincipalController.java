/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.CadastrarCurso;
import view.CadastrarDisciplina;
import view.CadastrarPreRequisitos;
import view.CadastrarSala;
import view.CadastrarTurma;
import view.CadastrarUsuario;
import view.Principal;

import model.Curso;
import model.Aluno;
import model.Disciplina;
import model.Professor;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class PrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private Button btCadastrarCurso;
    @FXML private Button btCadastrarDisciplina;
    @FXML private Button btCadastrarPreRequisitos;
    @FXML private Button btCadastrarUsuario;
    @FXML private Button btCadastrarSala;
    @FXML private Button btCadastrarTurma;
    @FXML private ChoiceBox<String> tipoPesquisaChoiceBox;
    @FXML private ChoiceBox<String> atributoChoiceBox;
    @FXML private Button pesquisaButton;
    @FXML private Button resultadosTableView;
    @FXML private TextField pesquisarTextiArea;
    @FXML private ListView<String> listaResultadosListView;
    
    // variavel contendo os tipos que podem ser pesquisados
    private String[] tiposPesquisa = {"Curso", "Aluno", "Professor", "Disciplina"};    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btCadastrarCurso.setOnMouseClicked(e ->{ abreCadastroCurso(); });
        btCadastrarDisciplina.setOnMouseClicked(e ->{ abreCadastroDiscplina(); });
        btCadastrarUsuario.setOnMouseClicked(e ->{ abreCadastroUsuario(); });        
        btCadastrarSala.setOnMouseClicked(e ->{ abreCadastroSala(); });        
        btCadastrarTurma.setOnMouseClicked(e-> { abreCadastroTurma(); });
        
        this.pesquisaButton.setOnMouseClicked(e -> {
        	// pega a sessão do hibernate
        	Session session = HibernateUtil.getSession();
        	if (session != null) {

        		// verifica qual tabela está selecionada
        		int selecaoTabelaIndice = tipoPesquisaChoiceBox.getSelectionModel().getSelectedIndex();
        		if (selecaoTabelaIndice == -1) selecaoTabelaIndice = 0;
        		
        		String stringQuery = "from " + this.tiposPesquisa[selecaoTabelaIndice] + " where nome like :param"; 
        		// create query
        		Query pesquisarQuery = session.createQuery(stringQuery);
        		        		
        		// adiciona os parametros        		
        		pesquisarQuery.setParameter("param", "%"+this.pesquisarTextiArea.getText()+"%");
        		        		
  
    			if (selecaoTabelaIndice == 0) {
    				
    				List<Curso> resultados = pesquisarQuery.list();
    				ObservableList<String> row = FXCollections.observableArrayList();
    				
    				for (Curso curso : resultados) {
    					System.out.println(" -> " + curso.getnome());    					
    	                row.add(curso.getnome());    	                
    				}
    				
    				this.listaResultadosListView.setItems(row);
    			} else if (selecaoTabelaIndice == 1) {
    				
    				List<Aluno> resultados = pesquisarQuery.list();
    				ObservableList<String> row = FXCollections.observableArrayList();
    				
    				for (Aluno aluno : resultados) {
    					System.out.println(" -> " + aluno.getNome());    					
    	                row.add(aluno.getNome());	                
    				}
    				
    				this.listaResultadosListView.setItems(row);    			
    			} else if (selecaoTabelaIndice == 2) {
    				
    				List<Professor> resultados = pesquisarQuery.list();
    				ObservableList<String> row = FXCollections.observableArrayList();
    				
    				for (Professor prof : resultados) {
    					System.out.println(" -> " + prof.getNome());    					
    	                row.add(prof.getNome());	                
    				}
    				
    				this.listaResultadosListView.setItems(row);   
    				
    			} else if (selecaoTabelaIndice == 3) {
    				
    				List<Disciplina> resultados = pesquisarQuery.list();
    				ObservableList<String> row = FXCollections.observableArrayList();
    				
    				for (Disciplina disc : resultados) {
    					System.out.println(" -> " + disc.getNome());    					
    	                row.add(disc.getNome());	                
    				}
    				
    				this.listaResultadosListView.setItems(row); 			
    			} 
        	}
        	
        });       
        
        // configura a choice box
        this.tipoPesquisaChoiceBox.getItems().addAll(Arrays.asList(tiposPesquisa));
        this.tipoPesquisaChoiceBox.getSelectionModel().select(0);
                 
    }
    public void fecha(){
        Principal.getStage().close();
    }
    
    public void abreCadastroUsuario(){
        CadastrarUsuario u = new CadastrarUsuario();
            fecha();
            try {
                u.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void abreCadastroSala(){
            CadastrarSala s = new CadastrarSala();
            fecha();
            try {
                s.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void abreCadastroTurma(){
        CadastrarTurma t = new CadastrarTurma();
            fecha();
            try {
                t.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void abreCadastroDiscplina(){
        CadastrarDisciplina d = new CadastrarDisciplina();
            fecha();
            try {
                d.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void abreCadastroCurso(){
        CadastrarCurso c = new CadastrarCurso();
            fecha();
            try {
                c.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void abreCadastroPreRequisitos(){
        CadastrarPreRequisitos pr = new CadastrarPreRequisitos();
            fecha();
            try {
                pr.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    
    
}
