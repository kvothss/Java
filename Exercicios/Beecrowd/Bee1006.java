package Exercicios.Bee;

import java.util.Scanner;

public class Bee1006 {
    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);

        double a = leitor.nextDouble();
        double b = leitor.nextDouble();
        double c = leitor.nextDouble();
        double media = (a * 2 + b * 3 + c * 5) / 10;

        System.out.printf("MEDIA = %.1f%n", media);
    }
}
