package Exercicios;

public class ConversaoTemperatura {
    public static void main(String[] args) {
        int celsius = 18;
        double fahrenheit = (int) (celsius * 1.8 +32);

        System.out.printf("A temperatura é: %.2fº fahrenheit %n",  fahrenheit);
    }
}
