package model.veiculos;
import model.cliente.Cliente;

public abstract class Veiculo {
    protected String modelo;
    protected String marca;
    protected String placa;
    protected String cor;
    protected int anodefabricao;
    protected double quilometragem;
    protected String categoria;
    protected Cliente cliente;

    public Veiculo(String modelo, String marca, String placa, String cor, int anodefabricao, double quilometragem,
            String categoria, Cliente cliente) {
        this.modelo = modelo;
        this.marca = marca;
        this.placa = placa;
        this.cor = cor;
        this.anodefabricao = anodefabricao;
        this.quilometragem = quilometragem;
        this.categoria = categoria;
        this.cliente = cliente;
    }

    public double getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(double quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Veiculo{" + "modelo=" + modelo + ", marca=" + marca + ", placa=" + placa + ", cor=" + cor + ", anodefabricao=" 
                + anodefabricao + ", Quilometragem=" + quilometragem + ", Categoria=" + categoria +
                ", Cliente=" + cliente +'}';
    }
    
    
    
}
