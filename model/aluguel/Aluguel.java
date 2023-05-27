package model.aluguel;
public class Aluguel {
    private String dataSaida;
    private String dataDevolucao;
    private int quantidadeDiarias;
    private double valorDiaria;

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public int getQuantidadeDiarias() {
        return quantidadeDiarias;
    }

    public void setQuantidadeDiarias(int quantidadeDiarias) {
        this.quantidadeDiarias = quantidadeDiarias;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public void verificarDisponibilidade(String modelo){
        
    }
    
    public void ControlheAluguel(String modelo){
        
    }
    
    public void valorPagar(){
        
    }
    
    public void MultaAtraso(){
        
    }
}
