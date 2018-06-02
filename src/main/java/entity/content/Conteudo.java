package main.java.entity.content;

import main.java.entity.member.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Conteudo extends Post {
    private String tituloConteudo;

    public Conteudo(int ID, Date date, Usuario autor, String conteudo, String tituloConteudo) {
        super(ID, date, autor, conteudo);
        setTituloConteudo(tituloConteudo);
    }

    public String getTituloConteudo() {
        return tituloConteudo;
    }

    public void setTituloConteudo(String tituloConteudo) {
        if(tituloConteudo.length() > 60) {
            System.out.println("Esse título é muito grande! Títulos devem ter menos de 60 caracteres.");
        } else {
            this.tituloConteudo = tituloConteudo;
        }
    }
}
