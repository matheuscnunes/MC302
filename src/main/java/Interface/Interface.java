package main.java.Interface;

import java.util.Scanner;

public class Interface {
    protected final String ANSI_RESET = "\u001B[0m";
    protected final String ANSI_BLACK = "\u001B[30m";
    protected final String ANSI_RED = "\u001B[31m";
    protected final String ANSI_GREEN = "\u001B[32m";
    protected final String ANSI_YELLOW = "\u001B[33m";
    protected final String ANSI_BLUE = "\u001B[34m";
    protected final String ANSI_PURPLE = "\u001B[35m";
    protected final String ANSI_CYAN = "\u001B[36m";
    protected final String ANSI_WHITE = "\u001B[37m";

    protected Scanner input;

    public Interface(Scanner input) {
        this.input = input;
    }


    // - Mark: PRINT
    protected void printAzul(String texto) {
        System.out.print(ANSI_BLUE + texto + ANSI_RESET);
    }

    protected void printRoxo(String texto) {
        System.out.print(ANSI_PURPLE + texto + ANSI_RESET);
    }

    protected void printAmarelo(String texto) {
        System.out.print(ANSI_YELLOW + texto + ANSI_RESET);
    }

    protected void printVermelho(String texto) {
        System.out.print(ANSI_YELLOW + texto + ANSI_RESET);
    }

    protected void printPreto(String texto) {
        System.out.print(ANSI_BLACK + texto + ANSI_RESET);
    }

    // - Mark: PRINTLN

    protected void printlnAzul(String texto) {
        System.out.println(ANSI_BLUE + texto + ANSI_RESET);
    }

    protected void printlnRoxo(String texto) {
        System.out.println(ANSI_PURPLE + texto + ANSI_RESET);
    }

    protected void printlnAmarelo(String texto) {
        System.out.println(ANSI_YELLOW + texto + ANSI_RESET);
    }

    protected void printlnVermelho(String texto) {
        System.out.println(ANSI_RED + texto + ANSI_RESET);
    }

    protected void printlnPreto(String texto) {
        System.out.println(ANSI_BLACK + texto + ANSI_RESET);
    }
}
