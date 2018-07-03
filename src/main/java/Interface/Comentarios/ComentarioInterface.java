package main.java.Interface.Comentarios;

import main.java.Interface.Interface;
import main.java.Interface.PostsInterface;
import main.java.entity.content.Comentario;
import main.java.entity.content.Post;
import main.java.entity.member.Usuario;
import main.java.repositorio.GerenciadorLogin;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ComentarioInterface extends Interface {
    private List<Comentario> comentarios;
    private int indexComentarioAtual = 0;
    private Post post = null;
    private Interface interfaceAnterior = null;

    public ComentarioInterface(Scanner input, Post post, Interface interfaceAnterior) {
        super(input);

        if (post == null) throw new IllegalArgumentException("[Comentário interface] Post não pode ser nulo");

        this.post = post;
        this.interfaceAnterior = interfaceAnterior;

        atualizaComentarios();
    }

    private void atualizaComentarios() {
        if (post == null) throw new RuntimeException("Post não pode ser nulo");

        comentarios = post.getComentarios();

        if (comentarios.size() <= 0) {
            indexComentarioAtual = -1;
        } else {
            indexComentarioAtual = 0;
        }
    }

    public void mostrarComentarios() {
        System.out.println("\nCOMENTÁRIOS \n");
        this.exibeComentarioAtual();
    }

    public void exibeComentarioAtual() {
        if (comentarios.size() < 1) {
            System.out.println("O post não possui comentários");
            this.printaOpcoes();
            return;
        }

        if (indexComentarioAtual < 0 || indexComentarioAtual >= comentarios.size()) {
            System.out.println("Index inválido para comentário.");
            printaOpcoes();
            return;
        }

        Comentario comentarioAtual = comentarios.get(indexComentarioAtual);

        if (comentarioAtual == null) {
            System.out.println("Não foi possível acessar esse comentário");
            printaOpcoes();
            return;
        }

        System.out.println(comentarioAtual.getAutor().getNome() + ":" + comentarioAtual.getTexto());

        printaOpcoes();
    }

    private void postAnterior() {
        if (indexComentarioAtual == 0) {
            super.printlnVermelho("Esse é o primeiro comentário, não é possível exibir comentário anterior.");
            capturarOpcaoEscolhida();
        } else {
            indexComentarioAtual--;
            exibeComentarioAtual();
        }
    }

    private void postPosterior() {
        if (indexComentarioAtual >= comentarios.size() - 1) {
            super.printlnVermelho("Esse é o ultimo comentário, não é possível exibir próximo comentário. ");
            capturarOpcaoEscolhida();
        } else {
            indexComentarioAtual++;
            exibeComentarioAtual();
        }
    }

    private void criarComentario() {
        try {
            Date dataAtual = new Date();
            Long time = dataAtual.getTime();

            int id = time.intValue();
            Usuario usuarioAtual = GerenciadorLogin.getInstance().getUsuarioLogado();

            System.out.println("Digite o comentário: ");

            String comentario = input.next() + input.nextLine();

            Comentario novoComentario = new Comentario(id, dataAtual, usuarioAtual, comentario);
            this.post.addComentario(novoComentario);

            super.printlnRoxo("\nComentário adicionado com sucesso.\n");

            atualizaComentarios();
            mostrarComentarios();
        } catch (RuntimeException e) {
            System.out.println("Não foi possível adicionar o comentário: ");
            e.printStackTrace();
        }

        mostrarComentarios();
    }

    private void printaOpcoes() {
        printlnAzul(" =================    ================    ================    ================");
        printlnAzul("|| (1) ANTERIOR  ||  || (2) PRÓXIMO  ||  || (3) COMENTAR ||  || (4) VOLTAR   ||");
        printlnAzul(" =================    ================    ================    ================");

        capturarOpcaoEscolhida();
    }

    private void sair() {
        System.out.println("Saindo dos comentários..");
        PostsInterface postsInterface = (PostsInterface) interfaceAnterior;
        try {
            postsInterface.exibirPost();
        } catch (Exception e) {
            System.out.println("Não foi possível exibir posts. Mensagem: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void capturarOpcaoEscolhida() {
        int op = input.nextInt();
        switch (op) {
            case 1:
                this.postAnterior();
                break;
            case 2:
                this.postPosterior();
                break;
            case 3:
                this.criarComentario();
                break;
            case 4:
                this.sair();
                break;
            default:
                System.out.println("Comando não disponível!");
                capturarOpcaoEscolhida();
        }
    }


}
