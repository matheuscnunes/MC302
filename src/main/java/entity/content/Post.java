package main.java.entity.content;

import main.java.entity.member.Usuario;

import java.util.Date;

public class Post {

    private final int ID;

    private Usuario autor;

    private Date date;

    private int denuncias;

    // TODO Add Usuario autor
    protected Post (int ID, Date date, Usuario autor) {
        this.ID = ID;
        this.autor = autor;
        this.date = date;
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
}
