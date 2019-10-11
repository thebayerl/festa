/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Create;
import model.Curso;
import model.Read;
import model.Update;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.CadastrarCurso;
import view.Principal;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class CadastrarCursoController implements Initializable {
    
    
    @FXML private TextField txNome;
    @FXML private Button btCadastrar;
    @FXML private Button btCancelar;
    @FXML private TextField txCodigoCurso;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        btCancelar.setOnMouseClicked((MouseEvent e)->{

            abrePrincipal();
        });
        
        btCadastrar.setOnMouseClicked((MouseEvent e)->{

            cadastraCurso();
                      
        });
    }

    public void cadastraCurso(){
        
    	String nome = txNome.getText();
        String codigoCurso = txCodigoCurso.getText();
        // verifica se o curso já existe        
        Query pesquisarQuery = HibernateUtil.getSession().createQuery("from Curso where codigo_curso = :codigo");
        pesquisarQuery.setParameter("codigo", codigoCurso);
        List<Curso> ret = pesquisarQuery.list();
                                
        if (ret.isEmpty()) {
        	Create c = new Create();
        	c.Curso(codigoCurso, nome);		         	
        } else {        	
        	Update upt = new Update();
        	upt.Curso(codigoCurso, nome);
        }
        
        abrePrincipal();
    }
    public void fecha() {
        CadastrarCurso.getStage().close();
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
