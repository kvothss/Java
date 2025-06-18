public class Produto {
    private int codProd;
    private String descricaoProd;
    private double precoProd;

    public Produto(){
        /* Construtor sem param */
        codProd = 0;
        descricaoProd = "AAA";
        precoProd = 0.0;
    }

    public Produto(int cod, String desc, double pr){
        /* Construtor com param */
        codProd = cod;
        descricaoProd = desc;
        precoProd = pr;
    }

    public Produto(Produto p){
        /* Construtor cópia */
        codProd = p.codProd;
        descricaoProd = p.descricaoProd;
        precoProd = p.precoProd;
    }

    public int getCodProd() {
        return codProd;
    }

    public void setCodProd(int codProd) {
        this.codProd = codProd;
    }

    public String getDescricaoProd() {
        return descricaoProd;
    }

    public void setDescricaoProd(String descricaoProd) {
        this.descricaoProd = descricaoProd;
    }

    public double getPrecoProd() {
        return precoProd;
    }

    public void setPrecoProd(double precoProd) {
        this.precoProd = precoProd;
    }

    public void imprimirProduto(){
        System.out.println("Código do produto: " + this.codProd);
        System.out.println("Descrição do produto:" + this.descricaoProd);
        System.out.println("Preço do produto" + this.precoProd);
    }

}
