package main.java.utils;

import main.java.entity.Disciplina;
import main.java.entity.Gerenciador;
import main.java.entity.Semestre;
import main.java.entity.Turma;
import main.java.entity.member.Aluno;
import main.java.entity.member.Monitor;
import main.java.entity.member.Professor;

import java.util.List;
import java.util.stream.Collectors;

public class GeradorDados {

    public static void gerarDados() {
        geraAlunos();
        geraMonitores();
        geraProfessores();
        geraDisciplina();
    }

    private static void geraAlunos() {
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

    private static void geraMonitores() {
        Monitor zejao = new Monitor(Gerenciador.proximoId(), 999666, 42, "Zejão", "ze@gmail.com", "pastel e itubaina");
        Monitor pontinho = new Monitor(Gerenciador.proximoId(), 999666, 42, "Pontinho", "pontinho@gmail.com", ".");

        Gerenciador.adicionarMonitor(zejao);
        Gerenciador.adicionarMonitor(pontinho);
    }

    private static void geraProfessores() {
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

    private static void geraTurmas() {
        Turma quintaSerie = new Turma(Gerenciador.proximoId(), 2018, Semestre.PRIMEIRO,
                Gerenciador.buscarTodasDisciplinas().get(0), Gerenciador.buscarTodosAlunos().stream().filter(
                a -> a.getId() % 2 == 0).collect(Collectors.toList()), Gerenciador.buscaProfessor("linda@imecc.br"),
                Gerenciador.buscarTodosMonitores(), null);
    }

}
