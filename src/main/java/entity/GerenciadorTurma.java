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

public class GerenciadorTurma {
    private static int id = 0;

    private static List<Turma> turmas = new ArrayList<Turma>();

    public GerenciadorTurma() {
        super();
    }

    ////////////         Métodos de gerenciamento de Turmas  ///////////////////////
    public static void adicionarTurma(Turma turma) throws Exception{
        if (turma == null)
            throw new NullPointerException("[Adicionar Turma] A turma a ser adicionado não pode ser nula");

        //Validar se a turma já existe na lista de turmas antes de inserir

        turmas.add(turma);
    }

    public static Turma removerTurma(int id) throws Exception{
        Turma turmaEncontrada = buscaTurma(id);

        if (turmaEncontrada == null) {
            //Turma não existe na base;
            return null;
        }

        turmas.remove(turmaEncontrada);
        return turmaEncontrada;
    }

    public static List<Turma> buscarTodasTurmas() {
        return turmas;
    }

    public static Turma buscaTurma(int id) throws Exception{
        List<Turma> turmasEncontradas = turmas.stream().filter(turma -> {
            return turma.getId() == id;
        }).collect(Collectors.toList());

        if (turmasEncontradas.size() >= 1) {
            return turmasEncontradas.get(0);
        }

        return null;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }
}
