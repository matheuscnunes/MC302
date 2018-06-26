package main.java.entity;

public enum Semestre {
    PRIMEIRO, SEGUNDO;

    public int getNumero() {
        switch (this) {
            case PRIMEIRO: return 1;
            case SEGUNDO: return 2;
        }
        return 0;
    }

    @Override
    public String toString() {
        switch (this) {
            case PRIMEIRO: return "1º Semestre";
            case SEGUNDO: return "2º Semestre";
        }
        return "";
    }
}
