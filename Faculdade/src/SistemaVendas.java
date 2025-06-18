import java.util.Scanner;

public class SistemaVendas{
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Clientes clientes = new Clientes();
        Pedidos pedidos = new Pedidos();
        Produto produto = new Produto();

        String mensagemClientes = """
                *********************
                Informe, respectivamente: código, nome, endereço e telefone.
                *********************
                """;

        System.out.println(mensagemClientes);
        clientes.setCodCli(entrada.nextInt());
        entrada.nextLine();
        clientes.setNomeCli(entrada.nextLine());
        clientes.setEnderecoCli(entrada.nextLine());
        clientes.setNumeroCli(entrada.nextLine());
        clientes.imprimeClientes();

        String mensagemPedidos = """
                *********************
                Informe, respectivamente: número, data e valor do pedido.
                *********************
                """;

        System.out.println(mensagemPedidos);
        pedidos.setNumeroPed(entrada.nextInt());
        entrada.nextLine();
        pedidos.setDataPed(entrada.nextLine());
        pedidos.setValorPed(entrada.nextDouble());
        pedidos.imprimePed();

        String mensagemProdutos = """
                *********************
                Informe, respectivamente: código, descrição e preço do produto.
                *********************
                """;

        System.out.println(mensagemProdutos);
        produto.setCodProd(entrada.nextInt());
        produto.setDescricaoProd(entrada.nextLine());
        produto.setPrecoProd(entrada.nextDouble());
        produto.imprimirProduto();

        String menu = """
                1- Exibir os dados do cliente
                2- Exibir os dados do pedido
                3- Exibir os dados do produto
                4- Exibir todas as opções
                5- Sair do programa
                """;
    }
}
