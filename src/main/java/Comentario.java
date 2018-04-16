package main.java;

import java.util.Date;

public class Comentario extends Post {

    private String comentario;

    private Boolean eRelevante;

    private String correcao;

    // TODO Add Usuario autor
    public Comentario(int id, Date data, String comentario, Boolean eRelevante, String correcao) {
        super(id, data);
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
