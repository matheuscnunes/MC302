package main.java.Interface.Posts;

import main.java.Interface.Interface;
import main.java.entity.Gerenciador;
import main.java.entity.Turma;
import main.java.entity.content.Pergunta;
import main.java.entity.content.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FiltrarPostsInterface extends Interface {
    private ArrayList<Post> postagens;

    public FiltrarPostsInterface(Scanner input, ArrayList<Post> postagens) {
        super(input);
        this.postagens = postagens;
    }

    public ArrayList<Post> filtrarPosts() {
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

    private ArrayList<Post> capturarOpcaoEscolhida() {
        int op = input.nextInt();
        while(op > 5 || op < 1) {
            System.out.println("Comando não reconhecido!");
            op = input.nextInt();
        }
        switch(op) {
            case 1:
                List<Post> postagensFiltradasPorAutor = filtrarPorAutor();
                if(postagensFiltradasPorAutor == null || postagensFiltradasPorAutor.isEmpty()) {
                    System.err.println("Não foram encontradas postagens do autor digitado!");
                    return postagens;
                } else {
                    return new ArrayList<Post>(postagensFiltradasPorAutor);
                }
            case 2:
                return new ArrayList<Post>(filtrarPorTurma());
            case 3:
                return new ArrayList<Post>(Gerenciador.getPerguntas());
            case 4:
                return new ArrayList<Post>(Gerenciador.getConteudos());
            case 5:
                return null; //return null faz com que não haja filtro na tela de posts
            default: return null;
        }
    }

    private List<Post> filtrarPorAutor() {
        System.out.print("Digite o nome do autor: ");
        String autor = input.next();
        return Gerenciador.filtrarPorAutor(postagens, autor);
    }

    private List<Post> filtrarPorTurma() {
        List<Turma> turmas = Gerenciador.buscarTodasTurmas();
        if(turmas.size() == 0) {
            System.err.println("Não há turmas cadastradas no sistema! Nào é possível filtrar por turma.");
            return null;
        }
        System.out.println("-----------------------------------------");
        System.out.println("  TURMAS");
        System.out.println("-----------------------------------------");
        for(int i = 0; i < turmas.size(); i++) {
            System.out.println((i + 1) + " - " + turmas.get(i).getDisciplina().getNome() + "(" + turmas.get(i).getSemestre().toString() + "/" + turmas.get(i).getAno() + ")");
        }
        int op = 0;
        while(op < 1 || op > turmas.size()) {
            System.out.print("\nDigite o número da opção desejada: ");
            op = input.nextInt();
        }
        return turmas.get(op - 1).getPosts();
    }
}
