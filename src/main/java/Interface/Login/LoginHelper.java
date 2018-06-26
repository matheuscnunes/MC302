package main.java.Interface.Login;

import main.java.entity.Gerenciador;
import main.java.entity.member.Aluno;
import main.java.utils.Utils;

import java.util.Scanner;

//TODO: Find a better name for this class!!
public class LoginHelper {
    public static String obtemEmail(Scanner input) {
        String email = "";
        do {
            if (!email.trim().equals("")) {
                System.out.println("E-mail inválido");
            }

            System.out.print("\nDigite seu e-mail: ");
            email = input.next();

        } while (!Utils.validateEmail(email));
        return email;
    }

    public static int obtemRa(Scanner input) {
        String strRa = "";
        do {
            if (!strRa.trim().equals("")) {
                System.out.println("RA inválido");
            }

            System.out.print("Digite o RA: ");
            strRa = input.next();

        } while(strRa.trim().length() != 6 || !Utils.isNumeric(strRa));
        return Integer.parseInt(strRa);
    }
}
