/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import model.Read;
import model.Sala;
import java.net.URL;
import java.util.ArrayList;
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
import view.CadastrarSala;
import view.Principal;

/**
 * FXML Controller class
 *
 * @author denin
 */
public class CadastrarSalaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TextField txCapacidade;
    @FXML private Button btCadastrar;
    @FXML private Button btCancelar;
    @FXML private TextField txCodigoSala;
    @FXML private ComboBox<String> comboBoxPredio;

    private List<String> listPredios = new ArrayList<>();
    private ObservableList<String> obsPredios;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        carregarPredios();

        btCancelar.setOnMouseClicked((MouseEvent e)->{
            abrePrincipal();
        });
        
        btCadastrar.setOnMouseClicked((MouseEvent e)->{
            cadastraSala();
        });
        
    }

    private boolean testaDados() {
        boolean erro = false;
        String alertmsg = "";

        if (!Read.Query("from Sala where codigoSala = '" + txCodigoSala.getText() + "'").isEmpty()) {
            alertmsg += "-Sala com codigoSala já existente\n";
            erro = true;
        }

        if (erro) {
            Alert alert = new Alert(Alert.AlertType.ERROR, alertmsg);
            alert.setHeaderText("Dados inválidos!");
            alert.show();
        }

        return erro;
    }
    
    public void cadastraSala(){

        if(testaDados()){
            return;
        }

        int capacidade = Integer.parseInt(txCapacidade.getText());
        String predio = comboBoxPredio.getValue();
        String codigoSala = txCodigoSala.getText();
        Sala s = new Sala(codigoSala, capacidade, predio);
        s.create();
        abrePrincipal();
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
    
    public void fecha(){
        CadastrarSala.getStage().close();
    }
    
    public void abrePrincipal(){
        //Principal p = new Principal();
        fecha();
//        try {
//            p.start(new Stage());
//        } catch (Exception ex) {
//            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
