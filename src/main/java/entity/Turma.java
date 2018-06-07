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

    public void adicionarAluno(Aluno aluno) throws Exception {
        if (aluno == null) {
            throw new Exception("Aluno nulo em adicionarAluno na Turma com id " + id);
        }
        alunos.add(aluno);
    }

    public boolean removerAluno(List<Aluno> alunos) throws Exception {
        if (this.alunos != null) {
            if (alunos == null || alunos.isEmpty()) {
                throw new Exception("Alunos não fornecidos em removerAlunos na Turma com id " + id);
            }
            return this.alunos.removeAll(alunos);
        }
        return false;
    }

    public boolean removerAluno(Aluno aluno) throws Exception {
        if (alunos != null) {
            if (aluno == null) {
                throw new Exception("Aluno não fornecido em removerAluno na Turma com id " + id);
            }
            return alunos.remove(aluno);
        }
        return false;
    }

    public Aluno removerAluno(int id) throws Exception {
        if (alunos != null) {
            Aluno aluno = getAluno(id);
            if (aluno == null) {
                throw new Exception("Aluno não fornecido em removerAluno na Turma com id " + id);
            }
            alunos.remove(aluno);
            return aluno;
        }
        return null;
    }

    public void adicionarPost(Post post) throws Exception {
        if (post == null) {
            throw new Exception("Post nulo em adicionarPost na Turma com id " + id);
        }
        posts.add(post);
    }

    public boolean removerPost(List<Post> posts) throws Exception {
        if (this.posts != null) {
            if (posts == null || posts.isEmpty()) {
                throw new Exception("Posts não fornecidos em removerPost na Turma com id " + id);
            }
            return this.posts.removeAll(posts);
        }
        return false;
    }

    public boolean removerPost(Post post) throws Exception {
        if (posts != null) {
            if (post == null) {
                throw new Exception("Post não fornecido em removerPost na Turma com id " + id);
            }
            return posts.remove(post);
        }
        return false;
    }

    public Post removerPost(int id) throws Exception {
        if (posts != null) {
            Post post = getPost(id);
            if (post == null) {
                throw new Exception("Post não fornecido em removerPost na Turma com id " + id);
            }
            posts.remove(post);
            return post;
        }
        return null;
    }

    public void adicionarMonitor(Monitor monitor) throws Exception {
        if (monitor == null) {
            throw new Exception("Monitor nulo em adicionarMonitor na Turma com id " + id);
        }
        alunos.add(monitor);
    }

    public boolean removerMonitor(List<Monitor> monitores) throws Exception {
        if (this.monitores != null) {
            if (monitores == null || monitores.isEmpty()) {
                throw new Exception("Monitores não fornecidos em removerMonitores na Turma com id " + id);
            }
            return this.monitores.removeAll(monitores);
        }
        return false;
    }

    public boolean removerMonitor(Monitor monitor) throws Exception {
        if (monitores != null) {
            if (monitor == null) {
                throw new Exception("Monitor não fornecido em removerMonitor na Turma com id " + id);
            }
            return monitores.remove(monitor);
        }
        return false;
    }

    public Monitor removerMonitor(int id) throws Exception {
        if (monitores != null) {
            Monitor monitor = getMonitor(id);
            if (monitor == null) {
                throw new Exception("Monitor não fornecido em removerMonitor na Turma com id " + id);
            }
            monitores.remove(monitor);
            return monitor;
        }
        return null;
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

    public Aluno getAluno(int id) {
        if (alunos != null) {
            for (Aluno aluno : alunos) {
                if (aluno.getId() == id) {
                    return aluno;
                }
            }
        }
        return null;
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

    public Monitor getMonitor(int id) {
        if (monitores != null) {
            for (Monitor monitor : monitores) {
                if (monitor.getId() == id) {
                    return monitor;
                }
            }
        }
        return null;
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

    public Post getPost(int id) {
        if (posts != null) {
            for (Post post : posts) {
                if (post.getID() == id) {
                    return post;
                }
            }
        }
        return null;
    }

    public void setPosts(List<Post> posts) throws Exception {
        if (posts == null) {
            throw new Exception("Lista de Post não fornecida para a turma em setPosts");
        }
        this.posts = posts;
    }
}
