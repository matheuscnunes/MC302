package main.java.utils;

import main.java.entity.Disciplina;
import main.java.repositorio.Gerenciador;
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
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        try {
            geraMonitores();
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        try {
            geraProfessores();
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        try {
            geraDisciplina();
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        try {
            geraTurmas();
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private static void geraAlunos() throws Exception{
        Aluno nudes = new Aluno(Gerenciador.proximoId(), 203373, 32, "Matheus Nudes", "matheus@gmail.com", "123");
        Aluno fritz = new Aluno(Gerenciador.proximoId(), 203263, 32, "Mateus Fritz", "mateus@gmail.com", "123");
        Aluno battle = new Aluno(Gerenciador.proximoId(), 197960, 32, "Giovanna Battle", "giovanna@gmail.com", "123");
        Aluno capson = new Aluno(Gerenciador.proximoId(), 200200, 32, "Matheus Capson", "capson@gmail.com", "123");
        Aluno chefinho = new Aluno(Gerenciador.proximoId(), 199444, 32, "jv chefinho", "chefinho@gmail.com", "123");

        Gerenciador.adicionarAluno(nudes);
        Gerenciador.adicionarAluno(fritz);
        Gerenciador.adicionarAluno(battle);
        Gerenciador.adicionarAluno(capson);
        Gerenciador.adicionarAluno(chefinho);
    }

    private static void geraMonitores() throws  Exception{
        Monitor zejao = new Monitor(Gerenciador.proximoId(), 999666, 42, "Zejão", "ze@gmail.com", "pastel e itubaina");
        Monitor pontinho = new Monitor(Gerenciador.proximoId(), 110022, 42, "Pontinho", "pontinho@gmail.com", ".");

        Gerenciador.adicionarMonitor(zejao);
        Gerenciador.adicionarMonitor(pontinho);
    }

    private static void geraProfessores() throws Exception{
        Professor julinho = new Professor(Gerenciador.proximoId(), "Julio Cesar", "reizinho@ic.br", "123");
        final Professor CA = new Professor(Gerenciador.proximoId(), "Arnaldo 358", "358@ic.br", "santana");
        Professor mozao = new Professor(Gerenciador.proximoId(), "Anne Bronzi", "linda@imecc.br", "123");

        Gerenciador.adicionarProfessor(julinho);
        Gerenciador.adicionarProfessor(CA);
        Gerenciador.adicionarProfessor(mozao);
    }

    private static void geraDisciplina() {
        try {
            Disciplina oo = new Disciplina(Gerenciador.proximoId(), "Programação orientada a objetos", "MC302");
            Disciplina disciplinaCA = new Disciplina(Gerenciador.proximoId(), "Fundamentos Matemáticos da Computação", "MC358");
            Disciplina algeLin = new Disciplina(Gerenciador.proximoId(), "Algebra Linear", "MA237");

            Gerenciador.adicionarDisciplina(oo);
            Gerenciador.adicionarDisciplina(disciplinaCA);
            Gerenciador.adicionarDisciplina(algeLin);
        } catch (Exception e) {
            System.out.println("Não foi possível realizar a carga inicial de disciplinas");
        }
    }

    private static void geraTurmas() throws Exception{

        List<Post> posts1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            posts1.add(geraPergunta().withComentario(geraComentario()));
            posts1.add(geraConteudo());
        }

        Turma quintaSerie = new Turma(Gerenciador.proximoId(), 2018, Semestre.PRIMEIRO,
                Gerenciador.buscaDisciplina("MA237"), Gerenciador.buscarTodosAlunos().stream().filter(
                a -> a.getId() % 2 == 0).collect(Collectors.toList()), Gerenciador.buscaProfessor("linda@imecc.br"),
                Gerenciador.buscarTodosMonitores().stream().filter(monitor -> monitor.getRa() == 110022)
                        .collect(Collectors.toList()), posts1);

        List<Post> posts2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            posts2.add(geraPergunta().withComentario(geraComentario()));
            posts2.add(geraConteudo().withComentario(geraComentario() ));
        }

        Turma oitavaSerie = new Turma(Gerenciador.proximoId(), 2018, Semestre.SEGUNDO,
                Gerenciador.buscaDisciplina("MC358"), Gerenciador.buscarTodosAlunos().stream().filter(
                a -> a.getId() % 2 == 1).collect(Collectors.toList()), Gerenciador.buscaProfessor("358@ic.br"),
                Gerenciador.buscarTodosMonitores().stream().filter(monitor -> monitor.getRa() == 999666).
                        collect(Collectors.toList()), posts2);

        Gerenciador.adicionarTurma(quintaSerie);
        Gerenciador.adicionarTurma(oitavaSerie);
    }

    private static final String[] TITULOS_PERGUNTAS = {"Título Pergunta 1", "Título Pergunta 2", "Título Pergunta 3", "Título Pergunta 4", "Título Pergunta 5", "Título Pergunta 6"};
    private static final String[] PERGUNTAS = {"Pergunta 1", "Pergunta 2", "Pergunta 3", "Pergunta 4", "Pergunta 5", "Pergunta 6"};

    private static Pergunta geraPergunta() {
        String textoPergunta = PERGUNTAS[Utils.randomInt(0, 5)];
        String tituloPergunta = TITULOS_PERGUNTAS[Utils.randomInt(0, 5)];
        LocalDate data = LocalDate.of(Utils.randomInt(2016, 2018), Utils.randomInt(1, 12), Utils.randomInt(1, 28));
        int id = Gerenciador.proximoId();
        Aluno aluno = Gerenciador.buscarTodosAlunos().get(Utils.randomInt(0, Gerenciador.buscarTodosAlunos().size() - 1));

        Pergunta pergunta = new Pergunta(id, Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant()), aluno, textoPergunta, tituloPergunta, false);
        try {
            Gerenciador.adicionarPergunta(pergunta);
        } catch (Exception e) {
            Utils.stringPrinter("Não foi possivel adicionar nova pergunta %s", e.getMessage());
        }
        return pergunta;
    }

    private static final String[] TITULOS_CONTEUDOS = {"Título Conteudo 1", "Título Conteudo 2", "Título Conteudo 3", "Título Conteudo 4", "Título Conteudo 5", "Título Pergunta 6"};
    private static final String[] CONTEUDOS = {"Conteudo 1", "Conteudo 2", "Conteudo 3", "Conteudo 4", "Conteudo 5", "Pergunta 6"};

    private static Conteudo geraConteudo() {
        String textoConteudo = CONTEUDOS[Utils.randomInt(0, 5)];
        String tituloConteudo = TITULOS_CONTEUDOS[Utils.randomInt(0, 5)];
        LocalDate data = LocalDate.of(Utils.randomInt(2016, 2018), Utils.randomInt(1, 12), Utils.randomInt(1, 28));
        int id = Gerenciador.proximoId();
        Professor professor = Gerenciador.buscarTodosProfessores().get(Utils.randomInt(0, Gerenciador.buscarTodosProfessores().size() - 1));

        Conteudo conteudo = new Conteudo(id, Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant()), professor, textoConteudo, tituloConteudo);
        try {
            Gerenciador.adicionarConteudo(conteudo);
        } catch (Exception e) {
            Utils.stringPrinter("Erro ao adicionar conteudo novo. %s", e.getMessage());
        }

        return conteudo;
    }

    static final String[] COMENTARIOS = {"Comentario 1", "Comentario 2", "Comentario 3", "Comentario 4", "Comentario 5", "Comentario 6"};

    private static Comentario geraComentario() {
        String comentario = COMENTARIOS[Utils.randomInt(0, 5)];

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.addAll(Gerenciador.buscarTodosProfessores());
        usuarios.addAll(Gerenciador.buscarTodosAlunos());
        usuarios.addAll(Gerenciador.buscarTodosMonitores());

        LocalDate data = LocalDate.of(Utils.randomInt(2016, 2018), Utils.randomInt(1, 12), Utils.randomInt(1, 28));
        int id = Gerenciador.proximoId();
        Usuario usuario = usuarios.get(Utils.randomInt(0, usuarios.size() - 1));

        return new Comentario(id, Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant()), usuario, comentario);
    }
}