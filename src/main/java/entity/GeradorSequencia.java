package main.java.entity;

public class GeradorSequencia {

    private static int sequencia = 0;

    public static int nextSequencia(){
        return ++sequencia;
    }
}
