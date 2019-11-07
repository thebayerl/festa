/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Departamento;
import model.Disciplina;
import model.Read;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.CadastrarDisciplina;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class CadastrarDisciplinaController implements Initializable {
    
	@FXML private TextField txNome;
    @FXML private Button btCadastrar;
    @FXML private Button btCancelar;
    @FXML private ComboBox<Departamento> comboBoxDepartamento;
    @FXML private ComboBox<Integer> comboBoxCreditos;
    @FXML private TextField txCodigoDisciplina;
    
    int maxCredito = 6;
    private List<Integer> listCreditos = new ArrayList<>();
    private List<Departamento> listDepartamentos = new ArrayList<>();
    private ObservableList<Integer> obsCreditos;
    private ObservableList<Departamento> obsDepartamentos;
    
    private Departamento departamento;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    	carregarCreditos();
    	carregarDepartametos();
    	
        btCancelar.setOnMouseClicked((MouseEvent e)->{
            abrePrincipal();
        });
        
        btCadastrar.setOnMouseClicked((MouseEvent e)->{
            cadastraDisciplina();
        });
    }
    
    public void carregarCreditos() {
    	
    	for(int i = 1 ;  i <= maxCredito ; i++) {
    		listCreditos.add(i);
    	}
    	
    	obsCreditos = FXCollections.observableArrayList(listCreditos);
    	comboBoxCreditos.setItems(obsCreditos);  
    	
    }
    
    public void carregarDepartametos() {
	
	listDepartamentos = Read.getDepartamento();
    obsDepartamentos = FXCollections.observableArrayList(listDepartamentos);
    
    comboBoxDepartamento.setItems(obsDepartamentos);
    	
    }
    
    public void cadastraDisciplina(){

    	// TODO: verifica se todos os  campos estão selecionados
    	String nome = txNome.getText();
    	
    	int creditos = comboBoxCreditos.getValue();
    	
    	String codigoDisciplina = txCodigoDisciplina.getText();
    	
    	
            	
    	departamento = (Departamento) comboBoxDepartamento.getValue();
        String departamentoId = departamento.getCodigoDepartamento();
        Disciplina d = new Disciplina(nome, creditos, departamentoId, codigoDisciplina);
        d.create();         
    }
    
    public void fecha(){
        CadastrarDisciplina.getStage().close();
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
