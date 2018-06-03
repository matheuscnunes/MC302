package main.java.main;

import main.java.Interface.TelaInicialInterface;
import main.java.entity.Gerenciador;
import main.java.utils.GeradorDados;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class Principal {

    public static void main(String args[]) {
        TelaInicialInterface.mostrarLogo();

        GeradorDados.gerarDados();
        Gerenciador.verifyList();
        Scanner input = new Scanner(System.in);

        TelaInicialInterface telaQuente = new TelaInicialInterface(input);
        telaQuente.boasVindas();
    }
}


