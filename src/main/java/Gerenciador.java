package main.java;

import entity.Disciplina;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Gerenciador {
    private static List<Professor> professores = new ArrayList<Professor>();
    private static List<Aluno> alunos = new ArrayList<Aluno>();
    private static List<Monitor> monitores = new ArrayList<Monitor>();
    private static List<Disciplina> disciplinas = new ArrayList<Disciplina>();
    private static List<Turma> turmas = new ArrayList<Turma>();
    private static Usuario usuarioAtual;

    public Gerenciador() {
        super();
    }

    public static boolean login(String email, String senha){
        //verifica nas listas se existe algum usuário com o email e senha

        //se encontrar, altera o atributo usuarioAtual e retorna true

        //se não encontrar, retorna false
        return true;
    }

    public static Usuario deslogar(){
        Usuario usuarioLogado = usuarioAtual;
        usuarioAtual = null;
        return usuarioLogado;
    }

    public static void adicionarAluno(Aluno aluno){
        if(aluno == null) throw new NullPointerException("[Adicionar Aluno] O aluno a ser adicionado não pode ser nulo");
        alunos.add(aluno);
    }

    public static Aluno removerAluno(int ra){
        Aluno alunoEncontrado = buscaAluno(ra);
        alunos.remove(alunoEncontrado);
        return alunoEncontrado;
    }

    public static List<Aluno> buscarTodosAlunos(){
        return alunos;
    }

    public static Aluno buscaAluno(int ra){
        Aluno alunoEncontrado = alunos.stream().filter(aluno -> {
            return aluno.ra == ra;
        }).collect(Collectors.toList()).get(0);

        return alunoEncontrado;
    }

    public static void adicionarProfessor(Professor professor){
       // return alunos;
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
