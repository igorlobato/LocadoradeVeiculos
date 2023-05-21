package com.mycompany.locadoradeveiculos;
public abstract class Veiculo {
    protected String modelo;
    protected String marca;
    protected String placa;
    protected String cor;
    protected int anodefabricao;

    public Veiculo(String modelo, String marca, String placa, String cor, int anodefabricao) {
        this.modelo = modelo;
        this.marca = marca;
        this.placa = placa;
        this.cor = cor;
        this.anodefabricao = anodefabricao;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getAnodefabricao() {
        return anodefabricao;
    }

    public void setAnodefabricao(int anodefabricao) {
        this.anodefabricao = anodefabricao;
    }

    @Override
    public String toString() {
        return "Veiculo{" + "modelo=" + modelo + ", marca=" + marca + ", placa=" + placa + ", cor=" + cor + ", anodefabricao=" + anodefabricao + '}';
    }
    
    
    
}
