package main.java.repositorio;

import main.java.entity.member.Usuario;

public class GerenciadorLogin {

    private static Usuario usuarioLogado;

    public static Usuario getUsuarioLogado(){
        return usuarioLogado;
    }
}
