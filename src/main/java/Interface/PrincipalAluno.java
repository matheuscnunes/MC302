package main.java.Interface;

import main.java.utils.Utils;

import java.util.Scanner;

//TODO: Find a better name for this class!!
public class PrincipalAluno {

    public static void main(Scanner input) {
        System.out.println("1 - NOVO ALUNO");
        System.out.println("2 - BUSCAR ALUNO POR RA");
        System.out.println("3 - REMOVER ALUNO");
        System.out.println("4 - EXIBIR ALUNOS POR TURMA");
        System.out.println("5 - SAIR");

        if (input.hasNextInt()) {
            int op = input.nextInt();

            switch (op) {
                case 1:
                    break;
                case 2:
                    buscarAluno(input);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Operação não cadastrada");
                    main(input);
            }
        }

        main(input);
    }



    public static String obtemEmail(Scanner input) {
        String email = "";
        do {
            if (!email.trim().equals("")) {
                System.out.println("E-mail inválido");
            }

            System.out.print("\nDigite seu e-mail: ");
            email = input.next();

        } while (!Utils.validateEmail(email));
        return email;
    }

    private static void buscarAluno(Scanner input) {
//        int ra = obtemRa(input);
//        Aluno alunoEncontrado = Gerenciador.buscaAluno(ra);
//
//        if (alunoEncontrado != null) {
//            System.out.println(alunoEncontrado.toString());
//        } else {
//            System.out.println("Aluno com ra " + ra + " não encontrado");
//        }
    }

}
