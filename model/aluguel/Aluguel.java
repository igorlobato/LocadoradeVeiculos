package model.aluguel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import model.veiculos.Veiculo;
import java.util.List;
import model.cliente.Cliente;
import model.veiculos.Categoria;

public class Aluguel implements Serializable{
    private Veiculo veiculo;
    private String dataSaida;
    private String dataDevolucao;
    private int quantidadeDiarias;
    private Cliente cliente;
    private boolean ativo;
    private double valorPagar;
    private double multa;
    private int diasAtrasados;
    private double quilometrosRodados;

    public Aluguel(Veiculo veiculo, String dataSaida, String dataDevolucao, Cliente cliente) {
        this.veiculo = veiculo;
        this.dataSaida = dataSaida;
        this.dataDevolucao = dataDevolucao;
        this.cliente = cliente;
        this.ativo = false;
        this.valorPagar = 0;
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

    public Cliente getCliente() {
        return cliente;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public int getDiasAtrasados() {
        return diasAtrasados;
    }

    public void setDiasAtrasados(int diasAtrasados) {
        this.diasAtrasados = diasAtrasados;
    }

    public double getQuilometrosRodados() {
        return quilometrosRodados;
    }

    public void setQuilometrosRodados(double quilometrosRodados) {
        this.quilometrosRodados = quilometrosRodados;
    }
    
    public void ativo() {
    this.ativo = true;
    }
    
    public void desativo() {
    this.ativo = false;
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
    
    public void calcularQuantidadeDiarias() {
    LocalDate dataSaida = LocalDate.parse(this.dataSaida, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    LocalDate dataDevolucao = LocalDate.parse(this.dataDevolucao, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    long diferenca = ChronoUnit.DAYS.between(dataSaida, dataDevolucao);
    this.quantidadeDiarias = Math.toIntExact(diferenca);    
    }
    }

    