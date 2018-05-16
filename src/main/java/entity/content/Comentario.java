package main.java.entity.content;

import main.java.entity.member.Usuario;

import java.util.Date;

public class Comentario extends Post {

    private String comentario;

    private Boolean isRelevante;

    private String correcao;

    public Comentario(int id, Date data, Usuario autor, String comentario, Boolean isRelevante, String correcao) {
        super(id, data, autor);
        this.comentario = comentario;
        this.isRelevante = isRelevante;
        this.correcao = correcao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Boolean getIsRelevante() {
        return isRelevante;
    }

    public void setIsRelevante(Boolean isRelevante) {
        this.isRelevante = isRelevante;
    }

    public String getCorrecao() {
        return correcao;
    }

    public void setCorrecao(String correcao) {
        this.correcao = correcao;
    }
}
