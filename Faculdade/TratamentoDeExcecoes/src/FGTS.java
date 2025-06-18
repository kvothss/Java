public class FGTS {
    private String nome;
    private double salario;

    public FGTS(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double calculaFGTS() {
        return this.salario * 0.08;
    }

    public void exibeInfo(){
        System.out.println("Nome: " + getNome());
        System.out.printf("Salário: R$ %.2f%n", getSalario());
        System.out.printf("FGTS: R$ %.2f%n", calculaFGTS());
    }
}
