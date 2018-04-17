package main.java.entity.member;

import main.java.entity.ControleDeFaltas;

import java.util.ArrayList;

public class Aluno extends Usuario {
    public final int ra;
    private int curso;
    private ArrayList<ControleDeFaltas> controladoresDeFaltas;

    public Aluno(int id, int tipoUsuario, int ra, int curso) {
        super(id, tipoUsuario);
        this.ra = ra;
        this.curso = curso;
        controladoresDeFaltas = new ArrayList<ControleDeFaltas>();
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

    public ArrayList<ControleDeFaltas> getControladoresDeFaltas() {
        return controladoresDeFaltas;
    }

    public boolean adicionarControladorDeFaltas(ControleDeFaltas novoControlador) {
        if(controladoresDeFaltas.contains(novoControlador))
            return false;
        controladoresDeFaltas.add(novoControlador);
        return true;
    }

    public boolean removerControladorDeFaltas(ControleDeFaltas removerControlador) {
        if(!controladoresDeFaltas.contains(removerControlador))
            return false;
        controladoresDeFaltas.add(removerControlador);
        return true;
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