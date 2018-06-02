package main.java.Interface;

import main.java.entity.Gerenciador;
import main.java.entity.member.Aluno;
import main.java.entity.member.TipoDeUsuario;
import main.java.entity.member.Usuario;

import java.util.Scanner;

public class LoginInterface extends Interface {
    private final TipoDeUsuario TIPO_USUARIO;

    public LoginInterface(Scanner input, TipoDeUsuario tipoUsuario) {
        super(input);
        this.TIPO_USUARIO = tipoUsuario;
        Aluno fakeAluno = new Aluno(3982, 203263, 42, "Mateus Freitas", "mateus@gmail.com", "1234");
        Gerenciador.adicionarAluno(fakeAluno);
    }

    public boolean apresentarLogin() {
        System.out.println("\n//////////////////////////////////////////\n");
        String email = PrincipalAluno.obtemEmail(input);
        System.out.print("\nDigite sua senha: ");
        String senha = input.next();

        return validarLogin(email, senha);
    }

    private boolean validarLogin(String email, String senha) {
        return Gerenciador.login(TIPO_USUARIO, email, senha);
    }
}
