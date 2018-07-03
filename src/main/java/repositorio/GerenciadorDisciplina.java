package main.java.repositorio;

import main.java.entity.Disciplina;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorDisciplina implements IGerenciador<Disciplina> {
    private static int id = 0;

    private static List<Disciplina> disciplinas = new ArrayList<Disciplina>();

    public GerenciadorDisciplina() {
        super();
    }

    @Override
    public void add(Disciplina disciplina) throws Exception {
        if (disciplina == null)
            throw new NullPointerException("[Adicionar Disciplina] A disciplina a ser adicionado não pode ser nula");

        disciplinas.add(disciplina);
    }

    @Override
    public Disciplina find(int id) throws Exception {
        List<Disciplina> disciplinasAchadas = disciplinas.stream().filter(disciplina -> {
            return disciplina.getId() == id;
        }).collect(Collectors.toList());

        if (disciplinasAchadas.size() >= 1) {
            return disciplinasAchadas.get(0);
        }

        return null;
    }

    @Override
    public Disciplina find(String codigo) throws Exception {
        List<Disciplina> disciplinasAchadas = disciplinas.stream().filter(disciplina -> {
            return disciplina.getCodigo().equals(codigo);
        }).collect(Collectors.toList());

        if (disciplinasAchadas.size() >= 1) {
            return disciplinasAchadas.get(0);
        }

        return null;
    }

    @Override
    public Disciplina remover(String codigo) throws Exception {
        Disciplina disciplinaEncontrada = find(codigo);

        if (disciplinaEncontrada == null) {
            //Disciplina não existe na base;
            return null;
        }

        disciplinas.remove(disciplinaEncontrada);
        return disciplinaEncontrada;
    }

    @Override
    public boolean remover(Disciplina obj) throws Exception {
        return this.disciplinas.remove(obj);
    }

    @Override
    public List<Disciplina> buscarTodos() throws Exception {
        return this.disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
