package main.java.main;

import main.java.entity.Gerenciador;
import main.java.entity.member.Aluno;

public class PrincipalGerenciador {

    public static void main(String[] args){
        testaLogin();

    }

    public static void testaLogin(){
        System.out.println("Testando Login...");

        Aluno aluno = new Aluno(1,1,1,"Gi","teste@gmail.com", "123");
        Gerenciador.adicionarAluno(aluno);
//        Gerenciador.login("teste@gmail.com", "123");
//
//        System.out.println("usuario atual: " + Gerenciador.getUsarioAtual());
    }
}
