package main.java.Interface;

import main.java.entity.Gerenciador;
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

    public ArrayList<Post> filtrarPosts() throws Exception{
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

    private ArrayList<Post> capturarOpcaoEscolhida() throws Exception{
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
                break;
            case 3:
                return new ArrayList<Post>(Gerenciador.getPerguntas());
            case 4:
                return new ArrayList<Post>(Gerenciador.getConteudos());
            case 5:
                return null; //return null faz com que não haja filtro na tela de posts
            default: return null;
        }
        return null;
    }

    private List<Post> filtrarPorAutor() throws Exception{
        System.out.print("Digite o nome do autor: ");
        String autor = input.next();
        return Gerenciador.filtrarPorAutor(postagens, autor);
    }
}
