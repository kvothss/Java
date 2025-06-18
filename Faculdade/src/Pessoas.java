public class Pessoas {
    private int id;
    private String nome;
    private String endereco;

    public int getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public String getEndereco(){
        return endereco;
    }

    public void setId(int id){
    this.id = id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public void imprimirPessoas(){
        System.out.println("Id: " + this.getId());
        System.out.println("Nome: " + this.getNome());
        System.out.println("endereco: " + this.getEndereco());
    }
}
