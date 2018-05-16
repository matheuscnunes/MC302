package main.java.Interface;

import main.java.entity.Gerenciador;
import main.java.entity.member.Aluno;
import main.java.entity.member.Monitor;
import main.java.entity.member.Professor;
import main.java.entity.member.Usuario;
import main.java.utils.Utils;

import java.util.Scanner;

public class CadastrarInterface extends Interface {
    private final TipoDeUsuario TIPO_USUARIO;

    public CadastrarInterface(Scanner input, TipoDeUsuario tipoUsuario) {
        super(input);
        this.TIPO_USUARIO = tipoUsuario;
    }

    public boolean apresentarCadastro() {
        switch (TIPO_USUARIO) {
            case ALUNO:
                Aluno aluno = cadastroAluno();
                Gerenciador.adicionarAluno(aluno);
                System.out.println("Aluno adicionado!");
                return true;
            case PROFESSOR:
                Professor prof = cadastroProfessor();
                Gerenciador.adicionarProfessor(prof);
                System.out.println("Professor adicionado!");
                return true;
            case MONITOR:
                Monitor monitor = cadastroMonitor();
                Gerenciador.adicionarMonitor(monitor);
                System.out.println("Monitor adicionado!");
                return true;
        }
        return false;
    }

    private Aluno cadastroAluno() {
        int ra = 0, curso = 0;
        String email = "", nome, senha;

        System.out.print("Digite seu RA: ");
        ra = obtemRa();
        System.out.print("\nDigite seu Curso: ");
        curso = obtemCurso();
        System.out.print("\nDigite seu e-mail: ");
        email = PrincipalAluno.obtemEmail(input);

        System.out.print("Digite o nome completo: ");
        nome = input.next();

        System.out.print("\nDigite uma nova senha: ");
        senha = input.next();

        Aluno novoAluno = new Aluno(1, ra, curso, nome, email, senha);
        return novoAluno;
    }

    private Professor cadastroProfessor() {
        String email = "", nome, senha;
        email = PrincipalAluno.obtemEmail(input);

        System.out.print("Digite o nome completo: ");
        nome = input.next();

        System.out.print("\nDigite uma nova senha: ");
        senha = input.next();

        Professor novoProfessor = new Professor(1, nome, email, senha);
        return novoProfessor;
    }

    private Monitor cadastroMonitor() {
        int ra = 0, curso = 0;
        String email = "", nome, senha;

        System.out.print("Digite seu RA: ");
        ra = obtemRa();
        System.out.print("\nDigite seu Curso: ");
        curso = obtemCurso();
        System.out.print("\nDigite seu e-mail: ");
        email = PrincipalAluno.obtemEmail(input);

        System.out.print("Digite o nome completo: ");
        nome = input.next();

        System.out.print("\nDigite uma nova senha: ");
        senha = input.next();

        Monitor novoMonitor = new Monitor(1, ra, curso, nome, email, senha);
        return novoMonitor;
    }

    private int obtemRa() {
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

    private int obtemCurso() {
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
}
