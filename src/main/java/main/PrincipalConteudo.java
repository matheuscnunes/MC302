package main.java.main;

import main.java.entity.Gerenciador;
import main.java.entity.content.Comentario;
import main.java.entity.content.Conteudo;
import main.java.entity.member.Aluno;
import main.java.entity.member.Usuario;
import main.java.utils.Utils;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PrincipalConteudo {

    public static void main(Scanner input) {
        System.out.println("1 - NOVO CONTEÚDO");
        System.out.println("2 - BUSCAR CONTEÚDO POR ID");
        System.out.println("3 - BUSCAR CONTEÚDOS POR AUTOR");
        System.out.println("4 - BUSCAR CONTEÚDOS POR INTERVALO DE DATA");
        System.out.println("5 - COMENTAR");
        System.out.println("6 - REMOVER  COMENTÁRIO");
        System.out.println("7 - REMOVER CONTEÚDO");
        System.out.println("8 - SAIR");

        if (input.hasNextInt()) {
            int op = input.nextInt();

            switch (op) {
                case 1:
                    addConteudo(input);
                    break;
                case 2:
                    buscarConteudoPorId(input);
                    break;
                case 3:
                    buscarConteudosPorAutor(input);
                    break;
                case 4:
                    //buscarConteudosPorIntervaloData(input);
                    break;
                case 5:
                    adicionarComentario(input);
                    break;
                case 6:
                    //removerComentario(input);
                    break;
                case 7:
                    //removerConteudo(input);
                    break;
                case 8:
                    Principal.main(new String[]{});
                    break;
                default:
                    System.out.println("Operação não cadastrada");
                    Principal.main(null);
            }

        }

        main(input);
    }

    private static void exibirConteudo(Conteudo conteudoPesquisado){
        if (conteudoPesquisado == null){
            System.out.println("Não existe nenhum conteúdo com esse ID");
        }
        else {
            System.out.println("Conteúdo : " + conteudoPesquisado.getConteudo());
            System.out.println("Autor : " + conteudoPesquisado.getAutor().getNome());
            List<Comentario> comentarios = conteudoPesquisado.getComentarios();
            if (comentarios != null){
                for (Comentario comentario : comentarios){
                    System.out.println("Comentário : " + comentario.getComentario());
                }
            }
            System.out.println();
        }
    }

    private static void adicionarComentario(Scanner input){
        int conteudoId = 0;
        try{
            System.out.println("Digite o id do conteúdo para adicinar comentários :  ");
            conteudoId = input.nextInt();
        }
        catch (Exception e) {
            adicionarComentario(input);
        }

        System.out.println("Digite o texto do comentário : ");
        try {
            String comentario = input.next();
            Gerenciador.adicionarComentario(conteudoId, comentario);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void buscarConteudosPorAutor(Scanner input){
        String autor = "";
        do{
            System.out.println("Digite o nome do autor para buscar os conteúdos : ");
            autor = input.next();
        }
        while (autor != "");

        List<Conteudo> conteudosBuscados = Gerenciador.buscaConteudos(autor);
        if (conteudosBuscados == null || conteudosBuscados.isEmpty()){
            System.out.println("Não existe nenhum conteúdo publicado por esse autor");
        }
        else{
            for (Conteudo conteudo : conteudosBuscados){
                exibirConteudo(conteudo);
            }
        }
    }

    private static void buscarConteudoPorId(Scanner input){
        int conteudoId = 0;
        try {
            System.out.println("Digite o ID do conteúdo a ser pesquisado: ");
            conteudoId = input.nextInt();
        }
        catch (Exception e) {
            buscarConteudoPorId(input);
        }
        Conteudo conteudoPesquisado = Gerenciador.buscaConteudo(conteudoId);
        exibirConteudo(conteudoPesquisado);
    }

    private static void addConteudo(Scanner input) {
        Usuario usuarioPostagem = Gerenciador.getUsuarioLogado();
        if (usuarioPostagem == null) {
            System.out.println("Você precisa fazer o login antes de realizar essa operação");
        }
        else {
            String texto = "";
            do {
                System.out.println("Digite o texto do conteúdo completo:");
                texto = input.next();
            }
            while (texto.trim().equals(""));

            Date dataPostagem = new Date();
            Conteudo conteudo = new Conteudo(Gerenciador.nextSequence(), dataPostagem, usuarioPostagem, texto);
            try {
                Gerenciador.adicionarConteudo(conteudo);
                System.out.println("Conteúdo adicionado!");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
