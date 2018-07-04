package main.java.repositorio;

import main.java.entity.member.*;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GerenciadorLogin {

    private static Usuario usuarioLogado;

    private static GerenciadorLogin gerenciadorLogin = null;

    private GerenciadorLogin() {
        super();
    }

    public static GerenciadorLogin getInstance() {
        if (gerenciadorLogin == null) {
            gerenciadorLogin = new GerenciadorLogin();
        }

        return gerenciadorLogin;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    private static boolean verifyLoginAdmin(String email, String senha) {
        if (email.equals("admin") && senha.equals("admin")) {
            Usuario admin = new Aluno(1, 1, 1, "admin", "admin", "admin");
            usuarioLogado = admin;
            return true;
        }

        return false;
    }

    public static boolean login(TipoDeUsuario tipoUsuario, String email, String senha) throws Exception {
        if (verifyLoginAdmin(email, senha)) {
            System.out.println("****** Admin logged ******");
            return true;
        }
        switch (tipoUsuario) {
            case PROFESSOR:
                loginProfessor(email, senha);
            case ALUNO:
                loginAluno(email, senha);
            case MONITOR:
                loginMonitor(email, senha);
        }
        return true;
    }

    private static void loginAluno(String email, String senha) throws Exception {
        Predicate<Usuario> predicate = usuario -> {
            return usuario.getEmail().equals(email) && usuario.getSenha().equals(senha);
        };

        GerenciadorAluno gerenciadorAluno = GerenciadorAluno.getInstance();
        List<Aluno> alunosAchados = gerenciadorAluno.buscarTodos().stream().filter(predicate).collect(Collectors.toList());
        if (alunosAchados.size() < 1) {
            throw new Exception("Não foi encontrado aluno cadastrado com o email (" + email + ") e senha (" + senha + ")");
        }
        if (alunosAchados.size() > 1) {
            throw new Exception("Foram encontrados vários alunos com o email (" + email + ") e senha (" + senha + ") : " + alunosAchados.toString() + ". Será utilizado somente o primeiro.");
        }

        usuarioLogado = alunosAchados.get(0);
    }

    private static void loginProfessor(String email, String senha) throws Exception {
        Predicate<Usuario> predicate = usuario -> {
            return usuario.getEmail().equals(email) && usuario.getSenha().equals(senha);
        };

        GerenciadorProfessor gerenciadorProfessor = GerenciadorProfessor.getInstance();
        List<Professor> profsAchados = gerenciadorProfessor.buscarTodos().stream().filter(predicate).collect(Collectors.toList());
        if (profsAchados.size() < 1) {
            throw new Exception("Não foi encontrado professor cadastrado com o email (" + email + ") e senha (" + senha + ")");

        }
        if (profsAchados.size() > 1) {
            throw new Exception("Foram encontrados vários professores com o email (" + email + ") e senha (" + senha + ") : " + profsAchados.toString() + ". Será utilizado somente o primeiro.");
        }

        usuarioLogado = profsAchados.get(0);
    }

    private static void loginMonitor(String email, String senha) throws Exception {
        Predicate<Usuario> predicate = usuario -> {
            return usuario.getEmail().equals(email) && usuario.getSenha().equals(senha);
        };

        GerenciadorMonitor gerenciadorMonitor = GerenciadorMonitor.getInstance();
        List<Monitor> monitoresAchados = gerenciadorMonitor.buscarTodos().stream().filter(predicate).collect(Collectors.toList());
        if (monitoresAchados.size() < 1) {
            throw new Exception("Não foi encontrado monitor cadastrado com o email (" + email + ") e senha (" + senha + ")");
        }
        if (monitoresAchados.size() > 1) {
            throw new Exception("Foram encontrados vários monitores com o email (" + email + ") e senha (" + senha + ") : " + monitoresAchados.toString() + ". Será utilizado somente o primeiro.");
        }

        usuarioLogado = monitoresAchados.get(0);
    }

    public static Usuario deslogar() {
        Usuario usuarioDeslogado = usuarioLogado;
        usuarioLogado = null;
        return usuarioDeslogado;
    }
}
