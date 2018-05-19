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
        System.out.println("\n//////////////////////////////////////////\n");
        String email = PrincipalAluno.obtemEmail(input);
        System.out.print("\nDigite sua senha: ");
        String senha = input.next();

        return validarLogin(email, senha);
    }

    private Usuario validarLogin(String email, String senha) {
        Usuario user = null;
        switch (TIPO_USUARIO) {
            case ALUNO:
                user = Gerenciador.buscaAluno(email);
                break;
            case PROFESSOR:
                user = Gerenciador.buscaProfessor(email);
                break;
            case MONITOR:
                user = Gerenciador.buscaMonitor(email);
                break;
        }
        if(user == null) {
            System.out.println("Não foi encontrado aluno cadastrado com esse email!");
            return null;
        }
        if(user.getSenha().equals(senha)) {
            Gerenciador.setUsuarioAtual(user);
            return user;
        }
        System.out.println("Senha incorreta! Tente novamente.");
        return null;
    }
}
