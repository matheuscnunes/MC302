package entity;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {

    private int id;
    private String nome, codigo;
    private List<Turma> turmas;

    public Disciplina() {
        this.id = 0;
        this.nome = "";
        this.codigo = "";
        this.turmas = new ArrayList<Turma>();
    }

    public Disciplina(int id, String nome, String codigo) throws Exception {
        this();
        this.setId(id);
        this.setNome(nome);
        this.setCodigo(codigo);
    }

    public Disciplina(int id, String nome, String codigo, List<Turma> turmas) throws Exception {
        this(id, nome, codigo);
        this.setTurmas(turmas);
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

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) throws Exception {
        if (nome == null || nome.trim().equals("")) {
            throw new Exception("Nome da disciplina não fornecido em setNome");
        }
        this.nome = nome;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) throws Exception {
        if (codigo == null || codigo.trim().equals("")) {
            throw new Exception("Código da disciplina não fornecido em setCodigo");
        }
        this.codigo = codigo;
    }

    public List<Turma> getTurmas() {
        return this.turmas;
    }

    public void setTurmas(List<Turma> turmas) throws Exception {
        if (turmas == null || turmas.isEmpty()) {
            throw new Exception("Turmas não fornecidas em setTurmas");
        }
        if (this.turmas == null) {
            this.turmas = turmas;
        } else {
            this.turmas.addAll(turmas);
        }
    }

    public void adicinarTurma(Turma turma) throws Exception {
        if (turma == null) {
            throw new Exception("Turma não instanciada em adicionarTurma");
        }
        if (this.turmas == null) {
            throw new Exception("Lista de turma não instanciada em Disciplina");
        }
        this.turmas.add(turma);
    }

    public Turma getTurma(int id) {
        if (this.turmas != null) {
            for (Turma turma : this.turmas) {
                if (turma.getId() == id) {
                    return turma;
                }
            }
        }
        return null;
    }

    public boolean removerTurmas(List<Turma> turmas) throws Exception {
        if (this.turmas != null) {
            if (turmas == null || turmas.isEmpty()) {
                throw new Exception("Turmas não fornecidas em removerTurmas");
            }
            return this.turmas.removeAll(turmas);
        }
        return false;
    }

    public boolean removerTurma(Turma turma) throws Exception {
        if (this.turmas != null) {
            if (turma == null) {
                throw new Exception("Turma não fornecida em removerTurma");
            }
            return this.turmas.remove(turma);
        }
        return false;
    }

    public Turma removerTurma(int id) {
        if (this.turmas != null) {
            Turma turma = this.getTurma(id);
            if (turma != null) {
                this.turmas.remove(turma);
            }
            return turma;
        }
        return null;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Disciplina) {
            Disciplina disciplina = (Disciplina) object;
            if (this.codigo == disciplina.getCodigo()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.codigo + ", " + this.nome;
    }

    // Main apenas para teste da classe Disciplina. Retirar depois.
    public static void main(String args[]) {
        try {
            Disciplina disciplina1 = new Disciplina(10, "Fundamentos matemáticos da computação", "MC358");
            System.out.println(disciplina1.toString());
            Turma turma = new Turma();
            disciplina1.adicinarTurma(turma);
            if (!disciplina1.removerTurma(turma)) {
                System.out.println("Turma já existente para remoção");
            }
            List<Turma> turmas = new ArrayList<Turma>();
            turmas.add(new Turma());
            Disciplina disciplina2 = new Disciplina(20, "Calculo III", "MA311", turmas);
            System.out.println(disciplina2.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
