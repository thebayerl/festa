package model;

public class TurmaView {
    private Integer id, professorId, disciplinaId, salaId, maxAlunos, departamentoId, creditos;
    private String professorNome, disciplinaNome, codigoSala, ano, semestre, dias, horarios, codigoDisciplina;

    public TurmaView(Integer id, Integer professorId, Integer disciplinaId, Integer salaId, Integer maxAlunos,
                     String professorNome, String disciplinaNome, String codigoSala, String ano, String semestre,
                     String dias, String horarios, Integer departamentoId, Integer creditos, String codigoDisciplina) {
        this.id = id;
        this.professorId = professorId;
        this.disciplinaId = disciplinaId;
        this.salaId = salaId;
        this.maxAlunos = maxAlunos;
        this.professorNome = professorNome;
        this.disciplinaNome = disciplinaNome;
        this.codigoSala = codigoSala;
        this.ano = ano;
        this.semestre = semestre;
        this.dias = dias;
        this.horarios = horarios;
        this.departamentoId = departamentoId;
        this.creditos = creditos;
        this.codigoDisciplina = codigoDisciplina;
    }
    
    public Integer getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Integer departamentoId) {
        this.departamentoId = departamentoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }

    public Integer getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Integer disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public Integer getSalaId() {
        return salaId;
    }

    public void setSalaId(Integer salaId) {
        this.salaId = salaId;
    }

    public Integer getMaxAlunos() {
        return maxAlunos;
    }

    public void setMaxAlunos(Integer maxAlunos) {
        this.maxAlunos = maxAlunos;
    }

    public String getProfessorNome() {
        return professorNome;
    }

    public void setProfessorNome(String professorNome) {
        this.professorNome = professorNome;
    }

    public String getDisciplinaNome() {
        return disciplinaNome;
    }

    public void setDisciplinaNome(String disciplinaNome) {
        this.disciplinaNome = disciplinaNome;
    }

    public String getCodigoSala() {
        return codigoSala;
    }

    public void setCodigoSala(String codigoSala) {
        this.codigoSala = codigoSala;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public String getCodigoDisciplina() {
        return codigoDisciplina;
    }
}
