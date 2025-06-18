public class Clientes {
    private int codCli;
    private String nomeCli;
    private String enderecoCli;
    private String numeroCli;

    public int getCodCli(){
        return codCli;
    }

    public String getNomeCli(){
        return nomeCli;
    }
    public String getEnderecoCli(){
        return enderecoCli;
    }
    public String getNumeroCli(){
        return numeroCli;
    }

    public void setCodCli(int codCli){
        this.codCli = codCli;
    }

    public void setNomeCli(String nomeCli){
        this.nomeCli = nomeCli;
    }

    public void setEnderecoCli(String enderecoCli){
        this.enderecoCli = enderecoCli;
    }

    public void setNumeroCli(String numeroCli){
        this.numeroCli = numeroCli;
    }

    public void imprimeClientes(){
        System.out.println("O código do cliente é: " + this.getCodCli());
        System.out.println("O nome do cliente é: " + this.getNomeCli());
        System.out.println("O endereço do cliente é: " + this.getEnderecoCli());
        System.out.println("O número do cliente é: " + this.getNumeroCli());
    }

}
