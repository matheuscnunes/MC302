package main.java.entity;

public class ControleDeFaltas {

    public final int id;
    private int numeroFaltas;
    private Turma turma;

    public ControleDeFaltas() {
        this.id = 0;
        this.numeroFaltas = 0;
    }

    public ControleDeFaltas(int id, Turma turma, int numeroFaltas) throws Exception {
        if (id <= 0) {
            throw new Exception("Id da disciplina deve ser positivo!");
        }
        this.id = id; //TODO: alterar para um gerador de IDs, assim a verificação não será mais necessária
        this.setTurma(turma);
        this.setNumeroFaltas(numeroFaltas);
    }

    public int getNumeroFaltas() {
        return numeroFaltas;
    }

    public void setNumeroFaltas(int numeroFaltas) throws Exception {
        if (numeroFaltas < 0) {
            throw new Exception("Número de faltas inválido para o controle de faltas em setNumeroFaltas");
        }
        this.numeroFaltas = numeroFaltas;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) throws Exception {
        if (turma == null) {
            throw new Exception("Turma não instanciada para o controle de faltas em setTurma");
        }
        this.turma = turma;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ControleDeFaltas) {
            ControleDeFaltas controleDeFaltas = (ControleDeFaltas) object;
            if (this.id == controleDeFaltas.id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String str = turma.getAno() + ", " + turma.getSemestre().toString() + " SEMESTRE";
        if (turma.getDisciplina() != null && turma.getDisciplina().getCodigo() != null &&
                !turma.getDisciplina().getCodigo().trim().equals("")) {
            str += ", " + turma.getDisciplina().getCodigo();
        }
        str += ", ";
        if (this.numeroFaltas <= 0) {
            str += "nenhuma falta";
        } else {
            str += this.numeroFaltas + " " + ((this.numeroFaltas > 1) ? "faltas" : "falta");
        }
        return str;
    }
}
