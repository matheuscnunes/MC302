package main.java.repositorio;

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

public class GerenciadorProfessor implements IGerenciador<Professor>{
    private static int id = 0;

    private static List<Professor> professores = new ArrayList<Professor>();

    public GerenciadorProfessor() {
        super();
    }

    ////////////         Métodos de gerenciamento de Professores  ///////////////////////
    public static void adicionarProfessor(Professor professor) throws Exception{
        if (professor == null)
            throw new NullPointerException("[Adicionar Professor] O professor a ser adicionado não pode ser nulo");

        Professor professorEncontrado = buscaProfessor(professor.getEmail());

        if (professorEncontrado != null) {
            throw new Error("[Adicionar Professor] Professor com email " + professor.getEmail() + " já existe.");
        }

        professores.add(professor);
    }

    public static Professor removerProfessor(int id) throws Exception{
        Professor profEncontrado = buscaProfessor(id);

        if (profEncontrado == null) {
            //Professor não existe na base;
            return null;
        }

        professores.remove(profEncontrado);
        return profEncontrado;
    }

    public static List<Professor> buscarTodosProfessores() {
        return professores;
    }

    public static Professor buscaProfessor(int id) throws Exception{
        List<Professor> professoresAchados = professores.stream().filter(professor -> {
            return professor.id == id;
        }).collect(Collectors.toList());

        if (professoresAchados.size() >= 1) {
            return professoresAchados.get(0);
        }

        return null;
    }

    public static Professor buscaProfessor(String email) throws Exception{
        List<Professor> professoresAchados = professores.stream().filter(professor -> {
            return professor.getEmail().equals(email);
        }).collect(Collectors.toList());

        if (professoresAchados.size() >= 1) {
            return professoresAchados.get(0);
        }

        return null;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    @Override
    public void add(Professor professor) throws Exception {
        if (professor == null)
            throw new NullPointerException("[Adicionar Professor] O professor a ser adicionado não pode ser nulo");

        Professor professorEncontrado = buscaProfessor(professor.getEmail());

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
        Professor profEncontrado = buscaProfessor(id);

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
