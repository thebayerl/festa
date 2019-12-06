package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfessorView {
    private Integer id, cursoId;
    private String  nome, cursoNome, username, email, telCel, telRes, cpf, rg, formacao;
    private Date dataNascimento;

    public ProfessorView(Integer id, Integer cursoId, String nome, String cursoNome, String email, String telCel,
                         String telRes, String cpf, String rg, String formacao, Date dataNascimento, String username) {
        this.id = id;
        this.cursoId = cursoId;
        this.nome = nome;
        this.cursoNome = cursoNome;
        this.email = email;
        this.telCel = telCel;
        this.telRes = telRes;
        this.cpf = cpf;
        this.rg = rg;
        this.formacao = formacao;
        this.dataNascimento = dataNascimento;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public String getNome() {
        return nome;
    }

    public String getUsername() {
        return username;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCursoNome() {
        return cursoNome;
    }

    public void setCursoNome(String cursoNome) {
        this.cursoNome = cursoNome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelCel() {
        return telCel;
    }

    public void setTelCel(String telCel) {
        this.telCel = telCel;
    }

    public String getTelRes() {
        return telRes;
    }

    public void setTelRes(String telRes) {
        this.telRes = telRes;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public String getDataNascimento() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(this.dataNascimento);
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "ProfessorView{" +
                "id=" + id +
                ", cursoId=" + cursoId +
                ", nome='" + nome + '\'' +
                ", cursoNome='" + cursoNome + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", telCel='" + telCel + '\'' +
                ", telRes='" + telRes + '\'' +
                ", cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", formacao='" + formacao + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
