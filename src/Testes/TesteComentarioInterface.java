package Testes;

import main.java.Interface.ComentarioInterface;
import main.java.entity.Gerenciador;
import main.java.entity.content.Comentario;
import main.java.entity.content.Conteudo;
import main.java.entity.member.Aluno;
import main.java.entity.member.Professor;
import main.java.entity.member.Usuario;

import java.util.Date;
import java.util.Scanner;

public class TesteComentarioInterface {

    public static void main(String[] args) throws Exception {
        TesteComentarioInterface();
    }

    public static void TesteComentarioInterface() throws Exception {
        Scanner input = new Scanner(System.in);
        Conteudo conteudoUm = new Conteudo(1,new Date(),new Aluno(1, 197960,1,"Giovanna","giovanna@gmail.com", "123"),"Olá, sou um conteúdo");
        Gerenciador.adicionarConteudo(conteudoUm);

        Comentario comentarioUm = new Comentario(1,new Date(),new Professor(1,"Julio", "julio@gmail.com", "123"),"comentario",true,"");
        conteudoUm.addComentario(comentarioUm);

        new ComentarioInterface(input, 1);
        ComentarioInterface.mostraComentarios();
    }

}
