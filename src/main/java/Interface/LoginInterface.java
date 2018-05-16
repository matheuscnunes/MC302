package main.java.Interface;

import main.java.entity.Gerenciador;
import main.java.entity.member.Aluno;
import main.java.entity.member.Usuario;

import java.util.Scanner;

public class LoginInterface extends Interface {
    private final TipoDeUsuario TIPO_USUARIO;

    public LoginInterface(Scanner input, TipoDeUsuario tipoUsuario) {
        super(input);
        this.TIPO_USUARIO = tipoUsuario;
    }

    public Usuario apresentarLogin() {
        System.out.println("\n//////////////////////////////////////////");
        System.out.print("\nDigite seu e-mail: ");
        String email = PrincipalAluno.obtemEmail(input);
        System.out.print("\nDigite sua senha: ");
        String senha = input.next();

        return validarLogin(email, senha);
    }

    private Usuario validarLogin(String email, String senha) {
        switch (TIPO_USUARIO) {
            case ALUNO:
                Aluno aluno = Gerenciador.buscaAluno(email);
                if(aluno == null) {
                    System.out.println("Não foi encontrado aluno cadastrado com esse email!");
                    return null;
                }
                if(aluno.getSenha().equals(senha))
                    return aluno;
                return null;
            case PROFESSOR:
                return null;
            case MONITOR:
                return null;
        }
        return null;
    }
}
