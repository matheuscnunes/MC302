package main.java.main;

import main.java.entity.*;

public class Principal {

    public static void main (String args[]) {
        System.out.print("Hello JAVAlis");

        //Teste Controle de Faltas
        try {
            Disciplina disc = new Disciplina(29, "Cálculo III", "MA311");
            Turma t = new Turma(20, 2018, Semestre.PRIMEIRO, disc, null, null, null, null);
            ControleDeFaltas controleDeFaltas1 = new ControleDeFaltas(20, t, 0);
            System.out.println(controleDeFaltas1.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
