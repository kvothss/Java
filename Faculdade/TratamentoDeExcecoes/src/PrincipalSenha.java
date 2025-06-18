import java.util.Scanner;
public class PrincipalSenha {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ValidadorSenha validar = new ValidadorSenha();

        while(true){
            try {
                System.out.println("Crie sua senha: ");
                String senha = entrada.nextLine();
                validar.validar(senha);
                System.out.println("Senha aceita.");
                break;
            } catch (SenhaException e){
                System.out.println("Erro: " + e.getMessage());
            } finally {
                System.out.println("Validação finalizada. \n");
            }
        }
    }
}
