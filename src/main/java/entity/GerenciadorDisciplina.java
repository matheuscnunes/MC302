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

public class GerenciadorDisciplina {
    private static int id = 0;

    private static List<Disciplina> disciplinas = new ArrayList<Disciplina>();

    public GerenciadorDisciplina() {
        super();
    }

    ////////////         Métodos de gerenciamento de Disciplinas  ///////////////////////
    public static void adicionarDisciplina(Disciplina disciplina) throws Exception{
        if (disciplina == null)
            throw new NullPointerException("[Adicionar Disciplina] A disciplina a ser adicionado não pode ser nula");

        //Validar se a disciplina já existe na lista de disciplinas antes de inserir

        disciplinas.add(disciplina);
    }

    public static Disciplina removerDisciplina(String codigo) throws Exception{
        Disciplina disciplinaEncontrada = buscaDisciplina(codigo);

        if (disciplinaEncontrada == null) {
            //Disciplina não existe na base;
            return null;
        }

        disciplinas.remove(disciplinaEncontrada);
        return disciplinaEncontrada;
    }

    public static List<Disciplina> buscarTodasDisciplinas() {
        return disciplinas;
    }

    public static Disciplina buscaDisciplina(String codigo) throws Exception{
        List<Disciplina> disciplinasAchadas = disciplinas.stream().filter(disciplina -> {
            return disciplina.getCodigo().equals(codigo);
        }).collect(Collectors.toList());

        if (disciplinasAchadas.size() >= 1) {
            return disciplinasAchadas.get(0);
        }

        return null;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
