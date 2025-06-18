package Exercicios.Bee;

import java.util.Scanner;

public class Bee1011 {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        double raio = leitor.nextDouble();
        double pi = 3.14159;
        double raioCubo = Math.pow(raio, 3);
        double divisorEsfera = 4.0/3;

        double volumeEsfera = divisorEsfera * pi * raioCubo;

        System.out.printf("VOLUME = %.3f%n", volumeEsfera);



    }
}
