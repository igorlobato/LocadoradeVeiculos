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
    private int diascomVeiculo;
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

    public int getDiascomVeiculo() {
        return diascomVeiculo;
    }

    public void setDiascomVeiculo(int diascomVeiculo) {
        this.diascomVeiculo = diascomVeiculo;
    }
    
    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }
    
    
    
    
    //public void alugarCarro(carro.getModelo(), carro.getCor(), carro.getAnodefabricacao()){
        
    //}
    

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
    String categoriaCarro = carro.getCategoria().toLowerCase();
    String categoriaMoto = moto.getCategoria().toLowerCase();

    if (categoriaCarro.equals("a")) {
        this.valorDiaria = 150;
    } else if (categoriaCarro.equals("b")) {
        this.valorDiaria = 195;
    } else if (categoriaCarro.equals("c")) {
        this.valorDiaria = 240;
    } else if (categoriaCarro.equals("d")) {
        this.valorDiaria = 290;
    } else if (categoriaCarro.equals("e")) {
        this.valorDiaria = 530;
    } else if (categoriaCarro.equals("suv")) {
        this.valorDiaria = 340;
    } else if (categoriaCarro.equals("mpv")) {
        this.valorDiaria = 390;
    }else{
        this.valorDiaria = 100;
    }

    if (categoriaMoto.equals("scooters")) {
        this.valorDiaria = 140;
    } else if (categoriaMoto.equals("naked")) {
        this.valorDiaria = 190;
    } else if (categoriaMoto.equals("esportivas")) {
        this.valorDiaria = 290;
    } else if (categoriaMoto.equals("touring")) {
        this.valorDiaria = 390;
    } else if (categoriaMoto.equals("adventure")) {
        this.valorDiaria = 440;
    } else if (categoriaMoto.equals("cruisers")) {
        this.valorDiaria = 340;
    } else if (categoriaMoto.equals("off-road")) {
        this.valorDiaria = 290;
    }else{
        this.valorDiaria = 100;
    }
    }
    
    public void MultaAtraso(){
        
        double diasAtrasados = (this.diascomVeiculo - this.quantidadeDiarias);
        double multa;
        
        if (diasAtrasados > 0){
            multa = (diasAtrasados * 250) + (diasAtrasados * this.valorDiaria);
            System.out.println("Você atrasou a devolução! Você deve pagar " + multa + "R$ de multa");
        }
    }
}
