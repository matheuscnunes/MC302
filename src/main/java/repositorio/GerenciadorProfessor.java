package main.java.repositorio;

import main.java.entity.member.Professor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorProfessor implements IGerenciador<Professor> {
    private static int id = 0;

    private static List<Professor> professores = new ArrayList<Professor>();

    private static GerenciadorProfessor gerenciadorProfessor = null;

    public static GerenciadorProfessor getInstance() {
        if (gerenciadorProfessor == null) {
            gerenciadorProfessor = new GerenciadorProfessor();
        }

        return gerenciadorProfessor;
    }

    private GerenciadorProfessor() {
        super();
    }

    ////////////         Métodos de gerenciamento de Professores  ///////////////////////
    @Override
    public void add(Professor professor) throws Exception {
        if (professor == null)
            throw new NullPointerException("[Adicionar Professor] O professor a ser adicionado não pode ser nulo");

        Professor professorEncontrado = find(professor.getEmail());

        if (professorEncontrado != null) {
            throw new Error("[Adicionar Professor] Professor com email " + professor.getEmail() + " já existe.");
        }

        professores.add(professor);
    }

    @Override
    public Professor find(int id) throws Exception {
        List<Professor> professoresAchados = professores.stream().filter(professor -> {
            return professor.id == id;
        }).collect(Collectors.toList());

        if (professoresAchados.size() >= 1) {
            return professoresAchados.get(0);
        }

        return null;
    }

    @Override
    public Professor find(String email) throws Exception {
        List<Professor> professoresAchados = professores.stream().filter(professor -> {
            return professor.getEmail().equals(email);
        }).collect(Collectors.toList());

        if (professoresAchados.size() >= 1) {
            return professoresAchados.get(0);
        }

        return null;
    }

    @Override
    public Professor remover(String id) throws Exception {
        Professor profEncontrado = find(id);

        if (profEncontrado == null) {
            //Professor não existe na base;
            return null;
        }

        professores.remove(profEncontrado);
        return profEncontrado;
    }

    @Override
    public boolean remover(Professor obj) throws Exception {
        return professores.remove(obj);
    }

    @Override
    public List<Professor> buscarTodos() throws Exception {
        return professores;
    }
}
