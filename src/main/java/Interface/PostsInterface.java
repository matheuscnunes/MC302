package main.java.Interface;

import main.java.entity.GeradorSequencia;
import main.java.entity.content.Conteudo;
import main.java.entity.content.Pergunta;
import main.java.entity.content.Post;
import main.java.repositorio.Gerenciador;
import main.java.repositorio.GerenciadorLogin;
import main.java.repositorio.GerenciadorTurma;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PostsInterface extends Interface {
    private int indiceUltimoPostExibido = 0;
    private ArrayList<Post> postagens;
    private ArrayList<Post> postagensFiltradas;

    public PostsInterface(Scanner input) {
        super(input);
        this.postagens = new ArrayList<>();
        this.postagensFiltradas = null;
        GerenciadorTurma gerenciadorTurma = new GerenciadorTurma();
        List<Conteudo> conteudos = new ArrayList<>();
        List<Pergunta> perguntas = new ArrayList<>();

        try {
            gerenciadorTurma.buscarTodos().forEach(turma -> postagens.addAll(turma.getPosts()));
        } catch (Exception e) {

        }
        Conteudo conteudoFake = new Conteudo(GeradorSequencia.nextSequencia(), new Date(), GerenciadorLogin.getUsuarioLogado(), "PUTA TEXTO DAORA AQUI SENSA SENSA SENSA SENSA \n AAAA ASIASOJSFGHDSKLDFJFKLSDAJLKS;DJFKLS;D \n SLDKFJKLJ JSADFKH".toLowerCase(), "Melhor conteudo!");
        Pergunta perguntaFake = new Pergunta(GeradorSequencia.nextSequencia(), new Date(), GerenciadorLogin.getUsuarioLogado(), "PUTA PERGUNTA DAORA AQUI SENSA SENSA SENSA SENSA \n AAAA ASIASOJSFGHDSKLDFJFKLSDAJLKS;DJFKLS;D \n SLDKFJKLJ JSADFKH".toLowerCase(), "Melhor Pergunta!", true);
        postagens.add(conteudoFake);
        postagens.add(perguntaFake);
        //TODO: ORDENAR POSTAGENS POR DATA!!
    }

    private ArrayList<Post> getPostagens() {
        if (postagensFiltradas == null)
            return postagens;
        else
            return postagensFiltradas;
    }

    public void exibirPost() throws Exception {
        System.out.println("////////////////////////////////////////////////////////////\n");

        Post postParaExibir = getPostagens().get(indiceUltimoPostExibido);
        if (postParaExibir instanceof Conteudo) {
            exibirConteudo((Conteudo) postParaExibir);
            mostrarBotaoAnteriorProximo();
            mostrarBotaoComentariosFiltrarDeletarVoltar(postParaExibir);
        } else if (postParaExibir instanceof Pergunta) {
            exibirPergunta((Pergunta) postParaExibir);
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
        if (indiceUltimoPostExibido == 0 && getPostagens().size() == 1) {
            return;
        }
        if (indiceUltimoPostExibido > 0 && indiceUltimoPostExibido + 1 < getPostagens().size()) {
            printlnAzul(" =================    ================");
            printlnAzul("|| (1) ANTERIOR  ||  || (2) PRÓXIMO  ||");
            printlnAzul(" =================    ================");
        } else if (indiceUltimoPostExibido == 0) {
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
        if (postagem.getAutor().equals(GerenciadorLogin.getUsuarioLogado())) {
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
        if (postagem.getAutor().equals(GerenciadorLogin.getUsuarioLogado())) {
            printlnAzul(" ====================    ================ " + ANSI_RED + "   ================ " + ANSI_BLUE + "   ===============");
            printlnAzul("|| (3) COMENTÁRIOS  ||  || (4) FILTRAR  ||" + ANSI_RED + "  || (5) DELETAR  ||" + ANSI_BLUE + "  || (6) Voltar  ||");
            printlnAzul(" ====================    ================ " + ANSI_RED + "   ================ " + ANSI_BLUE + "   ===============");
        } else {
            printlnAzul(" ====================    ================    ===============");
            printlnAzul("|| (3) COMENTÁRIOS  ||  || (4) FILTRAR  ||  || (5) Voltar  ||");
            printlnAzul(" ====================    ================    ===============");
        }

    }

    private void capturarOpcaoEscolhida() throws Exception {
        int op = input.nextInt();
        switch (op) {
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
                filtrarPostagens();
                break;
            case 5:
                if (getPostagens().get(indiceUltimoPostExibido).getAutor().equals(GerenciadorLogin.getUsuarioLogado())) {
                    System.out.println("Opção ainda não funciona!");
                } else {
                    voltar();
                }
                break;
            case 6:
                if (getPostagens().get(indiceUltimoPostExibido).getAutor().equals(GerenciadorLogin.getUsuarioLogado())) {
                    voltar();
                } else {
                    System.out.println("Comando não disponível!");
                }
            default:
                System.out.println("Comando não disponível!");
                capturarOpcaoEscolhida();
        }
    }

    private void filtrarPostagens() throws Exception {
        FiltrarPostsInterface filtrarPostagensInterface = new FiltrarPostsInterface(input, getPostagens());
        postagensFiltradas = filtrarPostagensInterface.filtrarPosts();
        indiceUltimoPostExibido = 0;
        exibirPost();
    }

    private void postAnterior() throws Exception {
        try {
            indiceUltimoPostExibido--;
            exibirPost();
        } catch (Exception e) {
            System.err.println("Esse é a primeira postagem! Escolha outra opção.");
            capturarOpcaoEscolhida();
        }
    }

    private void proximoPost() throws Exception {
        try {
            indiceUltimoPostExibido++;
            exibirPost();
        } catch (Exception e) {
            System.err.println("Esse é a última postagem! Escolha outra opção.");
            capturarOpcaoEscolhida();
        }
    }

    private void voltar() throws Exception {
        System.out.println("////////////////////////////////////////////////////////////\n");
        HomeInterface home = new HomeInterface(input);
        home.exibirHome();
    }
}