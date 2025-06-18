import java.util.Scanner;

public class PrincipalFGTS {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        FGTS fgts = new FGTS();

        while (true) {
            try {
                System.out.println("Informe o seu nome:");
                String nome = entrada.nextLine();
                if (nome.length() < 8) {
                    throw new IllegalArgumentException("Erro: Número de caracteres insuficientes, é preciso no mínimo 8 caracteres, digite novamente! ");
                } else if(nome.length() > 50) {
                    throw new IllegalArgumentException("Erro: Número de caracteres ultrapassa o limite, sendo o máximo permitido 50 caracteres.");
                }

                if (!nome.matches("^[A-Za-zÀ-ÿ\\s]+$")) {
                    throw new IllegalArgumentException("Erro: O nome informado contém caracteres não autorizados.");
                }

                fgts.setNome(nome);
                break;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true){
            try {
                System.out.println("Informe seu salário:");
                String entradaSalario = entrada.nextLine();
                double salario = Double.parseDouble(entradaSalario.replace(",", "."));

                if(salario < 1518.00){
                    throw new IllegalArgumentException("Erro: O salário informado é inferior ao mínimo(R$ 1518.00).");
                }
                fgts.setSalario(salario);
                break;
            } catch (NumberFormatException e){
                System.out.println("Erro: informe um salário válido (ex: 2000.00)");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("\nRecibo de Pagamento");
        fgts.exibeInfo();
    }


}