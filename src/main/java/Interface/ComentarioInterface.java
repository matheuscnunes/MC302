package main.java.Interface;

import main.java.entity.Gerenciador;
import main.java.entity.content.Comentario;
import main.java.entity.content.Conteudo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ComentarioInterface extends Interface {

    private static List<Comentario> comentarios;
    private static Comentario comentarioAtual = null;
    private static int indexComentatioAtual = -1;

    public ComentarioInterface(Scanner input, int conteudoId) {
        super(input);

        Conteudo conteudo = Gerenciador.buscaConteudo(conteudoId);
        comentarios = conteudo.getComentarios();

        if(comentarios.size() <= 0){
            comentarioAtual = null;
            indexComentatioAtual = -1;
        }else {
            comentarioAtual = comentarios.get(0);
            indexComentatioAtual = 0;
        }
    }

    public static void mostraComentarios() {
        System.out.println(comentarioAtual.getAutor().getNome() + ":" + comentarioAtual.getComentario());
        System.out.println("-----------------------------------------------------------------------------------------------------");
    }


}
