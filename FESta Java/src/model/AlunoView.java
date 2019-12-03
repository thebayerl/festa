package model;

import java.util.Date;

public class AlunoView {
    private Integer id, cursoId;
    private String  nome, cursoNome, email, telCel, telRes, cpf, rg;
    private Date dataIngresso, dataNascimento;

    public AlunoView(Integer id, Integer cursoId, String nome, String cursoNome, String email, String telCel,
                     String telRes, String cpf, String rg, Date dataIngresso, Date dataNascimento) {
        this.id = id;
        this.cursoId = cursoId;
        this.nome = nome;
        this.cursoNome = cursoNome;
        this.email = email;
        this.telCel = telCel;
        this.telRes = telRes;
        this.cpf = cpf;
        this.rg = rg;
        this.dataIngresso = dataIngresso;
        this.dataNascimento = dataNascimento;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public String getNome() {
        return nome;
    }

    public String getCursoNome() {
        return cursoNome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelCel() {
        return telCel;
    }

    public String getTelRes() {
        return telRes;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public Date getDataIngresso() {
        return dataIngresso;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }
}
