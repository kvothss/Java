package Exercicios;

public class descontoPorcentual {
    public static void main(String[] args) {
        double precoOriginal = 10.50;
        double percentualDesconto = 10;
        float porcentagem;
        porcentagem = (float) ((precoOriginal*percentualDesconto) / 100);
        System.out.println("Preço original do produto:" + precoOriginal);
        System.out.println("Preço do desconto é:" + porcentagem);
        System.out.println("Novo preço do produto é :" + (precoOriginal - porcentagem));
    }
}
