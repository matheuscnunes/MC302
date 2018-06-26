package main.java.entity;

import main.java.entity.content.Post;
import main.java.entity.member.*;

import java.util.List;

public class Turma {
    private int id;
    private int ano;
    private Semestre semestre;
    private Disciplina disciplina;
    private List<Aluno> alunos;
    private Professor professor;
    private List<Monitor> monitores;
    private List<Post> posts;

    public Turma(int id, int ano, Semestre semestre, Disciplina disciplina, List<Aluno>
            alunos, Professor professor, List<Monitor> monitores, List<Post> posts) {
        this.id = id;
        this.ano = ano;
        this.semestre = semestre;
        this.disciplina = disciplina;
        this.alunos = alunos;
        this.professor = professor;
        this.monitores = monitores;
        this.posts = posts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("Id da disciplina inválido em setId");
        }
        this.id = id;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) throws Exception {
        if (ano <= 0) {
            throw new Exception("Ano inválido para a turma em setAno");
        }
        this.ano = ano;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) throws Exception {
        if (semestre == null) {
            throw new Exception("Semestre não fornecido para a turma em setSemestre");
        }
        this.semestre = semestre;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) throws Exception {
        if (disciplina == null) {
            throw new Exception("Disciplina não instanciada para a turma em setDisciplina");
        }
        this.disciplina = disciplina;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) throws Exception {
        if (alunos == null) {
            throw new Exception("Lista de Aluno não fornecida para a turma em setAlunos");
        }
        this.alunos = alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) throws Exception {
        if (professor == null) {
            throw new Exception("Professor não instanciado para a turma em setProfessor");
        }
        this.professor = professor;
    }

    public List<Monitor> getMonitores() {
        return monitores;
    }

    public void setMonitores(List<Monitor> monitores) throws Exception {
        if (monitores == null) {
            throw new Exception("Lista de Monitor não fornecida para a turma em setMonitores");
        }
        this.monitores = monitores;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) throws Exception {
        if (posts == null) {
            throw new Exception("Lista de Post não fornecida para a turma em setPosts");
        }
        this.posts = posts;
    }

    public void addPost(Post post) throws Exception {
        if (post == null) {
            throw new Exception("Lista de Post não fornecida para a turma em setPosts");
        }
        this.posts.add(post);
    }
}
