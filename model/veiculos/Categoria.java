package model.veiculos;
public class Categoria {
    private String categoria;
    private String descricao;
    private double valorDiaria;
    private String para;

    public Categoria(String categoria, String descricao, double valorDiaria, String para) {
        this.categoria = categoria;
        this.descricao = descricao;
        this.valorDiaria = valorDiaria;
        this.para = para;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    @Override
    public String toString() {
        return categoria;
    }
}
