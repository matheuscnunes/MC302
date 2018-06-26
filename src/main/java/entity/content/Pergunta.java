package main.java.entity.content;

import main.java.entity.member.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pergunta extends Post {
    private String tituloPergunta;
    private boolean isAnonimo;

    public Pergunta(int id, Date date, Usuario autor, String pergunta, String tituloPergunta, boolean isAnonimo) {
        super(id, date, autor, pergunta);
        this.isAnonimo = isAnonimo;
        setTituloPergunta(tituloPergunta);
    }

    public boolean isAnonimo() {
        return isAnonimo;
    }

    public void setAnonimo(boolean anonimo) {
        isAnonimo = anonimo;
    }

    public String getTituloPergunta() {
        return tituloPergunta;
    }

    public void setTituloPergunta(String tituloPergunta) {
        if(tituloPergunta.length() > 60) {
            System.out.println("Esse título é muito grande! Títulos devem ter menos de 60 caracteres.");
        } else {
            this.tituloPergunta = tituloPergunta;
        }
    }

    public Pergunta withComentario(Comentario comentario) {
        addComentario(comentario);
        return this;
    }
}
