package main.java.entity.member;

public final class Professor extends Usuario {

    //, int tipoUsuario
    protected Professor(int id, String nome, String email, String senha) {
        super(id, nome, email, senha);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Professor) {
            Professor user = (Professor) obj;
            return user.id == id;
        }
        return false;
    }
}
