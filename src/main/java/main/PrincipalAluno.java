package main.java.main;

import main.java.entity.Gerenciador;
import main.java.entity.member.Aluno;
import main.java.utils.Utils;

import java.util.Scanner;

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
                    try {
                        addAluno(input);
                    }
                    catch (Exception e){
                        System.err.println(e.getMessage());
                    }
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
                    Principal.main(null);
            }
        }

        main(input);
    }

    private static void addAluno(Scanner input) throws Exception{
        int ra = 0, curso = 0;
        String email = "", nome, senha;

        ra = obtemRa(input);
        curso = obtemCurso(input);
        email = obtemEmail(input);

        System.out.println("Digite o nome completo:");
        nome = input.next();

        System.out.println("Digite a senha:");
        senha = input.next();

        Aluno novoAluno = new Aluno(1, ra, curso, nome, email, senha);
        Gerenciador.adicionarAluno(novoAluno);

        System.out.println("Aluno adicionado!");
    }

    private static int obtemRa(Scanner input) {
        String strRa = "";
        do {
            if (!strRa.trim().equals("")) {
                System.out.println("RA inválido");
            }

            System.out.println("Digite o RA:");
            strRa = input.next();

        } while(strRa.trim().length() != 6 || !Utils.isNumeric(strRa));
        return Integer.parseInt(strRa);
    }

    private static int obtemCurso(Scanner input) {
        String strCurso = "";
        do {
            if (!strCurso.trim().equals("")) {
                System.out.println("Curso inválido");
            }

            System.out.println("Digite o número do curso:");
            strCurso = input.next();

        } while(!Utils.isNumeric(strCurso));
        return Integer.parseInt(strCurso);
    }

    private static String obtemEmail(Scanner input) {
        String email = "";
        do {
            if (!email.trim().equals("")) {
                System.out.println("Email inválido");
            }

            System.out.println("Digite o email:");
            email = input.next();

        } while (!Utils.validateEmail(email));
        return email;
    }

    private static void buscarAluno(Scanner input) {
        int ra = obtemRa(input);
        Aluno alunoEncontrado = Gerenciador.buscaAluno(ra);

        if (alunoEncontrado != null) {
            System.out.println(alunoEncontrado.toString());
        } else {
            System.out.println("Aluno com ra " + ra + " não encontrado");
        }
    }

}
