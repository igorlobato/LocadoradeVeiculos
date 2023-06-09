package model.aluguel;

import model.veiculos.Carro;
import model.veiculos.Moto;
import model.veiculos.Veiculo;
import Facade.LocadoraFacade;
import java.util.List;

public class Aluguel {
    private Carro carro;
    private Moto moto;
    private String dataSaida;
    private String dataDevolucao;
    private int quantidadeDiarias;
    private double valorDiaria;
    
    private List<Veiculo> listaVeiculos; 

    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }
    
    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Moto getMoto() {
        return moto;
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
    }
    
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
         boolean disponivel = true;
    
    for (Veiculo veiculo : listaVeiculos) {
        if (veiculo.getModelo().equalsIgnoreCase(modelo)) {
            disponivel = false;
            break;
        }
    }
    
    if (disponivel) {
        System.out.println("O veículo está disponível para aluguel.");
    } else {
        System.out.println("O veículo não está disponível para aluguel.");
    }
    }
    
    public void ControlheAluguel(String modelo){
        
    }
    
    public void valorPagar(){
        
    }
    
    public void MultaAtraso(){
        
    }
}
