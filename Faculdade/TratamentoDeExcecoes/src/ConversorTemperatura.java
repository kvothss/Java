import java.util.Scanner;

public class ConversorTemperatura {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        double celsius = 0;

        while (true) {
            try {
                System.out.print("Digite a temperatura em graus Celsius: ");
                String valor = entrada.nextLine();

                celsius = Double.parseDouble(valor.replace(",", "."));

                if (celsius < -273.15) {
                    throw new IllegalArgumentException("Erro: A temperatura não pode ser menor que o zero absoluto (-273.15 ºC).");
                }

                break;

            } catch (NumberFormatException e) {
                System.out.println("Erro: Valor inválido. Digite um número real.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("Tentativa de leitura concluída.\n");
            }
        }
        double fahrenheit = (celsius * 9/5) + 32;
        System.out.printf("Temperatura em Fahrenheit: %.2f ºF%n", fahrenheit);
    }
}
