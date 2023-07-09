package model.veiculos;
import java.io.Serializable;
import model.cliente.Cliente;

public abstract class Veiculo implements Serializable{
    protected String modelo;
    protected String marca;
    protected String placa;
    protected String cor;
    protected int anodefabricao;
    protected double quilometragem;
    protected Categoria categoria;
    protected int alugado;


    public Veiculo(String modelo, String marca, String placa, String cor, int anodefabricao, double quilometragem,
            Categoria categoria) {
        this.modelo = modelo;
        this.marca = marca;
        this.placa = placa;
        this.cor = cor;
        this.anodefabricao = anodefabricao;
        this.quilometragem = quilometragem;
        this.categoria = categoria;
        this.alugado = 0;
    }

    public double getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(double quilometragem) {
        this.quilometragem = quilometragem;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
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

    public boolean isAlugado() {
        return alugado != 0;
    }    

    public void setAlugado(int alugado) {
        this.alugado = alugado;
    }
    
    public void alugar() {
    this.alugado = 1;
    }
    
    public void devolver() {
    this.alugado = 0;
    }

    @Override
    public String toString() {
        return "Veiculo{" + "modelo=" + modelo + ", marca=" + marca + ", placa=" + placa + ", cor=" + cor + ", anodefabricao=" 
                + anodefabricao + ", Quilometragem=" + quilometragem + ", Categoria=" + categoria + ", Alugado=" + alugado +'}';
    }
    
    
    
}
