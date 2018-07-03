package main.java.utils;

import main.java.entity.Disciplina;
import main.java.entity.GeradorSequencia;
import main.java.entity.Semestre;
import main.java.entity.Turma;
import main.java.entity.content.Comentario;
import main.java.entity.content.Conteudo;
import main.java.entity.content.Pergunta;
import main.java.entity.content.Post;
import main.java.entity.member.Aluno;
import main.java.entity.member.Monitor;
import main.java.entity.member.Professor;
import main.java.entity.member.Usuario;
import main.java.repositorio.*;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class GeradorDados {

    public static void gerarDados() {
        try {
            geraAlunos();
            geraMonitores();
            geraProfessores();
            geraDisciplina();
            geraTurmas();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void geraAlunos() throws Exception {
        Aluno nudes = new Aluno(GeradorSequencia.nextSequencia(), 203373, 32, "Matheus Nudes", "matheus@gmail.com", "123");
        Aluno fritz = new Aluno(GeradorSequencia.nextSequencia(), 203263, 32, "Mateus Fritz", "mateus@gmail.com", "123");
        Aluno battle = new Aluno(GeradorSequencia.nextSequencia(), 197960, 32, "Giovanna Battle", "giovanna@gmail.com", "123");
        Aluno capson = new Aluno(GeradorSequencia.nextSequencia(), 200200, 32, "Matheus Capson", "capson@gmail.com", "123");
        Aluno chefinho = new Aluno(GeradorSequencia.nextSequencia(), 199444, 32, "jv chefinho", "chefinho@gmail.com", "123");

        GerenciadorAluno gerenciadorAluno = GerenciadorAluno.getInstance();
        gerenciadorAluno.add(nudes);
        gerenciadorAluno.add(fritz);
        gerenciadorAluno.add(battle);
        gerenciadorAluno.add(capson);
        gerenciadorAluno.add(chefinho);
    }

    private static void geraMonitores() throws Exception {
        Monitor zejao = new Monitor(GeradorSequencia.nextSequencia(), 999666, 42, "Zejão", "ze@gmail.com", "pastel e itubaina");
        Monitor pontinho = new Monitor(GeradorSequencia.nextSequencia(), 110022, 42, "Pontinho", "pontinho@gmail.com", ".");

        GerenciadorMonitor gerenciadorMonitor = GerenciadorMonitor.getInstance();

        gerenciadorMonitor.add(zejao);
        gerenciadorMonitor.add(pontinho);
    }

    private static void geraProfessores() throws Exception {
        Professor julinho = new Professor(GeradorSequencia.nextSequencia(), "Julio Cesar", "reizinho@ic.br", "123");
        final Professor CA = new Professor(GeradorSequencia.nextSequencia(), "Arnaldo 358", "358@ic.br", "santana");
        Professor mozao = new Professor(GeradorSequencia.nextSequencia(), "Anne Bronzi", "linda@imecc.br", "123");

        GerenciadorProfessor gerenciadorProfessor = GerenciadorProfessor.getInstance();
        gerenciadorProfessor.add(julinho);
        gerenciadorProfessor.add(CA);
        gerenciadorProfessor.add(mozao);
    }

    private static void geraDisciplina() {
        try {
            Disciplina oo = new Disciplina(GeradorSequencia.nextSequencia(), "Programação orientada a objetos", "MC302");
            Disciplina disciplinaCA = new Disciplina(GeradorSequencia.nextSequencia(), "Fundamentos Matemáticos da Computação", "MC358");
            Disciplina algeLin = new Disciplina(GeradorSequencia.nextSequencia(), "Algebra Linear", "MA237");

            GerenciadorDisciplina gerenciadorDisciplina = GerenciadorDisciplina.getInstance();
            gerenciadorDisciplina.add(oo);
            gerenciadorDisciplina.add(disciplinaCA);
            gerenciadorDisciplina.add(algeLin);
        } catch (Exception e) {
            System.out.println("Não foi possível realizar a carga inicial de disciplinas");
        }
    }

    private static void geraTurmas() throws Exception {

        // Gera a turma Quinta Serie
        List<Post> postsQuintaSerie = new ArrayList<>();
        for (int i = 0; i < 5; i++) { // Gera posts randômicos
            postsQuintaSerie.add(geraPergunta().withComentario(geraComentario()));
            postsQuintaSerie.add(geraConteudo());
        }

        Disciplina ma237 = GerenciadorDisciplina.getInstance().find("MA237");
        List<Aluno> alunosQuintaSerie = GerenciadorAluno.getInstance().buscarTodos().stream().
                filter(a -> a.getId() % 2 == 0).collect(Collectors.toList());
        Professor professorQuintaSerie = GerenciadorProfessor.getInstance().find("linda@imecc.br");
        List<Monitor> monitoresQuintaSerie = GerenciadorMonitor.getInstance().buscarTodos().stream().
                filter(monitor -> monitor.getRa() == 110022).collect(Collectors.toList());

        Turma quintaSerie = new Turma(GeradorSequencia.nextSequencia(), 2018, Semestre.PRIMEIRO, ma237,
                alunosQuintaSerie, professorQuintaSerie, monitoresQuintaSerie, postsQuintaSerie);

        // Gera a turma Oitava Serie
        List<Post> postsOitavaSerie = new ArrayList<>();
        for (int i = 0; i < 5; i++) { // Gera posts randômicos
            postsOitavaSerie.add(geraPergunta().withComentario(geraComentario()));
            postsOitavaSerie.add(geraConteudo().withComentario(geraComentario()));
        }

        List<Aluno> alunosOitavaSerie = GerenciadorAluno.getInstance().buscarTodos().stream()
                .filter(a -> a.getId() % 2 == 1).collect(Collectors.toList());
        Professor professorOitavaSerie = GerenciadorProfessor.getInstance().find("358@ic.br");
        List<Monitor> monitoresOitavaSerie = GerenciadorMonitor.getInstance().buscarTodos().stream().
                filter(monitor -> monitor.getRa() == 999666).collect(Collectors.toList());
        Disciplina mc358 = GerenciadorDisciplina.getInstance().find("MC358");

        Turma oitavaSerie = new Turma(GeradorSequencia.nextSequencia(), 2018, Semestre.SEGUNDO, mc358,
                alunosOitavaSerie, professorOitavaSerie, monitoresOitavaSerie, postsOitavaSerie);

        GerenciadorTurma gerenciadorTurma = GerenciadorTurma.getInstance();
        gerenciadorTurma.add(quintaSerie);
        gerenciadorTurma.add(oitavaSerie);
    }

    private static final String[] TITULOS_PERGUNTAS = {"Título Pergunta 1", "Título Pergunta 2", "Título Pergunta 3",
            "Título Pergunta 4", "Título Pergunta 5", "Título Pergunta 6"};
    private static final String[] PERGUNTAS = {"Pergunta 1", "Pergunta 2", "Pergunta 3", "Pergunta 4", "Pergunta 5",
            "Pergunta 6"};

    private static Pergunta geraPergunta() {
        String textoPergunta = PERGUNTAS[Utils.randomInt(0, 5)];
        String tituloPergunta = TITULOS_PERGUNTAS[Utils.randomInt(0, 5)];
        LocalDate data = LocalDate.of(Utils.randomInt(2016, 2018), Utils.randomInt(1, 12), Utils.randomInt(1, 28));
        int id = GeradorSequencia.nextSequencia();

        Pergunta pergunta = null;
        try {
            List<Aluno> alunos = GerenciadorAluno.getInstance().buscarTodos();
            Aluno aluno = alunos.get(Utils.randomInt(0, alunos.size() - 1));

            pergunta = new Pergunta(id, Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant()), aluno,
                    textoPergunta, tituloPergunta, false);

        } catch (Exception e) {
            Utils.stringPrinter("Não foi possivel adicionar nova pergunta %s", e.getMessage());
        }

        return pergunta;
    }

    private static final String[] TITULOS_CONTEUDOS = {"Título Conteudo 1", "Título Conteudo 2", "Título Conteudo 3",
            "Título Conteudo 4", "Título Conteudo 5", "Título Pergunta 6"};
    private static final String[] CONTEUDOS = {"Conteudo 1", "Conteudo 2", "Conteudo 3", "Conteudo 4", "Conteudo 5",
            "Pergunta 6"};

    private static Conteudo geraConteudo() {
        String textoConteudo = CONTEUDOS[Utils.randomInt(0, 5)];
        String tituloConteudo = TITULOS_CONTEUDOS[Utils.randomInt(0, 5)];
        LocalDate data = LocalDate.of(Utils.randomInt(2016, 2018), Utils.randomInt(1, 12), Utils.randomInt(1, 28));
        int id = GeradorSequencia.nextSequencia();

        Conteudo conteudo = null;
        try {
            List<Professor> professores = GerenciadorProfessor.getInstance().buscarTodos();
            Professor professor = professores.get(Utils.randomInt(0, professores.size() - 1));

            conteudo = new Conteudo(id, Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant()), professor,
                    textoConteudo, tituloConteudo);
        } catch (Exception e) {
            Utils.stringPrinter("Erro ao adicionar conteudo novo. %s", e.getMessage());
        }

        return conteudo;
    }

    static final String[] COMENTARIOS = {"Comentario 1", "Comentario 2", "Comentario 3", "Comentario 4", "Comentario 5",
            "Comentario 6"};

    private static Comentario geraComentario() {
        String comentario = COMENTARIOS[Utils.randomInt(0, 5)];

        List<Usuario> usuarios = new ArrayList<>();
        try {
            usuarios.addAll(GerenciadorProfessor.getInstance().buscarTodos());
            usuarios.addAll(GerenciadorAluno.getInstance().buscarTodos());
            usuarios.addAll(GerenciadorMonitor.getInstance().buscarTodos());
        } catch (Exception e) {
            System.out.println("Erro tentando obter todos alunos, professores e monitores");
        }

        LocalDate data = LocalDate.of(Utils.randomInt(2016, 2018), Utils.randomInt(1, 12), Utils.randomInt(1, 28));
        int id = GeradorSequencia.nextSequencia();
        Usuario usuario = usuarios.get(Utils.randomInt(0, usuarios.size() - 1));

        return new Comentario(id, Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant()), usuario, comentario);
    }
}