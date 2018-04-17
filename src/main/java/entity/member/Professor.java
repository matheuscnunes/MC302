package main.java.entity.member;

public final class Professor extends Usuario {

    protected Professor(int id, int tipoUsuario) {
        super(id, tipoUsuario);
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
