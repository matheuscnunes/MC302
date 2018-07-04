package main.java.entity.member;

public abstract class Usuario {
    private static final int MIN_NOME_LENGTH = 3;

    public final int id;
    private String nome;
    private String email;
    private String senha;

    protected Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    //getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome.length() > Usuario.MIN_NOME_LENGTH)
            this.nome = nome;
        else
            System.out.println("O nome deve ter mais de 3 caracteres.");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(isEmail(email))
            this.email = email;
        else
            System.out.println("E-mail não está no formato correto.");
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    //helpers
    private boolean isEmail(String email) {
        return true;
    }
}
