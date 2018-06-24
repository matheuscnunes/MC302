package main.java.entity.member;

import java.util.ArrayList;

public class Aluno extends Usuario {
    public final int ra;
    private int curso;

    public Aluno(int id, int ra, int curso, String nome, String email, String senha) {
        super(id, nome, email, senha);
        this.ra = ra;
        this.curso = curso;
    }

    //getters e setters
    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        if(curso > 0)
            this.curso = curso;
        else
            System.out.println("Valor inválido de curso!");
    }

    public int getRa() {
        return ra;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "ra=" + ra +
                ", curso=" + curso +
                ", id=" + id +
                ", nome=" + getNome() +
                ", email=" + getEmail() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Aluno) {
            Aluno objAluno = (Aluno)obj;
            return objAluno.id == id || objAluno.ra == ra;
        }
        return false;
    }
}
