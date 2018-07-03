package main.java.Interface;

import main.java.repositorio.Gerenciador;
import main.java.utils.Utils;

import java.util.Scanner;

public class HomeInterface extends Interface {

    public HomeInterface(Scanner input) { super(input); }

    public void exibirHome() throws Exception{
        Utils.stringPrinter("Bem-vindo, %s!\n", Gerenciador.getUsarioAtual().getNome());

        System.out.println("Digite o número da opção desejada e aperte enter:");
        mostrarOpcoesHome();
        capturarOpcaoEscolhida();
    }

    private void mostrarOpcoesHome() {
        System.out.println("--------------------");
        System.out.println("|  1 - Ver posts   |");
        System.out.println("|  2 - Criar post  |");
        System.out.println("|  3 - Deslogar    |");
        System.out.println("--------------------");
    }

    private void capturarOpcaoEscolhida() throws Exception{
        int op = input.nextInt();
        switch(op) {
            case 1:
                PostsInterface postsInterface = new PostsInterface(input);
                postsInterface.exibirPost();
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                System.out.println("Comando não reconhecido!");
                mostrarOpcoesHome();
        }
    }
}
