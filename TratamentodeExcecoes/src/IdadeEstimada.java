import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class IdadeEstimada {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);

        Date dataNascimento = null;

        while (true) {
            try {
                System.out.println("Digite sua data de nascimento (dd/MM/yyyy): ");
                String entradaData = entrada.nextLine();
                dataNascimento = formato.parse(entradaData);

                if (dataNascimento.after(new Date())) {
                    throw new IllegalArgumentException("Erro: A data não pode estar no futuro.");
                }

                break;

            } catch (ParseException e) {
                System.out.println("Erro: Data em formato inválido!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("Tentativa de leitura da data realizada.\n");
            }
        }

        long idadeMilissegundos = new Date().getTime() - dataNascimento.getTime();
        long idadeAnos = idadeMilissegundos / (1000L * 60 * 60 * 24 * 365);

        System.out.println("Idade estimada: " + idadeAnos + " anos.");
    }
}
