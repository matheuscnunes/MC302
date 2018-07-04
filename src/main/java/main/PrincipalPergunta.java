package main.java.main;

import main.java.entity.GeradorSequencia;
import main.java.entity.Turma;
import main.java.entity.content.Comentario;
import main.java.entity.content.Pergunta;
import main.java.entity.content.Post;
import main.java.entity.member.Usuario;
import main.java.repositorio.Gerenciador;
import main.java.repositorio.GerenciadorLogin;
import main.java.repositorio.GerenciadorTurma;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PrincipalPergunta {

    public static void main(Scanner input) {
        System.out.println("1 - NOVA PERGUNTA");
        System.out.println("2 - BUSCAR PERGUNTA POR ID");
        System.out.println("3 - BUSCAR PERGUNTAS POR AUTOR");
        System.out.println("4 - BUSCAR TODAS AS PERGUNTAS");
        System.out.println("5 - COMENTAR");
        System.out.println("6 - REMOVER  COMENTÁRIO");
        System.out.println("7 - REMOVER PERGUNTA");
        System.out.println("8 - SAIR");

        if (input.hasNextInt()) {
            int op = input.nextInt();

            switch (op) {
                case 1:
                    addPergunta(input);
                    break;
                case 2:
                    buscarPerguntaPorId(input);
                    break;
                case 3:
                    try {
                        buscarPerguntasPorAutor(input);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 4:
                    buscarPerguntas();
                    break;
                case 5:
                    adicionarComentario(input);
                    break;
                case 6:
                    removerComentario(input);
                    break;
                case 7:
                    removerPergunta(input);
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

    private static void exibirPergunta(Pergunta perguntaPesquisada) {
        if (perguntaPesquisada == null) {
            System.out.println("Não existe nenhuma pergunta com esse ID");
        } else {
            System.out.println("ID : " + perguntaPesquisada.getID());
            System.out.println("Pergunta : " + perguntaPesquisada.getTexto());
            System.out.println("Autor : " + perguntaPesquisada.getAutor().getNome());
            List<Comentario> comentarios = perguntaPesquisada.getComentarios();
            if (comentarios != null) {
                for (Comentario comentario : comentarios) {
                    System.out.println("----- Comentário : " + comentario.getTexto());
                }
            }
            System.out.println();
        }
    }

    private static void adicionarComentario(Scanner input) {
        int perguntaId = 0;
        Turma comentarioNaTurma = null;
        Post pergunta = null;

        try {
            System.out.println("Digite o id da pergunta para adicinar comentários:  ");
            perguntaId = input.nextInt();

            for (Turma turma : GerenciadorTurma.getInstance().buscarTodos()) {
                for (Post post : turma.getPosts()) {
                    if (post.getID() == perguntaId) {
                        System.out.println("Digite o texto do comentário : ");
                        String textoComentario = input.next();
                        Comentario comentario = new Comentario(GeradorSequencia.nextSequencia(), new Date(),
                                GerenciadorLogin.getInstance().getUsuarioLogado(), textoComentario);
                        post.addComentario(comentario);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            adicionarComentario(input);
        }


    }

    private static void removerComentario(Scanner input) {
        final int comentarioId;
        try {
            System.out.println("Digite o id do comentário para remover : ");
            comentarioId = input.nextInt();

            Comentario comentarioRemovido = null;
            for (Turma turma : GerenciadorTurma.getInstance().buscarTodos()) {
                for (Post post : turma.getPosts()) {
                    List<Comentario> comentarios = post.getComentarios().stream().
                            filter(comentario -> comentario.getID() == comentarioId).collect(Collectors.toList());

                    if (comentarios.size() > 0) {
                        comentarioRemovido = comentarios.get(0);
                        post.removeComentario(comentarioRemovido);
                    }
                }
            }

            if (comentarioRemovido == null) {
                System.out.println("Não existe nenhum comentário com esse ID");
            } else {
                System.out.println("Comentário (" + comentarioRemovido.getTexto() + ") removido com sucesso !");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            removerComentario(input);
        }
    }

    private static void removerPergunta(Scanner input) {
        int perguntaId = 0;
        try {
            System.out.println("Digite o id da pergunta para remover : ");
            perguntaId = input.nextInt();

            Pergunta perguntaRemovida = null;
            for (Turma turma : GerenciadorTurma.getInstance().buscarTodos()) {
                for (Post post : turma.getPosts()) {
                    if (post.getID() == perguntaId) {
                        perguntaRemovida = (Pergunta) post;
                        turma.removerPost(post.getID());
                    }
                }
            }

            if (perguntaRemovida == null) {
                System.out.println("Não existe nenhuma pergunta com esse ID");
            } else {
                System.out.println("Pergunta do autor " + perguntaRemovida.getAutor().getNome() + " removida com sucesso");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            removerPergunta(input);
        }
    }

    private static void buscarPerguntas() {
        List<Pergunta> perguntasBuscadas = new ArrayList<>();
        try {
            for (Turma turma : GerenciadorTurma.getInstance().buscarTodos()) {
                List<Post> posts = turma.getPosts().stream().filter(post -> post instanceof Pergunta).collect(Collectors.toList());

                perguntasBuscadas = posts.stream().map(post -> (Pergunta) post).collect(Collectors.toList());
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter todas as perguntas");
        }

        if (perguntasBuscadas != null) {
            for (Pergunta pergunta : perguntasBuscadas) {
                exibirPergunta(pergunta);
            }
        }
    }

    private static void buscarPerguntasPorAutor(Scanner input) throws Exception {
        String autor = "";
        do {
            System.out.println("Digite o nome do autor para buscar as perguntas : ");
            autor = input.next();
        }
        while (autor.trim().equals(""));

        List<Pergunta> perguntasBuscadas = new ArrayList<>();
        try {
            for (Turma turma : GerenciadorTurma.getInstance().buscarTodos()) {
                List<Post> posts = new ArrayList<>();
                for (Post post : turma.getPosts()) {
                    if (post instanceof Pergunta && post.getAutor().getNome().equals(autor)) {
                        posts.add(post);
                    }
                }

                perguntasBuscadas = posts.stream().map(post -> (Pergunta) post).collect(Collectors.toList());
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter todas as perguntas");
        }

        if (perguntasBuscadas == null || perguntasBuscadas.isEmpty()) {
            System.out.println("Não existe nenhuma pergunta publicada por esse autor");
        } else {
            for (Pergunta pergunta : perguntasBuscadas) {
                exibirPergunta(pergunta);
            }
        }
    }

    private static void buscarPerguntaPorId(Scanner input) {
        final int perguntaId;
        try {
            System.out.println("Digite o ID da pergunta a ser pesquisada: ");
            perguntaId = input.nextInt();
            Pergunta perguntaPesquisada = null;
            for (Turma turma : GerenciadorTurma.getInstance().buscarTodos()) {
                List<Pergunta> perguntas = new ArrayList<>();
                for (Post post : turma.getPosts()) {
                    if (post instanceof Pergunta && post.getID() == perguntaId) {
                        perguntas.add((Pergunta) post);
                    }
                }

                if (perguntas.size() > 0) {
                    perguntaPesquisada = perguntas.get(0);
                }
            }

            if (perguntaPesquisada == null) {
                throw new Exception("Pergunta não encontrado por id");
            }
            exibirPergunta(perguntaPesquisada);
        } catch (Exception e) {
            buscarPerguntaPorId(input);
        }
    }

    private static void addPergunta(Scanner input) {
        Usuario usuarioPostagem = GerenciadorLogin.getInstance().getUsuarioLogado();
        if (usuarioPostagem == null) {
            System.out.println("Você precisa fazer o login antes de realizar essa operação");
        } else {
            String titulo = "";
            do {
                System.out.println("Digite o título da pergunta (até 60 caracteres):");
                titulo = input.next();
            }
            while (titulo.length() > 60);
            String texto = "";
            do {
                System.out.println("Digite o texto da pergunta completo:");
                texto = input.next();
            }
            while (texto.trim().equals(""));


            Date dataPostagem = new Date();
            Pergunta pergunta = new Pergunta(GeradorSequencia.nextSequencia(), dataPostagem, usuarioPostagem, texto, titulo, false);
            try {
                System.out.println("Insira o id de uma turma na qual esse post será adicionado : ");
                int idTurma = input.nextInt();
                Turma turma = GerenciadorTurma.getInstance().find(idTurma);
                turma.addPost(pergunta);
                System.out.println("Pergunta adicionada!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
