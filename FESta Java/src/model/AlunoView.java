package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AlunoView {
    private Integer id, cursoId;
    private String  nome, username, cursoNome, email, telCel, telRes, cpf, rg;
    private Date dataIngresso, dataNascimento;

    public AlunoView(Integer id, Integer cursoId, String nome, String cursoNome, String email, String telCel,
                     String telRes, String cpf, String rg, Date dataIngresso, Date dataNascimento, String username) {
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
        this.username = username;
    }

    private Date stringToDate(String data){
        Date formatData = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            formatData = format.parse ( data );
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        };
        return formatData;
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

    public String getUsername() { return username; }

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

    public String getDataIngresso() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(dataIngresso);
    }

    public String getDataNascimento() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(dataNascimento);
    }
}
