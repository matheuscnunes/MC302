package main.java.main;

import main.java.Interface.TelaInicialInterface;
import java.util.Scanner;

public class Principal {

    public static void main(String args[]) {
        TelaInicialInterface.mostrarLogo();

        Entrada[] entradas = Entrada.values();
        Scanner input = new Scanner(System.in);

        TelaInicialInterface telaQuente = new TelaInicialInterface(input);
        telaQuente.boasVindas();
    }
}


