package Exercicios.Bee;

import java.util.Scanner;

public class Bee1010 {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        int codProduto = leitor.nextInt();
        int unidadeProdutos = leitor.nextInt();
        double valorProduto = leitor.nextDouble();
        int codProduto2 = leitor.nextInt();
        int unidadeProdutos2 = leitor.nextInt();
        double valorProduto2 = leitor.nextDouble();

        double valorTotal = valorProduto * unidadeProdutos + valorProduto2 * unidadeProdutos2;

        leitor.close();

        System.out.printf("VALOR A PAGAR: R$ %.2f%n", valorTotal);

    }
}
