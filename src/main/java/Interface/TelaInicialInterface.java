package main.java.Interface;

import main.java.entity.member.*;

import java.util.Scanner;

enum OpcoesBoasVindas {
    ALUNO, MONITOR, PROFESSOR, SAIR;
}

public class TelaInicialInterface extends Interface {

    public TelaInicialInterface(Scanner input) {
        super(input);
    }

    public static void mostrarLogo() {
        System.out.println("   d8b                         888 d8b ");
        System.out.println("   Y8P                         888 Y8P ");
        System.out.println("                               888     ");
        System.out.println("  8888 8888b. 888  888 8888b.  888 888 .d8888b  ");
        System.out.println("  \"888    \"88b888  888    \"88b 888 888 88K      ");
        System.out.println("   888.d888888Y88  88P.d888888 888 888 \"Y8888b. ");
        System.out.println("   888888  888 Y8bd8P 888  888 888 888      X88 ");
        System.out.println("   888\"Y888888  Y88P  \"Y888888 888 888  88888P' ");
        System.out.println("   888                         ");
        System.out.println("  d88P                         ");
        System.out.println("888P\"                          ");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }

    public void boasVindas() throws Exception {
        System.out.println("Seja bem-vindo ao DA A RESPOSTA AÍ! Aqui os alunos e monitores de matérias da Unicamp se ajudam \ncom respostas de exercícios e conteúdos!");
        System.out.println("Para utilizar, você precisa se cadastrar ou realizar login como aluno, professor ou monitor. \nDigite o número da opção desejada:\n");

        OpcoesBoasVindas[] entradas = OpcoesBoasVindas.values();
        mostrarOpcoesBoasVindas();

        while (input.hasNextInt()) {
            int op = input.nextInt();

            if (op > 0 && op <= entradas.length) {
                switch (entradas[op - 1]) {
                    case ALUNO:
                        mostrarLoginCadastrar(TipoDeUsuario.ALUNO);
                        break;
                    case PROFESSOR:
                        mostrarLoginCadastrar(TipoDeUsuario.PROFESSOR);
                        break;
                    case MONITOR:
                        mostrarLoginCadastrar(TipoDeUsuario.MONITOR);
                        break;
                    case SAIR:
                        System.out.println("Bye JAVAlis");
                        return;
                    default:
                        System.out.println("Comando não reconhecido!");
                        mostrarOpcoesBoasVindas();
                }
            }
        }

    }

    private void mostrarOpcoesBoasVindas() {
        System.out.println("1 - Aluno");
        System.out.println("2 - Monitor");
        System.out.println("3 - Professor");
        System.out.println("4 - Sair");
    }

    private void mostrarLoginCadastrar(TipoDeUsuario tipoUsuario) throws Exception {
        System.out.println("(1) Login");
        System.out.println("(2) Cadastrar");
        System.out.println("(3) Voltar");

        int op = input.nextInt();
        switch(op) {
            case 1:
                LoginInterface telaLogin = new LoginInterface(input, tipoUsuario);
                boolean loginComSucesso = telaLogin.apresentarLogin();
                if (loginComSucesso) {
                    HomeInterface home = new HomeInterface(input);
                    home.exibirHome();
                } else {
                    mostrarLoginCadastrar(tipoUsuario);
                }
                break;
            case 2:
                CadastrarInterface cadastro = new CadastrarInterface(input, tipoUsuario);
                cadastro.apresentarCadastro();
                mostrarLoginCadastrar(tipoUsuario);
                break;
            case 3:
                boasVindas();
                break;
            default:
                System.out.println("Comando não reconhecido!");
                mostrarOpcoesBoasVindas();
        }
    }
}

