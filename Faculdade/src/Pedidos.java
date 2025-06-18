public class Pedidos{
    private int numeroPed;
    private String dataPed;
    private double valorPed;

    public int getNumeroPed(){
        return numeroPed;
    }

    public String getDataPed(){
        return dataPed;
    }

    public double getValorPed(){
        return valorPed;
    }

    public void setNumeroPed(int numeroPed){
        this.numeroPed = numeroPed;
    }

    public void setDataPed(String dataPed){
        this.dataPed = dataPed;
    }

    public void setValorPed(double valorPed){
        this.valorPed = valorPed;
    }

    public void imprimePed(){
        System.out.println("O número do pedido é: " + this.numeroPed);
        System.out.println("A data do pedido é: " + this.dataPed);
        System.out.println("O valor do pedido é: " + this.valorPed);
    }

}