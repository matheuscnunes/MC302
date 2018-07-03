package main.java.repositorio;

import main.java.entity.Turma;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorTurma implements IGerenciador<Turma> {
    private static int id = 0;

    private static List<Turma> turmas = new ArrayList<Turma>();

    public GerenciadorTurma() {
        super();
    }

    @Override
    public void add(Turma turma) throws Exception {
        if (turma == null)
            throw new NullPointerException("[Adicionar Turma] A turma a ser adicionado não pode ser nula");

        turmas.add(turma);
    }

    @Override
    public Turma find(int id) throws Exception {
        List<Turma> turmasEncontradas = turmas.stream().filter(turma -> {
            return turma.getId() == id;
        }).collect(Collectors.toList());

        if (turmasEncontradas.size() >= 1) {
            return turmasEncontradas.get(0);
        }

        return null;
    }

    @Override
    public Turma find(String ano) throws Exception {
        List<Turma> turmasEncontradas = turmas.stream().filter(turma -> {
            return turma.getAno() == Integer.parseInt(ano);
        }).collect(Collectors.toList());

        if (turmasEncontradas.size() >= 1) {
            return turmasEncontradas.get(0);
        }

        return null;
    }

    @Override
    public Turma remover(String id) throws Exception {
        Turma turmaEncontrada = find(Integer.parseInt(id));

        if (turmaEncontrada == null) {
            return null;
        }

        turmas.remove(turmaEncontrada);
        return turmaEncontrada;
    }

    @Override
    public boolean remover(Turma obj) throws Exception {
        return this.turmas.remove(obj);
    }

    @Override
    public List<Turma> buscarTodos() throws Exception {
        return this.turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }
}
