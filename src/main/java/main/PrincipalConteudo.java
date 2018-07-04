package main.java.main;

import main.java.entity.GeradorSequencia;
import main.java.entity.Turma;
import main.java.entity.content.Post;
import main.java.repositorio.*;
import main.java.entity.content.Comentario;
import main.java.entity.content.Conteudo;
import main.java.entity.member.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PrincipalConteudo {

    public static void main(Scanner input) {
        System.out.println("1 - NOVO CONTEÚDO");
        System.out.println("2 - BUSCAR CONTEÚDO POR ID");
        System.out.println("3 - BUSCAR CONTEÚDOS POR AUTOR");
        System.out.println("4 - BUSCAR TODOS OS CONTEÚDOS");
        System.out.println("5 - COMENTAR");
        System.out.println("6 - REMOVER  COMENTÁRIO");
        System.out.println("7 - REMOVER CONTEÚDO");
        System.out.println("8 - SAIR");

        if (input.hasNextInt()) {
            int op = input.nextInt();

            switch (op) {
                case 1:
                    addConteudo(input);
                    break;
                case 2:
                    buscarConteudoPorId(input);
                    break;
                case 3:
                    try {
                        buscarConteudosPorAutor(input);
                    }
                    catch (Exception e){
                        System.err.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        buscarConteudos();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    adicionarComentario(input);
                    break;
                case 6:
                    removerComentario(input);
                    break;
                case 7:
                    try {
                        removerConteudo(input);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 8:
                    Principal.main(new String[]{});
                    break;
                default:
                    System.out.println("Operação não cadastrada");
                    Principal.main(null);
            }

        }

        main(input);
    }

    private static void exibirConteudo(Conteudo conteudoPesquisado){
        if (conteudoPesquisado == null){
            System.out.println("Não existe nenhum conteúdo com esse ID");
        }
        else {
            System.out.println("ID : " + conteudoPesquisado.getID());
            System.out.println("Conteúdo : " + conteudoPesquisado.getTexto());
            System.out.println("Autor : " + conteudoPesquisado.getAutor().getNome());
            List<Comentario> comentarios = conteudoPesquisado.getComentarios();
            if (comentarios != null){
                for (Comentario comentario : comentarios){
                    System.out.println("Comentário : " + comentario.getTexto());
                }
            }
            System.out.println();
        }
    }

    private static void adicionarComentario(Scanner input){
        int conteudoId = 0;
        try{
            System.out.println("Digite o id do conteúdo para adicinar comentários :  ");
            conteudoId = input.nextInt();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            adicionarComentario(input);
        }

        System.out.println("Digite o texto do comentário : ");
        final int id = conteudoId;
        try {
            String comentario = input.next();
            List<Turma> turmas = GerenciadorTurma.getInstance().buscarTodos();
            Post postProcurado = null;
            for (Turma turma : turmas){
                List<Post> posts = turma.getPosts().stream().filter(post -> {
                    return post.getID() == id;
                }).collect(Collectors.toList());
                if (posts != null &&  !posts.isEmpty()){
                    postProcurado = posts.get(0);
                    break;
                }
            }
            if (postProcurado instanceof Conteudo){
                Conteudo conteudo = (Conteudo)postProcurado;
                Usuario usuario = GerenciadorLogin.getInstance().getUsuarioLogado();
                Comentario comentarioObj = new Comentario(GeradorSequencia.nextSequencia(), new Date(), usuario,
                        comentario, true, "");
                conteudo.addComentario(comentarioObj);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void removerComentario(Scanner input){
        int comentarioId = 0;
        try{
            System.out.println("Digite o id do comentário para remover : ");
            comentarioId = input.nextInt();
            List<Turma> turmas = GerenciadorTurma.getInstance().buscarTodos();
            boolean isComentarioRemovido = false;
            Comentario comentarioParaRemover = null;
            for (Turma turma : turmas){
                List<Post> posts = turma.getPosts();
                for (Post post : posts){
                    List<Comentario> comentarios = post.getComentarios();
                    for (Comentario comentario : comentarios){
                        if (comentario.getID() == comentarioId){
                            comentarioParaRemover = comentario;
                            break;
                        }
                    }
                    if (comentarioParaRemover != null){
                        isComentarioRemovido = post.getComentarios().remove(comentarioParaRemover);
                        break;
                    }
                }
                if (isComentarioRemovido){
                    break;
                }
            }
            if (comentarioParaRemover == null || !isComentarioRemovido){
                System.out.println("Não existe nenhum comentário com esse ID");
            }
            else{
                System.out.println("Comentário (" + comentarioParaRemover.getTexto() + ") removido com sucesso !");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            removerComentario(input);
        }
    }

    private static void removerConteudo(Scanner input) throws Exception{
        int conteudoId = 0;
        try{
            System.out.println("Digite o id do conteúdo para remover : ");
            conteudoId = input.nextInt();
            final int id = conteudoId;
            List<Turma> turmas = GerenciadorTurma.getInstance().buscarTodos();
            Post postProcurado = null;
            Conteudo conteudoRemovido = null;
            for (Turma turma : turmas){
                List<Post> posts = turma.getPosts().stream().filter(post -> {
                    return post.getID() == id;
                }).collect(Collectors.toList());
                if (posts != null && !posts.isEmpty()){
                    postProcurado = posts.get(0);
                    if (postProcurado instanceof Conteudo){
                        conteudoRemovido = (Conteudo)postProcurado;
                        posts.remove(postProcurado);
                    }
                    break;
                }
            }

            if (conteudoRemovido == null){
                System.out.println("Não existe nenhum conteúdo com esse ID");
            }
            else{
                System.out.println("Conteúdo do autor " +conteudoRemovido.getAutor().getNome() + " removido com sucesso");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            removerConteudo(input);
        }
    }

    private static void buscarConteudos() throws Exception{
        List<Conteudo> conteudosBuscados = new ArrayList<Conteudo>();
        List<Turma> turmas = GerenciadorTurma.getInstance().buscarTodos();
        for (Turma turma : turmas){
            List<Post> posts = turma.getPosts();
            for (Post post : posts){
                if (post instanceof Conteudo){
                    Conteudo conteudo = (Conteudo)post;
                    conteudosBuscados.add(conteudo);
                }
            }
        }
        if (conteudosBuscados != null){
            for (Conteudo conteudo : conteudosBuscados){
                exibirConteudo(conteudo);
            }
        }
    }

    private static void buscarConteudosPorAutor(Scanner input) throws Exception{
        String autor = "";
        do{
            System.out.println("Digite o nome do autor para buscar os conteúdos : ");
            autor = input.next();
        }
        while (autor.trim().equals(""));

        List<Conteudo> conteudosBuscados = new ArrayList<Conteudo>();
        List<Turma> turmas = GerenciadorTurma.getInstance().buscarTodos();
        for (Turma turma : turmas){
            List<Post> posts = turma.getPosts();
            for (Post post : posts){
                if (post instanceof Conteudo){
                    Conteudo conteudo = (Conteudo)post;
                    if (conteudo.getAutor().getNome().equals(autor)) {
                        conteudosBuscados.add(conteudo);
                    }
                }
            }
        }

        if (conteudosBuscados == null || conteudosBuscados.isEmpty()){
            System.out.println("Não existe nenhum conteúdo publicado por esse autor");
        }
        else{
            for (Conteudo conteudo : conteudosBuscados){
                exibirConteudo(conteudo);
            }
        }
    }

    private static void buscarConteudoPorId(Scanner input){
        int conteudoId = 0;
        try {
            System.out.println("Digite o ID do conteúdo a ser pesquisado: ");
            conteudoId = input.nextInt();
        }
        catch (Exception e) {
            buscarConteudoPorId(input);
        }
        Conteudo conteudoPesquisado = GerenciadorConteudo.buscaConteudo(conteudoId);
        exibirConteudo(conteudoPesquisado);
    }

    private static void addConteudo(Scanner input) {
        Usuario usuarioPostagem = GerenciadorLogin.getInstance().getUsuarioLogado();
        if (usuarioPostagem == null) {
            System.out.println("Você precisa fazer o login antes de realizar essa operação");
        }
        else {
            String titulo = "";
            do {
                System.out.println("Digite o título da pergunta (até 60 caracteres):");
                titulo = input.next();
            }
            while (titulo.length() > 60);
            String texto = "";
            do {
                System.out.println("Digite o texto do conteúdo completo:");
                texto = input.next();
            }
            while (texto.trim().equals(""));

            Date dataPostagem = new Date();
            Conteudo conteudo = new Conteudo(GeradorSequencia.nextSequencia(), dataPostagem, usuarioPostagem, texto, titulo);
            try {
                System.out.println("Digite o id da turma : ");
                int id_turma = input.nextInt();
                Turma turma = GerenciadorTurma.getInstance().find(id_turma);

                if (turma != null) {
                    turma.addPost(conteudo);
                    System.out.println("Conteúdo adicionado!");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
