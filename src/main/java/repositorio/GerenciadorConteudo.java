package main.java.repositorio;

import main.java.entity.content.Comentario;
import main.java.entity.content.Conteudo;
import main.java.entity.content.Post;
import main.java.entity.member.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorConteudo {
    private static int id = 0;

    private static List<Conteudo> conteudos = new ArrayList<Conteudo>();

    public GerenciadorConteudo() {
        super();
    }

    public static int proximoId() {
        GerenciadorConteudo.id++;
        return id;
    }

    public static void adicionarComentario(Post postagem, String textoComentario, Usuario usuario) throws Exception {
        if (postagem != null) {
            Comentario comentario = new Comentario(
                    GerenciadorConteudo.proximoId(), new Date(), usuario, textoComentario);
            postagem.addComentario(comentario);
        } else {
            throw new Exception("Não é possível adicionar comentário sem uma postagem!");
        }
    }

    public static void adicionarComentarioEmConteudo(int conteudoId, String textoComentario, Usuario usuario) throws Exception {
        Conteudo conteudo = buscaConteudo(conteudoId);
        adicionarComentario(conteudo, textoComentario, usuario);
    }

    public static Conteudo buscaConteudo(int id) {
        List<Conteudo> conteudosEncontrados = conteudos.stream().filter(conteudo -> {
            return conteudo.getID() == id;
        }).collect(Collectors.toList());
        if (!conteudosEncontrados.isEmpty()) {
            return conteudosEncontrados.get(0);
        }
        return null;
    }

    public static List<Conteudo> buscaConteudos(String autor) throws Exception{
        return conteudos.stream().filter(conteudo -> {
            return conteudo.getAutor().getNome().equals(autor);
        }).collect(Collectors.toList());
    }

    public static List<Post> filtrarPorAutor(ArrayList<Post> postagens, String autor) throws Exception{
        return postagens.stream().filter(post -> {
            return post.getAutor().getNome().equals(autor);
        }).collect(Collectors.toList());
    }

    public static List<Conteudo> buscaConteudos(){
        return conteudos;
    }

    private static class ComentarioRemocao{
        private Comentario comentario;
        private Post post;

        public ComentarioRemocao(Comentario comentario, Post post){
            this.comentario = comentario;
            this.post = post;
        }

        public Comentario getComentario(){
            return this.comentario;
        }

        public Post getPost(){
            return this.post;
        }
    }

    private static ComentarioRemocao buscarComentarioEmConteudos(int comentarioId) throws Exception{
        if (conteudos != null){
            for (Conteudo conteudo : conteudos){
                List<Comentario> comentariosBuscados = conteudo.getComentarios().stream().filter(
                        comentario -> {return comentario.getID() == comentarioId;}).collect(Collectors.toList());
                if (comentariosBuscados != null && !comentariosBuscados.isEmpty()){
                    return new ComentarioRemocao(comentariosBuscados.get(0), conteudo);
                }
            }
        }
        return null;
    }

    public static Comentario removerComentarioEmConteudo(int comentarioId) throws Exception
    {
        ComentarioRemocao comentarioRemocao = buscarComentarioEmConteudos(comentarioId);
        if (comentarioRemocao != null) {
            Conteudo conteudo = (Conteudo)comentarioRemocao.getPost();
            Comentario comentario = comentarioRemocao.getComentario();
            conteudo.removeComentario(comentario);
            return comentario;
        }
        return null;
    }

    public static Conteudo removerConteudo(int id) throws Exception{
        Conteudo conteudo = buscaConteudo(id);
        if (conteudo != null){
            conteudos.remove(conteudo);
            return conteudo;
        }
        return null;
    }

    public static void adicionarConteudo(Conteudo conteudo) throws Exception {
        if (conteudo == null) {
            throw new Exception("Conteúdo nulo ao adicionar conteúdo");
        }
        if (conteudos.contains(conteudo)) {
            throw new Exception("Conteúdo já cadastrado");
        }
        conteudos.add(conteudo);
    }
}
