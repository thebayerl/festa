package model;

public class HistoricoView {
    Integer alunoId, turmaId, frequencia, creditos;
    Double nota;
    String nomeAluno, codigoTurma, semestre, ano, resultado, nomeDisciplina, dias;

    public HistoricoView(Integer alunoId, Integer turmaId, Integer frequencia, Integer creditos, Double nota,
    		String nomeAluno, String codigoTurma, String semestre, String ano, String resultado, String nomeDisciplina,
    		String dias) {
        this.alunoId = alunoId;
        this.turmaId = turmaId;
        this.frequencia = frequencia;
        this.creditos = creditos;
        this.nota = nota;
        this.nomeAluno = nomeAluno;
        this.codigoTurma = codigoTurma;
        this.semestre = semestre;
        this.ano = ano;
        this.resultado = resultado;
        this.nomeDisciplina = nomeDisciplina;
        this.dias = dias;
    }
    
    public String getDias() {
        return dias;
    }
    
    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public Integer getAlunoId() {
        return alunoId;
    }

    public Integer getTurmaId() {
        return turmaId;
    }

    public Integer getFrequencia() {
        return frequencia;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public Double getNota() {
        return nota;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public String getSemestre() {
        return semestre;
    }

    public String getAno() {
        return ano;
    }

    public String getResultado() {
        return resultado;
    }
}
