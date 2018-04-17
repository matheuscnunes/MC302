package main.java;

import entity.Disciplina;

public class Gerenciador {
    private List<Professor> professores;
    private List<Aluno> alunos;
    private List<Monitor> monitores;
    private List<Disciplina> disciplinas;
    private List<Turma> turmas;
    private Usuario usuarioAtual;

    public Gerenciador() {
        this.professores = new ArrayList<Professor>();
        this.alunos = new ArrayList<Aluno>();
        this.monitores = new ArrayList<Monitor>();
        this.disciplinas = new ArrayList<Disciplina>();
        this.turmas = new ArrayList<Turma>();
    }

    public boolean login(String email, String senha){
        //verifica nas listas se existe algum usuário com o email e senha

        //se encontrar, altera o atributo usuarioAtual e retorna true

        //se não encontrar, retorna false
        return true;
    }

    public Usuario deslogar(){
        Usuario usuarioLogado = this.usuarioAtual;
        this.usuarioAtual = null;
        return usuarioLogado;
    }

    public void adicionarAluno(Aluno aluno){
        this.alunos
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Monitor> getMonitores() {
        return monitores;
    }

    public void setMonitores(List<Monitor> monitores) {
        this.monitores = monitores;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public Usuario getUsuarioAtual() {
        return usuarioAtual;
    }

    public void setUsuarioAtual(Usuario usuarioAtual) {
        this.usuarioAtual = usuarioAtual;
    }
}
