package com.mycompany.locadoradeveiculos;
public class Carro extends Veiculo{
    private int numerodeportas;
    private String tipodecombustivel;
    private int capacidadedoportamalas;

    public Carro(String modelo, String marca, String placa, String cor, int anodefabricao, int numerodeportas,
            String tipodecombustivel, int capacidadedoportamalas) {
        super(modelo, marca, placa, cor, anodefabricao);
        this.numerodeportas = numerodeportas;
        this.tipodecombustivel = tipodecombustivel;
        this.capacidadedoportamalas = capacidadedoportamalas;
    }

    public int getNumerodeportas() {
        return numerodeportas;
    }

    public void setNumerodeportas(int numerodeportas) {
        this.numerodeportas = numerodeportas;
    }

    public String getTipodecombustivel() {
        return tipodecombustivel;
    }

    public void setTipodecombustivel(String tipodecombustivel) {
        this.tipodecombustivel = tipodecombustivel;
    }

    public int getCapacidadedoportamalas() {
        return capacidadedoportamalas;
    }

    public void setCapacidadedoportamalas(int capacidadedoportamalas) {
        this.capacidadedoportamalas = capacidadedoportamalas;
    }

    @Override
    public String toString() {
        return super.toString() + "Carro{" + "numerodeportas=" + numerodeportas + ", tipodecombustivel=" + tipodecombustivel + ", capacidadedoportamalas=" + capacidadedoportamalas + '}';
    }
    
}
