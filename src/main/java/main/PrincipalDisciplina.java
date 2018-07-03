package main.java.main;

import main.java.repositorio.Gerenciador;
import main.java.entity.Disciplina;
import main.java.utils.Utils;

import java.util.Scanner;

public class PrincipalDisciplina {
    public static void main(Scanner input) {
        System.out.println("1 - NOVA DISCIPLINA");
        System.out.println("2 - BUSCAR DISCIPLINA POR CÓDIGO");
        System.out.println("3 - REMOVER DISCIPLINA");
        System.out.println("4 - EXIBIR TODAS AS DISCIPLINAS");
        System.out.println("5 - SAIR");

        if (input.hasNextInt()) {
            int op = input.nextInt();

            switch (op) {
                case 1:
                    addDisciplina(input);
                    break;
                case 2:
                    try {
                        buscarDisciplina(input);
                    }
                    catch (Exception e){
                        System.err.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        removeDisciplina(input);
                    }
                    catch (Exception e){
                        System.err.println(e.getMessage());
                    }
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

    private static void addDisciplina(Scanner input){
        int id;
        String nome, codigo;

        id = obtemId(input);

        System.out.println("Digite o nome da disciplina:");
        nome = input.next();

        System.out.println("Digite o código da disciplina:");
        codigo = input.next();

        try {
            Disciplina disc = new Disciplina(id, nome, codigo);
            Gerenciador.adicionarDisciplina(disc);
            System.out.println("Disciplina adicionada com sucesso");

        } catch (Exception e) {
            System.out.println("Erro ao criar disciplina");
        }
    }

    private static int obtemId(Scanner input) {
        String strId = "";

        do {
            if (!strId.trim().equals("")) {
                System.out.println("Id da disciplina inválido!");
            }
            System.out.println("Digite o ID da disciplina:");
            strId = input.next();

        }while(!Utils.isNumeric(strId));

        return Integer.parseInt(strId);
    }

    private static void buscarDisciplina(Scanner input) throws Exception{
        String codigo;

        System.out.println("Digite o código da disciplina:");
        codigo = input.next();

        Disciplina discEncontrada = Gerenciador.buscaDisciplina(codigo);

        if (discEncontrada != null) {
            System.out.println(discEncontrada.toString());
        } else {
            System.out.println("A disciplina de codigo " + codigo + " não foi encontrada");
        }
    }

    private static void removeDisciplina(Scanner input) throws Exception{
        String codigo;

        System.out.println("Digite o código da disciplina:");
        codigo = input.next();

        Disciplina discRemovida = Gerenciador.removerDisciplina(codigo);

        if (discRemovida != null) {
            System.out.println("A disciplina " + discRemovida.getNome() + " de codigo " + codigo + " foi removida");
        } else {
            System.out.println("A disciplina de codigo " + codigo + " não foi encontrada");
        }
    }
}
