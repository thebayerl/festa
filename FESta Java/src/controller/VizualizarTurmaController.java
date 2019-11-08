/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import view.CadastrarAluno;
import view.Principal;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VizualizarTurmaController implements Initializable {

    @FXML private ListView<String> listViewTurmas;

    private List<Matriculado> listMatriculados = new ArrayList<>();

    private List<Turma> listTurmas = new ArrayList<>();
    private List<String> listTurmasStr = new ArrayList<>();
    private ObservableList<String> obsTurmas;

    private List<Professor> listProfessor = new ArrayList<>();
    private ObservableList<Professor> obsProfessor;
	    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	carregarTurmas();
    }

    public void carregarTurmas() {

        Usuario user = LoggedUser.getInstance();
        String alunoId = String.valueOf(user.getId());
        listMatriculados = Read.getMatriculado(alunoId, null);
        String turmaId;
        for(Matriculado elemento: listMatriculados){
            turmaId = String.valueOf(elemento.getturmaId());
            Turma t = Read.getTurma(turmaId,  null,  null,  null,  null,  null,  null, null).get(0);
            listTurmas.add(t);

        }




        //listTurmas = Read.getTurma(null, null, null, null, null, null, null, null);
        List<String> professores = new ArrayList<>();

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Sala.class).buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            professores = session.createQuery("select p.nome from Professor p, Turma t where p.id = t.professorId").getResultList();
            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }

        for (int i = 0; i < listTurmas.size(); i++){
            listTurmasStr.add("Professor: " + professores.get(i)
                    + "  Semestre: " +  listTurmas.get(i).getSemestre()
                    + "  Ano: " + listTurmas.get(i).getAno()
                    + "  Qnt. Alunos: " +  listTurmas.get(i).getMaxAlunos());
        }

        obsTurmas = FXCollections.observableArrayList(listTurmasStr);
        listViewTurmas.setItems(obsTurmas);
    }

    public void fecha(){
        CadastrarAluno.getStage().close();
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
