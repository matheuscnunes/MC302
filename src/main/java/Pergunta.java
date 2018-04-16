package main.java;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pergunta extends Post {

    private String pergunta;

    private List<Comentario> comentarios;

    // TODO Add Usuario autor
    public Pergunta(int id, Date date, String pergunta) {
        super(id, date);
        this.pergunta = pergunta;
        this.comentarios = new ArrayList<>();
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void addComentario(Comentario comentario) {
        this.comentarios.add(comentario);
    }
}
