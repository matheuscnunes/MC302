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

    public void apresentarCadastro() {
        switch (TIPO_USUARIO) {
            case ALUNO:
                Aluno aluno = cadastroAluno();
                Gerenciador.adicionarAluno(aluno);
                System.out.println("Aluno adicionado!");
                break;
            case PROFESSOR:
                Professor prof = cadastroProfessor();
                Gerenciador.adicionarProfessor(prof);
                System.out.println("Professor adicionado!");
                break;
            case MONITOR:
                Monitor monitor = cadastroMonitor();
                Gerenciador.adicionarMonitor(monitor);
                System.out.println("Monitor adicionado!");
                break;
        }
    }

    private Aluno cadastroAluno() {
        int ra = 0, curso = 0;
        String email = "", nome, senha;

        ra = obtemRa();
        curso = obtemCurso();
        email = PrincipalAluno.obtemEmail(input);

        System.out.print("Digite seu nome: ");
        nome = input.next();

        System.out.print("\nCrie uma senha: ");
        senha = input.next();

        Aluno novoAluno = new Aluno(1, ra, curso, nome, email, senha);
        return novoAluno;
    }

    private Professor cadastroProfessor() {
        String email = "", nome, senha;
        email = PrincipalAluno.obtemEmail(input);

        System.out.print("Digite o nome: ");
        nome = input.next();

        System.out.print("Digite uma senha: ");
        senha = input.next();

        Professor novoProfessor = new Professor(1, nome, email, senha);
        return novoProfessor;
    }

    private Monitor cadastroMonitor() {
        int ra = 0, curso = 0;
        String email = "", nome, senha;

        ra = obtemRa();
        curso = obtemCurso();
        email = PrincipalAluno.obtemEmail(input);

        System.out.print("Digite seu nome: ");
        nome = input.next();

        System.out.print("\nCria uma senha: ");
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

            System.out.print("Digite o RA: ");
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

            System.out.println("Digite o número do curso: ");
            strCurso = input.next();

        } while(!Utils.isNumeric(strCurso));
        return Integer.parseInt(strCurso);
    }
}
