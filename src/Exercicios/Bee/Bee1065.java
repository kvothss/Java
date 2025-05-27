package Exercicios;

import java.util.Scanner;

public class Bee1065 {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        int numerosPares = 0;

        for (int i = 0; i<5; i++){
            int entrada = leitor.nextInt();

            if(entrada % 2 == 0){
                numerosPares++;
            }
        }
        System.out.println(numerosPares + " valores pares");

        leitor.close();
    }
}
