package main.java.main;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Scanner;

public class Principal {

    public static void main (String args[]) {
        System.out.println("Hello JAVAlis!\n");

        Entrada[] entradas = Entrada.values();
        Scanner input = new Scanner(System.in);

        System.out.println("1 - Aluno");
        System.out.println("2 - Professor");
        System.out.println("3 - Monitor");
        System.out.println("4 - Conteúdo");
        System.out.println("5 - Pergunta");
        System.out.println("6 - Sair");

        while (input.hasNextInt()) {
            int op = input.nextInt();

            if (op > 0 && op <= entradas.length) {
                switch (entradas[op - 1]) {
                    case ALUNO:
                        PrincipalAluno.main(input);
                        break;
                    case PROFESSOR:
                        System.out.println("Professor");
                        break;
                    case MONITOR:
                        System.out.println("Monitor");
                        break;
                    case CONTEUDO:
                        System.out.println("Conteúdo");
                        break;
                    case PERGUNTA:
                        System.out.println("Pergunta");
                        break;
                    case SAIR:
                        System.out.println("Bye JAVAlis");
                        return;
                    default:
                        System.out.println("Comando não reconhecido!");
                }
            }

            System.out.println("1 - Aluno");
            System.out.println("2 - Professor");
            System.out.println("3 - Monitor");
            System.out.println("4 - Sair");
        }
    }

}
