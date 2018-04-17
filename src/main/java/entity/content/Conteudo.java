package main.java.entity.content;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Conteudo extends Post {

    private String conteudo;

    private List<Comentario> comentarios;

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    // TODO Add Usuario autor
    public Conteudo(int ID, Date date, String conteudo) {
        super(ID, date);
        this.conteudo = conteudo;
        comentarios = new ArrayList<>();
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public void addComentario(Comentario comentario) {
        this.comentarios.add(comentario);
    }
}
