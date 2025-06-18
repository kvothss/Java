package Exercicios.Bee;

import java.util.Scanner;

public class Bee1009 {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        String nome = leitor.next();

        double salario = leitor.nextDouble();
        double porcentagem = 15.0;
        double vendas = leitor.nextDouble();
        double salarioTotal = salario + (vendas * porcentagem / 100);

        System.out.printf("TOTAL = R$ %.2f%n", salarioTotal);

        leitor.close(); // Boa prática: fechar o scanner após o uso
    }
}
