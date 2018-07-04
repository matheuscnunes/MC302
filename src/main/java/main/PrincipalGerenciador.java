package main.java.main;

import main.java.repositorio.Gerenciador;
import main.java.entity.member.Aluno;
import main.java.repositorio.GerenciadorAluno;

public class PrincipalGerenciador {

    public static void main(String[] args){
        try {
            testaLogin();
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static void testaLogin() throws Exception{
        System.out.println("Testando Login...");

        Aluno aluno = new Aluno(1,1,1,"Gi","teste@gmail.com", "123");
        GerenciadorAluno.getInstance().add(aluno);
//        Gerenciador.login("teste@gmail.com", "123");
//
//        System.out.println("usuario atual: " + Gerenciador.getUsarioAtual());
    }
}
