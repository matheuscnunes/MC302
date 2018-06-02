package main.java.Interface;

import main.java.entity.Gerenciador;
import main.java.entity.content.Conteudo;
import main.java.entity.content.Pergunta;
import main.java.entity.content.Post;
import main.java.entity.member.Usuario;
import main.java.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PostsInterface extends Interface {
    private int indiceUltimoPostExibido = 0;
    private List<Post> postagens;

    public PostsInterface(Scanner input) {
        super(input);
        this.postagens = new ArrayList<>();
        postagens.addAll(Gerenciador.getConteudos());
        postagens.addAll(Gerenciador.getPerguntas());
        Conteudo conteudoFake = new Conteudo(Gerenciador.nextSequence(), new Date(), Gerenciador.getUsuarioLogado(), "PUTA TEXTO DAORA AQUI SENSA SENSA SENSA SENSA \n AAAA ASIASOJSFGHDSKLDFJFKLSDAJLKS;DJFKLS;D \n SLDKFJKLJ JSADFKH".toLowerCase(), "Melhor conteudo!");
        Pergunta perguntaFake = new Pergunta(Gerenciador.nextSequence(), new Date(), Gerenciador.getUsuarioLogado(), "PUTA PERGUNTA DAORA AQUI SENSA SENSA SENSA SENSA \n AAAA ASIASOJSFGHDSKLDFJFKLSDAJLKS;DJFKLS;D \n SLDKFJKLJ JSADFKH".toLowerCase(), "Melhor Pergunta!", true);
        postagens.add(conteudoFake);
        postagens.add(perguntaFake);
        //TODO: ORDENAR POSTAGENS POR DATA!!
    }

    public void exibirPost() {
        System.out.println("////////////////////////////////////////////////////////////\n");

        Post postParaExibir = postagens.get(indiceUltimoPostExibido);
        if(postParaExibir instanceof Conteudo) {
            exibirConteudo((Conteudo)postParaExibir);
            mostrarBotaoAnteriorProximo();
            mostrarBotaoComentariosFiltrarDeletarVoltar(postParaExibir);
        } else if(postParaExibir instanceof Pergunta) {
            exibirPergunta((Pergunta)postParaExibir);
            mostrarBotaoAnteriorProximo();
            mostrarBotaoRespostasFiltrarDeletarVoltar(postParaExibir);
        }
        capturarOpcaoEscolhida();
    }

    private void exibirConteudo(Conteudo conteudo) {
        printlnPreto(conteudo.getTituloConteudo().toUpperCase());
        System.out.println("Por " + conteudo.getAutor().getNome() + " em " + conteudo.getDate().toString() + "  |   " + conteudo.getComentarios().size() + " comentários\n");
        printlnPreto(conteudo.getTexto() + "\n");
    }

    private void exibirPergunta(Pergunta pergunta) {
        printlnPreto(pergunta.getTituloPergunta().toUpperCase());
        System.out.println("Por " + (pergunta.isAnonimo() ? "Anônimo" : pergunta.getAutor().getNome()) + " em " + pergunta.getDate().toString() + "  |   " + pergunta.getComentarios().size() + " respostas\n");
        printlnPreto(pergunta.getTexto() + "\n");
    }

    private void mostrarBotaoAnteriorProximo() {
        if(indiceUltimoPostExibido == 0 && postagens.size() == 1) {
            return;
        }
        if(indiceUltimoPostExibido > 0 && indiceUltimoPostExibido + 1 < postagens.size()) {
            printlnAzul(" =================    ================");
            printlnAzul("|| (1) ANTERIOR  ||  || (2) PRÓXIMO  ||");
            printlnAzul(" =================    ================");
        } else if(indiceUltimoPostExibido == 0) {
            System.out.println(ANSI_RESET + " =================    " + ANSI_BLUE + "================");
            System.out.println(ANSI_RESET + "|| (1) ANTERIOR  ||  " + ANSI_BLUE + "|| (2) PRÓXIMO  ||");
            System.out.println(ANSI_RESET + " =================    " + ANSI_BLUE + "================");
        } else {
            System.out.println(ANSI_BLUE + " =================    " + ANSI_RESET + "================");
            System.out.println(ANSI_BLUE + "|| (1) ANTERIOR  ||  " + ANSI_RESET + "|| (2) PRÓXIMO  ||");
            System.out.println(ANSI_BLUE + " =================    " + ANSI_RESET + "================");
        }
    }

    private void mostrarBotaoRespostasFiltrarDeletarVoltar(Post postagem) {
        if(postagem.getAutor().equals(Gerenciador.getUsuarioLogado())) {
            printlnAzul(" ======================    ================ " + ANSI_RED + "   ================ " + ANSI_BLUE + "   ===============");
            printlnAzul("|| (3) VER RESPOSTAS  ||  || (4) FILTRAR  ||" + ANSI_RED + "  || (5) DELETAR  ||" + ANSI_BLUE + "  || (6) Voltar  ||");
            printlnAzul(" ======================    ================ " + ANSI_RED + "   ================ " + ANSI_BLUE + "   ===============");
        } else {
            printlnAzul(" ======================    ================    ===============");
            printlnAzul("|| (3) VER RESPOSTAS  ||  || (4) FILTRAR  ||  || (5) Voltar  ||");
            printlnAzul(" ======================    ================    ===============");
        }

    }

    private void mostrarBotaoComentariosFiltrarDeletarVoltar(Post postagem) {
        if(postagem.getAutor().equals(Gerenciador.getUsuarioLogado())) {
            printlnAzul(" ====================    ================ " + ANSI_RED + "   ================ " + ANSI_BLUE + "   ===============");
            printlnAzul("|| (3) COMENTÁRIOS  ||  || (4) FILTRAR  ||" + ANSI_RED + "  || (5) DELETAR  ||" + ANSI_BLUE + "  || (6) Voltar  ||");
            printlnAzul(" ====================    ================ " + ANSI_RED + "   ================ " + ANSI_BLUE + "   ===============");
        } else {
            printlnAzul(" ====================    ================    ===============");
            printlnAzul("|| (3) COMENTÁRIOS  ||  || (4) FILTRAR  ||  || (5) Voltar  ||");
            printlnAzul(" ====================    ================    ===============");
        }

    }

    private void capturarOpcaoEscolhida() {
        int op = input.nextInt();
        switch(op) {
            case 1:
                postAnterior();
                break;
            case 2:
                proximoPost();
                break;
            case 3:
                System.out.println("Opção ainda não funciona!");
                break;
            case 4:
                System.out.println("Opção ainda não funciona!");
                break;
            case 5:
                if(postagens.get(indiceUltimoPostExibido).getAutor().equals(Gerenciador.getUsuarioLogado())) {
                    System.out.println("Opção ainda não funciona!");
                } else {
                    voltar();
                }
                break;
            case 6:
                if(postagens.get(indiceUltimoPostExibido).getAutor().equals(Gerenciador.getUsuarioLogado())) {
                    voltar();
                } else {
                    System.out.println("Comando não disponível!");
                }
            default:
                System.out.println("Comando não disponível!");
                capturarOpcaoEscolhida();
        }
    }

    private void postAnterior() {
        if(indiceUltimoPostExibido > 0) {
            indiceUltimoPostExibido--;
            exibirPost();
        } else {
            System.err.println("Esse é a primeira postagem! Escolha outra opção.");
            capturarOpcaoEscolhida();
        }
    }

    private void proximoPost() {
        if(indiceUltimoPostExibido + 1 < postagens.size()) {
            indiceUltimoPostExibido++;
            exibirPost();
        } else {
            System.err.println("Esse é a última postagem! Escolha outra opção.");
            capturarOpcaoEscolhida();
        }
    }

    private void voltar() {
        System.out.println("////////////////////////////////////////////////////////////\n");
        HomeInterface home = new HomeInterface(input);
        home.exibirHome();
    }
}
