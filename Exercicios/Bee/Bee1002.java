package Exercicios.Bee;
import java.util.Scanner;

public class Bee1002 {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        double pi = 3.14159;
        double raio = leitor.nextDouble();
        double area = pi * raio * raio;


        System.out.printf("A=%.4f%n", area);

    }
}
