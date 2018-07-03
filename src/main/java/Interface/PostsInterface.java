package main.java.Interface;

import javafx.geometry.Pos;
import main.java.Interface.Comentarios.ComentarioInterface;
import main.java.Interface.HomeInterface;
import main.java.Interface.Interface;
import main.java.entity.GeradorSequencia;
import main.java.entity.Turma;
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
        GerenciadorTurma gerenciadorTurma = GerenciadorTurma.getInstance();

        try {
            gerenciadorTurma.buscarTodos().forEach(turma -> postagens.addAll(turma.getPosts()));
        } catch (Exception e) {

        }

        Conteudo conteudoFake = new Conteudo(GeradorSequencia.nextSequencia(), new Date(), GerenciadorLogin.getInstance().getUsuarioLogado(), "PUTA TEXTO DAORA AQUI SENSA SENSA SENSA SENSA \n AAAA ASIASOJSFGHDSKLDFJFKLSDAJLKS;DJFKLS;D \n SLDKFJKLJ JSADFKH".toLowerCase(), "Melhor conteudo!");
        Pergunta perguntaFake = new Pergunta(GeradorSequencia.nextSequencia(), new Date(), GerenciadorLogin.getInstance().getUsuarioLogado(), "PUTA PERGUNTA DAORA AQUI SENSA SENSA SENSA SENSA \n AAAA ASIASOJSFGHDSKLDFJFKLSDAJLKS;DJFKLS;D \n SLDKFJKLJ JSADFKH".toLowerCase(), "Melhor Pergunta!", true);
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
        if (postagem.getAutor().equals(GerenciadorLogin.getInstance().getUsuarioLogado())) {
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
        if (postagem.getAutor().equals(GerenciadorLogin.getInstance().getUsuarioLogado())) {
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
                ComentarioInterface comentarioInterface = new ComentarioInterface(input,getPostagens().get(indiceUltimoPostExibido), this);
                comentarioInterface.mostrarComentarios();
                capturarOpcaoEscolhida();
                break;
            case 4:
                filtrarPostagens();
                break;
            case 5:
                //DELETAR só aparece para o criador da postagem. Logo, esse if verifica qual opção de vdd a pessoa quer
                if(getPostagens().get(indiceUltimoPostExibido).getAutor().equals(GerenciadorLogin.getInstance().getUsuarioLogado())) {
                    removerPostagemAtual();
                } else {
                    voltar();
                }
                break;
            case 6:
                //VOLTAR pode ser a opção de número 6 ou 5. Só é 6 quando existe a opçao DELETAR
                if(getPostagens().get(indiceUltimoPostExibido).getAutor().equals(GerenciadorLogin.getInstance().getUsuarioLogado())) {
                    voltar();
                } else {
                    System.out.println("Comando não disponível!");
                    capturarOpcaoEscolhida();
                }
            default:
                System.out.println("Comando não disponível!");
                capturarOpcaoEscolhida();
        }
    }

    private void removerPostagemAtual() throws Exception {
        Post deletado = getPostagens().get(indiceUltimoPostExibido);

        Boolean removido = false;
        try {
            for (Turma turma : GerenciadorTurma.getInstance().buscarTodos()) {
                List<Post> posts = turma.getPosts().stream().filter(post -> post.getID() == deletado.getID())
                        .collect(Collectors.toList());

                if (posts.size() > 0) {
                    Post postRemovido = posts.get(0);

                    turma.removerPost(postRemovido);
                    postagens.remove(postRemovido);
                    if(postagensFiltradas != null) {
                        postagensFiltradas.remove(postRemovido);
                    }
                    removido = true;
                }
            }
        } catch (Exception e) {
            System.err.println("Não foi possível remover essa postagem :/");
        }

        if(removido) {
            proximoPost();
        } else {
            System.err.println("Não foi possível remover essa postagem :/");
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
