package model.aluguel;

import java.io.Serializable;
import model.veiculos.Veiculo;
import java.util.List;
import model.cliente.Cliente;
import model.veiculos.Categoria;

public class Aluguel implements Serializable{
    private Veiculo veiculo;
    private String dataSaida;
    private String dataDevolucao;
    private int quantidadeDiarias;
    private int diascomVeiculo;
    private Categoria categoria;
    private Cliente cliente;

    public Aluguel(Veiculo veiculo, String dataSaida, String dataDevolucao, int quantidadeDiarias, Categoria categoria, Cliente cliente) {
        this.veiculo = veiculo;
        this.dataSaida = dataSaida;
        this.dataDevolucao = dataDevolucao;
        this.quantidadeDiarias = quantidadeDiarias;
        this.categoria = categoria;
        this.cliente = cliente;
    }
    
    private List<Veiculo> listaVeiculos; 

    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }
    
    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
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
    
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        //mostrar os veiculos alugarodos e o status do aluguel data etc.
    }
    
    public void valorPagar(){
    
    }
    
    public void MultaAtraso(){
        
       /* double diasAtrasados = (this.diascomVeiculo - this.quantidadeDiarias);
        double multa;
        
        if (diasAtrasados > 0){
            multa = (diasAtrasados * 250) + (diasAtrasados * this.valorDiaria.getValorDiatia);
            System.out.println("Você atrasou a devolução! Você deve pagar " + multa + "R$ de multa");*/
        }
    }

