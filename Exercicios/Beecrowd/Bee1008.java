package Exercicios.Bee;

import java.util.Scanner;

public class Bee1008 {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        int numeroFuncionario = leitor.nextInt();
        int horasTrabalhadas = leitor.nextInt();
        double horaSalario = leitor.nextDouble();
        double salario = horasTrabalhadas * horaSalario;


        System.out.println("NUMBER = " + numeroFuncionario);
        System.out.printf("SALARY = U$ %.2f%n", salario);
    }
}
