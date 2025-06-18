import java.util.Scanner;

public class TestaPessoas {
    public static void main(String[] args){
        Scanner entrada = new Scanner(System.in);

        Pessoas pessoas = new Pessoas();

        pessoas.setId(entrada.nextInt());
        entrada.nextLine();
        pessoas.setEndereco(entrada.nextLine());
        pessoas.setNome(entrada.next());

        pessoas.imprimirPessoas();

       /* Pessoas pessoas2 = new Pessoas();

        pessoas2.setId(entrada.nextInt());
        entrada.nextLine();
        pessoas2.setEndereco(entrada.nextLine());
        pessoas2.setNome(entrada.nextLine());

        pessoas2.imprimirPessoas(); */
    }
}
