import java.util.Scanner;

public class TestaProd {
    public static void main(String[] args){
        Scanner entrada = new Scanner(System.in);
        Produto p1 = new Produto();
        p1.imprimirProduto();

        Produto p2 = new Produto(123, "Protetor Solar", 79.9);
        p2.imprimirProduto();

        Produto p3 = new Produto(p2);
        p3.imprimirProduto();

    }
}