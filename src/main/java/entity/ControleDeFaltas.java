package main.java.entity;

public class ControleDeFaltas {

    private int id, ano, numeroFaltas;
    private Semestre semestre;
    private Disciplina disciplina;

    public ControleDeFaltas() {
        this.id = 0;
        this.ano = 0;
        this.semestre = Semestre.PRIMEIRO;
        this.numeroFaltas = 0;
    }

    public ControleDeFaltas(int id, Disciplina disciplina, int ano, Semestre semestre, int numeroFaltas) throws Exception {
        this.setId(id);
        this.setDisciplina(disciplina);
        this.setAno(ano);
        this.setSemestre(semestre);
        this.setNumeroFaltas(numeroFaltas);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("Id da disciplina inválido em setId");
        }
        this.id = id;
    }

    public int getAno() {
        return this.ano;
    }

    public void setAno(int ano) throws Exception {
        if (ano <= 0) {
            throw new Exception("Ano inválido para o controle de faltas em setAno");
        }
        this.ano = ano;
    }

    public Semestre getSemestre() {
        return this.semestre;
    }

    public void setSemestre(Semestre semestre) throws Exception {
        if (semestre == null) {
            throw new Exception("Semestre não fornecido para o controle de faltas em setSemestre");
        }
        this.semestre = semestre;
    }

    public int getNumeroFaltas() {
        return this.numeroFaltas;
    }

    public void setNumeroFaltas(int numeroFaltas) throws Exception {
        if (numeroFaltas < 0) {
            throw new Exception("Número de faltas inválido para o controle de faltas em setNumeroFaltas");
        }
        this.numeroFaltas = numeroFaltas;
    }

    public Disciplina getDisciplina() {
        return this.disciplina;
    }

    public void setDisciplina(Disciplina disciplina) throws Exception {
        if (disciplina == null) {
            throw new Exception("Disciplina não instanciada para o controle de faltas em setDisciplina");
        }
        this.disciplina = disciplina;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ControleDeFaltas) {
            ControleDeFaltas controleDeFaltas = (ControleDeFaltas) object;
            if (this.id == controleDeFaltas.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String str = this.ano + ", " + this.semestre.toString() + " SEMESTRE";
        if (this.disciplina != null && this.disciplina.getCodigo() != null &&
                !this.disciplina.getCodigo().trim().equals("")) {
            str += ", " + this.disciplina.getCodigo();
        }
        str += ", ";
        if (this.numeroFaltas <= 0) {
            str += "nenhuma falta";
        } else {
            str += this.numeroFaltas + " " + ((this.numeroFaltas > 1) ? "faltas" : "falta");
        }
        return str;
    }

    // Main apenas para teste da classe ControleDeFaltas. Retirar depois
    public static void main(String args[]) {
        try {
            ControleDeFaltas controleDeFaltas1 = new ControleDeFaltas(20,
                    new Disciplina(29, "Cálculo III", "MA311"), 2018, Semestre.PRIMEIRO, 0);
            System.out.println(controleDeFaltas1.toString());

            ControleDeFaltas controleDeFaltas2 = new ControleDeFaltas(25,
                    new Disciplina(39, "Cálculo II", "MA345"), 2017, Semestre.SEGUNDO, 1);
            System.out.println(controleDeFaltas2.toString());

            ControleDeFaltas controleDeFaltas3 = new ControleDeFaltas(26,
                    new Disciplina(49, "Cálculo Numérico", "MA356"), 2018, Semestre.PRIMEIRO, 26);
            System.out.println(controleDeFaltas3.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
