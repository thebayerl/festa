/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.Arrays;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.CadastrarCurso;
import view.CadastrarDisciplina;
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
    @FXML private Label alertaDePesquisaLabel;
    
    // variavel contendo os tipos que podem ser pesquisados
    private String[] tiposPesquisa = {"Curso", "Aluno", "Professor", "Disciplina"};    
    
    
    @SuppressWarnings("unchecked")
	@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btCadastrarCurso.setOnMouseClicked(e ->{ abreCadastroCurso(); });
        btCadastrarDisciplina.setOnMouseClicked(e ->{ abreCadastroDiscplina(); });
        btCadastrarUsuario.setOnMouseClicked(e ->{ abreCadastroUsuario(); });        
        btCadastrarSala.setOnMouseClicked(e ->{ abreCadastroSala(); });        
        btCadastrarTurma.setOnMouseClicked(e-> { abreCadastroTurma(); });
        
        this.pesquisaButton.setOnMouseClicked(e -> {
        	
        	this.alertaDePesquisaLabel.setText("");
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
        		        		
        		// resultados da pesquisa
        		List resultados = pesquisarQuery.list();        		
        		ObservableList<String> row = FXCollections.observableArrayList();
        		// limpa a listView 
        		this.listaResultadosListView.getItems().clear();
        		
    			if (!resultados.isEmpty()) {
	        		if (selecaoTabelaIndice == 0) {
	    				
	    				// cast para lista de cursos
	    				List<Curso> cursos = (List<Curso>) resultados;
	    				
	    				for (Curso curso : cursos)    					
	    	                row.add(curso.getnome());    	                
	    				
	    				this.listaResultadosListView.setItems(row);
	    			} else if (selecaoTabelaIndice == 1) {
	    				
	    				List<Aluno> alunos = (List<Aluno>) resultados;
	    				
	    				for (Aluno aluno : alunos) {    					
	    	                row.add(aluno.getNome());	                
	    				}
	    					
	    			} else if (selecaoTabelaIndice == 2) {
	    				
	    				List<Professor> profs = (List<Professor>) resultados;
	    				
	    				for (Professor prof : profs) {
	    					System.out.println(" -> " + prof.getNome());    					
	    	                row.add(prof.getNome());	                
	    				}	    				   	    				
	    			} else if (selecaoTabelaIndice == 3) {
	    				
	    				List<Disciplina> disciplinas = (List<Disciplina>) resultados;
	    				
	    				for (Disciplina disc : disciplinas) {
	    					System.out.println(" -> " + disc.getNome());    					
	    	                row.add(disc.getNome());	                
	    				}	    				    						
	    			}
	    			// seta os resultados na lista
	    			this.listaResultadosListView.setItems(row);
	        	} else {
	        		this.alertaDePesquisaLabel.setText("Nenhum resultado encontrado!");
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
        	System.out.println("ENTREI USER4");
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
    
}
