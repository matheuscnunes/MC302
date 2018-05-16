package main.java.entity;

import main.java.entity.member.Aluno;
import main.java.entity.member.Monitor;
import main.java.entity.member.Professor;
import main.java.entity.member.Usuario;

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

    ////////////         Métodos de gerenciamento de Login  ///////////////////////

    public static boolean login(String email, String senha){
        //verifica nas listas se existe algum usuário com o email e senha recebidos no método

        //se encontrar, altera o atributo usuarioAtual e retorna true

        //se não encontrar, retorna false
        return true;
    }

    public static Usuario deslogar(){
        Usuario usuarioLogado = usuarioAtual;
        usuarioAtual = null;
        return usuarioLogado;
    }

    ////////////         Métodos de gerenciamento de Alunos  ///////////////////////
    public static void adicionarAluno(Aluno aluno){
        if(aluno == null) throw new NullPointerException("[Adicionar Aluno] O aluno a ser adicionado não pode ser nulo");

        Aluno alunoEncontrado = buscaAluno(aluno.ra);

        if(alunoEncontrado != null){
            throw new Error("[Adicionar Aluno] Aluno com RA " + aluno.ra + " já existe.");
        }

        alunos.add(aluno);
    }

    public static Aluno removerAluno(int ra){
        Aluno alunoEncontrado = buscaAluno(ra);

        if(alunoEncontrado == null){
            //Aluno não existe na base;
            return null;
        }

        alunos.remove(alunoEncontrado);
        return alunoEncontrado;
    }

    public static List<Aluno> buscarTodosAlunos(){
        return alunos;
    }

    public static Aluno buscaAluno(int ra){
        List<Aluno> alunosEncontrados = alunos.stream().filter(aluno -> {
            return aluno.ra == ra;
        }).collect(Collectors.toList());

        if(alunosEncontrados.size() >= 1){
            return alunosEncontrados.get(0);
        }

        return null;
    }

    public static Aluno buscaAluno(String email){
        List<Aluno> alunosEncontrados = alunos.stream().filter(aluno -> {
            return aluno.getEmail().equals(email);
        }).collect(Collectors.toList());

        if(alunosEncontrados.size() >= 1){
            return alunosEncontrados.get(0);
        }

        return null;
    }

    ////////////         Métodos de gerenciamento de Professores  ///////////////////////
    public static void adicionarProfessor(Professor professor){
        if(professor == null) throw new NullPointerException("[Adicionar Professor] O professor a ser adicionado não pode ser nulo");

        //Validar se professor já existe na lista de professores antes de inserir

        professores.add(professor);
    }

    public static Professor removerProfessor(int id){
        Professor profEncontrado = buscaProfessor(id);

        if(profEncontrado == null){
            //Professor não existe na base;
            return null;
        }

        professores.remove(profEncontrado);
        return profEncontrado;
    }

    public static List<Professor> buscarTodosProfessores(){
        return professores;
    }

    public static Professor buscaProfessor(int id){
        List<Professor> professoresAchados = professores.stream().filter(professor -> {
            return professor.id == id;
        }).collect(Collectors.toList());

        if(professoresAchados.size() >= 1){
            return professoresAchados.get(0);
        }

        return null;
    }

    ////////////         Métodos de gerenciamento de Monitores  ///////////////////////
    public static void adicionarMonitor(Monitor monitor){
        if(monitor == null) throw new NullPointerException("[Adicionar Monitor] O monitor a ser adicionado não pode ser nulo");

        //Validar se monitor já existe na lista de monitores antes de inserir

        monitores.add(monitor);
    }

    public static Monitor removerMonitor(int ra){
        Monitor monitorEncontrado = buscaMonitor(ra);

        if(monitorEncontrado == null){
            //Monitor não existe na base;
            return null;
        }

        monitores.remove(monitorEncontrado);
        return monitorEncontrado;
    }

    public static List<Monitor> buscarTodosMonitores(){
        return monitores;
    }

    public static Monitor buscaMonitor(int ra){
        List<Monitor> monitorersAchados = monitores.stream().filter(monitor -> {
            return monitor.ra == ra;
        }).collect(Collectors.toList());

        if(monitorersAchados.size() >= 1){
            return monitorersAchados.get(0);
        }

        return null;
    }

    ////////////         Métodos de gerenciamento de Disciplinas  ///////////////////////
    public static void adicionarDisciplina(Disciplina disciplina){
        if(disciplina == null) throw new NullPointerException("[Adicionar Disciplina] A disciplina a ser adicionado não pode ser nula");

        //Validar se a disciplina já existe na lista de disciplinas antes de inserir

        disciplinas.add(disciplina);
    }

    public static Disciplina removerDisciplina(String codigo){
        Disciplina disciplinaEncontrada = buscaDisciplina(codigo);

        if(disciplinaEncontrada == null){
            //Disciplina não existe na base;
            return null;
        }

        disciplinas.remove(disciplinaEncontrada);
        return disciplinaEncontrada;
    }

    public static List<Disciplina> buscarTodasDisciplinas(){
        return disciplinas;
    }

    public static Disciplina buscaDisciplina(String codigo){
        List<Disciplina> disciplinasAchadas = disciplinas.stream().filter(disciplina -> {
            return disciplina.getCodigo().equals(codigo);
        }).collect(Collectors.toList());

        if(disciplinasAchadas.size() >= 1){
            return disciplinasAchadas.get(0);
        }

        return null;
    }

    ////////////         Métodos de gerenciamento de Turmas  ///////////////////////
    public static void adicionarTurma(Turma turma){
        if(turma == null) throw new NullPointerException("[Adicionar Turma] A turma a ser adicionado não pode ser nula");

        //Validar se a turma já existe na lista de turmas antes de inserir

        turmas.add(turma);
    }

    public static Turma removerTurma(int id){
        Turma turmaEncontrada = buscaTurma(id);

        if(turmaEncontrada == null){
            //Turma não existe na base;
            return null;
        }

        turmas.remove(turmaEncontrada);
        return turmaEncontrada;
    }

    public static List<Turma> buscarTodasTurmas(){
        return turmas;
    }

    public static Turma buscaTurma(int id){
        List<Turma> turmasEncontradas = turmas.stream().filter(turma -> {
            return turma.getId() == id;
        }).collect(Collectors.toList());

        if(turmasEncontradas.size() >= 1){
            return turmasEncontradas.get(0);
        }

        return null;
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
