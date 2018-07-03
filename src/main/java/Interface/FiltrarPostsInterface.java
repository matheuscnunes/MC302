package main.java.Interface;

import main.java.entity.content.Conteudo;
import main.java.entity.content.Pergunta;
import main.java.entity.content.Post;
import main.java.repositorio.Gerenciador;
import main.java.repositorio.GerenciadorTurma;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FiltrarPostsInterface extends Interface {
    private ArrayList<Post> postagens;

    public FiltrarPostsInterface(Scanner input, ArrayList<Post> postagens) {
        super(input);
        this.postagens = postagens;
    }

    public ArrayList<Post> filtrarPosts() throws Exception {
        System.out.println("|||||||||||||||||||||||||||||||||||");
        System.out.println("|                                 |");
        System.out.println("|  1 - Filtrar por autor          |");
        System.out.println("|  2 - Filtrar por turma          |");
        System.out.println("|  3 - Exibir apenas perguntas    |");
        System.out.println("|  4 - Exibir apenas conteúdos    |");
        System.out.println("|  5 - Remover filtros            |");
        System.out.println("|                                 |");
        System.out.println("|||||||||||||||||||||||||||||||||||");

        return capturarOpcaoEscolhida();
    }

    private ArrayList<Post> capturarOpcaoEscolhida() throws Exception {
        int op = input.nextInt();
        while (op > 5 || op < 1) {
            System.out.println("Comando não reconhecido!");
            op = input.nextInt();
        }
        switch (op) {
            case 1:
                List<Post> postagensFiltradasPorAutor = filtrarPorAutor();
                if (postagensFiltradasPorAutor == null || postagensFiltradasPorAutor.isEmpty()) {
                    System.err.println("Não foram encontradas postagens do autor digitado!");
                    return postagens;
                } else {
                    return new ArrayList<Post>(postagensFiltradasPorAutor);
                }
            case 2:
                break;
            case 3:
                ArrayList<Post> perguntas = new ArrayList<>();
                GerenciadorTurma.getInstance().buscarTodos().forEach(turma ->
                        perguntas.addAll(turma.getPosts().stream().filter(post -> post instanceof Pergunta).collect(Collectors.toList()))
                );
                return perguntas;
            case 4:
                ArrayList<Post> conteudos = new ArrayList<>();
                GerenciadorTurma.getInstance().buscarTodos().forEach(turma ->
                        conteudos.addAll(turma.getPosts().stream().filter(post -> post instanceof Conteudo).collect(Collectors.toList()))
                );
                return conteudos;
            case 5:
                return null; //return null faz com que não haja filtro na tela de posts
            default:
                return null;
        }
        return null;
    }

    private List<Post> filtrarPorAutor() throws Exception {
        System.out.print("Digite o nome do autor: ");
        String autor = input.next();

        return postagens.stream().filter(post -> {
            return post.getAutor().getNome().equals(autor);
        }).collect(Collectors.toList());
    }
}
