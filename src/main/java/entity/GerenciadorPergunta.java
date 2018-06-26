package main.java.entity;

import main.java.entity.content.Comentario;
import main.java.entity.content.Conteudo;
import main.java.entity.content.Pergunta;
import main.java.entity.content.Post;
import main.java.entity.member.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GerenciadorPergunta {
    private static int id = 0;

    private static List<Pergunta> perguntas = new ArrayList<Pergunta>();

    public GerenciadorPergunta() {
        super();
    }

    public static Pergunta buscaPergunta(int id) {
        List<Pergunta> perguntasEncontradas = perguntas.stream().filter(pergunta -> {
            return pergunta.getID() == id;
        }).collect(Collectors.toList());
        if (!perguntasEncontradas.isEmpty()) {
            return perguntasEncontradas.get(0);
        }
        return null;
    }


    public static List<Pergunta> buscarPerguntas(String autor) throws Exception{
        return perguntas.stream().filter(pergunta -> {
            return pergunta.getAutor().getNome().equals(autor);
        }).collect(Collectors.toList());
    }

    public static List<Pergunta> buscarPerguntas(){
        return perguntas;
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

    private static ComentarioRemocao buscarComentarioEmPerguntas(int comentarioId) throws Exception{
        if (perguntas != null){
            for (Pergunta pergunta : perguntas){
                List<Comentario> comentariosBuscados = pergunta.getComentarios().stream().filter(
                        comentario -> {return comentario.getID() == comentarioId;}).collect(Collectors.toList());
                if (comentariosBuscados != null && !comentariosBuscados.isEmpty()) {
                    return new ComentarioRemocao(comentariosBuscados.get(0), pergunta);
                }
            }
        }
        return null;
    }


    public static Comentario removerComentarioEmPergunta(int comentarioId) throws Exception{
        ComentarioRemocao comentarioRemocao = buscarComentarioEmPerguntas(comentarioId);
        if (comentarioRemocao != null) {
            Pergunta pergunta = (Pergunta)comentarioRemocao.getPost();
            Comentario comentario = comentarioRemocao.getComentario();
            pergunta.removeComentario(comentario);
            return comentario;
        }
        return null;
    }

    public static Pergunta removerPergunta(int id) throws Exception{
        Pergunta pergunta = buscaPergunta(id);
        if (pergunta != null){
            perguntas.remove(pergunta);
            return pergunta;
        }
        return null;
    }

    public static List<Pergunta> getPerguntas() { return perguntas; }
}
