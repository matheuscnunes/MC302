package main.java.repositorio;

import main.java.entity.member.Aluno;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorAluno implements IGerenciador<Aluno> {
    private static int id = 0;

    private static List<Aluno> alunos = new ArrayList<Aluno>();

    private static GerenciadorAluno gerenciadorAluno = null;

    public static GerenciadorAluno getInstance() {
        if (gerenciadorAluno == null) {
            gerenciadorAluno = new GerenciadorAluno();
        }

        return gerenciadorAluno;
    }

    private GerenciadorAluno() {
        super();
    }

    public static int proximoId() {
        GerenciadorAluno.id++;
        return id;
    }

    @Override
    public void add(Aluno aluno) throws Exception {
        if (aluno == null)
            throw new NullPointerException("[Adicionar Aluno] O aluno a ser adicionado não pode ser nulo");

        Aluno alunoEncontrado = find(aluno.ra);

        if (alunoEncontrado != null) {
            throw new Error("[Adicionar Aluno] Aluno com RA " + aluno.ra + " já existe.");
        }

        alunos.add(aluno);
    }

    @Override
    public Aluno find(int ra) throws Exception {
        List<Aluno> alunosEncontrados = alunos.stream().filter(aluno -> aluno.getRa() == ra).collect(Collectors.toList());

        if (alunosEncontrados.size() >= 1) {
            return alunosEncontrados.get(0);
        }

        return null;
    }

    @Override
    public Aluno find(String email) throws Exception {
        List<Aluno> alunosEncontrados = alunos.stream().filter(aluno -> {
            return aluno.getEmail().equals(email);
        }).collect(Collectors.toList());

        if (alunosEncontrados.size() >= 1) {
            return alunosEncontrados.get(0);
        }

        return null;
    }

    @Override
    public Aluno remover(String ra) throws Exception {
        Aluno alunoEncontrado = find(Integer.parseInt(ra));

        if (alunoEncontrado == null) {
            //Aluno não existe na base;
            return null;
        }

        alunos.remove(alunoEncontrado);
        return alunoEncontrado;
    }

    @Override
    public boolean remover(Aluno obj) throws Exception {
        return this.alunos.remove(obj);
    }

    @Override
    public List<Aluno> buscarTodos() throws Exception {
        return this.alunos;
    }
}
