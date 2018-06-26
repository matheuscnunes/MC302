package main.java.entity.content;

import main.java.entity.member.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {

    private final int ID;
    private String texto;
    private Usuario autor;
    private Date date;
    private int denuncias;
    private List<Comentario> comentarios;

    // TODO Add Usuario autor
    protected Post (int ID, Date date, Usuario autor, String texto) {
        this.ID = ID;
        this.autor = autor;
        this.date = date;
        this.texto = texto;
        this.comentarios = new ArrayList<>();
        denuncias = 0;
    }

    public void denunciarPorAbuso() {
        denuncias += 1;
    }

    public Boolean apagar() {
        // TODO integração com o manager
        return false;
    }

    public int getID() {
        return ID;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDenuncias() {
        return denuncias;
    }

    public void setDenuncias(int denuncias) {
        this.denuncias = denuncias;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void addComentario(Comentario comentario) {
        this.comentarios.add(comentario);
    }

    public void removeComentario(Comentario comentario) { this.comentarios.remove(comentario);}

    @Override
    public boolean equals(Object other){
        if (other == null){
            return false;
        }
        if (other instanceof Post){
            Post otherPost = (Post)other;
            return this.ID == otherPost.getID();
        }
        return false;
    }
}
