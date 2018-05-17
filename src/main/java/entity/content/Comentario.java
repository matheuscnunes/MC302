package main.java.entity.content;

import main.java.entity.member.Usuario;

import java.util.Date;

public class Comentario extends Post {

    private String comentario;

    private Boolean eRelevante;

    private String correcao;

    public Comentario(int id, Date data, Usuario autor, String comentario){
        super(id, data, autor);
        this.comentario = comentario;
        this.eRelevante = true;
        this.correcao = "";
    }

    public Comentario(int id, Date data, Usuario autor, String comentario, Boolean eRelevante, String correcao) {
        super(id, data, autor);
        this.comentario = comentario;
        this.eRelevante = eRelevante;
        this.correcao = correcao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Boolean geteRelevante() {
        return eRelevante;
    }

    public void seteRelevante(Boolean eRelevante) {
        this.eRelevante = eRelevante;
    }

    public String getCorrecao() {
        return correcao;
    }

    public void setCorrecao(String correcao) {
        this.correcao = correcao;
    }
}
