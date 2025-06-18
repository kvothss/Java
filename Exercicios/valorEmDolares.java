package Exercicios;

public class valorEmDolares {
    public static void main(String[] args) {
        double dolarHoje = 4.94;
        int realHoje = 1;
        String mensagem = """
                "O valor do dólar hoje é: %.2f e do real é:%d %n
                """.formatted(dolarHoje, realHoje);
        System.out.println(mensagem);
    }
}
