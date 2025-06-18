package Exercicios;

public class Casting {
        public static void main(String[] args) {
            double saldo = 55.99 ;
            int reserva = 44;
            int valorTotal;
            valorTotal = (int) (saldo + reserva);
            System.out.println(valorTotal);
        }
}