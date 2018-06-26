package main.java.entity;

import main.java.entity.content.Comentario;
import main.java.entity.content.Conteudo;
import main.java.entity.content.Pergunta;
import main.java.entity.content.Post;
import main.java.entity.member.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GerenciadorAluno {
    private static int id = 0;

    private static List<Aluno> alunos = new ArrayList<Aluno>();

    public GerenciadorAluno() {
        super();
    }

    public static int proximoId() {
        GerenciadorAluno.id++;
        return id;
    }

    public static void adicionarAluno(Aluno aluno) throws Exception{
        if (aluno == null)
            throw new NullPointerException("[Adicionar Aluno] O aluno a ser adicionado não pode ser nulo");

        Aluno alunoEncontrado = buscaAluno(aluno.ra);

        if (alunoEncontrado != null) {
            throw new Error("[Adicionar Aluno] Aluno com RA " + aluno.ra + " já existe.");
        }

        alunos.add(aluno);
    }

    public static Aluno removerAluno(int ra) {
        Aluno alunoEncontrado = buscaAluno(ra);

        if (alunoEncontrado == null) {
            //Aluno não existe na base;
            return null;
        }

        alunos.remove(alunoEncontrado);
        return alunoEncontrado;
    }

    public static List<Aluno> buscarTodosAlunos() {
        return alunos;
    }

    public static Aluno buscaAluno(int ra) {
        List<Aluno> alunosEncontrados = alunos.stream().filter(aluno -> {
            return aluno.ra == ra;
        }).collect(Collectors.toList());

        if (alunosEncontrados.size() >= 1) {
            return alunosEncontrados.get(0);
        }

        return null;
    }


    public static Aluno buscaAluno(String email) {
        List<Aluno> alunosEncontrados = alunos.stream().filter(aluno -> {
            return aluno.getEmail().equals(email);
        }).collect(Collectors.toList());

        if (alunosEncontrados.size() >= 1) {
            return alunosEncontrados.get(0);
        }

        return null;
    }
}
