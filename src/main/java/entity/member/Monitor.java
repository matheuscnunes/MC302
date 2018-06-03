package main.java.entity.member;

import main.java.entity.Turma;

import java.util.ArrayList;

public final class Monitor extends Aluno {
    private ArrayList<Turma> monitorias;

    public Monitor(int id, int ra, int curso, String nome, String email, String senha) {
        super(id, ra, curso, nome, email, senha);
        monitorias = new ArrayList<Turma>();
    }

    public boolean adicionarMonitoria(Turma novaTurma) {
        if(monitorias.contains(novaTurma))
            return false;
        monitorias.add(novaTurma);
        return true;
    }

    public boolean removerMonitoria(Turma removerTurma) {
        if(!monitorias.contains(removerTurma))
            return false;
        monitorias.add(removerTurma);
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
