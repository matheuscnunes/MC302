package main.java.Interface;

import main.java.entity.member.Usuario;
import main.java.utils.Utils;

import java.util.Scanner;

public class HomeInterface extends Interface {

    public HomeInterface(Scanner input) { super(input); }

    public void exibirHome(TipoDeUsuario tipoUsuario, Usuario usuario) {
        Utils.stringPrinter("Bem-vindo %s! Nossa home ainda não está disponível, volte mais tarde!",
                usuario.getNome());
        new TelaInicialInterface(input).boasVindas();
    }

}
