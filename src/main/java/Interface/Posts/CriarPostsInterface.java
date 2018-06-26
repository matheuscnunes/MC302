package main.java.Interface.Posts;

import main.java.Interface.HomeInterface;
import main.java.Interface.Interface;
import main.java.entity.Gerenciador;
import main.java.entity.content.Comentario;
import main.java.entity.content.Conteudo;
import main.java.entity.content.Pergunta;
import main.java.entity.content.Post;
import main.java.entity.member.Usuario;
import sun.management.HotspotMemoryMBean;

import java.util.Date;
import java.util.Scanner;

public class CriarPostsInterface extends Interface {

    public CriarPostsInterface(Scanner input) {
        super(input);
    }

    public void criarPost() {

        try {
            int tipoDePost;
            int ID = Gerenciador.proximoId();
            Date date = new Date();
            Usuario autor = Gerenciador.getUsuarioLogado();
            String tituloPost, conteudo;

            printlnAmarelo("\nCriando um novo post...");

            printaOpcoes();
            tipoDePost = input.nextInt();

            System.out.println("Digite o título do post: ");
            tituloPost = input.next() + input.nextLine();

            System.out.println("Insira o conteúdo do post: ");
            conteudo = input.next() + input.nextLine();

            if (tipoDePost == 1) {
                Conteudo novoPost = new Conteudo(ID, date, autor, conteudo, tituloPost);
                Gerenciador.adicionarConteudo(novoPost);
                printlnRoxo("\nConteúdo adicionado com sucesso!\n");
            } else if (tipoDePost == 2) {
                Pergunta novoPost = new Pergunta(ID, date, autor, conteudo, tituloPost, false);
                Gerenciador.adicionarPergunta(novoPost);
                printlnRoxo("\nPergunta adicionada com sucesso!\n");
            }
            sair();
        }
        catch(Exception e) {
            System.out.println("\nNão foi possível adicionar o comentário: ");
            e.printStackTrace();
            sair();
        }
    }

    private void printaOpcoes() {
        System.out.print("\nSelecione o tipo de post desejado!\n");
        printlnAzul(" =================    =================");
        printlnAzul("|| (1) CONTEÚDO  ||  || (2) PERGUNTA  ||");
        printlnAzul(" =================    =================");
    }

    private void sair() {
        HomeInterface homeInterface = new HomeInterface(input);
        homeInterface.exibirHome();
    }
}